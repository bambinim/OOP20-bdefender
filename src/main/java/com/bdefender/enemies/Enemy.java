package com.bdefender.enemies;

import com.bdefender.Pair;

public interface Enemy {

	/**
	 *
	 * @return enemy position.
	 */
	Pair<Double, Double> getPosition();

	/**
	 * applies damage to the enemy.
	 * @param damage amount of damage.
	 */
	void takeDamage(Double damage);

	/**
	 *
	 * @return true if enemy is still alive.
	 */
	boolean isAlive();

	/**
	 *
	 * @return true if enemy has reached the end.
	 */
	boolean isArrived();

	/**
	 * set the isArrived param
	 * @param arrived true if enemy is arrived
	 */
	void setArrived(boolean arrived);

	/**
	 * move enemy to a certain position
	 * @param pos where to move the enemy
	 */
	void moveTo(Pair<Double, Double> pos);

	/**
	 *
	 * @return speed value. Greater is faster
	 */
	double getSpeed();

	/**
	 *
	 * @return actual enemy direction
	 */
	Pair<Integer, Integer> getDirection();

	/**
	 * modifies enemy direction
	 * @param dir new direction
	 */
	void setDirection(Pair<Integer, Integer> dir);

	/**
	 * applies enemy damage to player
	 */
	void doDamage();

	/**
	 *
	 * @return enemy type ID
	 */
	Integer getTypeId();
	
}
