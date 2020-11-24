package edu.team10.lifetime.application;

import java.util.Optional;

import edu.team10.lifetime.backend.TaskState;
import edu.team10.lifetime.backend.Profile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

// displays a list of all tasks and a button to create new tasks
public class TaskDashboard extends VBox {

	private Profile profile;

	public TaskDashboard() {
		super();
		this.setId("taskDashboard"); // ID is used for css

		profile = new Profile("default");

		makeAddBtn();
	}

	// creates a button to add tasks
	public void makeAddBtn() {
		// make & style button
		Button addBtn = new Button();
		addBtn.setId("addBtn");

		// event handler: if button is clicked, window pops up
		addBtn.setOnAction(event -> addTaskPopUp());

		this.getChildren().addAll(addBtn);
	}

	public void addTaskPopUp() {
		TextInputDialog dialog = new TextInputDialog("type here");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Give a name for your task");

		// get profile input
		Optional<String> result = dialog.showAndWait();

		// display profile input
		result.ifPresent(taskName -> {
			if (!profile.hasTask(taskName)) {
				this.displayTask(taskName);
			} else {
				// notify the profile that the task already exists.
				System.out.println("Warning: Task already exists.");
			}
		});

	}

	/**
	 * Add this task to the dashboard.
	 * 
	 * @param taskName
	 */
	public void displayTask(String taskName) {
		// add task record to backend
		profile.addTask(taskName);

		System.out.println(taskName);

		HBox taskContainer = new HBox();
		taskContainer.setId("taskBox");

		// make & style labels & buttons
		Label name = new Label(taskName);
		name.setFont(new Font("Arial", 28));

		TimeLabel timeDisplay = new TimeLabel(taskName);		// live display of time
		timeDisplay.setFont(new Font("Arial", 20));
		//timeDisplay.setVisibility(false);	// invisible on default

		Button playBtn = new Button();
		playBtn.setId("playBtn");

		// event handler
		playBtn.setOnAction(event -> {
			TaskState currentState = profile.getTaskState(taskName);
			switch (currentState) {
			case INACTIVE:
				playBtn.setStyle("-fx-background-image: url(\"images/pause.png\"); ");
				profile.startTask(taskName);
				timeDisplay.startTimer();		
				break;
			case ACTIVE:
				playBtn.setStyle("-fx-background-image: url(\"images/play.png\"); ");
				profile.togglePauseTask(taskName);
				timeDisplay.pauseTimer();
				break;
			case PAUSED:
				playBtn.setStyle("-fx-background-image: url(\"images/pause.png\"); ");
				profile.togglePauseTask(taskName);
				timeDisplay.startTimer();
				break;
			default:
				break;
			}
			// if ()

			System.out.println();
		});

		Button stopBtn = new Button();
		stopBtn.setId("stopBtn"); // style

		// event handler
		stopBtn.setOnAction(event -> {
			timeDisplay.setText("Status: Stopped");
			playBtn.setStyle("-fx-background-image: url(\"images/play.png\"); ");
			profile.stopTask(taskName);
			timeDisplay.stopTimer();
		});

		Button removeBtn = makeRemoveBtn(taskContainer, taskName);

		// add labels and buttons to taskContainer
		taskContainer.getChildren().addAll(playBtn, timeDisplay, name, stopBtn, removeBtn);

		// adds taskContainer to dashboard display
		this.getChildren().addAll(taskContainer);
	}

	// takes in the element that contains all the task's information to delete, and
	// task name to identify task
	// creates a button that removes the task from display when clicked
	public Button makeRemoveBtn(HBox taskContainer, String taskName) {
		// WIP: Pops up a prompt asking if the user really, really wants to remove the task
		// ALSO WIP: Handle delete when the task is active.
		Button removeBtn = new Button();
		removeBtn.setId("removeBtn"); // style

		// event handler: removes task
		removeBtn.setOnAction(event -> {
			profile.removeTask(taskName); // removes task data
			this.getChildren().remove(taskContainer); // removes display of task on dashboard
		});

		return removeBtn;
	}

	/**
	 * Check to see if this task name is valid. WIP
	 * possible features: having character limit
	 * have at least 1 char that isn't a space, 
	 * name shouldn't start or end with a space ,
	 * no more than 1 space in between words,
	 * 
	 * @param taskName
	 * @return
	 */
	public boolean validateTaskName(String taskName) {
		/*
		 * Pattern noSpecialChar = Pattern.compile("[^a-zA-Z0-9 ]"); Pattern
		 * hasCharacter = Pattern.compile("[a-zA-Z]"); Matcher hasNoSpecialChar =
		 * noSpecialChar.matcher(taskName); return !hasNoSpecialChar.find();
		 */
		return true;
	}

}
