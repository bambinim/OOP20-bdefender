package com.bdefender.event;

import com.bdefender.enemy.Enemy;

public class EnemyEvent extends EventImpl {

    /**
     * Enemy Killed.
     */
    public static final EventType<EnemyEvent> ENEMY_KILLED = new EventType<>("Enemy Killed");

    /**
     * Enemy spawned.
     */
    public static final EventType<EnemyEvent> ENEMY_SPAWNED = new EventType<>("Enemy Spawned");

    /**
     * Enemy reached end.
     */
    public static final EventType<EnemyEvent> ENEMY_REACHED_END = new EventType<>("Enemy Reached End");

    private final Enemy enemy;

    public EnemyEvent(final EventType<? extends Event> eventType, final Enemy source) {
        super(eventType);
        this.enemy = source;
    }

    /**
     * @return enemy
     */
    public Enemy getEnemy() {
        return this.enemy;
    }

}
