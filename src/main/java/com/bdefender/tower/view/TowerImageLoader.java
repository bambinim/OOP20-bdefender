package com.bdefender.tower.view;

import com.bdefender.tower.Tower;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class TowerImageLoader {

    private static final TowerImageLoader INSTANCE;
    private static final int N_TOWERS = 3;

    static {
        INSTANCE = new TowerImageLoader(N_TOWERS);
    }

    private final ArrayList<Image> towerImages = new ArrayList<>();
    private final ArrayList<Image> towerShootImages = new ArrayList<>();

    public TowerImageLoader(final int nTowers) {
        for (int i = 0; i < nTowers; i++) {
            Image towerImage;
            Image towerShootImage;
            try {
                towerImage =
                        new Image(ClassLoader.getSystemResource(String.format("towers/%d/tower.png", i)).openStream(),
                                64, 64, false, false);
            } catch (Exception e) {
                towerImage = null;
            }
            try {
                towerShootImage =
                        new Image(ClassLoader.getSystemResource(String.format("towers/%d/shoot.png", i)).openStream(),
                                32, 32, false, false);
            } catch (Exception e) {
                towerShootImage = null;
            }
            towerImages.add(towerImage);
            towerShootImages.add(towerShootImage);
        }
    }

    public static Image getTowerImage(final Tower tower) {
        return INSTANCE.towerImages.get(tower.getTowerTypeId());
    }

    public static Image getTowerShootImage(final Tower tower) {
        return INSTANCE.towerShootImages.get(tower.getTowerTypeId());
    }
}
