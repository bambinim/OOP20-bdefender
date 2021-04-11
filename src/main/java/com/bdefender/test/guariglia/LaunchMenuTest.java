package com.bdefender.test.guariglia;


import java.awt.Dimension;    
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;



public class LaunchMenuTest extends Application {
    private static final  int SCREEN_RESIZE = 2;
   // private final static String PATH_SEPARATOR = System.getProperty("file.separator");
    /**
     * @throws IOException 
     * 
     */
    @Override
    public void start(final Stage primaryStage) throws IOException {
        final AnchorPane anchorPane = new AnchorPane();
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) (dimension.getWidth() / SCREEN_RESIZE);
        final int height = (int) (dimension.getHeight() / SCREEN_RESIZE);

        final Pane rootPane = FXMLLoader.load(ClassLoader.getSystemResource("menu/launchMenu.fxml"));
        
        rootPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        anchorPane.setTopAnchor(rootPane, 0.0);
        anchorPane.setBottomAnchor(rootPane, 0.0);
        anchorPane.setLeftAnchor(rootPane, 0.0);
        anchorPane.setRightAnchor(rootPane, 0.0);

        anchorPane.getChildren().add(rootPane);
        primaryStage.setTitle("Prova");
        final Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.show();




    }

    public static void run(final String[] args) {
        launch(args);
    }

}
