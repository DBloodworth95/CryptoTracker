package cryptotracker.com.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UI extends Application {

    private final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainview.fxml"));

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent mainParent = fxmlLoader.load();
        Scene mainScene = new Scene(mainParent);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("CryptoTracker 0.1");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
