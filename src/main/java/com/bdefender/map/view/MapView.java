package com.bdefender.map.view;

import com.bdefender.event.EventHandler;
import com.bdefender.event.TowerEvent;
import com.bdefender.shop.TowerPlacementView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public interface MapView {

    /**
     * Default map width.
     */
    int MAP_WIDTH = 1280;
    /**
     * Default map height.
     */
    int MAP_HEIGHT = 736;

    /**
     * Show or hide tower placement view.
     * @param visible
     */
    void setTowerPlacementViewVisible(boolean visible);

    /**
     * @return tower placement view
     */
    TowerPlacementView getTowerPlacementView();

    /**
     * Reload all the towers in the view to display new once.
     */
    void reloadTowersView();

    /**
     * @return AnchorPane where enemies are rendered
     */
    Pane getEnemiesContainer();

    /**
     * @return AnchorPane where towers are rendered
     */
    AnchorPane getTowersContainer();

    /**
     * Set handler to call when tower is clicked.
     * @param handler
     */
    void setOnTowerClick(EventHandler<TowerEvent> handler);

    /**
     * @return handler called when tower is clicked
     */
    EventHandler<TowerEvent> getOnTowerClick();

}