package edu.team10.lifetime.application;

import edu.team10.lifetime.core.Profile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/** allows user to change view settingsView */
public class SettingsView implements IApplicationElement {
	HBox colorSettings, liveTimerSettings;
	Label title;
	
	VBox view;
	
	Profile currentProfile;
	
	public SettingsView(Profile currentProfile) {
		this.view = new VBox();
		view.setId("settingsPage");
		this.setProfile(currentProfile);
		
		title = new Label("Settings");
		title.setId("settingsPageTitle");
		
		initColorSettings();
		initLiveTimerSettings();
		
		view.getChildren().addAll(title, colorSettings, liveTimerSettings);
	}
	
	public void initColorSettings() {
		// set color theme
		if (currentProfile.getSetting("colorScheme") != null) {
			Main.changeColorTheme(getColorResource(currentProfile.getSetting("colorScheme")));
		} 
		else {
			// set green theme as default
			Main.currentColorTheme = getClass().getResource("/css/colors/green.css").toExternalForm();
			Main.scene.getStylesheets().add(Main.currentColorTheme);
		}
		colorSettings = new HBox();	// displays buttons to change color theme of application
		colorSettings.setId("colorSettingBox"); 
		
		Label settingName = new Label("Color");
		
		// make buttons for each color theme
		Button green = makeColorButton("green");
		Button blue = makeColorButton("blue");
		Button orange = makeColorButton("orange");
		
		// add buttons to color setting display
		colorSettings.getChildren().addAll(settingName, green, blue, orange);
	}
	
	// makes a new button for a color
	public Button makeColorButton(String colorName) {
		Button colorBtn = new Button();	
		colorBtn.setId(colorName + "Btn");	// for CSS styling
		
		// if button is clicked, change application's color theme 
		colorBtn.setOnAction(e -> {
			String url = getColorResource(colorName);
			// if the given color theme isn't already the current one, replace current with given
			currentProfile.changeSetting("colorScheme", colorName);
			if (!Main.currentColorTheme.equals(url))
				Main.changeColorTheme(url);	
		});
		return colorBtn;
	}
	

	private void initLiveTimerSettings() {
		liveTimerSettings = new HBox(); // contains label and 2 buttons
		liveTimerSettings.setId("liveTimerSetting");

		Label settingName = new Label("Live timer visibility");

		Button on = new Button("on");
		on.setOnAction(e -> {
			currentProfile.changeSetting("liveTimerVisible", "true");
		});

		Button off = new Button("off");
		off.setOnAction(e -> {
			currentProfile.changeSetting("liveTimerVisible", "false");
		});

		liveTimerSettings.getChildren().addAll(settingName, on, off);
	}

	/**
	 * 
	 * @param colorName
	 * @return A resource URL to the given color.
	 */
	public String getColorResource(String colorName) {
		return getClass().getResource("/css/colors/" + colorName + ".css").toExternalForm();
	}
	
	/**
	 * Replace the profile, updating as nessasary. 
	 * @param newProfile
	 */
	public void setProfile(Profile newProfile) {
		this.currentProfile = newProfile;
		
		// update the color scheme
		String url = getColorResource(currentProfile.getSetting("colorScheme"));
		Main.changeColorTheme(url);

	}
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VBox getView() {
		// TODO Auto-generated method stub
		return this.view;
	}
	
}
