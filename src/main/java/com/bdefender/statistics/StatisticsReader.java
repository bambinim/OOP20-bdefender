package com.bdefender.statistics;

import com.bdefender.Pair;
import com.bdefender.map.MapType;

public interface StatisticsReader {
    Pair<MapType, Long> getLongestTimePlayed();
    Pair<MapType, Integer> getHigherstRoundEver();
    MapType getMostPlayedMap();
}
