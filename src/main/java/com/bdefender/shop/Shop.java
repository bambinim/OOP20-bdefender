package com.bdefender.shop;
import com.bdefender.tower.Tower;
import com.bdefender.wallet.Wallet;
import com.bdefender.game.TowerName;


public interface Shop {
    boolean isTowerBuyable(TowerName tower);
    void buyTower(TowerName tower);
    void buyUpgrade(Tower tower);
    Wallet getWallet();
}
