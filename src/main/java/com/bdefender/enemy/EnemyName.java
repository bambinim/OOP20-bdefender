package com.bdefender.enemy;

public enum EnemyName {

    /**
     * First type of enemy.
     */
    AXE_OGRE(4.0, 40.0, 30.0, 1),

    /**
     * Second type of enemy.
     */
    SWORD_OGRE(3.0, 60.0, 35.0, 0),

    /**
     * Third type of enemy.
     */
    HAMMER_OGRE(8.0, 30.0, 50.0, 2);

    private final double damage;
    private final double speed;
    private final double life;
    private final int id;

    EnemyName(final double damage, final double speed, final double life, final int id) {
        this.damage = damage;
        this.speed = speed;
        this.life = life;
        this.id = id;
    }

    public double getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }

    public double getLife() {
        return life;
    }

    public int getId() {
        return id;
    }

}
