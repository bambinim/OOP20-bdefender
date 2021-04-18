package com.bdefender.shop;
import com.bdefender.game.TowerName;

public interface ShopController {
    void initialize();
    TowerName getLastTower();
    void setBtnUpgradeOff();
    void setBtnUpgradeOn();
}