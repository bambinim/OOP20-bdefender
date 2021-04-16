package com.bdefender.test.shola;

import com.bdefender.menu.MenuController;
import com.bdefender.shop.Shop;
import com.bdefender.wallet.Wallet;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;


public class ShopLoader {
    private final shopViewController shopViewController;
    private final Parent contentLoaded;
    public ShopLoader(final EventHandler<MouseEvent> buyTower1, Shop shop) throws IOException {
        this.shopViewController = new shopViewController(buyTower1, shop);
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("shops/shopView.fxml"));
        loader.setController(this.shopViewController);
        contentLoaded = loader.load();
    }
    public final Parent getParent() {
        return this.contentLoaded;
    }
}
