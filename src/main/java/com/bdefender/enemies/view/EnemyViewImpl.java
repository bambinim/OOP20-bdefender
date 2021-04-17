package com.bdefender.enemies.view;

import com.bdefender.enemies.Enemy;
import com.bdefender.map.Coordinates;
import javafx.scene.layout.AnchorPane;

public class EnemyViewImpl implements EnemyView{

    private AnchorPane panel;
    private Enemy enemy;

    public EnemyViewImpl(AnchorPane panel, Enemy enemy) {
        this.panel = panel;
        this.enemy = enemy;
    }

    @Override
    public void addEnemyToGameField() {

    }

    @Override
    public void removeEnemyFromGameField() {

    }

    private Coordinates getEnemyCoordinates(){
        return new Coordinates(this.enemy.getPosition().getX(), this.enemy.getPosition().getY());
    }
}
