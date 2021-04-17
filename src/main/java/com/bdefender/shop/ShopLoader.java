package com.bdefender.shop;

import com.bdefender.game.TowerName;
import com.bdefender.wallet.Wallet;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



public class ShopLoader {
    private final ShopView shopViewController;
    private final Parent contentLoaded;
    private final Shop shop;
    public ShopLoader(final Wallet wallet) throws IOException {
        this.shop = new ShopImpl(wallet);
        this.shopViewController = new ShopViewController(shop);
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
