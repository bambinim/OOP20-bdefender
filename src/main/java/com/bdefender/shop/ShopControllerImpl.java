package com.bdefender.shop;


import java.util.HashMap;
import java.util.Map;

import com.bdefender.game.TowerName;
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
    private EventHandler<MouseEvent> closeShop;
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
       towers.forEach((k, v) -> {
           k.setOnMousePressed((e) -> {
           buyTower(((Button) e.getSource()));
             closeShop.handle(e);
             });
       });

    }

    /*
     * when button is clicked proceed with the purchase of tower
     * */

    private void buyTower(final Button btn) {
        shop.buyTower(towers.get(btn));
        updMoneyVal();
        this.lastTower = towers.get(btn);
    
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
      lblFireBall.setText(TowerName.FIRE_BALL.getPrice().toString());
      lblThunderbolt.setText(TowerName.THUNDERBOLT.getPrice().toString());
  }

  public final TowerName getLastTower() {
      return this.lastTower;
  }



}
