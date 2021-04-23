package com.bdefender.enemy.view;

import com.bdefender.AppView;
import com.bdefender.enemy.Enemy;
import com.bdefender.map.Coordinates;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class EnemyGraphicMoverImpl implements EnemyGraphicMover {

    private final GraphicsContext gc;

    public EnemyGraphicMoverImpl(final AnchorPane pane) {
        Canvas canvas = new Canvas(AppView.DEFAULT_WIDTH, com.bdefender.map.MapView.MAP_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        pane.getChildren().add(canvas);
    }

    @Override
    public void moveEnemies(final ArrayList<Enemy> enemies) {
        HashMap<Enemy, Optional<Image>> enemiesImage = EnemiesViewLoader.getEnemiesImages(enemies);
        gc.clearRect(0, 0, AppView.DEFAULT_WIDTH, AppView.DEFAULT_HEIGHT);
        for (Enemy enemy : enemies) {
            Coordinates enemyPos = new Coordinates(enemy.getPosition().getX() - 1, enemy.getPosition().getY() - 1);
            if (enemiesImage.get(enemy).isPresent()) {
                gc.drawImage(enemiesImage.get(enemy).get(), enemyPos.getLeftPixel(), enemyPos.getTopPixel());
            }
        }
    }
}
