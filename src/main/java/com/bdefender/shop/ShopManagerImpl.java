package com.bdefender.shop;

import com.bdefender.event.EventHandler;
import com.bdefender.event.MouseEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;




public class ShopManagerImpl implements ShopManager {
    private final ShopController shopController;
    private final Parent shopView;


    public ShopManagerImpl(final Shop shop, final EventHandler<MouseEvent> closeShop) throws IOException {
        this.shopController = new ShopControllerImpl(shop, closeShop);
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("shops/shopView.fxml"));
        loader.setController(this.shopController);
        this.shopView = loader.load();
       // this.shopView.setView(loader.load());
    }

    @Override
    public final Parent getShopView() {
        return this.shopView;
    }

    @Override
    public final ShopController getShopController() {
        return this.shopController;
    }
}

