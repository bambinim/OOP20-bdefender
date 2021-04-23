package com.bdefender.tower.view;

import com.bdefender.tower.Tower;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Optional;

public class TowerImageLoader {

    private static final TowerImageLoader INSTANCE;
    private static final int N_TOWERS = 3;

    static {
        INSTANCE = new TowerImageLoader(N_TOWERS);
    }

    private final ArrayList<Optional<Image>> towerImages = new ArrayList<>();
    private final ArrayList<Optional<Image>> towerShootImages = new ArrayList<>();

    public TowerImageLoader(final int nTowers) {
        for (int i = 0; i < nTowers; i++) {
            Optional<Image> towerImage;
            Optional<Image> towerShootImage;
            try {
                towerImage = Optional.of(
                        new Image(ClassLoader.getSystemResource(String.format("towers/%d/tower.png", i)).openStream(),
                                64, 64, false, false));
            } catch (Exception e) {
                towerImage = Optional.empty();
            }
            try {
                towerShootImage = Optional.of(
                        new Image(ClassLoader.getSystemResource(String.format("towers/%d/shoot.png", i)).openStream(),
                                32, 32, false, false));
            } catch (Exception e) {
                towerShootImage = Optional.empty();
            }
            towerImages.add(towerImage);
            towerShootImages.add(towerShootImage);
        }
    }

    public static Optional<Image> getTowerImage(final Tower tower) {
        return INSTANCE.towerImages.get(tower.getTowerTypeId());
    }

    public static Optional<Image> getTowerShootImage(final Tower tower) {
        return INSTANCE.towerShootImages.get(tower.getTowerTypeId());
    }
}
