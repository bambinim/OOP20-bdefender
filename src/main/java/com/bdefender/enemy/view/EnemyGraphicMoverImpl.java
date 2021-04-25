package com.bdefender.enemy.view;

import com.bdefender.enemy.Enemy;
import com.bdefender.map.Coordinates;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnemyGraphicMoverImpl implements EnemyGraphicMover {

    // private final GraphicsContext gc;
    private final Pane container;
    private final Map<Enemy, ImageView> renderedEnemies = new HashMap<>();
    private final Object lock = new Object();

    public EnemyGraphicMoverImpl(final Pane pane) {
        // Canvas canvas = new Canvas(AppView.DEFAULT_WIDTH,
        // com.bdefender.map.MapView.MAP_HEIGHT);
        // this.gc = canvas.getGraphicsContext2D();
        // pane.getChildren().add(canvas);
        this.container = pane;
    }

    /*
     * @Override public void moveEnemies(final ArrayList<Enemy> enemies) {
     * HashMap<Enemy, Image> enemiesImage =
     * EnemiesViewLoader.getEnemiesImages(enemies); gc.clearRect(0, 0,
     * AppView.DEFAULT_WIDTH, AppView.DEFAULT_HEIGHT); for (Enemy enemy : enemies) {
     * Coordinates enemyPos = new Coordinates(enemy.getPosition().getX() - 1,
     * enemy.getPosition().getY() - 1); gc.drawImage(enemiesImage.get(enemy),
     * enemyPos.getLeftPixel(), enemyPos.getTopPixel()); } }
     */

    public final void moveEnemies(final List<Enemy> enemies) {
        Platform.runLater(() -> {
            synchronized (this.lock) {
                try {
                    for (final var i : enemies) {
                        if (!this.renderedEnemies.keySet().contains(i)) {
                            this.renderedEnemies.put(i, new ImageView(EnemiesViewLoader.getEnemyImage(i)));
                            this.container.getChildren().add(this.renderedEnemies.get(i));
                        }
                        final Coordinates enemyPos = new Coordinates(i.getPosition().getX() - 1, i.getPosition().getY() - 1);
                        this.renderedEnemies.get(i).setX(enemyPos.getLeftPixel());
                        this.renderedEnemies.get(i).setY(enemyPos.getTopPixel());
                    }
                    this.renderedEnemies.forEach((key, val) -> {
                        if (!enemies.contains(key)) {
                            this.renderedEnemies.remove(key);
                            this.container.getChildren().remove(val);
                        }
                    });
                } catch (ConcurrentModificationException e) { }
            }
        });
    }
}
