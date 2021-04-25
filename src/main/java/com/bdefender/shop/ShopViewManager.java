package com.bdefender.shop;
import java.util.Optional;
import com.bdefender.tower.TowerName;

public interface ShopViewManager {
    Optional<TowerName> getLastTowerClicked();
    void setEmptyLastTwClicked();
    void setBtnUpgradeOff();
    void setBtnUpgradeOn();
    void updLblMoney();
    void refreshTowerBtn();

}
