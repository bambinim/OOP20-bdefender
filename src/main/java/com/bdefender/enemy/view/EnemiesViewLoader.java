package com.bdefender.enemy.view;

import com.bdefender.enemy.Enemy;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class EnemiesViewLoader {

    private static final EnemiesViewLoader Instance;
    private static final int N_ENEMIES = 2;

    static {
        Instance = new EnemiesViewLoader(N_ENEMIES);
    }

    ArrayList<Optional<Image>> enemyImages = new ArrayList<>();

    public EnemiesViewLoader(int nEnemies) {
        for (int i = 0; i < nEnemies; i++) {
            Optional<Image> enemyImage;
            try {
                enemyImage = Optional.of(
                        new Image(ClassLoader.getSystemResource(String.format("enemies/%d/enemy.png", i)).openStream(),
                                64, 64, false, false));
            } catch (Exception e) {
                enemyImage = Optional.empty();
            }
            enemyImages.add(enemyImage);
        }
    }

    public static HashMap<Enemy, Optional<Image>> GetEnemiesImages(List<Enemy> enemies) {
        HashMap<Enemy, Optional<Image>> enemiesImages = new HashMap<>();
        for (Enemy enemy : enemies) {
            enemiesImages.put(enemy, Instance.enemyImages.get(enemy.getTypeId()));
        }
        return enemiesImages;
    }
}
