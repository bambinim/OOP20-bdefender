package com.bdefender.game;

import java.io.IOException;
import java.util.Optional;

import com.bdefender.map.MapLoader;
import com.bdefender.map.MapType;
import com.bdefender.map.MapView;
import com.bdefender.map.TowerBox;
import com.bdefender.map.Map;
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerName;
import com.bdefender.enemy.view.EnemyGraphicMoverImpl;
import com.bdefender.event.GameEvent;
import com.bdefender.tower.view.TowerViewImpl;
import com.bdefender.wallet.WalletImpl;
import com.bdefender.event.MouseEvent;
import com.bdefender.event.EventHandler;
import com.bdefender.shop.Shop;
import com.bdefender.shop.ShopImpl;
import com.bdefender.shop.ShopManager;
import com.bdefender.shop.ShopManagerImpl;
import com.bdefender.statistics.StatisticsWriter;
import com.bdefender.statistics.StatisticsWriterImpl;

public class GameControllerImpl implements GameController {

    private static final int DAMAGE_ON_REACHED_END = 50;
    //game GUI
    private final GameView view;
    private final Map map;
    private final MapView mapView;

    //enemies and tower
    private final TowersController towerController;
    private EnemiesController enemies;

    //economy and shop
    private final ShopManager shopManager;
    private final Shop shop;
    private Optional<TowerName> choosedTower = Optional.empty();
    private boolean isShopOpen = true;
    private static final  int INITIAL_AMOUNT = 600;

    //game Managment 
    private int lifePoint = 100;
    private int round;
    private int enemiesOffGame;
    private int enemiesToSpawn = 10;
    private int frequencyEnemies = 3;
    private static final int DEAD_MONEY = 20;
    private static final int INC_ENEMIES = 5;
    private static final int INC_FREQ = 3;
    private boolean runningState = true;
    private final StatisticsWriter statWriter;


    private EventHandler<GameEvent> onGameFinish;

    public GameControllerImpl(final MapType mapType) throws IOException {
        this.statWriter = new StatisticsWriterImpl();
        this.statWriter.gameStarted(mapType);
        this.map = MapLoader.getInstance().loadMap(mapType);
        this.mapView = new MapView(this.map);
        this.mapView.getTowerPlacementView().setOnBoxClick(e -> this.addTower(e));
        //shop
        this.shop = new ShopImpl(new WalletImpl(INITIAL_AMOUNT));
        this.shopManager = new ShopManagerImpl(shop, (e) -> this.closeShop());
        this.view = new GameView(this.mapView, this.shopManager.getShopView());
        //topBar
        //this.view.setActionTopM((e) -> this.openShop(), (e) -> this.startGame(), (e) -> System.exit(0));
        this.view.getTopMenuView().getShopButton().setOnMouseClick((e) -> this.openShop());
        this.view.getTopMenuView().getPlayButton().setOnMouseClick((e) -> this.startGame());
        this.view.getTopMenuView().getExitButton().setOnMouseClick((e) -> {
            this.finishGame();
            this.onGameFinish.handle(new GameEvent(GameEvent.GAME_QUIT));
        });
        //enemies and tower
        this.enemies = new EnemiesControllerImpl(this.map, new EnemyGraphicMoverImpl(this.mapView.getEnemiesPane()));
        this.towerController = new TowersControllerImpl((t) -> new TowerViewImpl(this.mapView.getTowersPane(), t), enemies.getEnemiesPool());
        this.mapView.setOnTowerClick(e -> {
            this.shopManager.getShopController().setTowerToUpg(e.getTower());
            this.openShop();
            shopManager.getShopController().setBtnUpgradeOn();
        });

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
        this.mapView.setTowerPlacementViewVisible(false);
        this.mapView.reloadTowersView();
        //enable all the buttons if round is finished, otherwise just shop and exit
        if (this.isRoundFinished()) {
            this.view.setAllButtonEnable();
        } else {
            this.view.getTopMenuView().getExitButton().enable();
            this.view.getTopMenuView().getShopButton().enable();
        }
    }

    /**
     * Close Shop window.
     */
    private void closeShop() {
        if (this.isShopOpen) {
            this.isShopOpen = false;
            this.view.getTopMenuView().getShopButton().enable();
            this.view.getChildren().remove(shopManager.getShopView());
            this.choosedTower = this.shopManager.getShopController().getLastTowerClicked();
            if (this.choosedTower.isPresent()) {
                //disabilito tutti i pulsanti
                //this.view.setAllButtonDisable();
                this.view.getTopMenuView().getExitButton().disable();
                this.view.getTopMenuView().getShopButton().disable();
                this.mapView.setTowerPlacementViewVisible(true);
            } 
        }
    }

    /**
     * Open Shop window.
     */
    private void openShop() {
        if (!this.isShopOpen) {
            this.isShopOpen = true;
            this.view.getTopMenuView().getShopButton().disable();
            this.view.getChildren().add(this.shopManager.getShopView());
            this.view.setBottomAnchor(this.shopManager.getShopView(), 0.0);
            //toglie la griglia di posizionamento
            this.mapView.setTowerPlacementViewVisible(false);
        }
    }


    /**
     * Check if round is finished.
     * @return true if it is false if is not.
     * */
    private boolean isRoundFinished() {
        return this.enemiesOffGame >= this.enemiesToSpawn || this.round == 0;
    }

    /**
     * Increment round and increase the level difficulty.
     * */
    private void nextRound() {
        this.enemiesToSpawn = this.enemiesToSpawn + GameControllerImpl.INC_ENEMIES;
        this.frequencyEnemies = this.frequencyEnemies + GameControllerImpl.INC_FREQ;
        this.view.setAllButtonEnable();
        this.enemies.stopMovingEnemies();
    }

    /** 
     * Event called when a enemy die.
     * Add money to the wallet and if the round is finished go for a new one.
     * */
    private void onDead() {
        this.shop.getWallet().addMoney(DEAD_MONEY);
        this.shopManager.getShopController().updLblMoney();
        this.shopManager.getShopController().refreshTowerBtn();
        this.enemiesOffGame++;
        if (this.isRoundFinished()) {
            this.nextRound();
        }
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
            this.finishGame();
        }
        if (this.isRoundFinished()) {
            this.nextRound();
        }
        System.out.println("LifePoint = " + this.lifePoint);
    }

    private void finishGame() {
        this.runningState = false;
        this.closeAllThread();
        this.statWriter.gameFinished(this.round);
        try {
            this.statWriter.saveStatistics();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        this.view.showGameOverMenu(this.round, (e) -> this.onGameFinish.handle(new GameEvent(GameEvent.GAME_QUIT)));
    }

    /**
     * Start the game and enemies start spawn.
     * */
    private void startGame() {
        this.round++;
        this.view.getTopMenuView().setRoundTextValue(this.round);
        this.enemiesOffGame = 0;
        this.view.getTopMenuView().getPlayButton().disable();
        enemies.startGenerate(this.frequencyEnemies, this.enemiesToSpawn, e -> this.onDead(), e -> this.onReachedEnd());
    }

    /**
     * Return true if game is running, false else.
     * @return game status
     */
    public boolean isRunning() {
        return this.runningState;
    }

    @Override
    public final void closeAllThread() {
        if (this.round != 0) {
        this.enemies.stopMovingEnemies();
        this.enemies.stopSpawner();
        }
        this.map.getOccupiedTowerBoxes().forEach((tb) -> this.towerController.removeTower(tb.getTower().get()));
    }
}
