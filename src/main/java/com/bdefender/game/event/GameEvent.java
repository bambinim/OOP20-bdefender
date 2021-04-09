package com.bdefender.game.event;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event {

    private static final long serialVersionUID = 6375664198341560180L;

    public GameEvent(final EventType<? extends Event> eventType) {
        super(eventType);
        // TODO Auto-generated constructor stub
    }

}
