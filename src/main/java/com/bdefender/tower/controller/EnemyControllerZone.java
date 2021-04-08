package com.bdefender.tower.controller;


import com.bdefender.Pair;

import java.util.Set;

public interface EnemyControllerZone extends EnemyController {
	
	public Set<Integer> applyDamageByZone(double radius, Pair<Double, Double> center, Double damage);
	
}
