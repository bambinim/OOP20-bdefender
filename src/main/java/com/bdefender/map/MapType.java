package com.bdefender.map;

public enum MapType {
    COUNTRYSIDE(0, "Country Side"), ICEPLAIN(1, "Ice Plain");
    private int mapNumber;
    private String mapName;

    public int getMapNumber() {
        return this.mapNumber;
    }
    public String getMapName() {
        return this.mapName;
    }

    MapType(final int mapNumber, String mapName) {
        this.mapNumber = mapNumber;
        this.mapName = mapName;
    }
}
