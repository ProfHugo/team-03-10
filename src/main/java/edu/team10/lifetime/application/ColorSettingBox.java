package edu.team10.lifetime.application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ColorSettingBox extends HBox{
	Label settingName;
	Button green;
	Button blue;
	
	public ColorSettingBox() {
		this.setId("colorSettingBox");
		settingName = new Label("Color");
		
		green = new Button();
		green.setId("greenBtn");
		green.setOnAction(e -> {
			Main.setColorTheme(getClass().getResource("/green.css").toExternalForm());
		});
		
		
		blue = new Button();
		blue.setId("blueBtn");
		blue.setOnAction(e -> {
			Main.setColorTheme(getClass().getResource("/blue.css").toExternalForm());
		});
		
		
		this.getChildren().addAll(settingName, green, blue);
	}
	
	
}
