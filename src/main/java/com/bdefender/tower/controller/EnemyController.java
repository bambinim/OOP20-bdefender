package com.bdefender.tower.controller;


import com.bdefender.Pair;

import java.util.Map;


public interface EnemyController {

		public Map<Integer, Pair<Double, Double>> getEnemiesInZone(double radius, Pair<Double, Double> center);
		
	
}
