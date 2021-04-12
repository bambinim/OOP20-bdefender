package com.bdefender.game;

import com.bdefender.enemies.pool.EnemiesPoolImpl;
import com.bdefender.enemies.pool.MapInteractorImpl;
import com.bdefender.game.event.GameEvent;
import com.bdefender.map.MapLoader;
import com.bdefender.map.MapView;
import com.bdefender.map.TowerBox;
import com.bdefender.map.Map;
import com.bdefender.tower.TowerFactory;
import javafx.event.EventHandler;
import com.bdefender.shop.TowerPlacementView;

public class GameControllerImpl implements GameController {

    private final GameView view;
    private final Map map;
    private final MapView mapView;
    private EventHandler<GameEvent> onGameFinish;

    public GameControllerImpl(final int map) {
        this.map = MapLoader.getInstance().loadMap(map);
        this.mapView = new MapView(this.map);
        this.view = new GameView(this.mapView);
        this.generateTestTower();
    }

    // TODO: remove after test
    private void generateTestTower() {
        final TowerFactory tFactory = new TowerFactory();
        final EnemiesPoolImpl pool = new EnemiesPoolImpl(new MapInteractorImpl(map));
        final TowerPlacementView placementView = new TowerPlacementView(this.map.getEmptyTowerBoxes());
        placementView.setOnBoxClick(event -> {
            final TowerBox box = (TowerBox) event.getSource();
            this.map.getEmptyTowerBoxes().forEach(el -> {
                if (el.equals(box)) {
                    el.setTower(tFactory.getTowerDirect1(pool, el.getCentralCoordinate()));
                    return;
                }
            });
            this.mapView.reloadTowersView();
            placementView.reload(this.map.getEmptyTowerBoxes());
        });
        this.mapView.getChildren().add(placementView);
    }

    /**
     * Returns the main view of the game.
     */
    @Override
    public GameView getView() {
        return this.view;
    }

    /**
     * Set event handler to call when game finishes.
     * @param handler
     */
    @Override
    public void setOnGameFinish(final EventHandler<GameEvent> handler) {
        this.onGameFinish = handler;
    }

    /**
     * Get event handler to call when game finishes.
     * @return handler
     */
    @Override
    public EventHandler<GameEvent> getOnGameFinish() {
        return this.onGameFinish;
    }
}
