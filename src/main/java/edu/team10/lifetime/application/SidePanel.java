package edu.team10.lifetime.application;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

// allows user to navigate between task dashboard and setting page
public class SidePanel {
	VBox view;	// Side panel on the left side in the application 
	
	public SidePanel() {
		view = new VBox();
		view.setId("sidePanel");
		
		// make buttons that appear on side panel 
		//Button taskDashboardBtn = makeSidePanelBtn("Tasks", "taskDashboardBtn", Main.taskDb);
		Button taskDashboardBtn = new Button("Tasks");
		taskDashboardBtn.setId("taskDashboardBtn");
		taskDashboardBtn.setOnAction(e -> {
			Main.setPage(Main.taskDb);
		});
//		System.out.println("settings: " + Main.settings);
//	    System.out.println(Main.settings.view.getId());
//		Button settingsBtn = makeSidePanelBtn("Settings", "settingsBtn", Main.settings.view);
		Button settingsBtn = new Button("Settings");
	    settingsBtn.setId("settingsBtn");
	    settingsBtn.setOnAction(e -> {
	    	Main.setPage(Main.settings.view);
	    });
	    
	    view.getChildren().addAll(taskDashboardBtn, settingsBtn);
	}
	
	/** makes a button to be placed on the side panel */
	public Button makeSidePanelBtn(String btnDisplayName, String id, VBox destination) {
		Button sidePanelBtn = new Button(btnDisplayName);
		sidePanelBtn.setId(id);	// CSS styling
		//System.out.println("constructor passed");
		System.out.println(destination);
		// will change application's center view when clicked
		sidePanelBtn.setOnAction(e -> {
	    	Main.setPage(destination);
	    });
		
		return sidePanelBtn;
	}
	
}
