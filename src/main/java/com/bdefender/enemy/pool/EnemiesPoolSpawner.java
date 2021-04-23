package com.bdefender.enemy.pool;

import com.bdefender.enemy.Enemy;

public interface EnemiesPoolSpawner {
    /**
     * add an enemy to the pool.
     *
     * @param enemy enemy to add.
     */
    void addEnemy(Enemy enemy);

}
