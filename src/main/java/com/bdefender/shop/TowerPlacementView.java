package com.bdefender.shop;

import java.util.List;
import java.util.stream.Collectors;
import com.bdefender.map.TowerBox;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

public class TowerPlacementView extends AnchorPane {

    private static final int RECTANGLE_SIZE = 60;
    private static final double RECTANGLE_OPACITY = 0.5;
    private EventHandler<MouseEvent> onBoxClick;

    public TowerPlacementView(final List<TowerBox> towerBoxes) {
        this.generateRectangles(towerBoxes);
    }

    private void generateRectangles(final List<TowerBox> towerBoxes) {
        this.getChildren().addAll(towerBoxes.stream().map(el -> {
            final Rectangle rec = new Rectangle();
            rec.setWidth(RECTANGLE_SIZE);
            rec.setHeight(RECTANGLE_SIZE);
            rec.setX(el.getTopLeftCoord().getLeftPixel() + 2);
            rec.setY(el.getTopLeftCoord().getTopPixel() + 2);
            rec.setFill(Color.GRAY);
            rec.setOnMouseEntered(e -> rec.setFill(Color.GREEN));
            rec.setOnMouseExited(e -> rec.setFill(Color.GRAY));
            rec.setCursor(Cursor.HAND);
            rec.opacityProperty().setValue(RECTANGLE_OPACITY);
            rec.setOnMouseClicked(event -> {
                this.onBoxClick.handle(event.copyFor(el, event.getTarget()));
            });
            return rec;
        }).collect(Collectors.toList()));
    }

    /**
     * Set event handler to call when box is clicked.
     * @param handler
     */
    public void setOnBoxClick(final EventHandler<MouseEvent> handler) {
        this.onBoxClick = handler;
    }

    /**
     * Return the event handler called when box is clicked.
     * @return event handler
     */
    public EventHandler<MouseEvent> getOnBoxClick() {
        return this.onBoxClick;
    }

    /**
     * Reload tower boxes.
     * @param towerBoxes
     */
    public void reload(final List<TowerBox> towerBoxes) {
        this.getChildren().clear();
        this.generateRectangles(towerBoxes);
    }

}
