package cryptotracker.com.controller;

import cryptotracker.com.model.CryptoPriceContainer;
import cryptotracker.com.worker.PriceUpdateWorker;
import javafx.animation.AnimationTimer;
import javafx.animation.FillTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MainController {

    private static final double WIDTH = 800;

    private static final double HEIGHT = 800;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Rectangle backgroundRect;
    @FXML
    private Label btcLabel;
    @FXML
    private Label btcPriceLabel;

    public void startPriceUpdater() {
        CryptoPriceContainer cryptoPriceContainer = new CryptoPriceContainer();
        PriceUpdateWorker priceUpdateWorker = new PriceUpdateWorker(cryptoPriceContainer);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (cryptoPriceContainer.getLock().tryLock()) {
                    try {
                        btcPriceLabel.setText(String.valueOf(cryptoPriceContainer.getBtcPrice()));
                    } finally {
                        cryptoPriceContainer.getLock().unlock();
                    }
                }
            }
        };

        animationTimer.start();

        priceUpdateWorker.start();
    }

    public void animateBackground() {
        backgroundRect = getBackgroundAnimation();
    }

    private Rectangle getBackgroundAnimation() {
        FillTransition fillTransition = new FillTransition(Duration.millis(1000), backgroundRect, Color.BLACK, Color.RED);
        fillTransition.setCycleCount(Timeline.INDEFINITE);
        fillTransition.setAutoReverse(true);
        fillTransition.play();
        return backgroundRect;
    }
}
