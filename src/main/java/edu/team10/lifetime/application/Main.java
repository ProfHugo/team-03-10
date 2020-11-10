package edu.team10.lifetime.application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			VBox vbox = addSidePanel();
			root.setLeft(vbox);

			VBox taskDb = new TaskDashboard();
			root.setCenter(taskDb);
			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private VBox addSidePanel() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(15, 12, 15, 12));
		vbox.setStyle("-fx-background-color: DARKSEAGREEN;");
		
	    Button taskBtn = new Button("Tasks");
	    taskBtn.setPrefSize(100, 40);		// adds padding, args: width, height
	    taskBtn.setStyle("-fx-background-color: transparent;");
	    
	    Button settingsBtn = new Button("Settings");
	    settingsBtn.setPrefSize(100, 40);		// adds padding, args: width, height
	    settingsBtn.setStyle("-fx-background-color: transparent;");
	    
	    Image img = new Image("/images/play.png");
	    ImageView view = new ImageView(img);
	    view.setFitHeight(20);
	    view.setFitWidth(20);
	    settingsBtn.setGraphic(view);
	    
	    vbox.getChildren().addAll(taskBtn, settingsBtn);
		
		return vbox;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
