package edu.team10.lifetime.application;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TaskDashboard extends VBox{
	
	public TaskDashboard() {
		super();
		this.setPadding(new Insets(15, 12, 15, 12));
		this.setStyle("-fx-background-color: GHOSTWHITE;");

	    makeAddBtn();

	}
	
	public void makeAddBtn() {
		// make & style button
	    Button addBtn = new Button("new task");
	    addBtn.setPrefSize(150, 50);		// adds padding, args: width, height
	    
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
	    
	    // verbose method
//	    if (result.isPresent()){
//	    		String name = result.get();
//	    		this.addTask(name);
//	    }

	    // lambda equivalent of verbose method
	    result.ifPresent(name -> this.displayTask(name));
	}
	public void displayTask(String taskName) {
		HBox hbox = new HBox();
		
		// make & style labels & buttons
		Label name = new Label(taskName);
		name.setFont(new Font("Arial", 28));	// bigger font than default
		
		Label status = new Label("Status: ");
		status.setFont(new Font("Arial", 20));	
		
	    Button playBtn = new Button("play");
	    playBtn.setPrefSize(50, 50);		// adds padding, args: width, height

	    // event handler
	    playBtn.setOnAction( event -> 
		status.setText("Status: timer on"));
	    
	    hbox.getChildren().addAll(name, playBtn, status);
	    hbox.setSpacing(10);
	    hbox.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN,null,null)));
	    
	    // add labels & buttons to VBox
	    this.getChildren().addAll(hbox);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	


}
