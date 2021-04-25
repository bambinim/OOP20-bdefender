package com.bdefender.tower.interactor;

import com.bdefender.Pair;
import com.bdefender.enemy.pool.EnemiesPoolInteractor;

import java.util.Map;
import java.util.stream.Collectors;

public class EnemyInteractorDirectImpl implements EnemyInteractorDirect {

    private final EnemiesPoolInteractor enemiesPool;

    public EnemyInteractorDirectImpl(final EnemiesPoolInteractor enemiesPool) {
        this.enemiesPool = enemiesPool;
    }

    @Override
    public Map<Integer, Pair<Double, Double>> getEnemiesInZone(final double radius, final Pair<Double, Double> center) {
        return this.enemiesPool.getEnemies(true).entrySet().stream()
                .filter(e -> Math.hypot(center.getY() - e.getValue().getPosition().getY(),
                        center.getX() - e.getValue().getPosition().getX()) <= radius)
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getPosition()));
    }

    @Override
    public Pair<Double, Double> getEnemyPosByID(final Integer id) {
        return enemiesPool.getEnemies(false).get(id).getPosition();
    }

    @Override
    public void applyDamageById(final Integer id, final Double damage) {
        this.enemiesPool.applyDamageById(id, damage);
    }

}
