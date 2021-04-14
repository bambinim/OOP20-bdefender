package com.bdefender.tower;

import com.bdefender.Pair;

import java.util.List;
import java.util.Set;

public interface Tower {
	
	public class NoEnemiesAroundException extends Exception {
		private static final long serialVersionUID = 1L;
		
		public NoEnemiesAroundException(String errorMessage) {
			super(errorMessage);
		}
	}

	public List<Pair<Double, Double>> shoot();
	
	public void upgradeLevel();
	
	public long getShootSpeed();

	public int getTowerId();

	public Pair<Double, Double> getPos();

}
