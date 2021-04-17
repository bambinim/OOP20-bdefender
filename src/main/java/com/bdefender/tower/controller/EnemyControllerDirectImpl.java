package com.bdefender.tower.controller;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;

public class EnemyControllerDirectImpl implements EnemyControllerDirect {


    private final EnemiesPoolInteractor enemiesPool;

    public EnemyControllerDirectImpl(final EnemiesPoolInteractor enemiesPool) {
        this.enemiesPool = enemiesPool;
    }

    /**
     * @return all enemies in the given radius.
     */
    @Override
    public Map<Integer, Pair<Double, Double>> getEnemiesInZone(final double radius, final Pair<Double, Double> center) {
        return this.enemiesPool.getEnemies(true).entrySet().stream().filter(e -> Math.hypot(center.getY() - e.getValue().getPosition().getY(), center.getX() - e.getValue().getPosition().getX()) <= radius)
				.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getPosition()));
    }

    /**
     * @return the position of the specificated enemy Pair<Double, Double> 
     */
    @Override
    public Pair<Double, Double> getEnemyPosByID(final Integer id) {
        return enemiesPool.getEnemies(false).get(id).getPosition();
    }

    /**
     * Takes life from the enemy specified by id.
     * @param id
     * @param damage
     */
    @Override
    public void applyDamageById(final Integer id, final Double damage) {
        this.enemiesPool.applyDamageById(id, damage);
    }


}
