package com.bdefender.wallet;

public class WalletImpl implements Wallet {
    private static final int NEG_LIMIT = -1;
    private int userMoney;
	
	 /**Set the initial value of the user money.
	 *@param initValue the initial amount of money.
	 */
	public WalletImpl(final int initValue) {
		this.userMoney = initValue;
	}
	
	/**
	 *Subtract the prices at the user money value.
	 */
	@Override
    public final void subtractMoney(final int price) {
		if (areMoneyEnough(price)) {
			this.userMoney = this.userMoney - price; 
		}
	}
	/**
	 * Check if there is enough money to proceed with the purchase.
	 * @return true if money are enough or false if not
	 */
	@Override
    public final boolean areMoneyEnough(final int price) {
		return this.userMoney - price > NEG_LIMIT;
	}
	
	/**
	 * @ return the user money amount.
	 */
	@Override
    public final int getMoney() {
		return this.userMoney;
	}
	/**
     * @param value money value that has to be added.
     */
    @Override
    public final void addMoney(final int value) {
        this.userMoney = this.userMoney + value;
    }

}
