package com.bdefender.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.Start;
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.api.FxRobot;

public class MapGuiTest {

    private Map map;
    private MapView mapView;

    @Start
    private void start(final Stage stage) {
        this.map = MapLoader.getInstance().loadMap(MapType.COUNTRYSIDE);
        this.mapView = new MapView(map);
        stage.setScene(new Scene(mapView));
        stage.show();
    }

    @Test
    public void testTowerPlacementBoxClick(final FxRobot robot) {
        final TowerBox tb = this.map.getEmptyTowerBoxes().get(0);
        this.mapView.setTowerPlacementViewVisible(true);
        this.mapView.getTowerPlacementView().setOnBoxClick(event -> {
            assertEquals(tb, event.getSource());
        });
        robot.clickOn(this.mapView.getTowerPlacementView().getChildren().get(0));
    }
}
