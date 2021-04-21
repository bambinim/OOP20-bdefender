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
        //check if money is properly increased
       final int INIT_VALUE = 300;
       Wallet wallet = new WalletImpl(INIT_VALUE);
       
       wallet.addMoney(50);
       wallet.addMoney(20);
       wallet.addMoney(2000);
       assertTrue(wallet.getMoney() == 2370);
       wallet.addMoney(0);
       assertTrue(wallet.getMoney() == 2370);
       wallet.addMoney(30);
       assertTrue(wallet.getMoney() == 2400);
    }
    @Test
    public void testWalletSubtraction() {
        //check that money is correctly decrease and that no negative amount are possible
        final int INIT_VALUE = 700;
        Wallet wallet = new WalletImpl(INIT_VALUE);
        
        wallet.subtractMoney(10);
        wallet.subtractMoney(40);
        wallet.subtractMoney(300);
        assertTrue(wallet.getMoney() == 350);
        wallet.subtractMoney(400);
        assertFalse(wallet.getMoney() == -50); //cant't be negative
        assertFalse(wallet.areMoneyEnough(900)); 
        assertTrue(wallet.areMoneyEnough(350)); //no decrease should have be done
        wallet.subtractMoney(350);
        assertTrue(wallet.getMoney() == 0);
       
        
       
    }
    @Test
    public void testMixedOperation() {
        //try addition e subctration together
        final int INIT_VALUE = 0;
        Wallet wallet = new WalletImpl(INIT_VALUE);
        assertFalse(wallet.areMoneyEnough(400));
        wallet.subtractMoney(90);
        assertTrue(wallet.getMoney() == 0);
        wallet.addMoney(60);
        wallet.addMoney(300);
        wallet.addMoney(490);
        assertTrue(wallet.getMoney() == 850);
        assertFalse(wallet.areMoneyEnough(851)); //false
        assertTrue(wallet.areMoneyEnough(850)); //true
        wallet.subtractMoney(849);
        wallet.subtractMoney(10); 
        assertTrue(wallet.getMoney() == 1); //no decrease should have be done
        
        
    }
    

}
