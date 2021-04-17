package com.bdefender.test.map;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import com.bdefender.map.MapLoader;
import com.bdefender.map.MapType;
import com.bdefender.map.MapView;
import com.bdefender.map.Map;

import java.util.ArrayList;
import java.util.List;

public class MapTest extends Application {
    private static final int TOWER_AREA_SIZE = 60;
    private static final int DEFAULT_IMG_HEIGHT = 760;
    private static final int DEFAULT_IMG_WIDTH = 1280;

    private PathTransition createTransition(final Node node, final Path path) {
        final PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.seconds(10));
        pathTransition.setNode(node);
        pathTransition.setAutoReverse(false);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        return pathTransition;
    }

    private Path createPath(final Map map) {
        final Path path = new Path();
        path.getElements().add(new MoveTo(map.getPath().get(0).getLeftPixel(), map.getPath().get(0).getTopPixel()));
        for (int i = 0; i < map.getPath().size(); i++) {
            path.getElements().add(new LineTo(map.getPath().get(i).getLeftPixel(), map.getPath().get(i).getTopPixel()));
        }
        return path;
    }

    private Circle createCircle(final Map map) {
        final Circle circle = new Circle();
        circle.setRadius(10.0);
        circle.setCenterX(map.getPath().get(0).getX() * 32);
        circle.setCenterY(map.getPath().get(0).getY() * 32);
        return circle;
    }

    private GridPane createStageLayout(final Stage stage) {
        // -- GRID PANE --
        final GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        // -- ANCHOR PANE --
        AnchorPane.setTopAnchor(gridPane, 0.0);
        AnchorPane.setBottomAnchor(gridPane, 0.0);
        AnchorPane.setLeftAnchor(gridPane, 0.0);
        AnchorPane.setRightAnchor(gridPane, 0.0);
        // -- SCENE AND STAGE --
        final Scene scene = new Scene(gridPane);
        stage.setWidth(DEFAULT_IMG_WIDTH);
        stage.setHeight(DEFAULT_IMG_HEIGHT);
        stage.setTitle("Map");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            gridPane.setScaleX(stage.getWidth() / DEFAULT_IMG_WIDTH);
        });
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            gridPane.setScaleY(stage.getHeight() / DEFAULT_IMG_HEIGHT);
        });
        return gridPane;
    }

    private List<Rectangle> createTowerPositioningGrid(final Map map) {
        final double rectangleOpacity = 0.7;
        final List<Rectangle> res = new ArrayList<>();
        map.getTowerBoxes().forEach((el) -> {
            final Rectangle rec = new Rectangle();
            rec.setWidth(TOWER_AREA_SIZE);
            rec.setHeight(TOWER_AREA_SIZE);
            rec.setX(el.getTopLeftCoord().getLeftPixel() + 2);
            rec.setY(el.getTopLeftCoord().getTopPixel() + 2);
            rec.setFill(Color.GRAY);
            rec.setOnMouseEntered(e -> rec.setFill(Color.GREEN));
            rec.setOnMouseExited(e -> rec.setFill(Color.GRAY));
            rec.setCursor(Cursor.HAND);
            rec.opacityProperty().setValue(rectangleOpacity);
            res.add(rec);
        });
        return res;
    }

    /**
     * load a test map with an animated circle on path.
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final GridPane gridPane = this.createStageLayout(primaryStage);
        final Map map = MapLoader.getInstance().loadMap(MapType.ICEPLAIN);
        final MapView root = new MapView(map);
        final Circle circle = this.createCircle(map);
        final Path path = createPath(map);
        final PathTransition pathTransition = this.createTransition(circle, path);
        final Rectangle rec = new Rectangle();
        gridPane.getChildren().add(root);
        root.getChildren().addAll(circle, rec);
        root.getChildren().addAll(this.createTowerPositioningGrid(map));
        pathTransition.play();
    }

    public static void run(final String[] args) {
        Application.launch(args);
    }
}
