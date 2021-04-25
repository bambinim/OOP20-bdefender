package com.bdefender;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import java.io.IOException;
import com.bdefender.game.GameController;
import com.bdefender.game.GameControllerImpl;
import com.bdefender.menu.LaunchMenuLoader;
import com.bdefender.menu.LaunchMenuLoaderImpl;
import javafx.stage.Screen;

public class AppView extends Application {
    /**
     * Default stage HEIGHT.
     */
    public static final int DEFAULT_HEIGHT = 802;
    /**
     * Default stage WIDTH.
     */
    public static final int DEFAULT_WIDTH = 1280;
    private static final double WINDOW_WIDTH_MULTIPLIER = 0.75; 
    private Stage primaryStage;
    private GameController gameController;
    private LaunchMenuLoader menuLoader;
 
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
        this.primaryStage.setScene(new Scene(this.root));
    }

    private void setWindowSize() {
        System.out.println("Screen width: " + Double.toString(Screen.getPrimary().getBounds().getWidth()));
        System.out.println("Screen height: " + Double.toString(Screen.getPrimary().getBounds().getHeight()));
        this.primaryStage.setWidth(Screen.getPrimary().getBounds().getWidth() * WINDOW_WIDTH_MULTIPLIER);
        this.primaryStage.setHeight(DEFAULT_HEIGHT * this.primaryStage.getWidth() / DEFAULT_WIDTH);
    }

    private void startGame() throws IOException {
        this.gameController = new GameControllerImpl(menuLoader.getController().getSelectedMap());
        this.gameController.setOnGameFinish((e) -> {
            this.startMenu();
        });
        //kill all thread before close the windows
        primaryStage.setOnCloseRequest((e) -> {
            if (this.gameController.isRunning()) {
                this.gameController.closeAllThread();
            }
        });
        this.setContent(this.gameController.getView());
    }

    /**
     * Start main UI.
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Base Defender");
        this.primaryStage.setResizable(true);
        //this.primaryStage.setWidth(DEFAULT_WIDTH);
        //this.primaryStage.setHeight(DEFAULT_HEIGHT);
        this.initializeView();
        this.setWindowSize();
        this.primaryStage.show();
        this.startMenu();
    }

    private void startMenu() {
        try {
            this.menuLoader = new LaunchMenuLoaderImpl((e) -> {
                try {
                    this.startGame();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            this.setContent(this.menuLoader.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set window content. Every time it creates a new scene.
     * @param parent - Content
     */
    public void setContent(final Parent parent) {
        this.root.getChildren().clear();
        this.root.getChildren().add(parent);
    }


    public static void run(final String[] args) {
        AppView.launch(args);
    }
}
