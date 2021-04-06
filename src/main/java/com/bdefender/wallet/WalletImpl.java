package com.bdefender.wallet;

public class WalletImpl implements Wallet {
    private final int NGLIMIT = -1;
    private int userMoney;
	
    /*
	 *set the initial value of the user money
	 */
	public WalletImpl(final int initValue) {
		this.userMoney = initValue;
	}
	
	/* 
	 *subtract the prices at the user money
	 */
	
	@Override
	public void subtractMoney(final int price) {
		if (areMoneyEnough(price)) {
			this.userMoney = this.userMoney - price; 
		}
	}
	/*
	 * check if there are enough money to proceed with the purchase
	 * @return true if money are enough or false if not
	 */
	@Override
	public boolean areMoneyEnough(final int price) {
		return this.userMoney - price > NGLIMIT;
	}
	
	/*
	 * @ return the user money amount
	 */
	
	@Override
	public int getMoney() {
		return this.userMoney;
	}

}
