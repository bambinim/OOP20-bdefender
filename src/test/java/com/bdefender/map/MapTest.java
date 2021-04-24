package com.bdefender.map;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.Start;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
public class MapTest {

    private Map map;
    private MapView mapView;

    @Start
    private void start(final Stage stage) {
        Platform.runLater(() -> {
            this.map = MapLoader.getInstance().loadMap(MapType.COUNTRYSIDE);
            this.mapView = new MapView(map);
            stage.setWidth(MapView.MAP_WIDTH);
            stage.setHeight(MapView.MAP_WIDTH);
            stage.setScene(new Scene(mapView));
            stage.show();
        });
    }

    @Test
    public void loadMapCountryside() {
        final Map map = MapLoader.getInstance().loadMap(MapType.COUNTRYSIDE);
        // test path
        assertEquals(map.getPath(), List.of(new Coordinates(0.0, 9.0), new Coordinates(7.0, 9.0), new Coordinates(7.0, 18.0),
                new Coordinates(14.0, 18.0), new Coordinates(14.0, 3.0)));
        // test tower boxes
        final List<TowerBox> towerBoxes = new ArrayList<>();
        for (double i = 4 ; i < 8; i+=2) {
            for (double j = 15; j < 22; j+=2) {
                towerBoxes.add(new TowerBox(new Coordinates(j, i)));
            }
        }
        assertEquals(map.getTowerBoxes(), towerBoxes);
        assertNotNull(map.getMapImage());
    }

    @Test
    public void loadMapIcePlain() {
        final Map map = MapLoader.getInstance().loadMap(MapType.ICEPLAIN);
        // test path
        assertEquals(map.getPath(), List.of(new Coordinates(3.0, 0.0), new Coordinates(3.0, 8.0)));
        /// test tower boxes
        final List<TowerBox> towerBoxes = new ArrayList<>();
        for (double i = 0; i < 9; i += 2) {
            for (double j = 0; j < 1; j += 2) {
                towerBoxes.add(new TowerBox(new Coordinates(j, i)));
            }
        }
        assertEquals(map.getTowerBoxes(), towerBoxes);
        assertNotNull(map.getMapImage());
    }

    @Test
    public void testTowerPlacementBoxClick(final FxRobot robot) {
        Platform.runLater(() -> {
            final TowerBox tb = this.map.getEmptyTowerBoxes().get(0);
            this.mapView.setTowerPlacementViewVisible(true);
            this.mapView.getTowerPlacementView().setOnBoxClick(event -> {
                assertEquals(tb, event.getSource());
            });
            robot.clickOn(this.mapView.getTowerPlacementView().getChildren().get(0));
        });
    }
}
