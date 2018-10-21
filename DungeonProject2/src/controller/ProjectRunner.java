package project.controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class ProjectRunner extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		Screen mainMenuScreen = new Screen(primaryStage, "Dungeons - Main Menu", "../view/MainMenuPage.fxml");
		MainMenuController mainMenuController = new MainMenuController(primaryStage);
		mainMenuScreen.start(mainMenuController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
