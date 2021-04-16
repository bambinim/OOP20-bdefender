package com.bdefender.shop;

import com.bdefender.game.TowerName;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



public class ShopLoader {
    private final ShopView shopViewController;
    private final Parent contentLoaded;
    private TowerName lastTower;
    public ShopLoader(Shop shop) throws IOException {
        this.shopViewController = new ShopViewController(shop);
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("shops/shopView.fxml"));
        loader.setController(this.shopViewController);
        contentLoaded = loader.load();
    }
    public final Parent getParent() {
        return this.contentLoaded;
    }
     public final TowerName getLastTwr() {
         return this.lastTower;
     }
    public final void setLastTwr(final TowerName lastTwr) {
        this.lastTower = lastTwr;
        System.out.println(this.lastTower.getName().toString());
    }
}
