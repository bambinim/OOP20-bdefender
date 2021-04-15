package com.bdefender.test.shola;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class shopViewController {
    

    private final EventHandler<MouseEvent> buyTower1;
    private final EventHandler<MouseEvent> buyTower2 = null;
    private final EventHandler<MouseEvent> buyTower3 = null;

    @FXML
    private Label lblMoney;

    @FXML
    private Button btnTower1;

    @FXML
    private Button btnTower2;

    @FXML
    private Button btnTower3;

    @FXML
    private Button btnTower4;
    
    public shopViewController(EventHandler<MouseEvent> buyTower1){
        this.buyTower1 = buyTower1;
    }
    public void initialize() {
        btnTower1.setOnMouseClicked(buyTower1);
        System.out.println("hahs");
    }
  

}
