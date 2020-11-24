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
	static Scene scene;
	static String currentColorTheme;
	
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
			scrollpane.setContent(taskDb);		// shows task dashboard on default
			root.setCenter(scrollpane);		// add scrollpane (containg task dashboard) to root
			
			scene = new Scene(root, 1280, 720);
			
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/css/taskDashboard.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/css/sidePanel.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/css/settingsPage.css").toExternalForm());
			
			// set green theme as default
			currentColorTheme = getClass().getResource("/css/colors/green.css").toExternalForm();	
			scene.getStylesheets().add(currentColorTheme);	
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// replaces old color theme with new theme passed in as a link to its CSS 
	public static void changeColorTheme(String chosenTheme) {
		// remove current color theme
		scene.getStylesheets().remove(currentColorTheme);	
		
		// add new
		currentColorTheme = chosenTheme;	// record this as current color theme
		scene.getStylesheets().add(chosenTheme);	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
