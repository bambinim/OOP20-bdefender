package com.bdefender.shop;
import com.bdefender.tower.Tower;

import java.util.Optional;

import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;


public interface Shop {
    boolean hasBoughtTower(Integer price);
    int getTowerPrice(String twrName);
    void setTowerPrice(String twrName, Integer price);
    Optional<Tower> buyTowerZone1(EnemiesPoolInteractor pool, Pair<Double, Double> pos, String twrName);
    Optional<Tower> buyTowerZone2(EnemiesPoolInteractor pool, Pair<Double, Double> pos, String twrName);
    Optional<Tower> buyTowerDirect1(EnemiesPoolInteractor pool, Pair<Double, Double> pos, String twrName);
    void buyUpgrade(Tower tw, String twName);

}
