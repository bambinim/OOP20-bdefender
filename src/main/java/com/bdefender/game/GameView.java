package com.bdefender.game;

import javafx.scene.layout.AnchorPane;
import com.bdefender.map.MapView;

public class GameView extends AnchorPane {

    private final TopMenuView topMenuView;

    public GameView(final MapView mapView) {
        this.topMenuView = new TopMenuView();
        mapView.setLayoutY(TopMenuView.HEIGHT);
        this.getChildren().addAll(mapView, this.topMenuView);
    }

    /**
     * @return top menu view
     */
    public TopMenuView getTopMenuView() {
        return this.topMenuView;
    }
}
