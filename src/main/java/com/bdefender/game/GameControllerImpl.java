package com.bdefender.game;

import com.bdefender.enemies.pool.EnemiesPoolImpl;
import com.bdefender.enemies.pool.MapInteractorImpl;
import com.bdefender.game.TowersController.TowerName;
import com.bdefender.game.event.GameEvent;
import com.bdefender.map.MapLoader;
import com.bdefender.map.MapView;
import com.bdefender.map.TowerBox;
import com.bdefender.map.Coordinates;
import com.bdefender.map.Map;
import com.bdefender.tower.TowerFactory;
import com.bdefender.tower.view.TowerViewImpl;
import com.bdefender.wallet.Wallet;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import com.bdefender.shop.ShopLoader;
import com.bdefender.shop.TowerPlacementView;

public class GameControllerImpl implements GameController {

    private final GameView view;
    private final Map map;
    private final MapView mapView;
    //enemies and tower
    private TowersController towerController;
    private EnemiesPoolImpl pool;
    //economy and shop
    private Wallet wallet;
    private ShopLoader shopLoader;
    TowerPlacementView placementView;
    
    private EventHandler<GameEvent> onGameFinish;

    public GameControllerImpl(final int mapID) {
        this.map = MapLoader.getInstance().loadMap(mapID);
        this.mapView = new MapView(this.map);
        this.view = new GameView(this.mapView);
        //enemies and tower
        this.pool = new EnemiesPoolImpl(new MapInteractorImpl(this.map));
        this.towerController = new TowersControllerImpl((t) -> new TowerViewImpl(this.view, t), this.pool);
        //click on map
        generatePlacementBoxLayer();

    }

    /*
     * Genera la le posizioni cliccabili sulla mappa. 
     */
    private void generatePlacementBoxLayer() {
        placementView = new TowerPlacementView(this.map.getEmptyTowerBoxes());
        placementView.setOnBoxClick((e) -> {
            final TowerBox boxClicked = (TowerBox) e.getSource();
            //Prendo dallo shop l'utima torre cliccata.
            addTower(TowerName.FIRE_ARROW /*ULTIMA TORRE CLICCATA*/, boxClicked.getTopLeftCoord());
        });
        this.mapView.getChildren().add(placementView);
    }

    /**
     * Add a tower to the tower controller and to the view.
     * @param towerName
     * @param coordinate
     */
    public void addTower(final TowerName towerName, final Coordinates coordinate) {
        this.towerController.addTower(towerName, coordinate);
        //toglie la griglia
        this.mapView.getChildren().remove(this.placementView);
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
