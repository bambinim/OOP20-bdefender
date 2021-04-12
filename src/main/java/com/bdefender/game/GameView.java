package com.bdefender.game;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import com.bdefender.map.MapView;

public class GameView extends GridPane {

    private final AnchorPane mainContainer = new AnchorPane();
    private final MapView mapView;

    public GameView(final MapView mapView) {
        this.mapView = mapView;
        this.setAlignment(Pos.CENTER);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
        this.getChildren().add(this.mainContainer);
        this.mainContainer.getChildren().add(this.mapView);
    }
}
