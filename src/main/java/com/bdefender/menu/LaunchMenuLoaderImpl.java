package com.bdefender.menu;



import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public class LaunchMenuLoaderImpl implements LaunchMenuLoader {
    private final MenuController menuController;
    private final Parent contentLoaded;

    public LaunchMenuLoaderImpl(final EventHandler<MouseEvent> playEvent) throws IOException {
        menuController = new MenuControllerImpl(playEvent);
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("menu/launchMenu.fxml"));
        loader.setController(this.menuController);
        contentLoaded = loader.load();
    }

    /**
     * Get the controller of the loaded GUI.
     * @return menuController
     */
    public MenuController getController() {
        return this.menuController;
    }

    /**
     * return the FXML loaded in an AnchorPane.
     * @return Parent
     */
    public Parent getParent() {
        return this.contentLoaded;

    }

}
