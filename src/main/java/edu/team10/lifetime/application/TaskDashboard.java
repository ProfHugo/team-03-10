package edu.team10.lifetime.application;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class TaskDashboard extends VBox{
	
	public TaskDashboard() {
		super();
		this.setId("taskDashboard");	// ID is used for css
		
	    makeAddBtn();
	}
	
	public void makeAddBtn() {
		// make & style button
	    Button addBtn = new Button();
	    addBtn.setId("addBtn");	
	    
	    // event handler: if button is clicked, window pops up  
	    addBtn.setOnAction( event -> 
		addTaskPopUp());
	    
	    this.getChildren().addAll(addBtn);
	}
	
	public void addTaskPopUp() {
	    TextInputDialog dialog = new TextInputDialog("type here");
	    dialog.setTitle("Text Input Dialog");
	    dialog.setHeaderText("Give a name for your task");

	    // get user input
	    Optional<String> result = dialog.showAndWait();	
	    
	    // display user input
	    result.ifPresent(name -> this.displayTask(name));
	}
	
	public void displayTask(String taskName) {
		HBox hbox = new HBox();
		hbox.setId("taskBox");
		
		// make & style labels & buttons
		Label name = new Label(taskName);
		name.setFont(new Font("Arial", 28));	
		
		Label status = new Label("Status: ");
		status.setFont(new Font("Arial", 20));
		
	    Button playBtn = new Button();
	    playBtn.setId("playBtn");

	    // event handler
	    playBtn.setOnAction( event -> {
	    	status.setText("Status: timer on");
			playBtn.setStyle("-fx-background-image: url(\"images/pause.png\"); ");
	    });
	    
	    hbox.getChildren().addAll(playBtn, status, name);
	    
	    // add labels & buttons to VBox
	    this.getChildren().addAll(hbox);
	}

}
