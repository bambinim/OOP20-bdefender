package com.bdefender.map;


public class TowerBox {

    private final Coordinates topLeftCoord;

    protected TowerBox(final Coordinates topLeftCoord) {
        this.topLeftCoord = topLeftCoord;
    }

    public final Coordinates getTopLeftCoord() {
        return this.topLeftCoord;
    }
}
