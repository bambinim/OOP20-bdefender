package com.bdefender.shop;

import com.bdefender.game.TowerName;
import com.bdefender.wallet.Wallet;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;



public class ShopLoader {
    private final ShopView shopViewController;
    private final Parent contentLoaded;
    private final Shop shop;
    public ShopLoader(final Shop shop, final EventHandler<MouseEvent> closeShop) throws IOException {
        this.shop = new ShopImpl(shop.getWallet());
        this.shopViewController = new ShopViewController(shop, closeShop);
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("shops/shopView.fxml"));
        loader.setController(this.shopViewController);
        contentLoaded = loader.load();
    }
    public final Parent getParent() {
        return this.contentLoaded;
    }

    public final ShopView getShopView(){
        return this.shopViewController;
    }
}
