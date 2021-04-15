package com.bdefender.game;

import com.bdefender.map.Coordinates;
import javafx.scene.layout.Pane;


public interface TowersController {

    /**
     * Creates the tower threads and add its view to the game panel.
     * @param tower tower type identification code.
     * @return created tower ID.
     */
    Integer addTower(TowerName tower, Coordinates pos);

    /**
     * Removes the tower from prompted panel and interrupts its life thread.
     * @param towerId tower ID.
     */
    void removeTower(Integer towerId);

    /**
     * Upgrade the tower to the next level.
     * @param towerId tower ID.
     * @return level after the upgrade
     */
    Integer upgradeTower(Integer towerId);


}
