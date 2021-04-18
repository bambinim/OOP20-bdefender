package com.bdefender.shop;

import java.util.stream.Stream;

import com.bdefender.game.TowerName;
import com.bdefender.tower.Tower;
import com.bdefender.wallet.Wallet;

public class ShopImpl implements Shop {
    private Wallet wallet; //to manage the money that the user has
    
    public ShopImpl(final Wallet wallet) {
        this.wallet = wallet;
    }

    /*
     * check if is possible to buy a specific tower.
     * @return true if the purchase is successful
     */

    @Override
    public final boolean canBuyTower(final TowerName tower) {
        return wallet.areMoneyEnough(tower.getPrice());
    }

    /*
     *subctract the money after the purchase of a tower 
     * */
    @Override
    public final void buyTower(final TowerName tower) {
        wallet.subtractMoney(tower.getPrice());
    }

    /*
     * Upgrade the tower and modifies the user money
     * @param tower that as to be upgraded
     * @return a tower upgraded
     * */

    @Override
    public final void buyUpgrade(final Tower tower) {
       
      final TowerName  typeToUpg = Stream.of(TowerName.values())
              .filter((x) -> x.getId() == tower.getTowerId())
              .findFirst()
              .get();
       
      if (wallet.areMoneyEnough(typeToUpg.getUpgCost())) {
           tower.upgradeLevel();
           wallet.subtractMoney(typeToUpg.getUpgCost());
       }

    }
    /*
     * @return the userWallet
     * */

    @Override
    public Wallet getWallet() {
        return this.wallet;
    }


}


