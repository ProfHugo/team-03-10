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
		this.setPadding(new Insets(15, 12, 15, 12));
		this.setStyle("-fx-background-color: GHOSTWHITE;");
		

	    makeAddBtn();

	}
	
	public void makeAddBtn() {
		Image img = new Image("/images/new_task.png");
	    ImageView view = new ImageView(img);
		
		// make & style button
	    Button addBtn = new Button();
	    addBtn.setPrefSize(150, 50);		// adds padding, args: width, height
	    addBtn.setGraphic(view);
	    addBtn.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
	    
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
		Button task = new Button();
		
		// make & style labels & buttons
		Label name = new Label(taskName);
		name.setFont(new Font("Arial", 28));	// bigger font than default
		
		
		Label status = new Label("Status: ");
		status.setFont(new Font("Arial", 20));
		
	    Button playBtn = new Button();
	    playBtn.setPrefSize(50, 50);		// adds padding, args: width, height
	    
	    Image img = new Image("/images/play.png");
	    ImageView view = new ImageView(img);
	    view.setFitHeight(50);
	    view.setFitWidth(50);
	    playBtn.setGraphic(view);
	    playBtn.setStyle("-fx-background-color: transparent;");
	    
	    Image pause = new Image("/images/pause.png");
	    ImageView pauseView = new ImageView(pause);
	    pauseView.setFitHeight(50);
	    pauseView.setFitWidth(50);

	    // event handler
	    playBtn.setOnAction( event -> {
	    	status.setText("Status: timer on");
			playBtn.setGraphic(pauseView);
	    }
		);
	    
	    hbox.getChildren().addAll(playBtn, status, name);
	    hbox.setSpacing(50);
	    hbox.setPadding(new Insets(15, 10, 15, 10));
	    hbox.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN,null,null)));
	    
	    // add labels & buttons to VBox
	    //this.getChildren().addAll(task);
	    this.getChildren().addAll(hbox);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	


}
