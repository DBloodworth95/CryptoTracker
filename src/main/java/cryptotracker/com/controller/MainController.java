package cryptotracker.com.controller;

import javafx.animation.FillTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MainController {

    private static final double WIDTH = 800;

    private static final double HEIGHT = 800;

    @FXML
    private AnchorPane mainPane;

    public void animateBackground() {
        Rectangle background = getBackgroundAnimation();
        mainPane.getChildren().add(background);
    }

    private Rectangle getBackgroundAnimation() {
        Rectangle background = new Rectangle(WIDTH, HEIGHT);
        FillTransition fillTransition = new FillTransition(Duration.millis(1000), background, Color.BLACK, Color.RED);
        fillTransition.setCycleCount(Timeline.INDEFINITE);
        fillTransition.setAutoReverse(true);
        fillTransition.play();
        return background;
    }
}
