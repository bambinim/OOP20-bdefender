package com.bdefender.game;

import javafx.scene.layout.AnchorPane;
import com.bdefender.map.MapView;

public class GameView extends AnchorPane {

    public GameView(final MapView mapView) {
        this.getChildren().add(mapView);
    }
}
