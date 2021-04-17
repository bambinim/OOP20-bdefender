package com.bdefender.map;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public final class MapLoader {

    private static final MapLoader MAP_LOADER = new MapLoader();

    private MapLoader() { }

    public static MapLoader getInstance() {
        return MapLoader.MAP_LOADER;
    }

    /**
     * Loads specified map.
     * @param map
     * @return loaded map
     */
    public Map loadMap(final MapType map) {
        return new Map(this.loadMapImage(ClassLoader.getSystemResource(String.format("maps/%d/map.png", map.getMapNumber()))),
                this.loadPath(ClassLoader.getSystemResource(String.format("maps/%d/path.txt", map.getMapNumber()))),
                this.loadTowerBoxes(ClassLoader.getSystemResource(String.format("maps/%d/towerboxes.txt", map.getMapNumber()))));
    }

    private Image loadMapImage(final URL imageFile) {
        Image mapImage;
        try {
            mapImage = new Image(imageFile.openStream());
        } catch (IOException e) {
            mapImage = null;
        }
        return mapImage;
    }

    private List<Coordinates> loadPath(final URL coordsFile) {
        List<Coordinates> path = new ArrayList<>();
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(coordsFile.openStream()));
            String line = reader.readLine();
            while (line != null) {
                path.add(this.parseCoordinate(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            path = List.of();
        }
        return path;
    }

    private List<TowerBox> loadTowerBoxes(final URL towerBoxesFile) {
        List<TowerBox> towerBoxes = new ArrayList<>();
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(towerBoxesFile.openStream()));
            String line = reader.readLine();
            while (line != null) {
                final String[] tmp = line.split("-"); 
                if (tmp.length > 1) {
                    final Coordinates start = this.parseCoordinate(tmp[0]);
                    final Coordinates end = this.parseCoordinate(tmp[1]);
                    for (double i = start.getY(); i < end.getY(); i += 2) {
                        for (double j = start.getX(); j < end.getX(); j += 2) {
                            towerBoxes.add(new TowerBox(new Coordinates(j, i)));
                        }
                    }
                } else {
                    towerBoxes.add(new TowerBox(this.parseCoordinate(tmp[0])));
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            towerBoxes = List.of();
        }
        return towerBoxes;
    }

    private Coordinates parseCoordinate(final String line) {
        final String[] tmp = line.split("\\|");
        return new Coordinates(Double.valueOf(tmp[0]), Double.valueOf(tmp[1]));
    }
}
