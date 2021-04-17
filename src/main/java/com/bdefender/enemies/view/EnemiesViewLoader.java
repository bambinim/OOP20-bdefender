package com.bdefender.enemies.view;

import com.bdefender.enemies.Enemy;
import javafx.scene.image.Image;

import java.util.*;

public class EnemiesViewLoader {

    ArrayList<Optional<Image>> enemyImages = new ArrayList<>();
    private static EnemiesViewLoader Instance;
    private static int N_ENEMIES = 2;

    static {
        Instance = new EnemiesViewLoader(N_ENEMIES);
    }

    public EnemiesViewLoader(int nEnemies){
        for (int i =0; i<nEnemies; i++){
            Optional<Image> enemyImage;
            try {
                enemyImage =  Optional.of(new Image(ClassLoader.getSystemResource(String.format("enemies/%d/enemy.png", i)).openStream(),64,64,false,false));
            } catch (Exception e){
                enemyImage = Optional.empty();
            }
            enemyImages.add(enemyImage);
        }
    }

    public static HashMap<Enemy, Optional<Image>> GetEnemiesImages(List<Enemy> enemies){
        HashMap<Enemy, Optional<Image>> enemiesImages = new HashMap<>();
        for (Enemy enemy : enemies) {
            enemiesImages.put(enemy, Instance.enemyImages.get(enemy.getTypeId()));
        }
        return  enemiesImages;
    }
}
