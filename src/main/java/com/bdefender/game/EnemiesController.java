package com.bdefender.game;

import com.bdefender.enemies.EnemyBase;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.enemies.view.EnemyView;

public interface EnemiesController {

    @FunctionalInterface
    interface EnemyViewImplementation {
        public EnemyView getView(EnemyBase enemy);
    }

    void startGenerate(int intensity, int totEnemies);

    EnemiesPoolInteractor getEnemiesPool();

}
