package com.bdefender.tower.view;

import com.bdefender.Pair;
import com.bdefender.map.Coordinates;

public interface TowerView {

    /**
     *starts shoot animation
     * @param target shoot target position.
     */
    void startShootAnimation(Pair<Double, Double> target);

    /**
     * adds the tower to game panel.
     */
    void addTowerToGameField();

    /**
     * removes tower from game panel
     */
    void removeTowerFromGameField();
}
