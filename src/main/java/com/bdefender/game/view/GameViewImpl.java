package com.bdefender.game.view;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import com.bdefender.component.ImageButton;
import com.bdefender.menu.GameOverMenu;
import com.bdefender.shop.ShopLoader;
import com.bdefender.event.MouseEvent;
import com.bdefender.map.Map;
import com.bdefender.map.view.MapViewImpl;
import com.bdefender.map.view.MapView;
import com.bdefender.event.EventHandler;

public class GameViewImpl extends AnchorPane implements GameView {

    private final TopMenuView topMenuView;
    private final MapView mapView;
    private final Parent shopView;
    private final ImageButton btnShop;
    private final ImageButton btnBackToMenu;
    private final ImageButton btnPlay;



    public GameViewImpl(final Map map, final ShopLoader shopLoader) {
        this.topMenuView = new TopMenuView();
        this.shopView = shopLoader.getShopView();
        var mapView = new MapViewImpl(map);
        mapView.setLayoutY(TopMenuView.HEIGHT);
        this.mapView = mapView;
        this.shopView.setLayoutY(TopMenuView.HEIGHT);
        this.btnShop = this.topMenuView.getShopButton();
        this.btnBackToMenu = this.topMenuView.getExitButton();
        this.btnPlay = this.topMenuView.getPlayButton();
        this.getChildren().addAll(mapView, this.topMenuView, this.shopView);
    }

    /**
     * Show the game over of the game, show also the level passed.
     * @param round
     * @param event
     */
    @Override
    public void showGameOverMenu(final int round, final EventHandler<MouseEvent> event) {
        final GameOverMenu gameOverMenu = new GameOverMenu(round);
        gameOverMenu.setBackToMenuEvent(event);
        Platform.runLater(() -> this.getChildren().add(gameOverMenu));
    }

    /**
     * @return top menu view
     */
    @Override
    public TopMenuView getTopMenuView() {
        return this.topMenuView;
    }

   /**
    * Set Action on the top bar button, to Open Shop, Start the match and go back to the menu.
    * @param openShop action we want to associate to the button Shop
    * @param startGame actione we want to associato to the button Play
    * @param backMenu action we want to associate to the button BackMenu
    */
    @Override
    public final void setActionTopM(final EventHandler<MouseEvent> openShop,
            final EventHandler<MouseEvent> startGame, 
            final EventHandler<MouseEvent> backMenu) {
        btnShop.setOnMouseClick(openShop);
        btnPlay.setOnMouseClick(startGame);
        btnBackToMenu.setOnMouseClick(backMenu);
    }

    /**
     * Enables all top menu buttons.
     **/
    @Override
    public final void setAllButtonEnable() {
       this.btnShop.enable();
       this.btnBackToMenu.enable();
       this.btnPlay.enable();
    }

    /**
     * Disables all top menu buttons.
     */
    @Override
    public void setAllButtonDisable() {
        this.btnShop.disable();
        this.btnBackToMenu.disable();
        this.btnPlay.disable();
    }
    /**
     * Set the progress on the life bar.
     * @param life
     */
    @Override
    public void setLifePiointsInTopMenu(final Double life) {
        this.topMenuView.setLifeProgressBarValue(life);
    }

    /**
     * Set the round level text.
     * @param round
     */
    @Override
    public void setRoundText(final int round) {
        this.topMenuView.setRoundTextValue(round);
    }

    /**
     * Return the map view.
     * @return map view
     */
    @Override
    public MapView getMapView() {
        return this.mapView;
    }

    /**
     * Show or hide shop view.
     * @param visible
     */
    @Override
    public void setShopVisible(final boolean visible) {
        if (visible && !this.getChildren().contains(this.shopView)) {
            this.getChildren().add(this.shopView);
        } else if (!visible && this.getChildren().contains(this.shopView)) {
            this.getChildren().remove(this.shopView);
        }
    }

    /**
     * @return true if shop is visible, false else
     */
    @Override
    public boolean isShopVisible() {
        return this.getChildren().contains(this.shopView);
    }

    @Override
    public Parent getView() {
        return this;
    }
}
