package com.bdefender.map;

import java.util.Optional;

import com.bdefender.tower.Tower;


public class TowerBox {

    private final Coordinates topLeftCoord;
    private Optional<Tower> tower = Optional.empty();

    protected TowerBox(final Coordinates topLeftCoord) {
        this.topLeftCoord = topLeftCoord;
    }

    /**
     * Return the coordinates of the top left pixel of the box.
     * @return - Coordinates object
     */
    public final Coordinates getTopLeftCoord() {
        return this.topLeftCoord;
    }
    
    /**
     * Return the tower in this box.
     * @return - Optional<Tower> object
     */
    public Optional<Tower> getTower() {
        return this.tower;
    }
    
    /**
     * Set the tower in this box.
     * @param tower
     */
    public void setTower(Tower tower) {
        this.tower = Optional.of(tower);
    }
}
