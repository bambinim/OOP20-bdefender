package com.bdefender.enemy.pool;

import java.util.Map;

import com.bdefender.enemy.Enemy;

public interface EnemiesPoolMover {

    /**
     * moves all enemies.
     */
    void moveEnemies();

    /**
     * 
     * @return alive enemies.
     */
    Map<Integer, Enemy> getAliveEnemies();
}
