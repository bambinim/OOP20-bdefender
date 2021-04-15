package com.bdefender;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import com.bdefender.game.GameController;
import com.bdefender.game.GameControllerImpl;
import com.bdefender.map.Map;

public class AppView extends Application {
    /**
     * Default stage HEIGHT.
     */
    public static final int DEFAULT_HEIGHT = 802;
    /**
     * Default stage WIDTH.
     */
    public static final int DEFAULT_WIDTH = 1280;
    private Stage primaryStage;
    private GameController gameController;
    private final GridPane root = new GridPane();

    private void initializeView() {
        this.root.setAlignment(Pos.CENTER);
        this.root.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.root.setScaleX(this.primaryStage.getWidth() / AppView.DEFAULT_WIDTH);
        });
        this.primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            this.root.setScaleY(this.primaryStage.getHeight() / AppView.DEFAULT_HEIGHT);
        });
        AnchorPane.setTopAnchor(this.root, 0.0);
        AnchorPane.setBottomAnchor(this.root, 0.0);
        AnchorPane.setLeftAnchor(this.root, 0.0);
        AnchorPane.setRightAnchor(this.root, 0.0);
    }

    // TODO: move to controller
    private void startGame() {
        this.gameController = new GameControllerImpl(Map.COUNTRYSIDE);
        this.setContent(this.gameController.getView());
    }

    /**
     * Start main UI.
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setWidth(DEFAULT_WIDTH);
        this.primaryStage.setHeight(DEFAULT_HEIGHT);
        this.primaryStage.setTitle("Base Defender");
        this.primaryStage.setResizable(true);
        this.primaryStage.show();
        this.initializeView();
        this.startGame();
    }

    /**
     * Set window content. Every time it creates a new scene.
     * @param parent - Content
     */
    public void setContent(final Parent parent) {
        this.root.getChildren().clear();
        this.root.getChildren().add(parent);
        this.primaryStage.setScene(new Scene(this.root));
    }

    public static void run(final String[] args) {
        AppView.launch(args);
    }
}
