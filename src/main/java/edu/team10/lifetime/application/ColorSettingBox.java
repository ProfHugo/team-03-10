package edu.team10.lifetime.application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

// displays buttons to change color theme of application
public class ColorSettingBox extends HBox{
	Label settingName;
	
	public ColorSettingBox() {
		this.setId("colorSettingBox");
		
		settingName = new Label("Color");
		
		// make buttons for each color theme
		Button green = makeColorButton("green");
		Button blue = makeColorButton("blue");
		Button orange = makeColorButton("orange");
		
		// add buttons to color setting display
		this.getChildren().addAll(settingName, green, blue, orange);
	}
	
	// makes a new button for a color
	public Button makeColorButton(String colorName) {
		Button colorBtn = new Button();	
		colorBtn.setId(colorName + "Btn");	// for CSS styling
		
		// if button is clicked, change application's color theme 
		colorBtn.setOnAction(e -> {
			String url = getClass().getResource("/css/colors/" + colorName + ".css").toExternalForm();
			// if the given color theme isn't already the current one, replace current with given
			if (!Main.currentColorTheme.equals(url))
				Main.changeColorTheme(url);	
		});
		return colorBtn;
	}
	
}
