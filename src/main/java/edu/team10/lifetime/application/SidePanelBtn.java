package edu.team10.lifetime.application;

import javafx.scene.control.Button;

// Instances of this class are buttons that appear on side panel
public class SidePanelBtn extends Button{
	
		public SidePanelBtn(String name) {
			super();
			
			this.setText(name);
			this.getStyleClass().add("sidePanelBtn");	// allows CSS to recognize class name and style instances of this class
		}
}
