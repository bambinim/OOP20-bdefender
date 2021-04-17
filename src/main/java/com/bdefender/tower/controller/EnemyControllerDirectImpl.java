package com.bdefender.tower.controller;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;

public class EnemyControllerDirectImpl implements EnemyControllerDirect {
	
	private final EnemiesPoolInteractor enemiesPool ;
	
	public EnemyControllerDirectImpl(EnemiesPoolInteractor enemiesPool) {
		this.enemiesPool = enemiesPool;
	}

	@Override
	public Map<Integer, Pair<Double, Double>> getEnemiesInZone(double radius, Pair<Double, Double> center) {
		return this.enemiesPool.getEnemies(true).entrySet().stream().filter(e -> Math.hypot(center.getY() - e.getValue().getPosition().getY(), center.getX() - e.getValue().getPosition().getX()) <= radius)
				.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getPosition()));
	}

	@Override
	public Pair<Double, Double> getEnemyPosByID(Integer id) {
		return enemiesPool.getEnemies(false).get(id).getPosition();
	}

	@Override
	public void applyDamageById(Integer id, Double damage) {
		this.enemiesPool.applyDamageById(id, damage);
	}

}
