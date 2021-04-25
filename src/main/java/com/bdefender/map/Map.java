package com.bdefender.map;

import java.util.List;

import javafx.scene.image.Image;

public interface Map {

    /**
     * Returns the enemies' path.
     * @return list of coordinates
     */
    List<Coordinates> getPath();

    /**
     * Return the tower boxes.
     * @return list of tower boxes
     */
    List<TowerBox> getTowerBoxes();

    /**
     * Return only tower boxes without towers inside.
     * @return list of tower boxes
     */
    List<TowerBox> getEmptyTowerBoxes();

    /**
     * Return tower boxes with towers inside.
     * @return list of tower boxes
     */
    List<TowerBox> getOccupiedTowerBoxes();

    /**
     * Return the background image of the map.
     * @return - Image object
     */
    Image getMapImage();

}