package com.bdefender.tower;

import com.bdefender.Pair;

public interface Tower {

    /**
     * @return center of the shoot.
     */
    Pair<Double, Double> shoot();

    /**
     * @return level before the upgrade.
     */
    int upgradeLevel();

    /**
     * @return number of shoot every 10 seconds
     */
    long getShootSpeed();

    /**
     * @return tower type ID.
     */
    int getTowerTypeId();

    /**
     * @return tower position
     */
    Pair<Double, Double> getPosition();

    class NoEnemiesAroundException extends Exception {
        private static final long serialVersionUID = 1L;

        public NoEnemiesAroundException(final String errorMessage) {
            super(errorMessage);
        }
    }

}
