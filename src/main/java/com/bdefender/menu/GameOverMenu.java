package com.bdefender.menu;


import java.io.IOException;
import java.net.URL;
import com.bdefender.AppView;
import com.bdefender.component.ImageButton;
import com.bdefender.event.EventHandler;
import com.bdefender.event.MouseEvent;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameOverMenu extends AnchorPane {

    private static final String FONT = "MV Boli";
    private static final int PARCHMENT_HEIGHT = 560;
    private static final int PARCHMENT_WIDTH = 440;
    private static final int PARCHMENT_Y_POSITION = 150;
    private static final int PARCHMENT_X_POSITION = AppView.DEFAULT_HEIGHT / 2;
    private EventHandler<MouseEvent> backToMenuEvent;

    public GameOverMenu(final int round) {
        this.setContent(round);
    }

    private void setContent(final int round) {
        //background
        final double backgroundOpacity = 0.4;
        final Rectangle backgroundRec = new Rectangle();
        backgroundRec.setWidth(AppView.DEFAULT_WIDTH);
        backgroundRec.setHeight(AppView.DEFAULT_HEIGHT);
        backgroundRec.setFill(Color.WHITE);
        backgroundRec.opacityProperty().setValue(backgroundOpacity);

        //parchment
        final ImageView parchment = new ImageView(loadImage(ClassLoader.getSystemResource("menu/img/parchment.png")));
        parchment.setFitWidth(PARCHMENT_WIDTH);
        parchment.setFitHeight(PARCHMENT_HEIGHT);
        parchment.setY(PARCHMENT_Y_POSITION);
        parchment.setX(PARCHMENT_X_POSITION);

        //back to menu btn
        final int backToMenuBtnWidth = 150;
        final int backToMenuBtnHeight = 75;
        final ImageButton backToMenuBtn = new ImageButton(loadImage(ClassLoader.getSystemResource("menu/img/backButton.png")));
        backToMenuBtn.setWidth(backToMenuBtnWidth);
        backToMenuBtn.setHeight(backToMenuBtnHeight);
        backToMenuBtn.setX(PARCHMENT_X_POSITION + PARCHMENT_WIDTH / 2);
        backToMenuBtn.setY(PARCHMENT_Y_POSITION + PARCHMENT_HEIGHT);
        backToMenuBtn.setOnMouseClick((e) -> {
            this.backToMenuEvent.handle(new MouseEvent(MouseEvent.MOUSE_CLICKED, e));
            });

        //label
        final Label gameOverLbl = this.gameOverLabel("GAME OVER", 36);
        gameOverLbl.setLayoutX(PARCHMENT_X_POSITION + PARCHMENT_X_POSITION / 4);
        gameOverLbl.setLayoutY(PARCHMENT_Y_POSITION * 2);
        final Label scoreLbl = this.gameOverLabel("Round:  " + Integer.toString(round), 24);
        scoreLbl.setLayoutX(PARCHMENT_X_POSITION + PARCHMENT_X_POSITION / 4);
        scoreLbl.setLayoutY(PARCHMENT_Y_POSITION * 3);

        this.getChildren().addAll(backgroundRec, parchment, backToMenuBtn, gameOverLbl, scoreLbl);

    }



    private Label gameOverLabel(final String text, final int fontSize) {
        final Label label = new Label();
        label.setText(text);
        label.setFont(Font.font(FONT, fontSize));
        label.setTextFill(Color.BLACK);
        return label;
    }

    /**
     *Set the event to "back to the menu" action. 
     * @param event
     */
    public void setBackToMenuEvent(final EventHandler<MouseEvent> event) {
        this.backToMenuEvent = event;
    }


    private Image loadImage(final URL url) {
        Image imagebase = null;
        try {
            imagebase = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagebase;
    }


}
