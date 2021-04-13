package com.bdefender.menu;

import java.net.URL;
import java.util.ResourceBundle;
import com.bdefender.map.MapType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MenuController implements Initializable {
    //Countryside default loaded 
    private int mapSelected = MapType.COUNTRYSIDE.getMapNumber(); 
    private final EventHandler<MouseEvent> onPlayClick;
    @FXML
    private Button startPlayBtn;

    @FXML
    private Button tutorialBtn;


    @FXML
    private Label titleLable;

    @FXML
    private ChoiceBox<String> mapChoiceBox;

    /**
     * Initialize main GUI menu and link play button event passed by costructor.
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        //Play Action
        startPlayBtn.setOnMouseClicked(this.onPlayClick);

        //choiceBox
        boolean firstIteration = true;
        //load all map name in the choiceBox 
        for (final MapType mapType : MapType.values()) {
            if (firstIteration) {
                mapChoiceBox.setValue(mapType.getMapName());
                firstIteration = false;
            }
            mapChoiceBox.getItems().add(mapType.getMapName());
        }

        mapChoiceBox.setOnAction((event) -> {
            this.mapSelected = mapIDbyName(mapChoiceBox.getSelectionModel().getSelectedItem());
            System.out.println("Ora vale = " + this.mapSelected);
        });

    }

    private int mapIDbyName(final String mapName) {
        for (final MapType mapType : MapType.values()) {
            if (mapType.getMapName().equals(mapName)) {
                return mapType.getMapNumber();
            }
        }
        return -1;
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
