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
     * Return the coordinates at the center of the box.
     * @return Coordinates object
     */
    public final Coordinates getCentralCoordinate() {
        return new Coordinates(this.topLeftCoord.getX() + 0.5, this.topLeftCoord.getY() + 0.5);
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
    public void setTower(final Tower tower) {
        this.tower = Optional.of(tower);
    }

    /**
     * Returns a string representation of the object.
     * @return string 
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof TowerBox) {
            final TowerBox tmp = (TowerBox) object;
            return tmp.getTopLeftCoord().equals(this.getTopLeftCoord()) && tmp.getTower().equals(this.getTower());
        }
        return false;
    }

    /**
     * Returns a hash code value for the object.
     * @return hash
     */
    @Override
    public int hashCode() {
        return this.topLeftCoord.hashCode() + this.tower.hashCode();
    }
}
