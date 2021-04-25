package com.bdefender.statistics;

import com.bdefender.Pair;
import com.bdefender.map.MapType;

public interface StatisticsReader {
    Pair<MapType, Integer> getHigherstRoundEver();
    MapType getMostPlayedMap();
    Long getTotTimePlayed();
}
