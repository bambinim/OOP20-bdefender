package com.defender.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import com.bdefender.shop.Shop;
import com.bdefender.shop.ShopImpl;
import com.bdefender.shop.ShopLoader;
import com.bdefender.tower.TowerName;
import com.bdefender.wallet.Wallet;
import com.bdefender.wallet.WalletImpl;

@ExtendWith(ApplicationExtension.class)
public class ShopTest {

    private ShopLoader shopManager;
    private Shop shop;
    private Wallet wallet;


    @Test
    public void testBuyTower() {
        final int INIT_AMOUNT = 100;
        this.wallet = new WalletImpl(INIT_AMOUNT);
        this.shop = new ShopImpl(wallet);
        shop.setTowerToBuy(Optional.of(TowerName.FIRE_ARROW));
        shop.buyTower(); //price 200
        assertEquals(INIT_AMOUNT, shop.getWallet().getMoney()); //expected no decrease
        assertFalse(shop.isTowerBuyable(TowerName.FIRE_ARROW)); //no enough money
        assertTrue(shop.isTowerBuyable(TowerName.FIRE_BALL)); //price 100
        shop.getWallet().addMoney(300);
        assertTrue(shop.isTowerBuyable(TowerName.FIRE_BALL)); //money are enough to buy tower
        shop.setTowerToBuy(Optional.of(TowerName.FIRE_BALL));
        shop.buyTower();
        assertEquals(300, shop.getWallet().getMoney()); //expect corret decrease
    }


}



