package com.bdefender.test.guariglia;

import javafx.application.Application;
import javafx.scene.Parent;
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
        final Parent parent = mainMenuLoader.getParent();
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }

    private void playAction() {
        System.out.println("Play click");
    }

    public static void run(final String[] args) {
        launch(args);
    }


}
