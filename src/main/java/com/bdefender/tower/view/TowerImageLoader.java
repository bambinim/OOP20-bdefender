package com.bdefender.tower.view;

import com.bdefender.tower.Tower;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Optional;

public class TowerImageLoader {

    private ArrayList<Optional<Image>> towerImages = new ArrayList<>();;
    private static TowerImageLoader Instance;
    private static int N_TOWERS = 3;

    static {
        Instance = new TowerImageLoader(N_TOWERS);
    }

    public TowerImageLoader(int nTowers){
        for (int i=0; i < nTowers; i++){
            Optional<Image> towerImage;
            try {
                towerImage = Optional.of(new Image(ClassLoader.getSystemResource(String.format("towers/%d/tower.png", i)).openStream(), 64, 64, false, false));
            } catch (Exception e) {
                towerImage = Optional.empty();
            }
            towerImages.add(towerImage);
        }
    }

    public static Optional<Image> GetTowerImage(Tower tower) {
        return Instance.towerImages.get(tower.getTowerId());
    }
}
