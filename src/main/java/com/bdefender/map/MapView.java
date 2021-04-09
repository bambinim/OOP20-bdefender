package com.bdefender.map;

import java.util.stream.Collectors;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MapView extends AnchorPane {

    private final Map map;
    private final AnchorPane towersPane = new AnchorPane();

    public MapView(final Map map) {
        this.map = map;
        this.getChildren().add(new ImageView(this.map.getMapImage()));
    }

    /**
     * Reload all the towers in the view to display new once.
     */
    public void reloadTowersView() {
        this.map.getTowerBoxes().stream().filter(el -> el.getTower().isPresent()).collect(Collectors.toList());
        // TODO: add tower view to towersPane
    }
}
