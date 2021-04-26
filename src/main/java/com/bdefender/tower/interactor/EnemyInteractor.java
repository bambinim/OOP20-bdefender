package com.bdefender.tower.interactor;

import com.bdefender.Pair;

import java.util.Map;

public interface EnemyInteractor {

    /**
     * @param radius  zone radius
     * @param center zone circle center
     * @return all enemies in the given radius.
     */
    Map<Integer, Pair<Double, Double>> getEnemiesInZone(double radius, Pair<Double, Double> center);

    /**
     * @param id enemy id
     * @return the position of the specific enemy Pair<Double, Double>
     */
    Pair<Double, Double> getEnemyPosByID(Integer id);
}
