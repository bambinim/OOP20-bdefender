package com.bdefender.menu;



import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public class MainMenuLoader {

    private final Parent contentLoaded;

    public MainMenuLoader(final EventHandler<MouseEvent> playEvent) throws IOException {
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("menu/launchMenu.fxml"));
        loader.setController(new MenuController(playEvent));
        contentLoaded = loader.load();
    }

    /**
     * 
     * @return Parent
     */
    public Parent getParent() {
        return this.contentLoaded;

    }

}
