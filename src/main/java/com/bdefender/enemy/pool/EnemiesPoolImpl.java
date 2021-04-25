package com.bdefender.enemy.pool;

import com.bdefender.Pair;
import com.bdefender.enemy.Enemy;
import com.bdefender.enemy.view.EnemyGraphicMover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EnemiesPoolImpl implements EnemiesPoolInteractor, EnemiesPoolMover, EnemiesPoolSpawner {

    private final Map<Integer, Enemy> enemies = new HashMap<>();
    private int counter = 0;
    private final MapInteractor mapInteractor;
    static class EnemyReachedEndException extends Exception {
        private static final long serialVersionUID = 1L;
    }

    public EnemiesPoolImpl(final MapInteractor mapInteractor) {
        this.mapInteractor = mapInteractor;
    }

    @Override
    public final Map<Integer, Enemy> getEnemies(final boolean alive) {
        return alive ? this.getAliveEnemies() : this.enemies;
    }

    public final void clearPool() {
        this.enemies.clear();
    }

    @Override
    public final Map<Integer, Enemy> getAliveEnemies() {
        synchronized (this.enemies) {
            return this.enemies.entrySet().stream().filter(e -> e.getValue().isAlive() && !e.getValue().isArrived())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
    }

    @Override
    public final void addEnemy(final Enemy enemy) {
        synchronized (this.enemies) {
            enemy.moveTo(mapInteractor.getSpawnPoint());
            enemy.setDirection(this.mapInteractor.getStartingDirection());
            enemies.put(counter++, enemy);
        }
    }

    @Override
    public final void applyDamageById(final int id, final Double damage) {
        synchronized (this.enemies) {
            this.enemies.get(id).takeDamage(damage);
        }
    }

    private Pair<Double, Double> getNextPos(final Pair<Integer, Integer> dir, final Pair<Double, Double> currPos,
            final Pair<Double, Double> distance) {
        final double newX = currPos.getX() + (distance.getX() * dir.getX());
        final double newY = currPos.getY() + (distance.getY() * dir.getY());
        return new Pair<>(newX, newY);
    }

    private boolean isAfterKeyPoint(final Pair<Double, Double> pos, final Pair<Double, Double> keyPoint,
            final Pair<Integer, Integer> dir) {
        return ((pos.getX() - keyPoint.getX()) > 0 && pos.getY().equals(keyPoint.getY())) || (pos.getX().equals(keyPoint.getX()) && (pos.getY() - keyPoint.getY()) * dir.getY() > 0);

    }

    private boolean keyPointIsAfter(final Pair<Double, Double> p1, final Pair<Double, Double> p2,
            final Pair<Integer, Integer> dir) {
        return (p1.getX() >= p2.getX()) && (p1.getY() - p2.getY()) * dir.getY() >= 0;
    }

    private Pair<Double, Double> getNextValidPos(final Pair<Double, Double> nextPosSimple, final Enemy enemy)
            throws EnemyReachedEndException {
        final ArrayList<Pair<Double, Double>> keyPoints = new ArrayList<>(this.mapInteractor.getKeyPoints());
        final Pair<Integer, Integer> dir = enemy.getDirection();
        final Pair<Double, Double> currPos = enemy.getPosition();
        Pair<Double, Double> nxtPos = nextPosSimple;
        for (final Pair<Double, Double> keyPoint : keyPoints) {
            if (keyPointIsAfter(keyPoint, currPos, dir) && isAfterKeyPoint(nxtPos, keyPoint, dir)) {
                final int nextXDir = dir.getX() == 0 ? 1 : 0;
                int nextYDir = 0;
                if (keyPoints.indexOf(keyPoint) + 1 == keyPoints.size()) {
                    throw new EnemyReachedEndException();
                } else {
                    final Double nextKeyPointY = keyPoints.get(keyPoints.indexOf(keyPoint) + 1).getY();
                    if (!nextKeyPointY.equals(keyPoint.getY())) {
                        nextYDir = nextKeyPointY > keyPoint.getY() ? 1 : -1;
                    }
                    enemy.setDirection(new Pair<>(nextXDir, nextYDir));
                    nxtPos = getNextPos(enemy.getDirection(), keyPoint, new Pair<>(
                            Math.abs(nxtPos.getY() - keyPoint.getY()), Math.abs(nxtPos.getX() - keyPoint.getX())));
                }
                break;
            }
        }
        return nxtPos;
    }

    @Override
    public final void moveEnemies(final long speedDiv) {
        synchronized (this.enemies) {
            for (final Enemy enemy : this.enemies.values()) {
                if (enemy.isAlive() && !enemy.isArrived()) {
                    try {
                        enemy.moveTo(getNextValidPos(getNextPos(enemy.getDirection(), enemy.getPosition(),
                                new Pair<>(enemy.getSpeed() / speedDiv, enemy.getSpeed() / speedDiv)), enemy));
                    } catch (EnemyReachedEndException ex) {
                        enemy.setArrived(true);
                        enemy.doDamage();
                    }
                }
            }
        }
    }

}
