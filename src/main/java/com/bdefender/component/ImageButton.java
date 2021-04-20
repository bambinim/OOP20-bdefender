package com.bdefender.component;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class ImageButton extends StackPane {

    private final Image image;
    private final ImageView imageView;
    private final Label label = new Label();
    private Optional<Image> disabledImage = Optional.empty();
    private boolean enabled = true;
    private EventHandler<MouseEvent> onMouseClick;

    public ImageButton(final Image image) {
        this.image = image;
        this.imageView = new ImageView(this.image);
        this.setCursor(Cursor.HAND);
        this.setOnMouseClicked(event -> {
            if (this.enabled) {
                this.onMouseClick.handle(event.copyFor(this, event.getTarget()));
            }
        });
        this.getChildren().addAll(this.imageView, this.label);
    }

    public ImageButton(final Image image, final String label) {
        this(image);
        this.setLabel(label);
    }

    /**
     * Set button's width.
     * @param value
     */
    public void setWidth(final double value) {
        this.setMinWidth(value);
        this.setMaxWidth(value);
        this.imageView.setFitWidth(value);
    }

    /**
     * Set button's height.
     * @param value
     */
    public void setHeight(final double value) {
        this.setMinHeight(value);
        this.setMaxHeight(value);
        this.imageView.setFitHeight(value);
    }

    /**
     * Set button's X position.
     * @param value
     */
    public void setX(final double value) {
        this.setLayoutX(value);
    }

    /**
     * Set button's Y position.
     * @param value
     */
    public void setY(final double value) {
        this.setLayoutY(value);
    }

    /**
     * Enable the button.
     */
    public void enable() {
        this.enabled = true;
        this.setCursor(Cursor.HAND);
        this.imageView.setImage(this.image);
    }

    /**
     * Disable the button.
     */
    public void disable() {
        this.enabled = false;
        this.setCursor(Cursor.DEFAULT);
        if (this.disabledImage.isPresent()) {
            this.imageView.setImage(this.disabledImage.get());
        }
    }

    /**
     * Set the image to show when button is disabled.
     * @param image
     */
    public void setDisabledImage(final Image image) {
        this.disabledImage = Optional.of(image);
    }

    /**
     * Set image label text.
     * @param label
     */
    public void setLabel(final String label) {
        this.label.setText(label);
    }

    /**
     * @return image label text
     */
    public String getLabel() {
        return this.label.getText();
    }

    /**
     * @param handler
     */
    public void setOnMouseClick(final EventHandler<MouseEvent> handler) {
        this.onMouseClick = handler;
    }

    /**
     * @return event handler
     */
    public EventHandler<MouseEvent> getOnMouseClick() {
        return this.onMouseClick;
    }
}
