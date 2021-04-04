package com.bdefender.map;

import java.util.Optional;

public class TowerBox {

    private final Coordinates topLeftCoord;

    protected TowerBox(Coordinates topLeftCoord) {
        this.topLeftCoord = topLeftCoord;
    }

    public Coordinates getTopLeftCoord() {
        return this.topLeftCoord;
    }
}
