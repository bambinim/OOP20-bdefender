package com.bdefender.tower.view;
import com.bdefender.tower.Tower;

@FunctionalInterface
public interface TowerViewImplementation {
    TowerView getView(Tower tower);
}
