package com.bdefender.tower.view;

import com.bdefender.Pair;

public interface TowerView {

    /**
     *starts shoot animation
     * @param target shoot target position.
     */
    void startShootAnimation(Pair<Double, Double> target);
}
