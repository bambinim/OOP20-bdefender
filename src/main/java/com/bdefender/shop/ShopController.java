package com.bdefender.shop;
import java.util.Optional;

import com.bdefender.game.TowerName;
import com.bdefender.tower.Tower;

public interface ShopController {
    void initialize();
    Optional<TowerName> getLastTower();
    void setBtnUpgradeOff();
    void setBtnUpgradeOn();
    void setEmptyLastTower();
    void setTowerToUpg(Tower tower);
    void updLblMoney();
}