package com.bdefender.tower;

public enum TowerName {

    /**
     * First Type of Tower (price, Id, Upgrade Cost, Damage, Range Radius, Shoot Speed).
     */
    FIRE_ARROW("Fire Arrow", 200, 0, 50, 13, 7, 8),

    /**
     * Second Type of Tower (price, Id, Upgrade Cost, Damage, Range Radius, Shoot Speed).
     */
    FIRE_BALL("Fire Ball", 100, 2, 30, 20, 7, 5),

    /**
     * Third Type of Tower (price, Id, Upgrade Cost, Damage, Range Radius, Shoot Speed).
     */
    THUNDERBOLT("Thunder Bolt", 120, 1, 45, 10, 8, 6),

    /**
     * Fourth Type of Tower (price, Id, Upgrade Cost, Damage, Range Radius, Shoot Speed).
     */
    ROCK("Rock", 50, 3, 55, 30, 5, 5);

    private final String name;
    private final Integer id;
    private final Integer price;
    private final Integer upgCost;
    private final Double damage;
    private final Double rangeRadius;
    private final Long shootSpeed;

    TowerName(final String name, final Integer price, final Integer id, final Integer upgCost, final double damage,
            final double rangeRadius, final long shootSpeed) {
        this.name = name;
        this.price = price;
        this.upgCost = upgCost;
        this.id = id;
        this.damage = damage;
        this.shootSpeed = shootSpeed;
        this.rangeRadius = rangeRadius;
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

    public Double getDamage() {
        return damage;
    }

    public Double getRangeRadius() {
        return rangeRadius;
    }

    public Long getShootSpeed() {
        return shootSpeed;
    }
}
