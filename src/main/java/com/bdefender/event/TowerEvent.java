package com.bdefender.event;

import com.bdefender.tower.Tower;

public class TowerEvent extends Event {

    public static final EventType<TowerEvent> TOWER_CLICKED = new EventType<>("Tower Clicked");
    private final Tower tower;

    public TowerEvent(final EventType<TowerEvent> type, final Tower tower) {
        super(type);
        this.tower = tower;
    }

    public Tower getTower() {
        return this.tower;
    }
}
