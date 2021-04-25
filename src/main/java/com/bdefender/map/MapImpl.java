package com.bdefender.map;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.image.Image;


public class MapImpl implements Map {


    private final List<Coordinates> path;
    private final List<TowerBox> towerBoxes;
    private final Image mapImage;

    protected MapImpl(final Image mapImage, final List<Coordinates> path, final List<TowerBox> towerBoxes) {
        this.path = path;
        this.towerBoxes = towerBoxes;
        this.mapImage = mapImage;
    }

    /**
     * Returns the enemies' path.
     * @return list of coordinates
     */
    @Override
    public final List<Coordinates> getPath() {
        return Collections.unmodifiableList(this.path);
    }

    /**
     * Return the tower boxes.
     * @return list of tower boxes
     */
    @Override
    public final List<TowerBox> getTowerBoxes() {
        return this.towerBoxes;
    }

    /**
     * Return only tower boxes without towers inside.
     * @return list of tower boxes
     */
    @Override
    public final List<TowerBox> getEmptyTowerBoxes() {
        return this.towerBoxes.stream().filter(el -> el.getTower().isEmpty()).collect(Collectors.toList());
    }

    /**
     * Return tower boxes with towers inside.
     * @return list of tower boxes
     */
    @Override
    public final List<TowerBox> getOccupiedTowerBoxes() {
        return this.towerBoxes.stream().filter((tb) -> tb.getTower().isPresent()).collect(Collectors.toList());
    }

    /**
     * Return the background image of the map.
     * @return - Image object
     */
    @Override
    public Image getMapImage() {
        return this.mapImage;
    }
}

