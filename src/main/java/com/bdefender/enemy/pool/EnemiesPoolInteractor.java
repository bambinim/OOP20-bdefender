package com.bdefender.enemy.pool;

import com.bdefender.enemy.Enemy;

import java.util.Map;

public interface EnemiesPoolInteractor {
    /**
     * @param onlyAliveEnemies true if you want only alive enemies, false in you
     *                         want all of them.
     * @return the map of enemies.
     */
    Map<Integer, Enemy> getEnemies(boolean onlyAliveEnemies);

    /**
     * applies damage to specific enemy.
     *
     * @param id     enemy id.
     * @param damage damage amount.
     */
    void applyDamageById(int id, Double damage);
}
