package com.bdefender.enemies.view;

import com.bdefender.enemies.Enemy;

import java.util.ArrayList;

public interface EnemyGraphicMover {

    /**
     * moves all the enemies on the view.
     * @param enemies list of enemies to move.
     */
    void moveEnemies(ArrayList<Enemy> enemies);

}
