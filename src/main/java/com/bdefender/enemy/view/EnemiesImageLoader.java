package com.bdefender.enemy.view;

import com.bdefender.enemy.Enemy;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnemiesImageLoader {

    private static final EnemiesImageLoader INSTANCE;
    private static final int N_ENEMIES = 3;

    static {
        INSTANCE = new EnemiesImageLoader(N_ENEMIES);
    }

    private final List<Image> enemyImages = new ArrayList<>();

    public EnemiesImageLoader(final int nEnemies) {
        for (int i = 0; i < nEnemies; i++) {
            Image enemyImage;
            try {
                enemyImage =
                        new Image(ClassLoader.getSystemResource(String.format("enemies/%d/enemy.png", i)).openStream(),
                                64, 64, false, false);
            } catch (Exception e) {
                enemyImage = null;
            }
            enemyImages.add(enemyImage);
        }
    }

    public static HashMap<Enemy, Image> getEnemiesImages(final List<Enemy> enemies) {
        HashMap<Enemy, Image> enemiesImages = new HashMap<>();
        for (Enemy enemy : enemies) {
            enemiesImages.put(enemy, INSTANCE.enemyImages.get(enemy.getTypeId()));
        }
        return enemiesImages;
    }

    public static Image getEnemyImage(final Enemy enemy) {
        return INSTANCE.enemyImages.get(enemy.getTypeId());
    }
}
