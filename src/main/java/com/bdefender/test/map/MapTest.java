package com.bdefender.test.map;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;
import javafx.animation.PathTransition;
import javafx.scene.shape.Path;
import com.bdefender.map.*;

public class MapTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Map");
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        //Pane root = new Pane();
        root.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        //root.setPrefWidth(1280);
       //root.setPrefHeight(736);
        

        
        Map map = MapLoader.getInstance().loadMap(Map.COUNTRYSIDE);
        Circle circle = new Circle();
        circle.setRadius(10.0);
        circle.setCenterX(map.getPath().get(0).getX() * 32);
        circle.setCenterY(map.getPath().get(0).getY() * 32);
        // CREATE TRANSITION
        Path path = new Path();
        path.getElements().add(new MoveTo(map.getPath().get(0).getLeftPixel(), map.getPath().get(0).getTopPixel()));
        for (int i = 0; i < map.getPath().size(); i++) {
            path.getElements().add(new LineTo(map.getPath().get(i).getLeftPixel(), map.getPath().get(i).getTopPixel()));
        }
        PathTransition transition = new PathTransition();
        transition.setPath(path);
        transition.setDuration(Duration.seconds(10));
        transition.setNode(circle);
        transition.setAutoReverse(false);
        transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transition.setCycleCount(1);
        transition.play();
        // END CREATE TRANSITION
        root.getChildren().addAll(map, path, circle);
        primaryStage.setResizable(true);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            Double windowW = primaryStage.getWidth();
            System.out.println("WindowW = " + windowW);
            System.out.println("root.getScaleX = " + root.getScaleX());
            root.setScaleX(windowW/1280);
            System.out.println(1 + ( map.getScaleX()/windowW));
            
            
       });

       primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
    	   Double windowH = primaryStage.getHeight();
    	   root.setScaleY(windowH/736);
       });
        
    }

    public static void run(String[] args) {
        Application.launch(args);
    }
}
