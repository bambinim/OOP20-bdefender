package com.bdefender.game;

import com.bdefender.map.Coordinates;
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerName;

public interface TowersController {

    /**
     * Creates the tower threads and add its view to the game panel.
     *
     * @param pos       tower spawn position
     * @param towerName tower type identification code.
     * @return created tower ID.
     */
    Tower addTower(TowerName towerName, Coordinates pos);

    /**
     * Removes the tower from prompted panel and interrupts its life thread.
     *
     * @param tower tower you want to remove.
     */
    void removeTower(Tower tower);

    /**
     * Upgrade the tower to the next level.
     *
     * @param tower tower you want to update.
     * @return level after the upgrade
     */
    Integer upgradeTower(Tower tower);

}
