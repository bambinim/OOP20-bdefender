package com.bdefender.enemies.pool;

import com.bdefender.enemies.Enemy;


public interface EnemiesPoolSpawner {
	/**
	 * add an enemy to the pool.
	 * @param enemy enemy to add.
	 */
	void addEnemy(Enemy enemy);

}


