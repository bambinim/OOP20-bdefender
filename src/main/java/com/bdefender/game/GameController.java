package com.bdefender.game;

import javafx.event.EventHandler;
import com.bdefender.game.event.GameEvent;

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
}
