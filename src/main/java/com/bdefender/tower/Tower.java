package com.bdefender.tower;

import com.bdefender.Pair;

import java.util.Set;

public interface Tower {
	
	public class NoEnemiesAroundException extends Exception {
		private static final long serialVersionUID = 1L;
		
		public NoEnemiesAroundException(String errorMessage) {
			super(errorMessage);
		}
	}

	/**
	 *
	 * @return center of the shoot.
	 */
	public Pair<Double, Double> shoot();
	
	public void upgradeLevel();
	
	public long getShootSpeed();

	public int getTowerId();
}
