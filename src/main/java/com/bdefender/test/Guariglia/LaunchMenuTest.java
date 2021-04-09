package com.bdefender.test.Guariglia;


import java.awt.Dimension; 
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import com.bdefender.enemies.view.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class LaunchMenuTest extends Application {
    private final static int SCREEN_RESIZE = 3;
    private final static String PATH_SEPARATOR = System.getProperty("file.separator");
    @Override
    public void start(Stage primaryStage) {
       
        Parent root = null;
        try {
           root = FXMLLoader.load(getClass().getResource("launchMenu.fxml"));
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        System.out.println(root.toString());
        primaryStage.setTitle("Prova");
        Scene scenaDiProva = new Scene(root);
        primaryStage.setScene(scenaDiProva);
        primaryStage.show();
        
    }
    
    public static void run(String[] args) {
        launch(args);
    }

}
