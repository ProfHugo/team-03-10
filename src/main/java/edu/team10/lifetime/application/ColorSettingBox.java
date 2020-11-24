package edu.team10.lifetime.application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

// displays buttons to change color theme of application
public class ColorSettingBox extends HBox{
	Label settingName;
	Button green;
	Button blue;
	Button orange;
	
	public ColorSettingBox() {
		this.setId("colorSettingBox");
		settingName = new Label("Color");
		
		green = new Button();
		green.setId("greenBtn");
		green.setOnAction(e -> {
			Main.changeColorTheme(getClass().getResource("/css/colors/green.css").toExternalForm());	
		});
		
		blue = new Button();
		blue.setId("blueBtn");
		blue.setOnAction(e -> {
			Main.changeColorTheme(getClass().getResource("/css/colors/blue.css").toExternalForm());
		});
		
		orange = new Button();
		orange.setId("orangeBtn");
		orange.setOnAction(e -> {
			Main.changeColorTheme(getClass().getResource("/css/colors/orange.css").toExternalForm());
		});
		
		this.getChildren().addAll(settingName, green, blue, orange);
	}
	
	
}
