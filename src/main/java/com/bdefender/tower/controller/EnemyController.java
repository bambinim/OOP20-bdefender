package com.bdefender.tower.controller;


import com.bdefender.Pair;

import java.util.Map;


public interface EnemyController {

    Map<Integer, Pair<Double, Double>> getEnemiesInZone(double radius, Pair<Double, Double> center);

    Pair<Double, Double> getEnemyPosByID(Integer id);
}
