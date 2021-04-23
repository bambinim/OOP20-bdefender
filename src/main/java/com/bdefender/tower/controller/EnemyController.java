package com.bdefender.tower.controller;

import com.bdefender.Pair;

import java.util.Map;

public interface EnemyController {

    /**
     * @return all enemies in the given radius.
     */
    Map<Integer, Pair<Double, Double>> getEnemiesInZone(double radius, Pair<Double, Double> center);

    /**
     * @return the position of the specific enemy Pair<Double, Double>
     */
    Pair<Double, Double> getEnemyPosByID(Integer id);
}
