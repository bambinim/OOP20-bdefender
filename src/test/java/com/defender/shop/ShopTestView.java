package com.defender.shop;

import java.io.IOException;

import com.bdefender.tower.interactor.EnemyInteractorDirect;
import com.bdefender.tower.interactor.EnemyInteractorDirectImpl;
import com.bdefender.tower.view.TowersImageLoader;
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
import com.bdefender.shop.ShopLoader;
import com.bdefender.shop.ShopLoaderImpl;
import com.bdefender.shop.ShopViewManager;
import com.bdefender.shop.ShopViewManagerImpl;
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerFactory;
import com.bdefender.tower.TowerName;
import com.bdefender.wallet.Wallet;
import com.bdefender.wallet.WalletImpl;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class ShopTestView {
    private ShopLoader shopLoader;
    private Shop shop;
    private ShopViewManager shopViewManager;
    private Wallet wallet;
    private static final int INIT_AMOUNT = 900;
    private static final int HEIGHT = 736;
    private static final int WEIGHT = 183;

    @Start
    private void start(final Stage stage) {
        Platform.runLater(() -> {
            this.wallet = new WalletImpl(INIT_AMOUNT);
            this.shop = new ShopImpl(wallet);
            this.shopViewManager = new ShopViewManagerImpl(this.shop, (e) -> System.out.println("test"));
            try {
                this.shopLoader = new ShopLoaderImpl(this.shopViewManager);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            stage.setWidth(ShopTestView.WEIGHT);
            stage.setHeight(ShopTestView.HEIGHT);
            final Scene scene = new Scene(this.shopLoader.getShopView());
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
        final EnemyInteractorDirect ctrl = new EnemyInteractorDirectImpl(new EnemiesPoolImpl(mapInt));

        robot.clickOn("#btnFireArrow");
        TowerName towerName = this.shopViewManager.getLastTowerClicked().get();
        Tower towerShop = tf.getTowerDirect(towerName, ctrl, new Pair<Double, Double>(0.0, 0.0));
        Tower towerCorrect = tf.getTowerDirect(TowerName.FIRE_ARROW, ctrl, new Pair<Double, Double>(0.0, 0.0));
        Assertions.assertEquals(TowersImageLoader.getTowerImage(towerCorrect), TowersImageLoader.getTowerImage(towerShop));

        robot.clickOn("#btnThunderbolt");
        towerName = this.shopViewManager.getLastTowerClicked().get();
        towerShop = tf.getTowerDirect(towerName, ctrl, new Pair<Double, Double>(0.0, 0.0));
        towerCorrect = tf.getTowerDirect(TowerName.THUNDERBOLT, ctrl, new Pair<Double, Double>(0.0, 0.0));

        Assertions.assertEquals(TowersImageLoader.getTowerImage(towerCorrect), TowersImageLoader.getTowerImage(towerShop));
        robot.clickOn("#btnFireBall");
        towerName = this.shopViewManager.getLastTowerClicked().get();
        towerShop = tf.getTowerDirect(towerName, ctrl, new Pair<Double, Double>(0.0, 0.0));
        towerCorrect = tf.getTowerDirect(TowerName.FIRE_BALL, ctrl, new Pair<Double, Double>(0.0, 0.0));
        Assertions.assertEquals(TowersImageLoader.getTowerImage(towerCorrect), TowersImageLoader.getTowerImage(towerShop));

    }
}
