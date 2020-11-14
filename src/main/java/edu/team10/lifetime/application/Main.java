package edu.team10.lifetime.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			VBox sidePanel = new SidePanel();
			root.setLeft(sidePanel);

			VBox taskDb = new TaskDashboard();
			root.setCenter(taskDb);
			
			
			Scene scene = new Scene(root, 1280, 720);
			
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/taskDashboard.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/sidePanel.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
