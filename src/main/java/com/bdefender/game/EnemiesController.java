package com.bdefender.game;

import com.bdefender.enemies.Enemy;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.enemies.view.EnemyView;

public interface EnemiesController {

    @FunctionalInterface
    interface EnemyViewImplementation {
        public EnemyView getView(Enemy enemy);
    }

    void startGenerate(int intensity, int totEnemies);

    EnemiesPoolInteractor getEnemiesPool();

}
