package com.bdefender.enemy.view;

import com.bdefender.enemy.Enemy;
import java.util.List;

public interface EnemiesGraphicMover {

    /**
     * moves all the enemies on the view.
     *
     * @param enemies list of enemies to move.
     */
    void moveEnemies(List<Enemy> enemies);

}
