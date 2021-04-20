package com.bdefender.tower.view;

import com.bdefender.Pair;
import com.bdefender.map.Coordinates;
import com.bdefender.tower.Tower;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class TowerViewImpl implements TowerView{

    private final Pane panel;
    private final Tower tower;
    private final ImageView towerImage;

    public TowerViewImpl(AnchorPane panel, Tower tower){
        this.tower = tower;
        this.panel = panel;
        towerImage = new ImageView(TowerImageLoader.GetTowerImage(this.tower).get());
        Coordinates towerPos = new Coordinates(this.tower.getPosition().getX(), this.tower.getPosition().getY());
        towerImage.setX(towerPos.getLeftPixel());
        towerImage.setY(towerPos.getTopPixel());
    }

    @Override
    public void startShootAnimation(Pair<Double, Double> target) {
        ImageView towerShoot = new ImageView(TowerImageLoader.GetTowerShootImage(tower).get());
        Platform.runLater(() -> {
            var shootAnimation = createTransition(towerShoot,new Coordinates(target.getX(), target.getY()));
            shootAnimation.setOnFinished(e -> panel.getChildren().remove(towerShoot));
            panel.getChildren().add(towerShoot);
            shootAnimation.play();
        });
    }

    @Override
    public void addTowerToGameField() {
        panel.getChildren().add(towerImage);
    }

    @Override
    public void removeTowerFromGameField() {
        panel.getChildren().remove(towerImage);
    }

    private Coordinates getTowerCoordinates(){
        return new Coordinates(this.tower.getPosition().getX(), this.tower.getPosition().getY());
    }

    private PathTransition createTransition(final Node node, final Coordinates target) {
        final PathTransition pathTransition = new PathTransition();
        final Path path = new Path();
        var towerPos = getTowerCoordinates();
        path.getElements().add(new MoveTo(towerPos.getLeftPixel(), towerPos.getTopPixel()));
        path.getElements().add(new LineTo(target.getLeftPixel(), target.getTopPixel()));
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.millis(100));
        pathTransition.setNode(node);
        pathTransition.setAutoReverse(false);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        return pathTransition;
    }

}
