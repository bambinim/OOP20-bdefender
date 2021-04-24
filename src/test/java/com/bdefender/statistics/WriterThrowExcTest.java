package com.bdefender.statistics;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.bdefender.map.MapType;

public class WriterThrowExcTest {

    @Test
    public void testMissingStart() throws Exception {
        final StatisticsWriter writer = new StatisticsWriterImpl();
        Assertions.assertThrows(IllegalStateException.class, () -> writer.saveStatistics());
    }

    @Test
    public void testMissingFinish() throws Exception {
        final StatisticsWriter writer = new StatisticsWriterImpl();
        writer.gameStarted(MapType.COUNTRYSIDE);
        Assertions.assertThrows(IllegalStateException.class, () -> writer.saveStatistics());
    }

}
