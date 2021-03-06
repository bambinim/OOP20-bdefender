package com.bdefender.enemy.pool;

import com.bdefender.Pair;
import com.bdefender.map.Coordinates;
import com.bdefender.map.Map;

import java.util.ArrayList;
import java.util.List;

public class MapInteractorImpl implements MapInteractor {
    private final Map map;

    public MapInteractorImpl(final Map map) {
        this.map = map;
    }

    @Override
    public final List<Coordinates> getKeyPoints() {
       final List<Coordinates> keyPoints = new ArrayList<>(map.getPath());
        keyPoints.remove(0);
        return keyPoints;
    }

    @Override
    public final Pair<Integer, Integer> getStartingDirection() {
        return this.getDirectionFromKeyPoints(map);
    }

    private Pair<Integer, Integer> getDirectionFromKeyPoints(final Map map) {
        final int initialX = map.getPath().get(0).getX().equals(map.getPath().get(1).getX()) ? 0 : 1;
        final int initialY = map.getPath().get(0).getY().equals(map.getPath().get(1).getY()) ? 0 : 1;
        return new Pair<>(initialX, initialY);
    }

    @Override
    public final Coordinates getSpawnPoint() {
        return map.getPath().get(0);
    }

}
