package com.bdefender.game;

public enum TowerName {
 
    FIRE_ARROW("Fire Arrow", 200,5),
    FIRE_BALL("Fire Ball", 100,15),
    ROCK("Rock", 50,20),
    THUNDERBOLT("Thunder Bolt", 120,10);

    private final String name;
    private final Integer price;
    private final Integer upgCost;

    TowerName(final String name, final Integer price, final Integer upgCost){
        this.name = name;
        this.price = price;
        this.upgCost = upgCost;
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

}

