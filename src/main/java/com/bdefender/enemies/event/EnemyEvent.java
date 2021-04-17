package com.bdefender.enemies.event;

import com.bdefender.enemies.Enemy;
import javafx.event.Event;
import javafx.event.EventType;

public class EnemyEvent extends Event {

    /**
     * 
     */
    private static final long serialVersionUID = -6175312624802207211L;
    public static final EventType<EnemyEvent> ENEMY_KILLED = new EventType<>("Enemy Killed");
    public static final EventType<EnemyEvent> ENEMY_SPAWNED = new EventType<>("Enemy Killed");
    public static final EventType<EnemyEvent> ENEMY_REACHED_END = new EventType<>("Enemy Killed");

    private final Enemy source;

    public EnemyEvent(final EventType<? extends Event> eventType, final Enemy source) {
        super(eventType);
        this.source = source;
    }

    /**
     * @return enemy
     */
    public Enemy getSource() {
        return this.source;
    }

}
