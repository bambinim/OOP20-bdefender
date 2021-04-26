package com.bdefender.tower.interactor;

import com.bdefender.Pair;
import com.bdefender.tower.Tower;

import java.util.Map;

public class ChooseCloserEnemy implements ChooseTargetMethod {
    /**
     * @param interactor enemies interactor
     * @param rangeRadius radius of tower's range circle
     * @param pos tower position
     */
    @Override
    public Integer getTargetId(final EnemyInteractor interactor, final Double rangeRadius, final Pair<Double, Double> pos) throws Tower.NoEnemiesAroundException {
        Map<Integer, Pair<Double, Double>> enemiesInRange = interactor.getEnemiesInZone(rangeRadius, pos);

        if (enemiesInRange.isEmpty()) {
            throw new Tower.NoEnemiesAroundException("No enemies around this tower");
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
}
