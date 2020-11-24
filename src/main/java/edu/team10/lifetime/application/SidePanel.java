package edu.team10.lifetime.application;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

// Side panel on the left side in the application 
public class SidePanel extends VBox{
	
	public SidePanel() {
		this.setId("sidePanel");
		
		SidePanelBtn taskBtn = new SidePanelBtn("Tasks");
		taskBtn.setOnAction(e -> {
			Main.scrollpane.setContent(Main.taskDb);
		});
	    
		SidePanelBtn settingsBtn = new SidePanelBtn("Settings");
	    settingsBtn.setId("settingsBtn");
	    settingsBtn.setOnAction(e -> {
	    	Main.scrollpane.setContent(Main.settingsPage);
	    });
	    
	    this.getChildren().addAll(taskBtn, settingsBtn);
	}
	
}
