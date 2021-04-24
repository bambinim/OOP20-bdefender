package com.bdefender.shop;
import java.util.Optional;

import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerName;

public interface ShopController {
    Optional<TowerName> getLastTowerClicked();
    void setBtnUpgradeOff();
    void setBtnUpgradeOn();
    void setEmptyLastTower();
    void setTowerToUpg(Tower tower);
    void updLblMoney();
    void refreshTowerBtn();

}
