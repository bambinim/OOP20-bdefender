package com.bdefender.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import com.bdefender.map.MapType;
import com.bdefender.event.EventHandler;
import com.bdefender.event.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuControllerImpl implements Initializable, MenuController {
    //Countryside default loaded 
    private MapType selectedMap = MapType.COUNTRYSIDE; 
    private final EventHandler<MouseEvent> onPlayClick;
    @FXML
    private Button startPlayBtn;

    @FXML
    private Button tutorialBtn;


    @FXML
    private Label titleLable;

    @FXML
    private ChoiceBox<String> mapChoiceBox;

    public MenuControllerImpl(final EventHandler<MouseEvent> playEvent) {
        this.onPlayClick = playEvent;
    }

    /**
     * Initialize main GUI menu and link play button event passed by costructor, load tutorial 
     * text popup.
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        //Play Action
        startPlayBtn.setOnMouseClicked((event) -> 
            this.onPlayClick.handle(new MouseEvent(MouseEvent.MOUSE_CLICKED, event.getSource())));
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
            this.selectedMap = getMapByName(mapChoiceBox.getSelectionModel().getSelectedItem());
            System.out.println("Ora vale = " + this.selectedMap);
        });

        //tutorial action
        this.tutorialBtn.setOnMouseClicked((e) -> this.popup());
        onMouseOverHandler(this.tutorialBtn, Color.BLACK, Color.BROWN);

    }

    private void popup() {
        final URL tutorialFile = ClassLoader.getSystemResource("menu/gameTutorial.txt");
        final Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        //text area
        final TextArea text = new TextArea();
        text.setEditable(false);
        text.setWrapText(true);
        //load game tutorial text
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(tutorialFile.openStream()));
            String line = reader.readLine();
            while (line != null) {
                text.appendText(line + "\n");
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Button coloseModal
        final Button closeModal = new Button("Capito!");
        closeModal.setOnMouseClicked((e) -> dialogStage.close());
        final VBox vbox = new VBox(text, closeModal);
        vbox.setAlignment(Pos.CENTER);


        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
    }

    /**
     * @return intMapNumber
     */
    @Override
    public MapType getSelectedMap() {
        return this.selectedMap;
    }


    private MapType getMapByName(final String mapName) {
        for (final MapType mapType : MapType.values()) {
            if (mapType.getMapName().equals(mapName)) {
                return mapType;
            }
        }
        return MapType.COUNTRYSIDE;
    }


    private void onMouseOverHandler(final Button s, final Color defaultColor, final Color hoverColor) {
        s.setOnMouseEntered(e -> s.setTextFill(hoverColor));
        s.setOnMouseExited(e -> s.setTextFill(defaultColor));
    }




}
