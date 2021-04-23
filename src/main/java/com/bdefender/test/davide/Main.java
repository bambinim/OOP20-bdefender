
package com.bdefender.test.davide;

import com.bdefender.enemy.view.EnemyGraphicMoverImpl;
import com.bdefender.game.*;
import com.bdefender.map.Coordinates;
import com.bdefender.map.Map;
import com.bdefender.map.MapLoader;
import com.bdefender.map.MapType;
import com.bdefender.tower.view.TowerViewImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Map map = MapLoader.getInstance().loadMap(MapType.COUNTRYSIDE);
        primaryStage.setTitle("Map");
        AnchorPane root = new AnchorPane();
        root.setMaxWidth(1280);
        root.setMaxHeight(736);
        root.getChildren().addAll(new ImageView(map.getMapImage()));
        primaryStage.setResizable(true);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        EnemiesController enemiesController = new EnemiesControllerImpl(map, new EnemyGraphicMoverImpl(root));

        TowersController ctrl = new TowersControllerImpl(t -> new TowerViewImpl(root, t),
                enemiesController.getEnemiesPool());
        enemiesController.startGenerate(20, 200, e -> {
            System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        }, event -> {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        });
        ctrl.addTower(TowerName.THUNDERBOLT, new Coordinates(10.0, 8.0));

        ctrl.addTower(TowerName.FIRE_ARROW, new Coordinates(18.0, 8.0));

        ctrl.addTower(TowerName.FIRE_ARROW, new Coordinates(18.0, 8.0));

        ctrl.addTower(TowerName.FIRE_ARROW, new Coordinates(18.0, 8.0));

        ctrl.addTower(TowerName.FIRE_ARROW, new Coordinates(18.0, 8.0));

        ctrl.addTower(TowerName.FIRE_ARROW, new Coordinates(18.0, 8.0));

    }
}
