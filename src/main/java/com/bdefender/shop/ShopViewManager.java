package com.bdefender.shop;
import java.util.Optional;
import com.bdefender.tower.TowerName;

public interface ShopViewManager {
    Optional<TowerName> getLastTowerClicked();
    void setEmptyLastTwClicked();
    void setUpgradeOff();
    void setUpgradeOn();
    void updMoney();
    void refreshTowerChoosable();

}
