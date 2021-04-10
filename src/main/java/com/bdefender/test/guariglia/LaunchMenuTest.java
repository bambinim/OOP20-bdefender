package com.bdefender.test.guariglia;


import java.awt.Dimension;    
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class LaunchMenuTest extends Application {
    private static final  int SCREEN_RESIZE = 2;
   // private final static String PATH_SEPARATOR = System.getProperty("file.separator");
    /**
     * 
     */
    @Override
    public void start(final Stage primaryStage) {
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) (dimension.getWidth() / SCREEN_RESIZE);
        final int height = (int) (dimension.getHeight() / SCREEN_RESIZE);
         Parent rootPane = null;
        try {
           rootPane = FXMLLoader.load(getClass().getResource("launchMenu.fxml"));
        } catch (IOException e) {

            e.printStackTrace();
        }
        primaryStage.setTitle("Prova");
        final Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void run(final String[] args) {
        launch(args);
    }

}
