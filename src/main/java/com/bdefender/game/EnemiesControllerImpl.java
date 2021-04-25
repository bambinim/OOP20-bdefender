package com.bdefender.game;

import com.bdefender.enemy.Enemy;
import com.bdefender.enemy.EnemyFactory;
import com.bdefender.enemy.EnemyName;
import com.bdefender.enemy.pool.EnemiesPoolImpl;
import com.bdefender.enemy.pool.EnemiesPoolInteractor;
import com.bdefender.enemy.pool.EnemiesPoolMover;
import com.bdefender.enemy.pool.EnemiesPoolSpawner;
import com.bdefender.enemy.pool.MapInteractorImpl;
import com.bdefender.enemy.view.EnemyGraphicMover;
import com.bdefender.event.EnemyEvent;
import com.bdefender.event.EventHandler;
import com.bdefender.map.Map;

import java.util.ArrayList;
import java.util.Random;

public class EnemiesControllerImpl implements EnemiesController {

    private final EnemiesPoolImpl pool;
    private EnemyMoverThread moverThread;
    private final EnemyGraphicMover gMover;
    private EnemySpawnerThread spawnerThread;

    public EnemiesControllerImpl(final Map map, final EnemyGraphicMover graphicMover) {
        this.pool = new EnemiesPoolImpl(new MapInteractorImpl(map));
        this.gMover = graphicMover;
        this.moverThread = new EnemyMoverThread(this.pool, this.gMover);
    }

    @Override
    public void startGenerate(final int intensity, final int totEnemies, final EventHandler<EnemyEvent> onDead,
            final EventHandler<EnemyEvent> onReachedEnd) {
        this.pool.clearPool();
        this.moverThread.killMover();
        this.moverThread = new EnemyMoverThread(this.pool, this.gMover);
        this.spawnerThread = new EnemySpawnerThread(intensity, totEnemies, pool, onDead, onReachedEnd);
        this.moverThread.start();
        spawnerThread.start();
    }

    @Override
    public EnemiesPoolInteractor getEnemiesPool() {
        return pool;
    }

    @Override
    public void stopMovingEnemies() {
        this.moverThread.killMover();
    }

    @Override
    public void stopSpawner() {
        this.spawnerThread.shutdown();
    }
}

class EnemySpawnerThread extends Thread {

    private static final long TEN_SEC = 10000;
    private final int intensity;
    private final int totEnemies;
    private final EnemiesPoolSpawner spawner;
    private final EnemyFactory factory = new EnemyFactory();
    private final EventHandler<EnemyEvent> onDead;
    private final EventHandler<EnemyEvent> onReachedEnd;
    private boolean stop = false;

    EnemySpawnerThread(final int intensity, final int totEnemies, final EnemiesPoolSpawner spawner,
            final EventHandler<EnemyEvent> onDead, final EventHandler<EnemyEvent> onReachedEnd) {
        this.intensity = intensity;
        this.totEnemies = totEnemies;
        this.spawner = spawner;
        this.onDead = onDead;
        this.onReachedEnd = onReachedEnd;
    }

    public Enemy getEnemyByType(final int enemyCod) {
        switch (enemyCod) {
        case 0:
            return factory.getEnemy(EnemyName.AXE_OGRE, this.onDead, this.onReachedEnd);
        case 1:
            return factory.getEnemy(EnemyName.SWORD_OGRE, this.onDead, this.onReachedEnd);
        case 2:
            return factory.getEnemy(EnemyName.HAMMER_OGRE, this.onDead, this.onReachedEnd);
        default:
            return null;
        }
    }

    @Override
    public void run() {
        /*
         * for (int i = 0; i < totEnemies; i++) { try { sleep(TEN_SEC / intensity);
         * Random random = new Random(); Enemy enemy =
         * getEnemyByType(random.nextInt(3)); spawner.addEnemy(enemy); } catch
         * (InterruptedException ex) { System.out.println(ex.getMessage()); } }
         */
        int i = 0;
        while (!stop && i < this.totEnemies) {
            try {
                sleep(TEN_SEC / intensity);
                Random random = new Random();
                Enemy enemy = getEnemyByType(random.nextInt(3));
                spawner.addEnemy(enemy);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            i++;
        }
    }

    public void shutdown() {
        this.stop = true;
    }
}

class EnemyMoverThread extends Thread {

    private final EnemiesPoolMover mover;
    private final EnemyGraphicMover gMover;
    private boolean alive = true;
    private static final long SPEED_DIV = 1000;
    private static final long TEN_SEC = 10000;

    EnemyMoverThread(final EnemiesPoolMover mover, final EnemyGraphicMover gMover) {
        this.mover = mover;
        this.gMover = gMover;
    }

    public void killMover() {
        this.alive = false;
    }

    @Override
    public void run() {
        while (alive) {
            try {
                sleep(TEN_SEC / SPEED_DIV);
                this.mover.moveEnemies(SPEED_DIV);
                this.gMover.moveEnemies(new ArrayList<>(mover.getAliveEnemies().values()));
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
