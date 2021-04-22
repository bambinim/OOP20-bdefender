package com.bdefender.shop;

import com.bdefender.event.EventHandler;
import com.bdefender.event.MouseEvent;
import com.bdefender.game.TowerName;
import com.bdefender.wallet.Wallet;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;




public class ShopManager {
    private final ShopController shopController;
    private final Parent contentLoaded;
    private final Shop shop;
    public ShopManager(final Shop shop, final EventHandler<MouseEvent> closeShop) throws IOException {
        this.shop = new ShopImpl(shop.getWallet());
        this.shopController = new ShopControllerImpl(shop, closeShop);
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("shops/shopView.fxml"));
        loader.setController(this.shopController);
        contentLoaded = loader.load();
    }
    public final Parent getShopView() {
        return this.contentLoaded;
    }

    public final ShopController getShopController(){
        return this.shopController;
    }
}
