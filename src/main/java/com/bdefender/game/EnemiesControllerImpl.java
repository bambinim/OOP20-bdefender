package com.bdefender.game;

import com.bdefender.enemies.Enemy;
import com.bdefender.enemies.EnemyFactory;
import com.bdefender.enemies.event.EnemyEvent;
import com.bdefender.enemies.pool.*;
import com.bdefender.enemies.pool.EnemiesPoolImpl;
import com.bdefender.map.Map;
import com.bdefender.enemies.view.EnemyGraphicMover;
import javafx.event.EventHandler;

import java.util.Random;


public class EnemiesControllerImpl implements EnemiesController{

    private EnemiesPoolImpl pool;
    private EnemyMoverThread moverThread;

    public EnemiesControllerImpl(Map map,EnemyGraphicMover graphicMover){
        this.pool = new EnemiesPoolImpl(new MapInteractorImpl(map),graphicMover);
        this.moverThread = new EnemyMoverThread(this.pool);
    }

    @Override
    public void startGenerate(int intensity, int totEnemies, EventHandler<EnemyEvent> onDead, EventHandler<EnemyEvent> onReachedEnd) {
        EnemyMoverThread moverThread = new EnemyMoverThread(this.pool);
        EnemySpawnerThread spawnerThread = new EnemySpawnerThread(intensity, totEnemies, pool, onDead, onReachedEnd);
        moverThread.start();
        spawnerThread.start();
    }

    @Override
    public EnemiesPoolInteractor getEnemiesPool() {
        return pool;
    }
}

class EnemySpawnerThread extends Thread {

    private final int intensity;
    private final int totEnemies;
    private static final long TEN_SEC = 10000;
    private final EnemiesPoolSpawner spawner;
    private final EnemyFactory factory = new EnemyFactory();
    private final EventHandler<EnemyEvent> onDead;
    private final EventHandler<EnemyEvent> onReachedEnd;

    public EnemySpawnerThread(int intensity, int totEnemies, EnemiesPoolSpawner spawner, EventHandler<EnemyEvent> onDead, EventHandler<EnemyEvent> onReachedEnd) {
        this.intensity = intensity;
        this.totEnemies = totEnemies;
        this.spawner = spawner;
        this.onDead = onDead;
        this.onReachedEnd = onReachedEnd;
    }

    public Enemy getEnemyByType(int enemyCod){
        switch (enemyCod){
            case 0: return factory.getEnemy1(this.onDead, this.onReachedEnd);
            case 1: return factory.getEnemy2(this.onDead, this.onReachedEnd);
            case 2: return factory.getEnemy3(this.onDead, this.onReachedEnd);
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
