package com.bdefender.statistics;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.bdefender.Pair;
import com.bdefender.map.MapType;

public final class StatisticsReaderImpl implements StatisticsReader {


    private Scanner scanner;
    private final List<Game> gameList = new ArrayList<>();
    private static final StatisticsReader INSTANCE = new StatisticsReaderImpl();

    private StatisticsReaderImpl() {
        final File file = new File(FileInformation.STATISTIC_FILE.getCompletePath());
        //se non esiste il file lo crea
        if (!file.exists()) { 
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //istanzia scanner
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.loadInformation();
    }

    /**
     * Load all information from the file.
     */
    private void loadInformation() {
        while (this.scanner.hasNextLine()) {
            final String line = this.scanner.nextLine();
            final String[] tmp = line.split("\\" + FileInformation.getSeparatorCharatter());
            final MapType mapReaded = List.of(MapType.values()).stream()
                    .filter((m) -> m.getMapName().equals(tmp[0]))
                    .findFirst().get();
            this.gameList.add(new Game(mapReaded, Long.parseLong(tmp[1]), Integer.parseInt(tmp[2])));
        }
    }
    public static StatisticsReader getInstance() {
        return INSTANCE;
    }

    @Override
    public Long getTotTimePlayed() { 
        Long totTime = 0L;
        for (final Game game : gameList) {
            totTime = totTime + game.getDuration();
        }
        return totTime;
    }

    @Override
    public Pair<MapType, Integer> getHigherstRoundEver() {
        //se non ci sono partite
        if (this.gameList.isEmpty()) {
            return new Pair<>(MapType.COUNTRYSIDE, 0);
        }
        final Game higherstGame = this.gameList.stream()
                .sorted((g1, g2) -> g2.getRound() - g1.getRound())
                .findFirst().get();
        return new Pair<>(higherstGame.getMap(), higherstGame.getRound());
    }

    @Override
    public MapType getMostPlayedMap() {
        //se non ci sono partite
        if (this.gameList.isEmpty()) {
            return MapType.COUNTRYSIDE;
        }
        final Map<MapType, Integer> counterMap = new HashMap<>();
        //carico tutte le mappe
        List.of(MapType.values()).stream()
            .forEach((mapType) -> counterMap.put(mapType, 0));
        //conto le occorrenze
        this.gameList.stream()
            .forEach((game) -> counterMap.put(game.getMap(), counterMap.get(game.getMap()) + 1));
        return counterMap.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .findFirst().get().getKey();
    }

    private class Game {


        private final MapType map;
        private final Long duration;
        private final int round;

        Game(final MapType map, final Long duration, final int round) {
            this.map = map;
            this.duration = duration;
            this.round = round;
        }

        public MapType getMap() {
            return map;
        }

        public Long getDuration() {
            return duration;
        }

        public int getRound() {
            return round;
        }

        @Override
        public String toString() {
            return "Game [map=" + map.getMapName() + ", duration=" + duration + ", round=" + round + "]";
        }
    }

}

