package com.bdefender.game.event;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event {

    private static final long serialVersionUID = 6375664198341560180L;
    /**
     * 
     */
    public static final EventType<GameEvent> GAME_FINISH = new EventType<>("Game Finish");
    public static final EventType<GameEvent> GAME_QUIT = new EventType<>("Game Quit");
    public GameEvent(final EventType<? extends Event> eventType) {
        super(eventType);
        // TODO Auto-generated constructor stub
    }

}
