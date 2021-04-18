package com.bdefender.game;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import com.bdefender.component.ImageButton;
import com.bdefender.map.MapView;
import com.bdefender.shop.ShopManager;

public class GameView extends AnchorPane {

    private final TopMenuView topMenuView;
    private final Parent shopView;
    
    

    public GameView(final MapView mapView, final Parent shopView) {
        this.topMenuView = new TopMenuView();
        this.shopView = shopView;
        mapView.setLayoutY(TopMenuView.HEIGHT);
        this.getChildren().addAll(mapView, this.shopView, this.topMenuView);
       
    }

    /**
     * @return top menu view
     */
    public TopMenuView getTopMenuView() {
        return this.topMenuView;
    }

   
    public void setActionTopM(EventHandler<MouseEvent> openShop, EventHandler<MouseEvent> backMenu) {
        ImageButton btnShop = this.topMenuView.getShopButton();
        ImageButton btnExit = this.topMenuView.getExitButton();
        btnShop.setOnMouseClick(openShop);
        btnExit.setOnMouseClick(backMenu);
        
    }
    
}
