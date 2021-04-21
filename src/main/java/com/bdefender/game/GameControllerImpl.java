package com.bdefender.game;

import java.io.IOException;
import java.util.Optional;

import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.enemies.pool.MapInteractorImpl;
import com.bdefender.enemies.pool.EnemiesPoolImpl;
import com.bdefender.game.event.GameEvent;
import com.bdefender.map.MapLoader;
import com.bdefender.map.MapType;
import com.bdefender.map.MapView;
import com.bdefender.map.TowerBox;
import com.bdefender.map.Map;
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerFactory;
import com.bdefender.enemies.view.EnemyGraphicMoverImpl;
import com.bdefender.tower.view.TowerViewImpl;
import com.bdefender.wallet.WalletImpl;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import com.bdefender.shop.Shop;
import com.bdefender.shop.ShopImpl;
import com.bdefender.shop.ShopManager;
import com.bdefender.shop.TowerPlacementView;

public class GameControllerImpl implements GameController {

    private static final int DAMAGE_ON_REACHED_END = 5;
    //game GUI
    private final GameView view;
    private final Map map;
    private final MapView mapView;
    private TowerPlacementView placementView;

    //enemies and tower
    private final TowersController towerController;
    private final EnemiesController enemies;

    //economy and shop
    private final ShopManager shopManager;
    private final Shop shop;
    private Optional<TowerName> choosedTower = Optional.empty();
    private static final  int INITIAL_AMOUNT = 1000;

    //game Managment 
    private int lifePoint = 100;
    private int round = 0;
    private static final int DEAD_MONEY = 20;
    private int enemiesOffGame = 0;
    private int enemiesToSpawn = 10;
    private static final int FREQUENCY_ENEMIES= 5;


    private EventHandler<GameEvent> onGameFinish;

    public GameControllerImpl(final MapType mapType) throws IOException {
        this.map = MapLoader.getInstance().loadMap(mapType);
        this.mapView = new MapView(this.map);
        //shop
        this.shop = new ShopImpl(new WalletImpl(INITIAL_AMOUNT));
        this.shopManager = new ShopManager(shop, (e) -> this.closeShop());
        this.view = new GameView(this.mapView, this.shopManager.getShopView());
        //topBar
        this.view.setActionTopM((e) -> this.openShop(), (e) -> this.startGame(), (e) -> System.exit(0));
        //enemies and tower
        this.enemies = new EnemiesControllerImpl(this.map, new EnemyGraphicMoverImpl(this.mapView));
        this.towerController = new TowersControllerImpl((t) -> new TowerViewImpl(new AnchorPane(), t), enemies.getEnemiesPool());


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
        final Tower tower = this.towerController.addTower(choosedTower.get(), boxClicked.getCentralCoordinate());
        boxClicked.setTower(tower);
        this.shopManager.getShopController().setEmptyLastTower();
        this.removeBoxLayer();
        this.mapView.reloadTowersView();
        this.generatedUpgradeBoxLayer();
        //abilito tutti i pulsanti
        this.view.setAllButtonEnable();
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
            this.openShop();
            shopManager.getShopController().setBtnUpgradeOn();
        });
        this.mapView.getChildren().add(this.placementView);
    }

    /**
     * Close Shop widow.
     */
    private void closeShop() {
        this.view.getTopMenuView().getShopButton().enable();
        this.view.getChildren().remove(shopManager.getShopView());
        this.choosedTower = this.shopManager.getShopController().getLastTower();
        if (this.choosedTower.isPresent()) {
            //disabilito tutti i pulsanti
            this.view.setAllButtonDisable();
            this.generatePlacementBoxLayer();
        } 
    }
    /**
     * Open Shop window.
     */
    private void openShop() {
        this.view.getTopMenuView().getShopButton().disable();
        this.view.getChildren().add(this.shopManager.getShopView());
        this.view.setBottomAnchor(this.shopManager.getShopView(), 0.0);
        //toglie la griglia di posizionamento
        removeBoxLayer();


    }


    /**
     * Check if round is finished.
     * @return true if it is false if is not.
     * */
    private boolean isRoundFinished(){
        return this.enemiesOffGame == this.enemiesToSpawn;
    }

    /**
     * Increment round and increase the level difficulty.
     * */
    private void nextRound() {
        this.round++;
        if (this.round % 2 == 0){
            //aumento nemici
        }
        else {
            //aumento frequenza
        }
    }

    /** 
     * Event called when a enemy die.
     * Add money to the wallet and if the round is finished go for a new one.
     * */
    private void onDead() {
        this.shop.getWallet().addMoney(DEAD_MONEY);
        Platform.runLater(() -> this.shopManager.getShopController().updLblMoney());
        this.enemiesOffGame++;
        if (this.isRoundFinished()) {
            this.nextRound();
        }
        this.enableButtonIfRoundEnd();
    }

    /**
     * Event called when an enemy pass the end of the path.
     * decreases the player's life and checks if it is possible to continue the game.
     */
    private void onReachedEnd() {
        this.lifePoint = this.lifePoint - DAMAGE_ON_REACHED_END;
        final Double lifePointToDouble = (double) this.lifePoint;
        this.view.setLifePiointsInTopMenu(lifePointToDouble / 100.0);
        this.enemiesOffGame++;
        if (this.lifePoint <= 0) {
            System.out.println("Morto");
           // this.onGameFinish.handle();
        }
        this.enableButtonIfRoundEnd();
        System.out.println("LifePoint = " + this.lifePoint);
    }

    private void enableButtonIfRoundEnd() {
        //if all enemies are died or passed the end unlock the menu buttons.
        if (this.enemiesOffGame >= this.enemiesToSpawn) {
            this.view.setAllButtonEnable();
        }
    }

    /**
     * start the game enemies start spawn.
     * */
    private void startGame() {
        this.enemiesOffGame = 0;
        this.view.getTopMenuView().getPlayButton().disable();
        enemies.startGenerate(FREQUENCY_ENEMIES, this.enemiesToSpawn, (e) -> this.onDead(), (e) -> this.onReachedEnd());
    }





}
