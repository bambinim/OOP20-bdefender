package com.bdefender.menu;



import java.io.IOException;
import com.bdefender.event.EventHandler;
import com.bdefender.event.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;


public class LaunchMenuLoaderImpl implements LaunchMenuLoader {
    private final MenuController menuController;
    private final AnchorPane pane = new AnchorPane();
    private final Parent menuContent;
    private Parent statisticContent = null;

    public LaunchMenuLoaderImpl(final EventHandler<MouseEvent> playEvent) throws IOException {
        menuController = new MenuControllerImpl(playEvent, (e) -> this.setParentToShow(this.statisticContent));
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("menu/launchMenu.fxml"));
        loader.setController(this.menuController);
        menuContent = loader.load();

        loader = new FXMLLoader(ClassLoader.getSystemResource("menu/statisticsMenu.fxml"));
        final StatisticsMenuControllerImpl statController = new StatisticsMenuControllerImpl((e) -> this.setParentToShow(this.menuContent));
        loader.setController(statController);
        this.statisticContent = loader.load();
        this.setParentToShow(this.menuContent);
    }


    private void setParentToShow(final Parent parent) {
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
    public MenuController getController() {
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
