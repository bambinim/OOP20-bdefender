package com.bdefender.shop;

import java.util.Optional;
import java.util.stream.Stream;
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerName;
import com.bdefender.wallet.Wallet;

public class ShopImpl implements Shop {
    private final Wallet wallet; //to manage the money that the user has
    private  Optional<Tower> towerToUpgrade = Optional.empty();
    private Optional<TowerName> towerToBuy = Optional.empty();
    public ShopImpl(final Wallet wallet) {
        this.wallet = wallet;
    }

    /*
     * check if is possible to buy a specific tower.
     * @return true if the purchase is successful
     */

    @Override
    public final boolean isTowerBuyable(final TowerName tower) {
        return wallet.areMoneyEnough(tower.getPrice());
    }

    /*
     *subctract the money after the purchase of a tower 
     * */
    @Override
    public final void buyTower() {
        if (this.towerToBuy.isPresent()) {
            wallet.subtractMoney(this.towerToBuy.get().getPrice());
            this.towerToBuy = Optional.empty();
        }
    }

    /*
     * Upgrade the tower and modifies the user money
     * @param tower that as to be upgraded
     * @return a tower upgraded
     * */

    @Override
    public final void buyUpgrade() {
        if (this.towerToUpgrade.isPresent()) {
            //find the upgrade prices
            final TowerName  typeToUpg = Stream.of(TowerName.values())
                    .filter((x) -> x.getId() == towerToUpgrade.get().getTowerTypeId())
                    .findFirst()
                    .get();
            //check if money is enough
            if (wallet.areMoneyEnough(typeToUpg.getUpgCost())) {
                this.towerToUpgrade.get().upgradeLevel();
                wallet.subtractMoney(typeToUpg.getUpgCost());
                this.towerToUpgrade = Optional.empty();
            }
        }
    }

    /**
     * @return the userWallet
     */
    @Override
    public final Wallet getWallet() {
        return this.wallet;
    }

    /**
     * Set tower to Upgrade.
     * @param tower we want to upgrade.
     */
    public void setTowerToUpg(final Tower tower) {
        this.towerToUpgrade = Optional.of(tower);
    }

    /**
     * Set the tower we want to buy.
     * */
    @Override
    public final void setTowerToBuy(final Optional<TowerName> tower) {
        this.towerToBuy = tower;
    }

    /**
     * Set the tower that need to be Upgraded.
     * */
    @Override
    public final Optional<Tower> getTowerToUpg() {
        return this.towerToUpgrade;
    }


}


