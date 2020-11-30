package edu.team10.lifetime.application;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/** allows user to change view settings */
public class Settings {
	
	VBox view;
	Label title;
	ColorSetting colorSetting;
	LiveTimerSetting liveTimerSetting;
	
	public Settings() {
		super();
		
		view = new VBox();
		view.setId("settingsPage");
		
		title = new Label("Settings");
		title.setId("settingsPageTitle");
		
		colorSetting = new ColorSetting();
		liveTimerSetting = new LiveTimerSetting(); 
		
		view.getChildren().addAll(title, colorSetting.view, liveTimerSetting.view);
	}
	
	public VBox getView() {
		return view;
	}
	
}
