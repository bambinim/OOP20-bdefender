package com.bdefender.shop;
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerName;
import com.bdefender.wallet.Wallet;


public interface Shop {
    boolean isTowerBuyable(TowerName tower);
    void buyTower(TowerName tower);
    void buyUpgrade(Tower tower);
    Wallet getWallet();
}
