package com.bdefender.statistics;

import com.bdefender.map.MapType;

public interface StatisticsWriter {
    void gameStarted(MapType map);
    void gameFinished(int round); 
    void saveStatistics() throws Exception;

}
