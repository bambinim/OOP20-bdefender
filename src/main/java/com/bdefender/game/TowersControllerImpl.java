package com.bdefender.game;

import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.map.Coordinates;
import com.bdefender.tower.view.TowerViewLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.HashMap;
import java.util.Map;

public class TowersControllerImpl implements TowersController{

    private final Pane panel;
    private final Map<Integer, TowerData> towersData = new HashMap<>();
    private int towerCounter = 0;
    private final TowerFactory factory = new TowerFactory();
    private final EnemiesPoolInteractor pool;

    public TowersControllerImpl(Pane panel, EnemiesPoolInteractor enemyPool) {
        this.panel = panel;
        this.pool = enemyPool;
    }

    private Tower getTowerByTypeName(TowerName name, Coordinates pos){
        switch (name) {
            case ROCK: return factory.getTowerDirect1(this.pool,pos);
            case FIRE_BALL: factory.getTowerDirect1(this.pool,pos);
            case FIRE_ARROW: factory.getTowerDirect1(this.pool,pos);
            case THUNDERBOLT: factory.getTowerDirect1(this.pool,pos);
        }
        return null;
    }

    @Override
    public Integer addTower(TowerName name, Coordinates pos) {
        Tower tower = getTowerByTypeName(name, pos);
        Image towerImage = TowerViewLoader.GetTowerImage(tower).get();
        ImageView towerView = new ImageView(towerImage);
        TowerThread thread = new TowerThread(tower,panel);
        towersData.put(++towerCounter,new TowerData(towerView,thread));
        this.panel.getChildren().add(towerView);
        thread.start();
        return towerCounter;
    }

    @Override
    public void removeTower(Integer towerId) {
        this.towersData.get(towerId).getThread().killTower();
        this.panel.getChildren().remove(this.towersData.get(towerId).getView());
        this.towersData.remove(towerId);
    }

    @Override
    public Integer upgradeTower(Integer towerId) {
        return null;
    }
}

class TowerData {
    private final ImageView towerView;
    private final TowerThread thread;
    public TowerData(ImageView towerView,TowerThread thread){
        this.towerView = towerView;
        this.thread = thread;
    }

    public ImageView getView(){
        return this.towerView;
    }

    public TowerThread getThread(){
        return this.thread;
    }

}

class TowerThread extends Thread {
    private final Tower tower;
    private final Pane panel;
    private boolean alive;

    public TowerThread(Tower tower, Pane panel){
        this.tower = tower;
        this.panel = panel;
    }

    public void killTower(){
        this.alive = false;
    }

    @Override
    public void run() {
        while(alive){
            try {
                sleep(1000L * tower.getShootSpeed());
                if (tower.shoot().isEmpty()) {
                    System.out.println("No more enemies around...");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
