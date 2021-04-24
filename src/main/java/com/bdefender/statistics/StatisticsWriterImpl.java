package com.bdefender.statistics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;


import com.bdefender.map.MapType;

public class StatisticsWriterImpl implements StatisticsWriter {
    private static final String SEPARATOR_CHARATTER = FileInformation.getSeparatorCharatter();
    private Optional<Long> startTime = Optional.empty();
    private Optional<MapType> playedMap = Optional.empty();
    private Optional<Long> totTimePlayed = Optional.empty();
    private Optional<Integer> round = Optional.empty();


    /**
     * notify to StatisticsLogger the start of the match.
     * @throws IllegalStateException
     */
    @Override
    public void gameStarted(final MapType map) {
        this.playedMap = Optional.of(map);
        if (this.startTime.isPresent()) {
            throw new IllegalStateException();
        }
        this.startTime = Optional.of(System.currentTimeMillis());
    }

    /**
     * notify to StatisticsLogger the end of the match.
     * @throws IllegalStateException
     */
    @Override
    public void gameFinished(final int round) {
        if (this.totTimePlayed.isPresent()) {
            throw new IllegalStateException();
        }
        this.round = Optional.of(round);
        final Long totTimeTMP = System.currentTimeMillis() - this.startTime.get();
        totTimePlayed = Optional.of(totTimeTMP);
    }

    /**
     * Write game statistic in the statistics file.
     * @throws Exception 
     */
    public void saveStatistics() throws IOException {
        //check all value needed
        if (this.playedMap.isEmpty() || this.totTimePlayed.isEmpty() || this.round.isEmpty()) {
            throw new IllegalStateException("Missing values");
        }
        this.appendLog(this.playedMap.get().getMapName() + this.SEPARATOR_CHARATTER 
                + Long.toString(this.totTimePlayed.get()) + this.SEPARATOR_CHARATTER
                + Integer.toString(this.round.get()));
    }

    private void appendLog(final String s) throws IOException {
        final File file = new File(FileInformation.STATISTIC_FILE.getCompletePath());
        final FileWriter fw = new FileWriter(file, true);
        fw.append(s + "\n");
        fw.close();
        }




}
