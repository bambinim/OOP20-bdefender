package com.bdefender.enemy.pool;

import java.util.Map;

import com.bdefender.enemy.Enemy;

public interface EnemiesPoolMover {

    /**
     * moves all enemies.
     * 
     * @param speedDiv enemy speed divisor
     */
    void moveEnemies(long speedDiv);

    /**
     * 
     * @return alive enemies.
     */
    Map<Integer, Enemy> getAliveEnemies();
}
