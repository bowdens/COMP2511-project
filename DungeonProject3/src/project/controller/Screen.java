package project.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Screen {
    private Stage stage;
    private String screenTitle;
    private FXMLLoader fxmlLoader;

    public Screen(Stage stage, String screenTitle, String fxmlFile) {
        this.stage = stage;
        this.screenTitle = screenTitle;
        this.fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(fxmlFile));
    }

    public Stage getStage() {
        return stage;
    }

    public void start(Controller c) {
        stage.setTitle(screenTitle);
        fxmlLoader.setController(c);
        try {
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}