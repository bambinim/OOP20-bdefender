package com.bdefender.game;

import com.bdefender.enemies.event.EnemyEvent;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import javafx.event.EventHandler;

public interface EnemiesController {

    /**
     * start enemies generation
     * @param intensity enemies to spawn in 10 seconds
     * @param totEnemies number of enemies to spawn
     * @param onDead  handler of enemy death event
     * @param onReachedEnd handler of enemy reached end event
     */
    void startGenerate(int intensity, int totEnemies, EventHandler<EnemyEvent> onDead, EventHandler<EnemyEvent> onReachedEnd);

    /**
     *
     * @return enemies pool
     */
    EnemiesPoolInteractor getEnemiesPool();

}
