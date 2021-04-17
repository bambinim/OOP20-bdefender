package com.bdefender.enemies;

import com.bdefender.Pair;

public interface Enemy {

	@FunctionalInterface
	interface EnemyStateChanged {
		void StateChanged();
	}

	Pair<Double, Double> getPosition();
	
	void takeDamage(Double damage);
	
	boolean isAlive();

	boolean isArrived();

	void setArrived(boolean arrived);
	
	void moveTo(Pair<Double, Double> pos);
	
	double getSpeed();
	
	Pair<Integer, Integer> getDirection();
	
	void setDirection(Pair<Integer, Integer> dir);
	
	double getLife();

	void doDamage();

	Integer getTypeId();
	
}
