package com.bdefender;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.bdefender.game.GameController;
import com.bdefender.game.GameControllerImpl;
import com.bdefender.map.Map;

public class AppView extends Application {
    /**
     * Default stage HEIGHT.
     */
    public static final int DEFAULT_IMG_HEIGHT = 760;
    /**
     * Default stage WIDTH.
     */
    public static final int DEFAULT_IMG_WIDTH = 1280;
    private Stage primaryStage;
    private GameController gameController;

    private void startGame() {
        this.gameController = new GameControllerImpl(Map.ICE_PLAIN);
        this.setContent(this.gameController.getView());
    }

    /**
     * Start main UI.
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setWidth(DEFAULT_IMG_WIDTH);
        this.primaryStage.setHeight(DEFAULT_IMG_HEIGHT);
        this.primaryStage.setTitle("Base Defender");
        this.primaryStage.setResizable(true);
        this.primaryStage.show();
        this.startGame();
    }

    /**
     * Set window content. Every time it creates a new scene.
     * @param parent - Content
     */
    public void setContent(final Parent parent) {
        this.primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            parent.setScaleX(this.primaryStage.getWidth() / AppView.DEFAULT_IMG_WIDTH);
        });
        this.primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            parent.setScaleY(this.primaryStage.getHeight() / AppView.DEFAULT_IMG_HEIGHT);
        });
        this.primaryStage.setScene(new Scene(parent));
    }

    public static void run(final String[] args) {
        AppView.launch(args);
    }
}
