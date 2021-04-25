package com.bdefender.menu;

import java.net.URL;
import java.util.ResourceBundle;

import com.bdefender.event.EventHandler;
import com.bdefender.event.MouseEvent;
import com.bdefender.statistics.StatisticsReader;
import com.bdefender.statistics.StatisticsReaderImpl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class StatisticsMenuControllerImpl implements Initializable, StatisticsMenuController {

        private final EventHandler<MouseEvent> onBackToMenuClick;

        @FXML
        private Label titleLable;

        @FXML
        private Label maxRoundLbl;

        @FXML
        private Label mostPlayedMapLbl;

        @FXML
        private Label totTimeLbl;

        @FXML
        private ImageView backToMenuBtn;

        public StatisticsMenuControllerImpl(final EventHandler<MouseEvent> event) {
            this.onBackToMenuClick = event;
        }

        /**
         * initialize the GUI.
         */
        @Override
        public void initialize(final URL location, final ResourceBundle resources) {
            this.initializeBackToMenu();
            this.loadStat();
        }

        private void initializeBackToMenu() {
            this.backToMenuBtn.setOnMouseClicked((e) -> {
                this.onBackToMenuClick.handle(new MouseEvent(MouseEvent.MOUSE_CLICKED, e.getSource()));
            });
        }

        /**
         * 
         */
        @Override
        public final void loadStat() {
            final StatisticsReader reader = StatisticsReaderImpl.getInstance();
            this.maxRoundLbl.setText(Integer.toString(reader.getHigherstRoundEver().getY()));
            this.mostPlayedMapLbl.setText(reader.getMostPlayedMap().getMapName());
            this.totTimeLbl.setText(Integer.toString((int) (reader.getTotTimePlayed() / 1000) / 60) + "min");
            //carica le statistiche nella lbl
        }

}
