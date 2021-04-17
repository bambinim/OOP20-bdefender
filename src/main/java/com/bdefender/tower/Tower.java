package com.bdefender.tower;

import com.bdefender.Pair;


public interface Tower {

    class NoEnemiesAroundException extends Exception {
        private static final long serialVersionUID = 1L;

        public NoEnemiesAroundException(final String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     *
     * @return center of the shoot.
     */
    Pair<Double, Double> shoot();

    void upgradeLevel();

    long getShootSpeed();

    int getTowerId();

    Pair<Double, Double> getPos();

    Pair<Double, Double> getPosition();


}
