package com.bdefender.shop;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;




public class ShopLoaderImpl implements ShopLoader {
        private final Parent shopView;


    public ShopLoaderImpl(final ShopViewManager shopViewManager) throws IOException {
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("shops/shopView.fxml"));
        loader.setController(shopViewManager);
        this.shopView = loader.load();
    }

    @Override
    public final Parent getShopView() {
        return this.shopView;
    }

}

