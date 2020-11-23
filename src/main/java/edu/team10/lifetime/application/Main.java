package edu.team10.lifetime.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	static BorderPane root;
	static ScrollPane scrollpane;
	static SidePanel sidePanel;
	static TaskDashboard taskDb;
	static SettingsPage settingsPage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = new BorderPane();
			
			sidePanel = new SidePanel();
			root.setLeft(sidePanel);

			taskDb = new TaskDashboard();
			settingsPage = new SettingsPage();
			
			// allow scrolling
			scrollpane = new ScrollPane();	
			scrollpane.setContent(taskDb);
			root.setCenter(scrollpane);		// add scrollpane (containg task dashboard) to root
			
			Scene scene = new Scene(root, 1280, 720);
			
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/taskDashboard.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/sidePanel.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/settingsPage.css").toExternalForm());

			
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
