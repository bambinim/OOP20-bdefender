package com.defender.shop;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.bdefender.game.TowerName;
import com.bdefender.shop.Shop;
import com.bdefender.shop.ShopImpl;
import com.bdefender.wallet.Wallet;
import com.bdefender.wallet.WalletImpl;

public class ShopTest {
    @Test
    public void testBuyTower() {
        
        final int INIT_AMOUNT = 100;
        Wallet wallet = new WalletImpl(INIT_AMOUNT);
        Shop shop = new ShopImpl(wallet);
        shop.buyTower(TowerName.FIRE_ARROW); //price 200
        assertTrue(shop.getWallet().getMoney() == INIT_AMOUNT); //expected no decrease
        assertFalse(shop.canBuyTower(TowerName.FIRE_ARROW)); //no enough money
        assertTrue(shop.canBuyTower(TowerName.FIRE_BALL)); //price 100
        shop.getWallet().addMoney(300);
        assertTrue(shop.canBuyTower(TowerName.FIRE_BALL)); //money are enough to buy tower
        shop.buyTower(TowerName.FIRE_BALL);
        assertTrue(shop.getWallet().getMoney() == 300 ); //expect corret decrease
    }

}
