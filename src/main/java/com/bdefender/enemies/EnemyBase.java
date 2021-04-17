package com.bdefender.enemies;

import com.bdefender.Pair;

public interface EnemyBase {

	@FunctionalInterface
	interface EnemyStateChanged {
		void StateChanged();
	}

	Pair<Double, Double> getPosition();
	
	void takeDamage(Double damage);
	
	boolean isAlive();
	
	void moveTo(Pair<Double, Double> pos);
	
	double getSpeed();
	
	Pair<Integer, Integer> getDirection();
	
	void setDirection(Pair<Integer, Integer> dir);
	
	double getLife();

	void setOnDeadAction(EnemyStateChanged onDead);

	Integer getTypeId();
	
}
