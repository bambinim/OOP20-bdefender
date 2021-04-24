package com.bdefender.enemy;

import static org.junit.jupiter.api.Assertions.*;

import com.bdefender.enemy.view.EnemiesViewLoader;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import com.bdefender.enemy.pool.EnemiesPoolImpl;
import com.bdefender.enemy.pool.MapInteractor;
import com.bdefender.enemy.pool.MapInteractorImpl;
import com.bdefender.map.MapLoader;
import com.bdefender.map.MapType;

import java.util.ArrayList;
import java.util.List;


class EnemiesTest {

    @Test
    void testMovement() {
        MapInteractor mapInt = new MapInteractorImpl(MapLoader.getInstance().loadMap(MapType.COUNTRYSIDE));
        EnemiesPoolImpl pool = new EnemiesPoolImpl(mapInt);
        EnemyFactory factory = new EnemyFactory();
        pool.addEnemy(factory.getEnemy(EnemyName.AXE_OGRE, e -> System.out.println("dead"), e -> System.out.println("reached end")));

        Enemy enemy = pool.getEnemies(false).get(0);

        assertEquals(enemy.getPosition().getX(), 0.0);
        assertEquals(enemy.getPosition().getY(), 9.0);

        pool.moveEnemies(10);

        assertEquals(enemy.getPosition().getX(), 4.0);
        assertEquals(enemy.getPosition().getY(), 9.0);

        pool.moveEnemies(10);

        assertEquals(enemy.getPosition().getX(), 7.0);
        assertEquals(enemy.getPosition().getY(), 10.0);


        pool.addEnemy(factory.getEnemy(EnemyName.SWORD_OGRE, e -> System.out.println("dead"), e -> System.out.println("reached end")));
        enemy = pool.getEnemies(false).get(1);

        for (int i = 0; i < 15; i++){
            pool.moveEnemies(10);
        }

        assertEquals(enemy.getPosition().getX(), 35.0);
        assertEquals(enemy.getPosition().getY(), 6.0);
    }

    @Test
    void testReachEnd(){
        MapInteractor mapInt = new MapInteractorImpl(MapLoader.getInstance().loadMap(MapType.COUNTRYSIDE));
        EnemiesPoolImpl pool = new EnemiesPoolImpl(mapInt);
        EnemyFactory factory = new EnemyFactory();
        pool.addEnemy(factory.getEnemy(EnemyName.SWORD_OGRE, e -> System.out.println("dead"), e -> System.out.println("reached end")));

        Enemy enemy = pool.getEnemies(false).get(0);

        for (int i = 0; i < 16; i++){
            assertFalse(enemy.isArrived());
            pool.moveEnemies(10);
        }

        assertTrue(enemy.isArrived());

        pool.addEnemy(factory.getEnemy(EnemyName.AXE_OGRE, e -> System.out.println("dead"), e -> System.out.println("reached end")));

        enemy = pool.getEnemies(false).get(1);

        for (int i = 0; i < 24; i++){
            assertFalse(enemy.isArrived());
            pool.moveEnemies(10);
        }

        assertTrue(enemy.isArrived());

        pool.addEnemy(factory.getEnemy(EnemyName.HAMMER_OGRE, e -> System.out.println("dead"), e -> System.out.println("reached end")));

        enemy = pool.getEnemies(false).get(2);

        for (int i = 0; i < 32; i++){
            assertFalse(enemy.isArrived());
            pool.moveEnemies(10);
        }

        assertTrue(enemy.isArrived());

    }

    @Test
    void testResourcesLoad(){
        EnemyFactory factory = new EnemyFactory();
        List<Enemy> enemies = new ArrayList<>(List.of(factory.getEnemy(EnemyName.SWORD_OGRE, e ->{}, e -> {}), factory.getEnemy(EnemyName.AXE_OGRE, e ->{}, e -> {}), factory.getEnemy(EnemyName.HAMMER_OGRE, e ->{}, e -> {})));

        ArrayList<Image> enemiesImages = new ArrayList<>(EnemiesViewLoader.getEnemiesImages(enemies).values());

        for (Image image : enemiesImages) {
            assertNotNull(image);
        }

    }

}
