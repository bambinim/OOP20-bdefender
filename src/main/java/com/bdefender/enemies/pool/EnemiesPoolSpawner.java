package com.bdefender.enemies.pool;


import com.bdefender.Pair;
import com.bdefender.enemies.Enemy;
import com.bdefender.map.Coordinates;

public interface EnemiesPoolSpawner {
	void addEnemy(Enemy enemy);
	Coordinates getSpawnPoint();
	Pair<Integer, Integer> getSpawnDir();
}


