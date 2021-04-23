package com.bdefender.tower;

import com.bdefender.Pair;
import com.bdefender.enemy.pool.EnemiesPoolInteractor;
import com.bdefender.tower.controller.EnemyControllerDirect;
import com.bdefender.tower.controller.EnemyControllerDirectImpl;

import java.util.Map;

public class TowerFactory {

    private static final double NEXT_LEVEL_MULT = 0.25;

    /**
     * Generate a direct shot tower.
     *
     * @param pool
     * @param pos
     * @return Tower
     */
    public Tower getTowerDirect1(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
        final Double damage = 13.0;
        final Double rangeRadius = 10.0;
        final Long shootSpeed = 8L;
        final int id = 0;
        return this.towerDirectByParams(damage, rangeRadius, shootSpeed, pool, pos, id);
    }

    /**
     * Generate a direct shot tower.
     *
     * @param pool
     * @param pos
     * @return Tower
     */
    public Tower getTowerDirect2(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
        final Double damage = 20.0;
        final Double rangeRadius = 10.0;
        final Long shootSpeed = 5L;
        final int id = 1;
        return this.towerDirectByParams(damage, rangeRadius, shootSpeed, pool, pos, id);
    }

    public Tower getTowerDirect3(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
        final Double damage = 20.0;
        final Double rangeRadius = 9.0;
        final Long shootSpeed = 10L;
        final int id = 2;
        return this.towerDirectByParams(damage, rangeRadius, shootSpeed, pool, pos, id);
    }

    private Tower towerDirectByParams(final Double damage, final Double rangeRadius, final Long shootSpeed,
            final EnemiesPoolInteractor pool, final Pair<Double, Double> pos, final int id) {

        return new Tower() {

            private final EnemyControllerDirect enemiesCtrl = new EnemyControllerDirectImpl(pool);
            private int level = 1;

            @Override
            public Pair<Double, Double> shoot() {
                try {
                    int targetId = this.getOptimalTarget();
                    this.enemiesCtrl.applyDamageById(targetId, damage + ((level - 1) * NEXT_LEVEL_MULT));
                    return this.enemiesCtrl.getEnemyPosByID(targetId);
                } catch (NoEnemiesAroundException ex) {
                    return null;
                }
            }

            @Override
            public int upgradeLevel() {
                return ++level;
            }

            public Integer getOptimalTarget() throws NoEnemiesAroundException {
                Map<Integer, Pair<Double, Double>> enemiesInRange = this.enemiesCtrl
                        .getEnemiesInZone(rangeRadius + level - 1, pos);

                if (enemiesInRange.isEmpty()) {
                    throw new NoEnemiesAroundException("No enemies around this tower");
                }

                Pair<Integer, Double> closerEnemy = new Pair<>(0, rangeRadius + 1);

                for (var enemy : enemiesInRange.entrySet()) {
                    double distance = Math.sqrt(Math.pow(enemy.getValue().getX() - pos.getX(), 2)
                            + Math.pow(enemy.getValue().getY() - pos.getY(), 2));
                    if (distance <= closerEnemy.getY()) {
                        closerEnemy = new Pair<>(enemy.getKey(), distance);
                    }
                }

                return closerEnemy.getX();
            }

            @Override
            public long getShootSpeed() {
                return shootSpeed;
            }

            @Override
            public int getTowerTypeId() {
                return id;
            }

            @Override
            public Pair<Double, Double> getPosition() {
                return pos;
            }
        };
    }
}
