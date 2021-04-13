package com.bdefender.wallet;

public interface Wallet {
    void subtractMoney(int price);
    void addMoney(int value);
    boolean areMoneyEnough(int price);
    int getMoney();

}
