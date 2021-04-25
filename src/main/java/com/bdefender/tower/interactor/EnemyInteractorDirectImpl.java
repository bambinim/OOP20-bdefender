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

    /**
     * @param radius size of area radius
     * @param center coordinates of area center 
     * @return  map of enemies in a specific circular area
     */
    @Override
    public Map<Integer, Pair<Double, Double>> getEnemiesInZone(final double radius, final Pair<Double, Double> center) {
        return this.enemiesPool.getEnemies(true).entrySet().stream()
                .filter(e -> Math.hypot(center.getY() - e.getValue().getPosition().getY(),
                        center.getX() - e.getValue().getPosition().getX()) <= radius)
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getPosition()));
    }

    /**
     * @param id enemy id
     * @return position of the enemy that match given id
     */
    @Override
    public Pair<Double, Double> getEnemyPosByID(final Integer id) {
        return enemiesPool.getEnemies(false).get(id).getPosition();
    }

    /**
     * applies damage to the enemy that match given id.
     * @param id enemy id
     * @param damage damage amount
     */
    @Override
    public void applyDamageById(final Integer id, final Double damage) {
        this.enemiesPool.applyDamageById(id, damage);
    }

}
