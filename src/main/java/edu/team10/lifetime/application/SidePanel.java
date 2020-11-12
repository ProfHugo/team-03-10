package edu.team10.lifetime.application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

// Side panel on the left side in the application 
public class SidePanel extends VBox{
	
	public SidePanel() {
		this.setId("sidePanel");
		
	    Button taskBtn = new SidePanelBtn("Tasks");
	    
	    Button settingsBtn = new SidePanelBtn("Settings");
	    settingsBtn.setId("settingsBtn");
	    
	    this.getChildren().addAll(taskBtn, settingsBtn);
	}
	
}
