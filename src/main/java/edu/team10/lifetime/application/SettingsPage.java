package edu.team10.lifetime.application;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsPage extends VBox{
	
	Label title;
	
	public SettingsPage() {
		super();
		this.setId("settingsPage");
		
		title = new Label("Settings");
		title.setId("settingsPageTitle");
		
		//SettingBox colorSetting = makeColorSetting( );
		ColorSettingBox colorSetting = new ColorSettingBox();
		
		this.getChildren().addAll(title, colorSetting);
	}
	
	
}
