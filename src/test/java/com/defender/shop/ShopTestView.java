package com.defender.shop;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import com.bdefender.Pair;
import com.bdefender.enemy.pool.EnemiesPoolImpl;
import com.bdefender.enemy.pool.MapInteractor;
import com.bdefender.enemy.pool.MapInteractorImpl;
import com.bdefender.map.MapLoader;
import com.bdefender.map.MapType;
import com.bdefender.shop.Shop;
import com.bdefender.shop.ShopImpl;
import com.bdefender.shop.ShopManager;
import com.bdefender.shop.ShopManagerImpl;
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerFactory;
import com.bdefender.tower.TowerName;
import com.bdefender.tower.view.TowerImageLoader;
import com.bdefender.wallet.Wallet;
import com.bdefender.wallet.WalletImpl;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class ShopTestView {
    private ShopManager shopmanager;
    private Shop shop;
    private Wallet wallet;
    private static final int INIT_AMOUNT = 900;
    private static final int HEIGHT = 736;
    private static final int WEIGHT = 183;

    @Start
    private void start(final Stage stage) {
        Platform.runLater(() -> {
            try {
                this.wallet = new WalletImpl(INIT_AMOUNT);
                this.shop = new ShopImpl(wallet);
                this.shopmanager = new ShopManagerImpl(shop, (e) -> System.out.println("test"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            stage.setWidth(ShopTestView.WEIGHT);
            stage.setHeight(ShopTestView.HEIGHT);
            final Scene scene = new Scene(this.shopmanager.getShopView());
            stage.setScene(scene);
            stage.show();
        });
    }

    /**
     * check if the button of the shop call the right tower.
     * @param robot
     */
    @Test
    public void testTowerIsCorrect(final FxRobot robot) {

        final TowerFactory tf = new TowerFactory();
        final MapInteractor mapInt = new MapInteractorImpl(MapLoader.getInstance().loadMap(MapType.COUNTRYSIDE));
        final EnemiesPoolImpl pool = new EnemiesPoolImpl(mapInt);

        robot.clickOn("#btnFireArrow");
        TowerName towerName = this.shopmanager.getShopController().getLastTowerClicked().get();
        Tower towerShop = tf.getTowerDirect(towerName, pool, new Pair<Double, Double>(0.0, 0.0));
        Tower towerCorrect = tf.getTowerDirect(TowerName.FIRE_ARROW, pool, new Pair<Double, Double>(0.0, 0.0));
        Assertions.assertEquals(TowerImageLoader.getTowerImage(towerCorrect), TowerImageLoader.getTowerImage(towerShop));

        robot.clickOn("#btnThunderbolt");
        towerName = this.shopmanager.getShopController().getLastTowerClicked().get();
        towerShop = tf.getTowerDirect(towerName, pool, new Pair<Double, Double>(0.0, 0.0));
        towerCorrect = tf.getTowerDirect(TowerName.THUNDERBOLT, pool, new Pair<Double, Double>(0.0, 0.0));

        Assertions.assertEquals(TowerImageLoader.getTowerImage(towerCorrect), TowerImageLoader.getTowerImage(towerShop));
        robot.clickOn("#btnFireBall");
        towerName = this.shopmanager.getShopController().getLastTowerClicked().get();
        towerShop = tf.getTowerDirect(towerName, pool, new Pair<Double, Double>(0.0, 0.0));
        towerCorrect = tf.getTowerDirect(TowerName.FIRE_BALL, pool, new Pair<Double, Double>(0.0, 0.0));
        Assertions.assertEquals(TowerImageLoader.getTowerImage(towerCorrect), TowerImageLoader.getTowerImage(towerShop));

    }
}
