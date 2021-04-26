package com.bdefender.game.view;

import com.bdefender.event.EventHandler;
import com.bdefender.event.MouseEvent;
import com.bdefender.map.view.MapView;

import javafx.scene.Parent;

public interface GameView {

    /**
     * Show the game over of the game, show also the level passed.
     * @param round
     * @param event
     */
    void showGameOverMenu(int round, EventHandler<MouseEvent> event);

    /**
     * @return top menu view
     */
    TopMenuView getTopMenuView();

    /**
    * Set Action on the top bar button, to Open Shop, Start the match and go back to the menu.
    * @param openShop action we want to associate to the button Shop
    * @param startGame actione we want to associato to the button Play
    * @param backMenu action we want to associate to the button BackMenu
    */
    void setActionTopM(EventHandler<MouseEvent> openShop, EventHandler<MouseEvent> startGame,
            EventHandler<MouseEvent> backMenu);

    /**
     * Enables all top menu buttons.
     **/
    void setAllButtonEnable();

    /**
     * Disables all top menu buttons.
     */
    void setAllButtonDisable();

    /**
     * Set the progress on the life bar.
     * @param life
     */
    void setLifePiointsInTopMenu(Double life);

    /**
     * Set the round level text.
     * @param round
     */
    void setRoundText(int round);

    /**
     * Return the map view.
     * @return map view
     */
    MapView getMapView();

    /**
     * Show or hide shop view.
     * @param visible
     */
    void setShopVisible(boolean visible);

    /**
     * @return true if shop is visible, false else
     */
    boolean isShopVisible();

    /**
     * @return Parent object which can be rendered
     */
    Parent getView();

}