package com.bdefender.game;

import com.bdefender.event.GameEvent;
import com.bdefender.event.EventHandler;

public interface GameController {

    /**
     * Return game's view.
     * @return GameView object
     */
    GameView getView();

    /**
     * Set event handler to call when game finishes.
     * @param handler
     */
    void setOnGameFinish(EventHandler<GameEvent> handler);

    /**
     * Get event handler to call when game finishes.
     * @return handler
     */
    EventHandler<GameEvent> getOnGameFinish();

    void closeAllThread();

    boolean isRunning();
}
