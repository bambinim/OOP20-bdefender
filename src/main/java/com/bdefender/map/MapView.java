package com.bdefender.map;

import java.util.stream.Collectors;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import com.bdefender.tower.view.TowerImageLoader;

public class MapView extends AnchorPane {

    private static final int TOWER_WIDTH = 60;
    private static final int TOWER_HEIGHT = 60;
    private final Map map;
    private final AnchorPane towersPane = new AnchorPane();
    private final AnchorPane enemiesPane = new AnchorPane();

    public MapView(final Map map) {
        this.map = map;
        this.getChildren().addAll(new ImageView(this.map.getMapImage()), this.towersPane, this.enemiesPane);
    }

    /**
     * Reload all the towers in the view to display new once.
     */
    public void reloadTowersView() {
        this.towersPane.getChildren().clear();
        this.towersPane.getChildren().addAll(this.map.getTowerBoxes().stream().filter(el -> el.getTower().isPresent()).map(el -> {
               final ImageView tmp = new ImageView(TowerImageLoader.GetTowerImage(el.getTower().get()).get());
               tmp.setX(el.getTopLeftCoord().getLeftPixel() + 2);
               tmp.setY(el.getTopLeftCoord().getTopPixel() + 2);
               tmp.maxWidth(TOWER_WIDTH);
               tmp.minWidth(TOWER_WIDTH);
               tmp.maxHeight(TOWER_HEIGHT);
               tmp.minHeight(TOWER_HEIGHT);
               return tmp;
            }).collect(Collectors.toList())
        );
    }

    /**
     * @return AnchorPane where enemies must be rendered
     */
    public AnchorPane getEnemiesPane() {
        return this.enemiesPane;
    }
}
