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
     * @param handler - handler called when game finishes
     */
    void setOnGameFinish(EventHandler<GameEvent> handler);
}
