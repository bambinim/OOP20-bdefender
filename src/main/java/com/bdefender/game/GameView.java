package com.bdefender.game;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import com.bdefender.map.Map;

public class GameView extends GridPane {

    private final AnchorPane mapAnchorPane = new AnchorPane();
    private final AnchorPane mainAnchorPane = new AnchorPane();
    private final Map map;

    public GameView(final Map map) {
        this.map = map;
        this.setAlignment(Pos.CENTER);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
        this.mapAnchorPane.getChildren().add(this.map);
        this.getChildren().addAll(this.mapAnchorPane, this.mainAnchorPane);
    }

    /**
     * Returns the current map.
     * @return map object
     */
    public Map getMap() {
        return this.map;
    }
}
