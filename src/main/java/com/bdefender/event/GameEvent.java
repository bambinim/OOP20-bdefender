package com.bdefender.event;

public class GameEvent extends Event {

    public static final EventType<GameEvent> GAME_FINISH = new EventType<>("Game Finish");
    /**
     * 
     */
    public static final EventType<GameEvent> GAME_QUIT = new EventType<>("Game Quit");
    public GameEvent(final EventType<? extends Event> eventType) {
        super(eventType);
        // TODO Auto-generated constructor stub
    }

}
