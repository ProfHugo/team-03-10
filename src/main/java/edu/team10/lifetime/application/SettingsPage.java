package edu.team10.lifetime.application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SettingsPage extends VBox{
	
	Label title;
	
	public SettingsPage() {
		super();
		this.setId("settingsPage");
		
		title = new Label("Settings");
		title.setId("settingsPageTitle");
		
		SettingBox colorSetting = makeColorSetting( );
		
		this.getChildren().addAll(title, colorSetting);
	}
	
	SettingBox makeColorSetting( ) {
		SettingBox colorSetting = new SettingBox();
		
		Label settingName = new Label("Color");
		
		Button green = new Button();
		green.setId("greenBtn");
		green.setOnAction(e -> {
			Main.sidePanel.setStyle("-fx-background-color: DARKSEAGREEN;");

		});
		
		Button blue = new Button();
		blue.setId("blueBtn");
		blue.setOnAction(e -> {
			Main.sidePanel.setStyle("-fx-background-color: POWDERBLUE;");

		});
		
		colorSetting.getChildren().addAll(settingName, green, blue);
		
		return colorSetting;
	}
	
	
}
