package com.bdefender.game;

import com.bdefender.Pair;
import com.bdefender.enemy.pool.EnemiesPoolInteractor;
import com.bdefender.map.Coordinates;
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerFactory;
import com.bdefender.tower.TowerName;
import com.bdefender.tower.view.TowerView;

import java.util.HashMap;
import java.util.Map;

public class TowersControllerImpl implements TowersController {

    private final Map<Tower, TowerData> towersData = new HashMap<>();
    private final TowerFactory factory = new TowerFactory();
    private final EnemiesPoolInteractor pool;
    private final TowerViewImplementation towerViewImplementation;

    public TowersControllerImpl(final TowerViewImplementation viewImplementation, final EnemiesPoolInteractor enemyPool) {
        this.towerViewImplementation = viewImplementation;
        this.pool = enemyPool;
    }

    private Tower getTowerByTypeName(final TowerName name, final Coordinates pos) {
       return this.factory.getTowerDirect(name, this.pool, pos);
    }

    /**
     * adds a tower to the game field .
     * 
     * @param name the type of tower to add
     * @param pos tower spawn position
     * @return created tower
     */
    @Override
    public Tower addTower(final TowerName name, final Coordinates pos) {
        Tower tower = getTowerByTypeName(name, pos);
        TowerView view = towerViewImplementation.getView(tower);
        TowerThread thread = new TowerThread(tower, view);
        towersData.put(tower, new TowerData(view, thread));
        thread.start();
        return tower;
    }

    /**
     * remove a tower from the game field.
     * 
     * @param tower tower to remove from the game field
     */
    @Override
    public void removeTower(final Tower tower) {
        this.towersData.get(tower).getThread().killTower();
        this.towersData.remove(tower);
    }

    /**
     * @param tower tower to upgrade
     * @return level after the upgrade
     */
    @Override
    public Integer upgradeTower(final Tower tower) {
        return tower.upgradeLevel();
    }

    @FunctionalInterface
    public interface TowerViewImplementation {
        TowerView getView(Tower tower);
    }
}

class TowerData {
    private final TowerView towerView;
    private final TowerThread thread;

    TowerData(final TowerView view, final TowerThread thread) {
        this.towerView = view;
        this.thread = thread;
    }

    public TowerView getView() {
        return this.towerView;
    }

    public TowerThread getThread() {
        return this.thread;
    }

}

class TowerThread extends Thread {
    private static final long TEN_SECONDS = 10000;
    private final TowerView view;
    private final Tower tower;
    private boolean alive = true;

    TowerThread(final Tower tower, final TowerView view) {
        this.view = view;
        this.tower = tower;
    }

    public void killTower() {
        this.alive = false;
    }

    @Override
    public void run() {
        while (alive) {
            try {
                sleep(TEN_SECONDS / tower.getShootSpeed());
                Pair<Double, Double> shootTargetPos;
                shootTargetPos = tower.shoot();
                if (shootTargetPos != null) {
                    view.startShootAnimation(new Pair<>(shootTargetPos.getX(), shootTargetPos.getY()));
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
