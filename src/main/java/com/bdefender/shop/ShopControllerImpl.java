package com.bdefender.shop;


import java.util.HashMap;
import java.util.Map;

import com.bdefender.game.TowerName;
import com.bdefender.tower.Tower;
import com.bdefender.wallet.Wallet;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ShopControllerImpl implements ShopController {


    private final Shop shop;
    private Map<Button, TowerName> towers = new HashMap<>();
    private TowerName lastTower = null;
    private Tower towerToUpg = null;
    private EventHandler<MouseEvent> closeShop;
    
    private final Double DIS_OP = 0.5;
    private final Double EN_OP = 0.0;
    @FXML
    private Label lblMoney;

    @FXML
    private Label lblFireArrow;

    @FXML
    private Button btnFireArrow;

    @FXML
    private Label lblFireBall;

    @FXML
    private Button btnFireBall;

    @FXML
    private Label lblThunderbolt;

    @FXML
    private Button btnThunderbolt;

    @FXML
    private Label lblUpgrade;

    @FXML
    private Button btnUpgrade;

 
    public ShopControllerImpl(final Shop shop, EventHandler<MouseEvent> closeShop) {
        this.shop = shop;
        this.closeShop = closeShop;
    }

    public final void initialize() {

       initializeButton();
       initializeLabel();
       updMoneyVal();
       this.setBtnUpgradeOff();
       towers.forEach((k, v) -> {
           k.setOnMousePressed((e) -> {
           buyTower(((Button) e.getSource()));
             closeShop.handle(e);
             });
       });
       btnUpgrade.setOnMouseClicked((e) -> this.buyUpgrade());

    }

    /*
     * when button is clicked proceed with the purchase of tower
     * */

    private void buyTower(final Button btn) {
        shop.buyTower(towers.get(btn));
        updMoneyVal();
        this.lastTower = towers.get(btn);
    }
    /** when the button upgrade is clicked prooced subctract the money.
     * @param the tower need to be updated
     */
    private void buyUpgrade() {
        shop.buyUpgrade(this.towerToUpg);
    }

   /*
    * useful to keep updated the money value
    * */
    private void updMoneyVal() {
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
     System.out.println("costo");
      lblFireBall.setText(TowerName.FIRE_BALL.getPrice().toString());
      lblThunderbolt.setText(TowerName.THUNDERBOLT.getPrice().toString());
  }

  /**Enable all other buttons and set upgradeBtn disabled. 
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


  public final TowerName getLastTower() {
      return this.lastTower;
  }

  public void setTowerToUpg(Tower tower) {
    this.towerToUpg = tower;
}


}
