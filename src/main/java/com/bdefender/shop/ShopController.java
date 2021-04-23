package com.bdefender.shop;
import java.util.Optional;

import com.bdefender.game.TowerName;
import com.bdefender.tower.Tower;

public interface ShopController {
    Optional<TowerName> getLastTowerClicked();
    void setBtnUpgradeOff();
    void setBtnUpgradeOn();
    void setEmptyLastTower();
    void setTowerToUpg(Tower tower);
    void updLblMoney();

}
