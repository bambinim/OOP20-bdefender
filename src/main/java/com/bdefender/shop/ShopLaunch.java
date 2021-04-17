package com.bdefender.shop;


import com.bdefender.wallet.Wallet;
import com.bdefender.wallet.WalletImpl;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ShopLaunch extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        final int INITIAL_AMOUNT = 500;

        Wallet wlt = new WalletImpl(INITIAL_AMOUNT);
        Shop shop = new ShopImpl(wlt);
        /*
        ShopLoader shopLoader = new ShopLoader(shop);
        final Parent parent = shopLoader.getParent();
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

         */
    }
    public static void run(final String[] args) {
        launch(args);
    }

}
