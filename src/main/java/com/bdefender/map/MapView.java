package com.bdefender.map;

import java.util.stream.Collectors;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import com.bdefender.tower.view.TowerImageLoader;
import com.bdefender.component.ImageButton;

public class MapView extends AnchorPane {

    /**
     * Default map width.
     */
    public static final int MAP_WIDTH = 1280;
    /**
     * Default map height.
     */
    public static final int MAP_HEIGHT = 736;
    
    private static final int TOWER_WIDTH = 60;
    private static final int TOWER_HEIGHT = 60;
    private final Map map;
    private final AnchorPane towersPane = new AnchorPane();
    private final AnchorPane enemiesPane = new AnchorPane();
    private EventHandler<MouseEvent> onTowerClick;

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
               final ImageButton tmp = new ImageButton(TowerImageLoader.GetTowerImage(el.getTower().get()).get());
               tmp.setX(el.getTopLeftCoord().getLeftPixel() + 2);
               tmp.setY(el.getTopLeftCoord().getTopPixel() + 2);
               tmp.setWidth(TOWER_WIDTH);
               tmp.setHeight(TOWER_HEIGHT);
               tmp.setOnMouseClick(event -> {
                   this.onTowerClick.handle(event.copyFor(el.getTower().get(), event.getTarget()));
               });
               tmp.setOnMouseEntered(event -> {
                   tmp.setLabel("Livello: ");
               });
               tmp.setOnMouseExited(event -> {
                   tmp.setLabel("");
               });
               return tmp;
            }).collect(Collectors.toList())
        );
    }

    /**
     * @return AnchorPane where enemies are rendered
     */
    public AnchorPane getEnemiesPane() {
        return this.enemiesPane;
    }

    /**
     * @return AnchorPane where towers are rendered
     */
    public AnchorPane getTowersPane() {
        return this.towersPane;
    }

    /**
     * Set handler to call when tower is clicked.
     * @param handler
     */
    public void setOnTowerClick(final EventHandler<MouseEvent> handler) {
        this.onTowerClick = handler;
    }

    /**
     * @return handler called when tower is clicked
     */
    public EventHandler<MouseEvent> getOnTowerClick() {
        return this.onTowerClick;
    }
}
