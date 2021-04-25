package com.bdefender.shop;

import com.bdefender.event.EventHandler;
import com.bdefender.event.MouseEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;




public class ShopLoaderImpl implements ShopLoader {
    private final ShopViewManager shopController;
    private final Parent shopView;


    public ShopLoaderImpl(final Shop shop, final EventHandler<MouseEvent> closeShop) throws IOException {
        this.shopController = new ShopViewManagerImpl(shop, closeShop);
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
    public final ShopViewManager getShopViewManager() {
        return this.shopController;
    }
}

