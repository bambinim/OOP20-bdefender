package com.bdefender.game;

public enum TowerName {
 
    FIRE_ARROW("Fire Arrow", 200),
    FIRE_BALL("Fire Ball", 100),
    ROCK("Rock", 50),
    THUNDERBOLT("Thunder Bolt", 20);

    private final String name;
    private final Integer price;
    TowerName(final String name, final Integer price){
        this.name = name;
        this.price = price;
        }
     public String getName() {
         return this.name;
     }

     public Integer getPrice() {
       return this.price;
     }


}

