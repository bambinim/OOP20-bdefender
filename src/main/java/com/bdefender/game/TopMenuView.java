package com.bdefender.game;

import java.io.IOException;
import java.net.URL;

import com.bdefender.AppView;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import com.bdefender.component.ImageButton;

public class TopMenuView extends AnchorPane {

    /**
     * Menu bar height in pixel.
     */
    public static final int HEIGHT = 40;
    private static final int BUTTON_SIZE = 30;
    private final ImageButton playButton;
    private final ImageButton exitButton;

    public TopMenuView() {
        final Rectangle rec = new Rectangle();
        rec.setX(0);
        rec.setY(0);
        rec.setWidth(AppView.DEFAULT_WIDTH);
        rec.setHeight(HEIGHT);
        rec.setFill(Color.rgb(100, 70, 36));
        this.getChildren().add(rec);
        this.playButton = new ImageButton(this.loadImage(ClassLoader.getSystemResource("game/play.png")));
        this.playButton.setDisabledImage(this.loadImage(ClassLoader.getSystemResource("game/play-grey.png")));
        this.exitButton = new ImageButton(this.loadImage(ClassLoader.getSystemResource("game/times.png")));
        this.positionButtons();
    }

    private void positionButtons() {
        this.playButton.setFitWidth(BUTTON_SIZE);
        this.playButton.setFitHeight(BUTTON_SIZE);
        this.playButton.setX(30);
        this.playButton.setY(5);
        this.exitButton.setFitWidth(BUTTON_SIZE);
        this.exitButton.setFitHeight(BUTTON_SIZE);
        this.exitButton.setX(1200);
        this.exitButton.setY(5);
        this.getChildren().addAll(this.playButton, this.exitButton);
    }

    private Image loadImage(final URL imageFile) {
        Image image;
        try {
            image = new Image(imageFile.openStream());
        } catch (IOException e) {
            image = null;
        }
        return image;
    }

    /**
     * @return play button
     */
    public ImageButton getPlayButton() {
        return this.playButton;
    }

    /**
     * @return exit button
     */
    public ImageButton getExitButton() {
        return this.exitButton;
    }
}
