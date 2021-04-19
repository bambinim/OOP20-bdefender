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
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerFactory;
import com.bdefender.enemies.view.EnemyGraphicMoverImpl;
import com.bdefender.tower.view.TowerViewImpl;
import com.bdefender.wallet.Wallet;
import com.bdefender.enemies.view.EnemiesPoolImpl;
import com.bdefender.wallet.WalletImpl;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
        this.pool = new EnemiesPoolImpl(new MapInteractorImpl(this.map), new EnemyGraphicMoverImpl(this.mapView.getEnemiesPane()));
        this.towerController = new TowersControllerImpl((t) -> new TowerViewImpl(new AnchorPane(), t), this.pool);
        //click on map
        //generatePlacementBoxLayer();

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

    /**
     * Add a tower to the tower controller to the view.
     * @param MouseEvent
     */
    private void addTower(final MouseEvent event) {
        final TowerBox boxClicked = (TowerBox) event.getSource();
        final TowerName choosedTower = this.shopManager.getShopController().getLastTower();
        final Tower tower = this.towerController.addTower(choosedTower, boxClicked.getCentralCoordinate());
        boxClicked.setTower(tower);

        this.removeBoxLayer();
        this.mapView.reloadTowersView();
        //this.generatedUpgradeBoxLayer();
    }

    /*
     * Genera le posizioni cliccabili sulla mappa per posizionare una torre. 
     */
    private void generatePlacementBoxLayer() {
        placementView = new TowerPlacementView(this.map.getEmptyTowerBoxes());
        placementView.setOnBoxClick((e) -> this.addTower(e));
        this.mapView.getChildren().add(placementView);
    }

    private void removeBoxLayer() {
        try {
            this.mapView.getChildren().remove(this.placementView);
        } catch (Exception e) {
        }
    }

    /*
     * Genera le poszioni cliccabili per potenziare le torri
     */
    private void generatedUpgradeBoxLayer() {
        this.placementView = new TowerPlacementView(this.map.getOccupiedTowerBoxes());

        this.placementView.setOnBoxClick((e) -> {
            final TowerBox boxClicked = (TowerBox) (e.getSource());
            this.shopManager.getShopController().setTowerToUpg(boxClicked.getTower().get());
            System.out.println("Clicco per potenziare la torre in -> " + boxClicked.getTower().get().getPosition());
            this.openShop();
            shopManager.getShopController().setBtnUpgradeOn();
        });
        this.view.getChildren().add(this.placementView);
    }

    private void closeShop() {
        this.view.getChildren().remove(shopManager.getShopView());
        this.generatePlacementBoxLayer();
    }

    private void openShop() {
        this.view.getChildren().add(this.shopManager.getShopView());
        this.view.setBottomAnchor(this.shopManager.getShopView(), 0.0);
        //toglie la griglia di posizionamento
        removeBoxLayer();


    }



}
