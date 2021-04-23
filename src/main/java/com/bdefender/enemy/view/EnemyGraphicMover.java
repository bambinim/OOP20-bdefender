package com.bdefender.enemy.view;

import com.bdefender.enemy.Enemy;

import java.util.ArrayList;

public interface EnemyGraphicMover {

    /**
     * moves all the enemies on the view.
     *
     * @param enemies list of enemies to move.
     */
    void moveEnemies(ArrayList<Enemy> enemies);

}
