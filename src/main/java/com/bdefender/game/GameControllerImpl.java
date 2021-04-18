package com.bdefender.game;

import java.io.IOException;

import com.bdefender.component.ImageButton;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.enemies.pool.MapInteractorImpl;
import com.bdefender.enemies.view.EnemiesPoolImpl;
import com.bdefender.game.event.GameEvent;
import com.bdefender.map.MapLoader;
import com.bdefender.map.MapType;
import com.bdefender.map.MapView;
import com.bdefender.map.TowerBox;
import com.bdefender.map.Coordinates;
import com.bdefender.map.Map;
import com.bdefender.tower.TowerFactory;
import com.bdefender.enemies.view.EnemyGraphicMoverImpl;
import com.bdefender.tower.view.TowerViewImpl;
import com.bdefender.wallet.Wallet;
import com.bdefender.enemies.view.EnemiesPoolImpl;
import com.bdefender.wallet.WalletImpl;


import javafx.event.EventHandler;

import com.bdefender.shop.Shop;
import com.bdefender.shop.ShopImpl;
import com.bdefender.shop.ShopManager;
import com.bdefender.shop.TowerPlacementView;

public class GameControllerImpl implements GameController {
    
   
    private final GameView view;
    private final Map map;
    private final MapView mapView;
    //enemies and tower
    private TowersController towerController;
    private EnemiesPoolInteractor pool;
    //economy and shop
    private final ShopManager shopManager;
    private final Shop shop;
    TowerPlacementView placementView;
    private final int INITIAL_AMOUNT = 1000;
   
    
    private EventHandler<GameEvent> onGameFinish;

    public GameControllerImpl(final MapType mapType) throws IOException {
        this.map = MapLoader.getInstance().loadMap(mapType);
        this.mapView = new MapView(this.map);
        //shop
        this.shop = new ShopImpl(new WalletImpl(INITIAL_AMOUNT));
        this.shopManager = new ShopManager(shop, (e) -> this.closeShop());
        this.view = new GameView(this.mapView, this.shopManager.getShopView());
         //topBar
        this.view.setActionTopM((e) -> this.openShop(), (e) -> System.exit(1));
        
        //enemies and tower
        this.pool = new com.bdefender.enemies.view.EnemiesPoolImpl(new MapInteractorImpl(this.map), new EnemyGraphicMoverImpl(this.mapView));
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
            final TowerName choosedTower = this.shopManager.getShopController().getLastTower();
            //Prendo dallo shop l'utima torre cliccata.
            addTower(choosedTower, boxClicked.getTopLeftCoord());
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
        final EnemiesPoolInteractor pool = new EnemiesPoolImpl(new MapInteractorImpl(map), new EnemyGraphicMoverImpl(this.view));
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
    
    private void closeShop(){
        this.view.getChildren().remove(shopManager.getShopView());
        this.generatePlacementBoxLayer();
    }
    
    private void openShop() {
        this.view.getChildren().add(this.shopManager.getShopView());
        this.view.setBottomAnchor(this.shopManager.getShopView(), 0.0);
        
    }

    
    
}
