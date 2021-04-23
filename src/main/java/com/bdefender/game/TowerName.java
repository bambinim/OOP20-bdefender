package com.bdefender.game;

public enum TowerName {

    /**
     * First Type of Tower (price, Id, Upgrade Cost).
     */
    FIRE_ARROW("Fire Arrow", 200, 0, 5),

    /**
     * Second Type of Tower (price, Id, Upgrade Cost).
     */
    FIRE_BALL("Fire Ball", 100, 2, 15),

    /**
     * Third Type of Tower (price, Id, Upgrade Cost).
     */
    THUNDERBOLT("Thunder Bolt", 120, 1, 10),

    /**
     * Fourth Type of Tower (price, Id, Upgrade Cost).
     */
    ROCK("Rock", 50, 3, 20);


    private final String name;
    private final Integer id;
    private final Integer price;
    private final Integer upgCost;

    TowerName(final String name, final Integer price, final Integer id,final Integer upgCost){
        this.name = name;
        this.price = price;
        this.upgCost = upgCost;
        this.id = id;
    }
    public String getName() {
        return this.name;
    }

    public Integer getPrice() {
        return this.price;
    }
    public Integer getUpgCost() {
        return this.upgCost;
    }
    public Integer getId() {
        return this.id;
    }
}

