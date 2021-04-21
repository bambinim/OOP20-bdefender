package com.defender.shop;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.bdefender.shop.Shop;
import com.bdefender.shop.ShopImpl;
import com.bdefender.wallet.Wallet;
import com.bdefender.wallet.WalletImpl;

public class WalletTest {
    @Test
    public void testWalletAddition() {
       final int INIT_VALUE = 300;
       Wallet wallet = new WalletImpl(INIT_VALUE);
       
       wallet.addMoney(50);
       wallet.addMoney(20);
       wallet.addMoney(2000);
       assertTrue(wallet.getMoney() == 2370);
       wallet.addMoney(0);
       assertTrue(wallet.getMoney() == 2370);
    }
    @Test
    public void testWalletSubtraction() {
        final int INIT_VALUE = 700;
        Wallet wallet = new WalletImpl(INIT_VALUE);
        
        wallet.subtractMoney(10);
        wallet.subtractMoney(40);
        wallet.subtractMoney(300);
        assertTrue(wallet.getMoney() == 350);
        wallet.subtractMoney(400);
        assertFalse(wallet.getMoney() == -50);
        assertFalse(wallet.areMoneyEnough(900));
        assertTrue(wallet.areMoneyEnough(350));
        wallet.subtractMoney(350);
        assertTrue(wallet.getMoney() == 0);
       
        
       
    }
    @Test
    public void testMixedOperation() {
        final int INIT_VALUE = 0;
        Wallet wallet = new WalletImpl(INIT_VALUE);
        assertFalse(wallet.areMoneyEnough(400));
        wallet.subtractMoney(90);
        assertTrue(wallet.getMoney() == 0);
        wallet.addMoney(60);
        assertTrue(wallet.getMoney() == 60);
        
    }
    

}
