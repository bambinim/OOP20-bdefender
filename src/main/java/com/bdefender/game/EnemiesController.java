package com.bdefender.game;

import com.bdefender.enemies.event.EnemyEvent;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import javafx.event.EventHandler;

public interface EnemiesController {

    void startGenerate(int intensity, int totEnemies, EventHandler<EnemyEvent> onDead, EventHandler<EnemyEvent> onReachedEnd);

    EnemiesPoolInteractor getEnemiesPool();

}
