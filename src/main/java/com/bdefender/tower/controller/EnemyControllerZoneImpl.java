package com.bdefender.tower.controller;
import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class EnemyControllerZoneImpl implements EnemyControllerZone {

    private final EnemiesPoolInteractor enemiesPool;

    public EnemyControllerZoneImpl(final EnemiesPoolInteractor enemiesPool) {
        this.enemiesPool = enemiesPool;
    }

    @Override
    public Map<Integer, Pair<Double, Double>> getEnemiesInZone(final double radius, final Pair<Double, Double> center) {
        return IntStream.range(0, this.enemiesPool.getEnemies().size()).boxed()
                .collect(Collectors.toMap(i -> i, i -> this.enemiesPool.getEnemies().get(i))).entrySet().stream()
                .filter(e -> Math.hypot(e.getValue().getPosition().getY() - center.getY(), e.getValue().getPosition().getX() - center.getX()) <= radius)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().getPosition()));
    }

    @Override
    public Pair<Double, Double> getEnemyPosByID(final Integer id) {
        return enemiesPool.getEnemies().get(id).getPosition();
    }

    @Override
    public Set<Integer> applyDamageByZone(final double radius, final Pair<Double, Double> center, final Double damage) {
        final Set<Integer> enemiesId = getEnemiesInZone(radius, center).entrySet().stream()
                .map(e -> e.getKey())
                .collect(Collectors.toSet());
        for (final Integer enemyId : enemiesId) {
            enemiesPool.applyDamageById(enemyId, damage);
        }
        return enemiesId;
    }

}
