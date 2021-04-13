package com.bdefender.map;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Map {
    /**
     * Map set in a countryside.
     */
    public static final int COUNTRYSIDE = 0;
    /**
     * Map set in an icy plain.
     */
    public static final  int ICE_PLAIN = 1;

    private final List<Coordinates> path;
    private final List<TowerBox> towerBoxes;
    private final Image mapImage;

    protected Map(final Image mapImage, final List<Coordinates> path, final List<TowerBox> towerBoxes) {
        this.path = path;
        this.towerBoxes = towerBoxes;
        this.mapImage = mapImage;
    }

    /**
     * Returns the enemies' path.
     * @return list of coordinates
     */
    public final List<Coordinates> getPath() {
        return Collections.unmodifiableList(this.path);
    }

    /**
     * Return the tower boxes.
     * @return list of tower boxes
     */
    public final List<TowerBox> getTowerBoxes() {
        return this.towerBoxes;
    }

    /**
     * Return only tower boxes without towers inside.
     * @return list of tower boxes
     */
    public final List<TowerBox> getEmptyTowerBoxes() {
        return this.towerBoxes.stream().filter(el -> el.getTower().isEmpty()).collect(Collectors.toList());
    }

    /**
     * @return number of empty boxes
     */
    public final int countEmpyTowerBoxes() {
        return (int) this.towerBoxes.stream().filter(el -> el.getTower().isEmpty()).count();
    }

    /**
     * Return the background image of the map.
     * @return - Image object
     */
    public Image getMapImage() {
        return this.mapImage;
    }


}
