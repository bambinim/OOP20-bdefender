package com.bdefender.test.guariglia;

import java.io.IOException;

import com.bdefender.map.MapType;
import com.bdefender.statistics.StatisticsWriter;
import com.bdefender.statistics.StatisticsWriterImpl;

public class StatisticsTest {

    public static void main(final String[] args) {
        
        StatisticsWriter statLog = null;
        statLog = new StatisticsWriterImpl();
        
        statLog.gameStarted(MapType.COUNTRYSIDE);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        statLog.gameFinished(60);
        try {
            statLog.saveStatistics();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
