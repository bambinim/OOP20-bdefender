package com.bdefender.menu;

import java.net.URL;
import java.util.ResourceBundle;
import com.bdefender.map.MapType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable {
    //Countryside default loaded 
    private int selectedMap = MapType.COUNTRYSIDE.getMapNumber(); 
    private final EventHandler<MouseEvent> onPlayClick;
    @FXML
    private Button startPlayBtn;

    @FXML
    private Button tutorialBtn;


    @FXML
    private Label titleLable;

    @FXML
    private ChoiceBox<String> mapChoiceBox;

    public MenuController(final EventHandler<MouseEvent> handler) {
        this.onPlayClick = handler;
    }

    /**
     * Initialize main GUI menu and link play button event passed by costructor, load tutorial 
     * text popup.
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        //Play Action
        startPlayBtn.setOnMouseClicked(this.onPlayClick);
        onMouseOverHandler(this.startPlayBtn, Color.BLACK, Color.BROWN);

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
            this.selectedMap = mapIDbyName(mapChoiceBox.getSelectionModel().getSelectedItem());
            System.out.println("Ora vale = " + this.selectedMap);
        });

        //tutorial action
        this.tutorialBtn.setOnMouseClicked((e) -> this.popup());
        onMouseOverHandler(this.tutorialBtn, Color.BLACK, Color.BROWN);

    }

    private void popup() {
        System.out.println("in popup");
        final Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        final VBox vbox = new VBox(new Text("Hi"), new Button("Ok."));
        vbox.setAlignment(Pos.CENTER);


        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
    }
    /**
     * @return intMapNumber
     */
    public int getSelectedMap() {
        return this.selectedMap;
    }


    private int mapIDbyName(final String mapName) {
        for (final MapType mapType : MapType.values()) {
            if (mapType.getMapName().equals(mapName)) {
                return mapType.getMapNumber();
            }
        }
        return -1;
    }



    /**
     * @param event
     */
    public void playGUIEvent(final ActionEvent event) {
        System.out.println("#playGUIEvent");
    }

    private void onMouseOverHandler(final Button s, final Color defaultColor, final Color hoverColor) {
        s.setOnMouseEntered(e -> s.setTextFill(hoverColor));
        s.setOnMouseExited(e -> s.setTextFill(defaultColor));
    }




}
