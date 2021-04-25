package com.bdefender.shop;
import java.util.Optional;

import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerName;
import com.bdefender.wallet.Wallet;


public interface Shop {
    boolean isTowerBuyable(TowerName tower);
    void buyTower();
    void buyUpgrade();
    Wallet getWallet();
    void setTowerToUpg(Tower tower);
    void setTowerToBuy(Optional<TowerName> tower);
    Optional<Tower> getTowerToUpg();
 
}
