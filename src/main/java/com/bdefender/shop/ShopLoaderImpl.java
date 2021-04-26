package com.bdefender.shop;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;




public class ShopLoaderImpl implements ShopLoader {
    private final ShopViewManager shopViewManager;
    private final Parent shopView;


    public ShopLoaderImpl(final ShopViewManager shopViewManager) throws IOException {
        this.shopViewManager = shopViewManager;
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("shops/shopView.fxml"));
        loader.setController(this.shopViewManager);
        this.shopView = loader.load();
       // this.shopView.setView(loader.load());
    }

    @Override
    public final Parent getShopView() {
        return this.shopView;
    }

    @Override
    public final ShopViewManager getShopViewManager() {
        return this.shopViewManager;
    }
}

