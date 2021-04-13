package com.bdefender.menu;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.glass.ui.EventLoop.State;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;

public class MenuController implements Initializable {
    private EventHandler<MouseEvent> onPlayClick;
    @FXML
    private Button startPlayBtn;

    @FXML
    private Button tutorialBtn;

    @FXML
    private MenuButton mapSelectionBtn;

    @FXML
    private Label titleLable;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        startPlayBtn.setOnMouseClicked(this.onPlayClick);

    }

    public MenuController(final EventHandler<MouseEvent> handler) {
        this.onPlayClick = handler;
    }
/**
 * 
 * @param event
 */
    public void playGUIEvent(final ActionEvent event) {
        System.out.println("#playGUIEvent");
    }



}
