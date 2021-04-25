package com.bdefender.shop;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.bdefender.event.EventHandler;
import com.bdefender.event.MouseEvent;
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerName;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ShopViewManagerImpl implements ShopViewManager {


    private final Shop shop;
    private final Map<Button, TowerName> btnTowerMap = new HashMap<>();
    private Optional<TowerName> lastTowerClicked = Optional.empty();
    private Tower towerToUpg;
    private final EventHandler<MouseEvent> closeShop;
    private boolean isUpgrading;
    private static final Double DIS_OP = 0.5;
    private static final Double EN_OP = 0.0;

    @FXML
    private Label lblMoney;

    @FXML
    private Button bntCloseShop;

    @FXML
    private Label lblFireArrow;

    @FXML
    private Button btnFireArrow;

    @FXML
    private Label lblThunderBolt;

    @FXML
    private Button btnThunderbolt;

    @FXML
    private Label lblFireBall;

    @FXML
    private Button btnFireBall;

    @FXML
    private Label lblUpgrade;

    @FXML
    private Button btnUpgrade;


    public ShopViewManagerImpl(final Shop shop, final EventHandler<MouseEvent> closeShop) {
        this.shop = shop;
        this.closeShop = closeShop;
    }

    public final void initialize() {

        this.initializeButton();
        this.initializeLabel();
        this.updLblMoney();
        this.setBtnUpgradeOff();

        //add events to tower buttons
        this.btnTowerMap.forEach((k, v) -> {
            k.setOnMousePressed((e) -> {
                this.buyTower((Button) e.getSource());
                this.closeShop.handle(new MouseEvent(MouseEvent.MOUSE_CLICKED, e.getSource()));
            });
        });

        //add event to upgrade button
        btnUpgrade.setOnMouseClicked((e) -> {
            this.buyUpgrade();
            this.setBtnUpgradeOff();
            this.closeShop.handle(new MouseEvent(MouseEvent.MOUSE_CLICKED, e.getSource()));
        });

        //add event to Close button
        bntCloseShop.setOnMouseClicked(e -> this.closeShop.handle(new MouseEvent(MouseEvent.MOUSE_CLICKED, e.getSource())));
    }

    /**
     * If money are enough let the tower's button enable, othewise disable it.
     */
    public final void refreshTowerBtn() {
        if (!isUpgrading) {
            btnTowerMap.forEach((k, v) -> {
                if (shop.isTowerBuyable(v)) {
                    enableButton(k);
                } else {
                    disableButton(k);
                }
            });
        }
    }

    /**
     * Enable all  buttons and set upgradeBtn disabled. 
     */
    public void setBtnUpgradeOn() {
        btnTowerMap.forEach((k, v) -> disableButton(k));
        this.isUpgrading = true;
        this.towerToUpg = this.shop.getTowerToUpg().get();
        final TowerName  typeToUpg = Stream.of(TowerName.values())
                .filter((x) -> x.getId() == this.towerToUpg.getTowerTypeId())
                .findFirst()
                .get();
        if (this.shop.getWallet().areMoneyEnough(typeToUpg.getUpgCost())) {
            enableButton(btnUpgrade);
        }
    }

    /**
     * Disable all other buttons and set upgradeBtn enable. 
     */
    public final void setBtnUpgradeOff() {
        btnTowerMap.forEach((k, v) -> {
            if (shop.isTowerBuyable(v)) {
                enableButton(k);
            }
        });
        this.isUpgrading = false;
        disableButton(btnUpgrade);
    }

    /**
     * @return lastTower The tower choosed to be bought
     */
    public final Optional<TowerName> getLastTowerClicked() {
        return this.lastTowerClicked;
    }

    /**
     * Set empty lastTowerClicked.
     * */
    @Override
    public void setEmptyLastTwClicked() {
        this.lastTowerClicked = Optional.empty();

    }

    /**
     * Keeps updated the money value label.
     */
    public final void updLblMoney() {
        Platform.runLater(() -> lblMoney.setText(Integer.toString(shop.getWallet().getMoney())));
    }

    /**
     * Fill the map with the button and the towerName it is linked with.
     */
    private void initializeButton() {
        btnTowerMap.put(btnFireArrow, TowerName.FIRE_ARROW);
        btnTowerMap.put(btnFireBall, TowerName.FIRE_BALL);
        btnTowerMap.put(btnThunderbolt, TowerName.THUNDERBOLT);
    }

    /**
     * Initialize the labels with the prices of the towers.
     */
    private void initializeLabel() {
        lblFireArrow.setText(TowerName.FIRE_ARROW.getPrice().toString());
        lblFireBall.setText(TowerName.FIRE_BALL.getPrice().toString());
        lblThunderBolt.setText(TowerName.THUNDERBOLT.getPrice().toString());
    }

    /**
     * Proceed with the purchase of the tower, update the label and saves it.
     * @param btn of the tower that has been clicked
     */
    private void buyTower(final Button btn) {
        this.shop.setTowerToBuy(Optional.of(this.btnTowerMap.get(btn)));
        shop.buyTower();
        this.updLblMoney();
        this.lastTowerClicked = Optional.of(this.btnTowerMap.get(btn));
        this.refreshTowerBtn();
    }

    /** When the button upgrade is clicked  subctract the money and Upgrade the tower saved to be upgraded.
     */
    private void buyUpgrade() {
        shop.buyUpgrade();
        this.updLblMoney();
        this.refreshTowerBtn();
    }


    private void enableButton(final Button btn) {
        btn.setOpacity(EN_OP);
        Platform.runLater(() -> btn.setText(""));
        btn.setDisable(false);
    }

    private void disableButton(final Button btn) {
        btn.setOpacity(DIS_OP);
        Platform.runLater(() -> btn.setText("Disabled"));
        btn.setDisable(true);
    }






}
