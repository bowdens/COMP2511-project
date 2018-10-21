package project.controller;

import javafx.stage.Stage;

public abstract class Controller {
	
    private Stage stage;

    public Controller(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}