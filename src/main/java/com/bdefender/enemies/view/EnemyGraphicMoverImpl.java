package com.bdefender.enemies.view;

import com.bdefender.enemies.EnemyBase;
import com.bdefender.map.Coordinates;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class EnemyGraphicMoverImpl implements EnemyGraphicMover {

    private GraphicsContext gc;

    public EnemyGraphicMoverImpl(AnchorPane pane) {
        Canvas canvas = new Canvas(1280,1280);
        this.gc = canvas.getGraphicsContext2D();
        pane.getChildren().add(canvas);
    }

    @Override
    public void moveEnemies(ArrayList<EnemyBase> enemies) {
        HashMap<EnemyBase, Optional<Image>> enemiesImage = EnemiesViewLoader.GetEnemiesImages(enemies);
        gc.clearRect(0, 0, 1280,1280);
        for(EnemyBase enemy : enemies){
            Coordinates enemyPos = new Coordinates(enemy.getPosition().getX() - 1, enemy.getPosition().getY() - 1);
            if(enemiesImage.get(enemy).isPresent()) {
                gc.drawImage(enemiesImage.get(enemy).get(), enemyPos.getLeftPixel(), enemyPos.getTopPixel());
            }
        }
    }
}
