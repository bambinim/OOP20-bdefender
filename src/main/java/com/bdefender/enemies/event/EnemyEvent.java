package com.bdefender.enemies.event;

import com.bdefender.enemies.EnemyBase;
import com.bdefender.game.event.GameEvent;
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

    private final EnemyBase source;

    public EnemyEvent(final EventType<? extends Event> eventType, final EnemyBase source) {
        super(eventType);
        this.source = source;
    }

    /**
     * @return enemy
     */
    public EnemyBase getSource() {
        return this.source;
    }

}
