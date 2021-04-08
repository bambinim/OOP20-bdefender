package com.bdefender.game;

import com.bdefender.game.event.GameEvent;
import com.bdefender.map.MapLoader;
import com.bdefender.map.Map;
import javafx.event.EventHandler;

public class GameControllerImpl implements GameController {

    private final GameView view;
    private final Map map;

    public GameControllerImpl(final int map) {
        this.map = MapLoader.getInstance().loadMap(map);
        this.view = new GameView(this.map);
    }

    @Override
    public GameView getView() {
        return this.view;
    }

    @Override
    public void setOnGameFinish(final EventHandler<GameEvent> handler) {
        // TODO Auto-generated method stub
    }
}
