package com.bdefender.test.guariglia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;

public class LauchMenuController{

    @FXML
    private Button startPlayBtn;

    @FXML
    private Button tutorialBtn;

    @FXML
    private MenuButton mapSelectionBtn;

    @FXML
    private Label titleLable;

    @FXML
    void play(ActionEvent event) {
        System.out.println("Prova");
    }

}
