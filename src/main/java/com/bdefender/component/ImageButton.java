package com.bdefender.component;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends ImageView {

    private Image image;
    private Optional<Image> disabledImage = Optional.empty();
    private boolean enabled = true;
    private EventHandler<MouseEvent> onMouseClick;

    public ImageButton(final Image image) {
        super(image);
        this.image = image;
        this.setCursor(Cursor.HAND);
        super.setOnMouseClicked(event -> {
            if (this.enabled) {
                this.onMouseClick.handle(event.copyFor(this, event.getTarget()));
            }
        });
    }

    /**
     * Enable the button.
     */
    public void enable() {
        this.enabled = true;
        this.setCursor(Cursor.HAND);
        this.setImage(this.image);
    }

    /**
     * Disable the button.
     */
    public void disable() {
        this.enabled = false;
        this.setCursor(Cursor.DEFAULT);
        if (this.disabledImage.isPresent()) {
            this.setImage(this.disabledImage.get());
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
