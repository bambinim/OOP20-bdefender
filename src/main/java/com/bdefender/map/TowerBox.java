package com.bdefender.map;


public class TowerBox {

    private final Coordinates topLeftCoord;

    protected TowerBox(final Coordinates topLeftCoord) {
        this.topLeftCoord = topLeftCoord;
    }

    /**
     * Return the coordinates of the top left pixel of the box.
     * @return
     */
    public final Coordinates getTopLeftCoord() {
        return this.topLeftCoord;
    }
}
