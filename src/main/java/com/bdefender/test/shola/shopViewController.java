package com.bdefender.test.shola;


import java.util.HashMap;
import java.util.Map;

import com.bdefender.game.TowerName;
import com.bdefender.shop.Shop;
import com.bdefender.wallet.Wallet;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class shopViewController {

    final Double OP_DISABLE = 0.5;
    final Double OP_ENABLE = 0.0;

    private final Shop shop;
    private final EventHandler<MouseEvent> buyTower1;
    private final EventHandler<MouseEvent> buyTower2 = null;
    private final EventHandler<MouseEvent> buyTower3 = null;
    private  TowerName lastPurchase = null;
    private Map<Button, TowerName> btnTower = new HashMap<>();

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
    private Label lblRock;

    @FXML
    private Button btnRock;

    @FXML
    private Label lblThunderBolt;

    @FXML
    private Button btnThunderBolt;
 
    public shopViewController(EventHandler<MouseEvent> buyTowe, Shop shop){
        this.buyTower1 = buyTowe;
        this.shop = shop;

    }

    public final void initialize() {

       initializeButton();
       initializeLabel();
       updMoneyVal();
       btnTower.forEach((k, v) -> k.setOnMouseClicked(e -> {
           buyTower((Button) e.getSource());
       }));

        System.out.println("clicked");
    }

    /*
     * when button is clicked proceed with the purchase of tower
     * */

    private void buyTower(final Button btn) {
        shop.buyTower(btnTower.get(btn));
        updMoneyVal();
        this.lastPurchase = btnTower.get(btn);
    }

   /*
    * useful to keep updated the money value
    * */
    private void updMoneyVal() {
        lblMoney.setText("" + shop.getWallet().getMoney());
    }

    /*
     * set view details for disable button
     * */
  private void disableButton(final Button btn) {
      btn.setDisable(true);
      btn.setOpacity(OP_DISABLE);
      btn.setText("Can't buy");
  }

  /*
   * set graphic details for enabled button
   * */
  private void enableButton(final Button btn) {
   btn.setDisable(false);
   btn.setOpacity(OP_ENABLE);
   btn.setText("");
  }

  /*
   * Fill the map with the button and the towerName it is linked with
   * */
  private void initializeButton() {
      btnTower.put(btnFireArrow, TowerName.FIRE_ARROW);
      btnTower.put(btnFireBall, TowerName.FIRE_BALL);
      btnTower.put(btnRock, TowerName.ROCK);
      btnTower.put(btnThunderBolt, TowerName.THUNDERBOLT);
  }

  /*set in the labels the prices of the towers
   * */
  private void initializeLabel() {
      lblFireArrow.setText("" + TowerName.FIRE_ARROW.getPrice());
      lblFireBall.setText("" + TowerName.FIRE_BALL.getPrice());
      lblRock.setText("" + TowerName.ROCK.getPrice());
      lblThunderBolt.setText("" + TowerName.THUNDERBOLT.getPrice());
  }
  /*
   * @retun the TowerName of the last tower which was bought (last button clicked)
   * */
  public final TowerName lastPurchase() {
      return this.lastPurchase;
  }


}
