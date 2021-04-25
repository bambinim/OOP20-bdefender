package com.bdefender.menu;



import java.io.IOException;
import com.bdefender.event.EventHandler;
import com.bdefender.event.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;


public class LaunchMenuLoaderImpl implements LaunchMenuLoader {
    private final MenuViewManager menuController;
    private final AnchorPane pane = new AnchorPane();
    private final Parent menuContent;
    private Parent statisticContent = null;
    private final StatisticsMenuController statController;

    public LaunchMenuLoaderImpl(final EventHandler<MouseEvent> playEvent) throws IOException {
        menuController = new MenuViewManagerImpl(playEvent, (e) -> {
            this.setParentToShow(this.statisticContent);
            });
        final FXMLLoader menuLoader = new FXMLLoader(ClassLoader.getSystemResource("menu/launchMenu.fxml"));
        menuLoader.setController(this.menuController);
        menuContent = menuLoader.load();

        statController = new StatisticsMenuControllerImpl((e) -> this.setParentToShow(this.menuContent));
        final FXMLLoader statLoader = new FXMLLoader(ClassLoader.getSystemResource("menu/statisticsMenu.fxml"));
        statLoader.setController(statController);
        this.statisticContent = statLoader.load();
        this.setParentToShow(this.menuContent);
    }


    private void setParentToShow(final Parent parent) {
        this.statController.loadStat();
        if (this.pane.getChildren().contains(this.statisticContent)) {
            this.pane.getChildren().remove(this.statisticContent);
        }
        if (this.pane.getChildren().contains(this.menuContent)) {
            this.pane.getChildren().remove(this.menuContent);
        }
        this.pane.getChildren().add(parent);
    }


    /**
     * Get the controller of the loaded GUI.
     * @return menuController
     */
    public MenuViewManager getViewManager() {
        return this.menuController;
    }


    /**
     * return the FXML loaded in an AnchorPane.
     * @return Parent
     */
    public Parent getParent() {
        return this.pane;

    }

}
