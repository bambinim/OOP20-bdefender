package com.bdefender.map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MapTest {

    @Test
    public void loadMapCountryside() {
        final Map map = MapLoader.getInstance().loadMap(MapType.COUNTRYSIDE);
        // test path
        assertEquals(map.getPath(), List.of(new Coordinates(0.0, 9.0), new Coordinates(7.0, 9.0)));
        // test tower boxes
        final List<TowerBox> towerBoxes = new ArrayList<TowerBox>();
        for (double i = 4 ; i < 8; i+=2) {
            for (double j = 15; j < 22; j+=2) {
                towerBoxes.add(new TowerBox(new Coordinates(j, i)));
            }
        }
        assertEquals(map.getTowerBoxes(), towerBoxes);
    }

    @Test
    public void loadMapIcePlain() {
        final Map map = MapLoader.getInstance().loadMap(MapType.ICEPLAIN);
    }
}
