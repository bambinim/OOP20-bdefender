package com.bdefender.game;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import com.bdefender.component.ImageButton;
import com.bdefender.map.MapView;
import com.bdefender.menu.GameOverMenu;
import com.bdefender.event.MouseEvent;
import com.bdefender.event.EventHandler;

public class GameView extends AnchorPane {

    private final TopMenuView topMenuView;
    private final Parent shopView;
    private ImageButton btnShop;
    private ImageButton btnBackToMenu;
    private ImageButton btnPlay;



    public GameView(final MapView mapView, final Parent shopView) {
        this.topMenuView = new TopMenuView();
        this.shopView = shopView;
        mapView.setLayoutY(TopMenuView.HEIGHT);
        this.shopView.setLayoutY(TopMenuView.HEIGHT);
        this.btnShop = this.topMenuView.getShopButton();
        this.btnBackToMenu = this.topMenuView.getExitButton();
        this.btnPlay = this.topMenuView.getPlayButton();
        this.getChildren().addAll(this.topMenuView, mapView, this.shopView);
    }

    /**
     * Show the game over of the game, show also the level passed.
     * @param round
     * @param event
     */
    public void showGameOverMenu(final int round, final EventHandler<MouseEvent> event) {
        final GameOverMenu gameOverMenu = new GameOverMenu(round);
        gameOverMenu.setBackToMenuEvent(event);
        Platform.runLater(() -> this.getChildren().add(gameOverMenu));
    }

    /**
     * @return top menu view
     */
    public TopMenuView getTopMenuView() {
        return this.topMenuView;
    }

   /**
    * Set Action on the top bar button, to Open Shop, Start the match and go back to the menu.
    * @param openShop action we want to associate to the button Shop
    * @param startGame actione we want to associato to the button Play
    * @param backMenu action we want to associate to the button BackMenu
    */
    public final void setActionTopM(final EventHandler<MouseEvent> openShop,
            final EventHandler<MouseEvent> startGame, 
            final EventHandler<MouseEvent> backMenu) {
        btnShop.setOnMouseClick(openShop);
        btnPlay.setOnMouseClick(startGame);
        btnBackToMenu.setOnMouseClick(backMenu);
    }

    /**
     * @param flag true if we need to set all the button Off false if we need to set all the buttons on.
     **/
    public final void setAllButtonEnable() {
       this.btnShop.enable();
       this.btnBackToMenu.enable();
       this.btnPlay.enable();
    }

    public void setAllButtonDisable() {
        this.btnShop.disable();
        this.btnBackToMenu.disable();
        this.btnPlay.disable();
    }
    /**
     * Set the progress on the life bar.
     * @param life
     */
    public void setLifePiointsInTopMenu(final Double life) {
        this.topMenuView.setLifeProgressBarValue(life);
    }
    
    /**
     * Set the round level text.
     * @param round
     */
    public void setRoundText(final int round) {
        this.topMenuView.setRoundTextValue(round);
    }
}
