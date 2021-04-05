package com.bdefender.test.map;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import com.bdefender.map.*;

import java.util.ArrayList;
import java.util.List;

public class MapTest extends Application {

    private Path createTransition(Map map, Node node) {
        Path path = new Path();
        path.getElements().add(new MoveTo(map.getPath().get(0).getLeftPixel(), map.getPath().get(0).getTopPixel()));
        for (int i = 0; i < map.getPath().size(); i++) {
            path.getElements().add(new LineTo(map.getPath().get(i).getLeftPixel(), map.getPath().get(i).getTopPixel()));
        }
        PathTransition transition = new PathTransition();
        transition.setPath(path);
        transition.setDuration(Duration.seconds(10));
        transition.setNode(node);
        transition.setAutoReverse(false);
        transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transition.setCycleCount(1);
        transition.play();
        return path;
    }

    private Circle createCircle(Map map) {
        Circle circle = new Circle();
        circle.setRadius(10.0);
        circle.setCenterX(map.getPath().get(0).getX() * 32);
        circle.setCenterY(map.getPath().get(0).getY() * 32);
        return circle;
    }

    private AnchorPane createStageLayout(Stage stage) {
        // -- GRID PANE --
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        // -- ANCHOR PANE --
        AnchorPane root = new AnchorPane();
        AnchorPane.setTopAnchor(gridPane, 0.0);
        AnchorPane.setBottomAnchor(gridPane, 0.0);
        AnchorPane.setLeftAnchor(gridPane, 0.0);
        AnchorPane.setRightAnchor(gridPane, 0.0);
        // -- SCENE AND STAGE --
        gridPane.add(root, 0, 0);
        Scene scene = new Scene(gridPane);
        stage.setWidth(1280);
        stage.setHeight(760);
        stage.setTitle("Map");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            gridPane.setScaleX(stage.getWidth() / 1280);
            //primaryStage.setHeight(primaryStage.getWidth() / 1.73);
        });
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            gridPane.setScaleY(stage.getHeight() / 760);
            //primaryStage.setWidth(primaryStage.getHeight() * 1.73);
        });
        return root;
    }

    private List<Rectangle> createTowerPositioningGrid(Map map) {
        List<Rectangle> res = new ArrayList<Rectangle>();
        map.getTowerBoxes().forEach((el) -> {
            Rectangle rec = new Rectangle();
            rec.setWidth(60);
            rec.setHeight(60);
            rec.setX(el.getTopLeftCoord().getLeftPixel() + 2);
            rec.setY(el.getTopLeftCoord().getTopPixel() + 2);
            rec.setFill(Color.GRAY);
            rec.setOnMouseEntered(e -> rec.setFill(Color.GREEN));
            rec.setOnMouseExited(e -> rec.setFill(Color.GRAY));
            rec.setCursor(Cursor.HAND);
            rec.opacityProperty().setValue(0.7);
            res.add(rec);
        });
        return res;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = this.createStageLayout(primaryStage);
        Map map = MapLoader.getInstance().loadMap(Map.COUNTRYSIDE);
        Circle circle = this.createCircle(map);
        Path path = this.createTransition(map, circle);
        Rectangle rec = new Rectangle();
        root.getChildren().addAll(map, circle, rec);
        root.getChildren().addAll(this.createTowerPositioningGrid(map));
    }

    public static void run(String[] args) {
        Application.launch(args);
    }
}
