package com.bdefender.enemies.pool;

import com.bdefender.enemies.Enemy;

import java.util.ArrayList;
import java.util.Map;

public interface EnemiesPoolInteractor {
	/**
	 *
	 * @param alive true if you want only alive enemies, false in you want all of them
	 * @return the map of enemies
	 */
	Map<Integer,Enemy> getEnemies(boolean alive);

	void applyDamageById(int id, Double damage);
}
