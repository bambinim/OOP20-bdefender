package com.defender.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.bdefender.shop.Shop;
import com.bdefender.shop.ShopImpl;
import com.bdefender.wallet.Wallet;
import com.bdefender.wallet.WalletImpl;

public class WalletTest {
    int INIT_VALUE = 0;
    Wallet wallet;
    @Test
    public void testWalletAddition() {
        //check if money is properly increased
       INIT_VALUE = 300;
       this.wallet = new WalletImpl(INIT_VALUE);
       this.wallet.addMoney(50);
       this.wallet.addMoney(20);
       this.wallet.addMoney(2000);
       assertEquals(2370, this.wallet.getMoney());
       wallet.addMoney(0);
       assertEquals(2370, this.wallet.getMoney());
       wallet.addMoney(30);
       assertEquals(2400, this.wallet.getMoney());
    }

    @Test
    public void testWalletSubtraction() {
        //check that money is correctly decrease and that no negative amount are possible
        INIT_VALUE = 700;
        this.wallet = new WalletImpl(INIT_VALUE);
        wallet.subtractMoney(10);
        wallet.subtractMoney(40);
        wallet.subtractMoney(300);
        assertEquals(350, this.wallet.getMoney());
        wallet.subtractMoney(400);
        assertNotEquals(-50, this.wallet.getMoney()); //cant't be negative
        assertFalse(wallet.areMoneyEnough(900)); 
        assertTrue(wallet.areMoneyEnough(350)); //no decrease should have be done
        wallet.subtractMoney(350);
        assertEquals(0, this.wallet.getMoney());
    }

    @Test
    public void testMixedOperation() {
        //try addition e subctration together
        final int INIT_VALUE = 0;
        this.wallet = new WalletImpl(INIT_VALUE);
        assertFalse(wallet.areMoneyEnough(400));
        wallet.subtractMoney(90);
        assertEquals(0, this.wallet.getMoney());
        wallet.addMoney(60);
        wallet.addMoney(300);
        wallet.addMoney(490);
        assertEquals(850, this.wallet.getMoney());
        assertFalse(wallet.areMoneyEnough(851)); //false
        assertTrue(wallet.areMoneyEnough(850)); //true
        wallet.subtractMoney(849);
        wallet.subtractMoney(10); 
        assertEquals(1, this.wallet.getMoney());//no decrease should have be done

    }


}
