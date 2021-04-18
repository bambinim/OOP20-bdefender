package com.bdefender.shop;
import com.bdefender.game.TowerName;
import com.bdefender.tower.Tower;

public interface ShopController {
    void initialize();
    TowerName getLastTower();
    void setBtnUpgradeOff();
    void setBtnUpgradeOn();
    void setTowerToUpg(Tower tower);
}