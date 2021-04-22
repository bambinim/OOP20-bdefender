package com.bdefender.shop;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.bdefender.event.EventHandler;
import com.bdefender.event.MouseEvent;
import com.bdefender.game.TowerName;
import com.bdefender.tower.Tower;
import com.bdefender.wallet.Wallet;
import com.sun.source.doctree.AttributeTree.ValueKind;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ShopControllerImpl implements ShopController {


    private final Shop shop;
    private Map<Button, TowerName> towers = new HashMap<>();
    private Optional<TowerName> lastTower = Optional.empty();
    private Tower towerToUpg = null;
    private EventHandler<MouseEvent> closeShop;

    private final Double DIS_OP = 0.5;
    private final Double EN_OP = 0.0;
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
 
    public ShopControllerImpl(final Shop shop, final EventHandler<MouseEvent> closeShop) {
        this.shop = shop;
        this.closeShop = closeShop;
    }

    public final void initialize() {

       initializeButton();
       initializeLabel();
       updLblMoney();
       this.setBtnUpgradeOff();
       towers.forEach((k, v) -> {
           k.setOnMousePressed((e) -> {
           buyTower(((Button) e.getSource()));
             this.closeShop.handle(new MouseEvent(MouseEvent.MOUSE_CLICKED, e.getSource()));
             });
       });
       btnUpgrade.setOnMouseClicked((e) -> {
           this.buyUpgrade();
           this.setBtnUpgradeOff();
       });

       bntCloseShop.setOnMouseClicked(e -> this.closeShop.handle(new MouseEvent(MouseEvent.MOUSE_CLICKED, e.getSource())));
    }

    /*
     * when button is clicked proceed with the purchase of tower
     * */

    private void buyTower(final Button btn) {
            shop.buyTower(towers.get(btn));
            updLblMoney();
            this.lastTower = Optional.of(towers.get(btn));
            this.refreshToweBtn();
    }
    /** when the button upgrade is clicked prooced subctract the money.
     * @param the tower need to be updated
     */
    private void buyUpgrade() {
        shop.buyUpgrade(this.towerToUpg);
    }
    
    /**
     * @return lastTower The tower choosed to be bought
     */
    public final Optional<TowerName> getLastTower() {
        return this.lastTower;
    }

  public final void setEmptyLastTower() {
      this.lastTower = Optional.empty();
  }

  /**
   * Set tower to Upgrade.
   * @param tower we want to upgrade.
   */
    public void setTowerToUpg(final Tower tower) {
      this.towerToUpg = tower;
  }

   /*
    * nees to keep updated the money value label
    * */
    public final void updLblMoney() {
        lblMoney.setText("" + shop.getWallet().getMoney());
    }
 
  /*
   * Fill the map with the button and the towerName it is linked with
   * */
  private void initializeButton() {
      towers.put(btnFireArrow, TowerName.FIRE_ARROW);
      towers.put(btnFireBall, TowerName.FIRE_BALL);
      towers.put(btnThunderbolt, TowerName.THUNDERBOLT);
  }

  /*set in the labels the prices of the towers
   * */
  private void initializeLabel() {
      lblFireArrow.setText(TowerName.FIRE_ARROW.getPrice().toString());
      lblFireBall.setText(TowerName.FIRE_BALL.getPrice().toString());
      lblThunderBolt.setText(TowerName.THUNDERBOLT.getPrice().toString());
  }

  /**
   * If money are enough let the tower's button enable, othewise disable it.
   */
  public final void refreshToweBtn() {
      towers.forEach((k, v) -> {
        if (shop.canBuyTower(v)) {
            enableButton(k);
        } else {
            disableButton(k);
        }
      });
  }

  /**Enable all  buttons and set upgradeBtn disabled. 
   */
  public void setBtnUpgradeOn() {
      towers.forEach((k, v) -> disableButton(k));
      enableButton(btnUpgrade);
  }

  /**Disable all other buttons and set upgradeBtn enable. 
   */
  public final void setBtnUpgradeOff() {
      towers.forEach((k, v) -> enableButton(k));
      disableButton(btnUpgrade);
  }

 
  private void enableButton(final Button btn) {
    btn.setOpacity(EN_OP);
    btn.setText("");
    btn.setDisable(false);
  }

  private void disableButton(final Button btn) {
   btn.setOpacity(DIS_OP);
   btn.setText("Disabled");
   btn.setDisable(true);
  }





}
