package com.bdefender.test.guariglia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.bdefender.menu.*;

public class TestMenu extends Application {

    /**
     * 
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final MainMenuLoader mainMenuLoader = new MainMenuLoader((e) -> this.playAction());
        final Scene scene = mainMenuLoader.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void playAction() {
        System.out.println("Play click");
    }

    public static void run(final String[] args) {
        launch(args);
    }


}
