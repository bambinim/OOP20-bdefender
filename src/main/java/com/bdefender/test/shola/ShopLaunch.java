package com.bdefender.test.shola;


import com.bdefender.shop.Shop;
import com.bdefender.shop.ShopImpl;
import com.bdefender.wallet.Wallet;
import com.bdefender.wallet.WalletImpl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShopLaunch extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        final int INITIAL_AMOUNT = 500;
        
        Wallet wlt = new WalletImpl(INITIAL_AMOUNT);
        Shop shop = new ShopImpl(wlt);
        
      
        ShopLoader shopLoader = new ShopLoader((e) -> System.out.println("ssd"));
        final Parent parent = shopLoader.getParent();
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    
    }
    public static void run(final String[] args) {
        launch(args);
    }
    

}
