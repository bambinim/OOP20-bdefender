package com.bdefender.enemy.pool;


import com.bdefender.Pair;
import com.bdefender.map.Coordinates;

import java.util.List;

public interface MapInteractor {

    /**
     * @return list of map key points
     */
    List<Coordinates> getKeyPoints();

    /**
     * @return the starting direction.
     */
    Pair<Integer, Integer> getStartingDirection();

    /**
     * @return the first key point
     */
    Coordinates getSpawnPoint();

}
