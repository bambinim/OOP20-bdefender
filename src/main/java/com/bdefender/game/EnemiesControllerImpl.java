package com.bdefender.game;

import com.bdefender.enemies.Enemy;
import com.bdefender.enemies.EnemyFactory;
import com.bdefender.enemies.pool.*;
import com.bdefender.enemies.view.EnemiesPoolImpl;
import com.bdefender.enemies.view.EnemyView;
import com.bdefender.map.Map;
import com.bdefender.enemies.view.EnemyGraphicMover;

import java.util.Random;


public class EnemiesControllerImpl implements EnemiesController{

    private EnemiesPoolImpl pool;
    private EnemyViewImplementation viewImpl;

    public EnemiesControllerImpl(Map map, EnemyViewImplementation viewImpl, EnemyGraphicMover graphicMover){
        this.pool = new EnemiesPoolImpl(new MapInteractorImpl(map),graphicMover);
        this.viewImpl = viewImpl;
    }

    @Override
    public void startGenerate(int intensity, int totEnemies) {
        EnemyMoverThread moverThread = new EnemyMoverThread(this.pool);
        EnemySpawnerThread spawnerThread = new EnemySpawnerThread(intensity, totEnemies, pool, viewImpl);
        moverThread.start();
        spawnerThread.start();
    }

    @Override
    public EnemiesPoolInteractor getEnemiesPool() {
        return pool;
    }
}

class EnemySpawnerThread extends Thread {

    @FunctionalInterface

    interface FinishOperation {
        void onFinish();
    }

    private final int intensity;
    private final int totEnemies;
    private static final long TEN_SEC = 10000;
    private final EnemiesPoolSpawner spawner;
    private final EnemyFactory factory = new EnemyFactory();
    private final EnemiesControllerImpl.EnemyViewImplementation viewImpl;

    public EnemySpawnerThread(int intensity, int totEnemies, EnemiesPoolSpawner spawner, EnemiesControllerImpl.EnemyViewImplementation viewImpl) {
        this.intensity = intensity;
        this.totEnemies = totEnemies;
        this.spawner = spawner;
        this.viewImpl = viewImpl;
    }

    public Enemy getEnemyByType(int enemyCod){
        switch (enemyCod){
            case 0: return factory.getEnemy1(this.spawner.getSpawnPoint(), this.spawner.getSpawnDir());
            case 1: return factory.getEnemy2(this.spawner.getSpawnPoint(), this.spawner.getSpawnDir());
            case 2: return factory.getEnemy3(this.spawner.getSpawnPoint(), this.spawner.getSpawnDir());
            default: return null;
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < totEnemies; i++ ){
            try {
                sleep(TEN_SEC / intensity);
                Random random = new Random();
                Enemy enemy = getEnemyByType(random.nextInt(2));
                spawner.addEnemy(enemy);
                EnemyView view = this.viewImpl.getView(enemy);
                enemy.setOnDeadAction(view::removeEnemyFromGameField);
            } catch (InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}

class EnemyMoverThread extends Thread {

    private final EnemiesPoolMover mover;
    private boolean alive = true;

    public EnemyMoverThread(EnemiesPoolMover mover) {
        this.mover = mover;
    }

    public void killMover(){
        this.alive = false;
    }

    @Override
    public void run() {
        while(alive){
            try {
                sleep(10L);
                mover.moveEnemies();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
