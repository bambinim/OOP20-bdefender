package com.bdefender.menu;



import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainMenuLoader {
    private Scene scene;
    private MenuController menuController;

    public MainMenuLoader(final EventHandler<MouseEvent> playEvent) throws IOException {
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("menu/launchMenu.fxml"));
        menuController = new MenuController(playEvent);
        loader.setController(menuController);

       // final Pane rootPane = loader.load(ClassLoader.getSystemResource("menu/launchMenu.fxml"));
        
        this.scene = new Scene(loader.load());
    }

    /**
     * 
     * @return scene
     */
    public Scene getScene() {
        return this.scene;

    }

}
