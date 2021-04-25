package com.bdefender.tower;

import static org.junit.jupiter.api.Assertions.*;

import com.bdefender.Pair;
import com.bdefender.enemy.EnemyFactory;
import com.bdefender.enemy.EnemyName;
import com.bdefender.enemy.pool.EnemiesPoolImpl;
import com.bdefender.enemy.pool.MapInteractor;
import com.bdefender.enemy.pool.MapInteractorImpl;
import com.bdefender.map.MapLoader;
import com.bdefender.map.MapType;
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerFactory;
import com.bdefender.tower.TowerName;
import org.junit.jupiter.api.Test;

class TowerTest {

    @Test
    void testTowerShoot() {

        MapInteractor mapInt = new MapInteractorImpl(MapLoader.getInstance().loadMap(MapType.COUNTRYSIDE));
        EnemiesPoolImpl pool = new EnemiesPoolImpl(mapInt);
        EnemyFactory factory = new EnemyFactory();
        pool.addEnemy(factory.getEnemy(EnemyName.SWORD_OGRE, e -> {}, e -> {}));
        pool.addEnemy(factory.getEnemy(EnemyName.HAMMER_OGRE, e -> {}, e -> {}));

        TowerFactory tFactory = new TowerFactory();
        Tower tower = tFactory.getTowerDirect(TowerName.FIRE_ARROW,pool, new Pair<>(11.0, 9.0));

        tower.shoot();

        //no enemies around
        assertEquals(35, pool.getEnemies(false).get(0).getLife());
        assertEquals(50, pool.getEnemies(false).get(1).getLife());

        pool.getEnemies(false).get(0).moveTo(new Pair<>(14.0, 9.0));

        tower.shoot();

        //enemy 0 in range
        assertEquals(35 - 13, pool.getEnemies(false).get(0).getLife());
        assertEquals(50, pool.getEnemies(false).get(1).getLife());

        pool.getEnemies(false).get(1).moveTo(new Pair<>(7.0, 14.0));

        tower.shoot();

        //both in range but 0 is closer
        assertEquals(35 - (13 * 2), pool.getEnemies(false).get(0).getLife());
        assertEquals(50, pool.getEnemies(false).get(1).getLife());

        pool.getEnemies(false).get(0).moveTo(new Pair<>(7.0, 14.0));
        pool.getEnemies(false).get(1).moveTo(new Pair<>(14.0, 9.0));

        tower.shoot();

        //both in range but 1 is closer
        assertEquals(35 - (13 * 2), pool.getEnemies(false).get(0).getLife());
        assertEquals(50 - 13, pool.getEnemies(false).get(1).getLife());

        pool.getEnemies(false).get(0).moveTo(new Pair<>(14.0, 9.0));

        tower.shoot();

        //both the in the same position, tower shoots the last enemy in the array
        assertEquals(35 - (13 * 2), pool.getEnemies(false).get(0).getLife());
        assertEquals(50 - (13 * 2), pool.getEnemies(false).get(1).getLife());

    }

}
