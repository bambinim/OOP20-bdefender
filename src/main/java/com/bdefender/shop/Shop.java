package com.bdefender.shop;
import com.bdefender.tower.Tower;
import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.game.TowerName;


public interface Shop {
    boolean canBuyTower(TowerName tower);
    void buyTower(TowerName tower);
    Tower buyUpgrade(Tower tower);
}
