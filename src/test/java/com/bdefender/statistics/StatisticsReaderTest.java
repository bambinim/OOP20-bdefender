package com.bdefender.statistics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.bdefender.map.MapType;

public class StatisticsReaderTest {
    @Test
    public void testDefaultValues() {
        this.resetStatistics();
        final StatisticsReader reader = StatisticsReaderImpl.getInstance();
        Assertions.assertEquals(0, reader.getTotTimePlayed());
        Assertions.assertEquals(MapType.COUNTRYSIDE, reader.getMostPlayedMap());
        Assertions.assertEquals(0, reader.getHigherstRoundEver().getY());
    }

    private void resetStatistics() {
        final File file = new File(FileInformation.STATISTIC_FILE.getCompletePath());
        try {
            final FileWriter fw = new FileWriter(file);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
