package edu.team10.lifetime.application;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsPage extends VBox{
	
	Label title;
	LiveTimerSetting liveTimerSetting;
	
	public SettingsPage() {
		super();
		this.setId("settingsPage");
		
		title = new Label("Settings");
		title.setId("settingsPageTitle");
		
		ColorSettingBox colorSetting = new ColorSettingBox();
		liveTimerSetting = new LiveTimerSetting(); 
		
		this.getChildren().addAll(title, colorSetting, liveTimerSetting.view);
	}
	
	
}
