package com.defender.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import com.bdefender.shop.Shop;
import com.bdefender.shop.ShopImpl;
import com.bdefender.shop.ShopManager;
import com.bdefender.tower.TowerName;
import com.bdefender.wallet.Wallet;
import com.bdefender.wallet.WalletImpl;

@ExtendWith(ApplicationExtension.class)
public class ShopTest {

    private ShopManager shopManager;
    private Shop shop;
    private Wallet wallet;


    @Test
    public void testBuyTower() {
        final int INIT_AMOUNT = 100;
        this.wallet = new WalletImpl(INIT_AMOUNT);
        this.shop = new ShopImpl(wallet);
        shop.buyTower(TowerName.FIRE_ARROW); //price 200
        assertEquals(INIT_AMOUNT, shop.getWallet().getMoney()); //expected no decrease
        assertFalse(shop.isTowerBuyable(TowerName.FIRE_ARROW)); //no enough money
        assertTrue(shop.isTowerBuyable(TowerName.FIRE_BALL)); //price 100
        shop.getWallet().addMoney(300);
        assertTrue(shop.isTowerBuyable(TowerName.FIRE_BALL)); //money are enough to buy tower
        shop.buyTower(TowerName.FIRE_BALL);
        assertEquals(300, shop.getWallet().getMoney()); //expect corret decrease
    }


}



