package com.bdefender.shop;
import com.bdefender.game.TowerName;

public interface ShopView {
    void initialize();
    TowerName getLastTower();
}