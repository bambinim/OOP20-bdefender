package com.bdefender.tower.view;

import com.bdefender.Pair;
import com.bdefender.map.Coordinates;
import com.bdefender.tower.Tower;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TowerViewImpl implements TowerView{

    private final Pane panel;
    private final Tower tower;
    private final ImageView towerImage;

    public TowerViewImpl(Pane panel, Tower tower){
        this.tower = tower;
        this.panel = panel;
        towerImage = new ImageView(TowerImageLoader.GetTowerImage(this.tower).get());
        Coordinates towerPos = new Coordinates(this.tower.getPosition().getX(), this.tower.getPosition().getY());
        towerImage.setX(towerPos.getLeftPixel());
        towerImage.setY(towerPos.getTopPixel());
    }

    @Override
    public void startShootAnimation(Pair<Double, Double> target) {

    }

    @Override
    public void addTowerToGameField() {
        panel.getChildren().add(towerImage);
    }

    @Override
    public void removeTowerFromGameField() {
        panel.getChildren().remove(towerImage);
    }
}
