package com.bdefender.shop;

import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.wallet.Wallet;

public class ShopImpl implements Shop {
    private Wallet wallet; //to manage the money that the user has
    private TowerFactory tf = new TowerFactory();
    private Map<String, Integer> twrPrices = new HashMap<>(); //to set prices of tower
    private Map<String, Integer> upgPrices = new HashMap<>(); //to set prices of upgrade

    public ShopImpl(final Wallet wallet) {
        this.wallet = wallet;
    }

    /*
     * @return the price of a specific tower
     * 
     */
    public final int getTowerPrice(final String twrName) {
        return this.twrPrices.get(twrName);
    }

    /*
     * set the price of a specific tower
     */

    @Override
    public final void setTowerPrice(final String twrName, final Integer price) {
        twrPrices.put(twrName, price);
    }
    /*
     * check if is possible to buy a specific tower.
     * @return true if the purchase is successful
     */

    @Override
    public boolean hasBoughtTower(final Integer price) {
        if (wallet.areMoneyEnough(price)) {
            wallet.subtractMoney(price);
            return true;
        }
        return false;
    }

    //if is possible to buy the tower then return a new tower which has to be added to the map
    public final Optional<Tower> buyTowerZone1(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos, final String twrName) {
       final int twrPrice = twrPrices.get(twrName);
        return hasBoughtTower(twrPrice) ? Optional.of(tf.getTowerZone1(pool, pos)) : Optional.empty();
    }

    public final Optional<Tower> buyTowerZone2(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos, final String twrName) {
        int twrPrice = twrPrices.get(twrName);
        return hasBoughtTower(twrPrice) ? Optional.of(tf.getTowerZone2(pool, pos)) : Optional.empty();
    }

    public final Optional<Tower> buyTowerDirect1(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos, final String twrName) {
        int twrPrice = twrPrices.get(twrName);
        return hasBoughtTower(twrPrice) ? Optional.of(tf.getTowerDirect1(pool, pos)) : Optional.empty();
    }



    /*
     * upgrade the level of the towers
     * */
    @Override
    public void buyUpgrade(final Tower tw, final String twName) {
        Integer upPrice = upgPrices.get(twName);
        if (wallet.areMoneyEnough(upPrice)) {
            wallet.subtractMoney(upPrice);
            tw.upgradeLevel();
        }

    }

}
