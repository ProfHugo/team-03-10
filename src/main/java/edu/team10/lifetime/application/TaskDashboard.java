package edu.team10.lifetime.application;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import edu.team10.lifetime.backend.Profile;
import edu.team10.lifetime.backend.TaskState;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

// displays a list of all tasks and a button to create new tasks
public class TaskDashboard extends VBox {

	private Profile profile;

	public TaskDashboard(Profile profile) {
		super();
		this.setId("taskDashboard"); // ID is used for css

		this.profile = profile;

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

		HBox taskContainer = new HBox();
		taskContainer.setId("taskBox");

		// make & style labels & buttons
		Label name = new Label(taskName);
		name.setFont(new Font("Arial", 28));

		Label status = new Label("Status: " + TaskState.INACTIVE.toString());
		status.setFont(new Font("Arial", 20));

		// Live Timer Display
		Label timeDisplay = new Label("00:00:00"); // live display of time
		timeDisplay.setFont(new Font("Arial", 20));
		LiveTimer liveTimer = new LiveTimer();
		Timeline timerUpdate = new Timeline(new KeyFrame(Duration.millis(200), event -> {
			boolean isLiveTimerVisible = Boolean.parseBoolean(profile.getSetting("liveTimerVisible"));
			if (isLiveTimerVisible) {
				timeDisplay.setVisible(true);
				timeDisplay.setText(liveTimer.toString());
			} else {
				timeDisplay.setVisible(false);
			}
		}));
		timerUpdate.setCycleCount(Animation.INDEFINITE);
		timerUpdate.play();

		Button playBtn = new Button();
		playBtn.setId("playBtn");

		// event handler
		playBtn.setOnAction(event -> {
			TaskState currentState = profile.getTaskState(taskName);
			switch (currentState) {
			case INACTIVE:
				playBtn.setStyle("-fx-background-image: url(\"images/pause.png\"); ");
				status.setText("Status: " + TaskState.ACTIVE.toString());
				profile.startTask(taskName);
				liveTimer.startTimer();

				break;
			case ACTIVE:
				playBtn.setStyle("-fx-background-image: url(\"images/play.png\"); ");
				status.setText("Status: " + TaskState.PAUSED.toString());
				profile.togglePauseTask(taskName);
				liveTimer.pauseTimer();
				break;
			case PAUSED:
				playBtn.setStyle("-fx-background-image: url(\"images/pause.png\"); ");
				status.setText("Status: " + TaskState.ACTIVE.toString());
				profile.togglePauseTask(taskName);
				liveTimer.startTimer();
				break;
			default:
				break;
			}
		});

		Button stopBtn = new Button();
		stopBtn.setId("stopBtn"); // style

		// event handler
		stopBtn.setOnAction(event -> {
			liveTimer.stopTimer();
			playBtn.setStyle("-fx-background-image: url(\"images/play.png\"); ");
			status.setText("Status: " + TaskState.INACTIVE.toString());
			profile.stopTask(taskName);
		});

		Button removeBtn = makeRemoveBtn(taskContainer, taskName);

		// add labels and buttons to taskContainer
		taskContainer.getChildren().addAll(playBtn, timeDisplay, name, status, stopBtn, removeBtn);

		// adds taskContainer to dashboard display
		this.getChildren().addAll(taskContainer);
	}

	// takes in the element that contains all the task's information to delete, and
	// task name to identify task
	// creates a button that removes the task from display when clicked
	public Button makeRemoveBtn(HBox taskContainer, String taskName) {
		// WIP: Pops up a prompt asking if the user really, really wants to remove the
		// task
		// ALSO WIP: Handle delete when the task is active.
		Button removeBtn = new Button();
		removeBtn.setId("removeBtn"); // style

		// event handler: removes task
		removeBtn.setOnAction(event -> {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText("Deleting " + taskName + " from Task Dashboard");
			alert.setContentText("Are you sure you want to remove this task from your dashboard?");

			// Verification Prompt
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				TaskState currentState = profile.getTaskState(taskName);

				// Stop the task first before removing if it's currently active.
				if (currentState.equals(TaskState.ACTIVE) || currentState.equals(TaskState.PAUSED)) {
					profile.stopTask(taskName);
				}

				profile.removeTask(taskName); // removes task data

				/**
				 * TimeLabel liveTimer = (TimeLabel) taskContainer.getChildren().get(1);
				 * liveTimer.stopTimer(); // timer will stop running
				 * Main.settingsView.liveTimerSetting.timers.remove(liveTimer); // remove live
				 * timer
				 **/
				// System.out.println("timer still there:
				// "+Main.settingsView.liveTimerSetting.timers.contains(liveTimer));
				this.getChildren().remove(taskContainer); // removes display of task on dashboard
			} else {
				// do nothing
			}

		});

		return removeBtn;
	}

	/**
	 * Replace the profile and all the tasks on the dashboard. Stops all active
	 * tasks of the old profile.
	 * 
	 * @param newProfile
	 */
	public void setProfile(Profile newProfile) {
		TreeSet<String> taskNames = profile.getAllTaskNames();

		// Stop all the current profile's tasks.
		for (String taskName : taskNames) {
			TaskState currentState = profile.getTaskState(taskName);
			// Stop the task first before removing if it's currently active.
			if (currentState.equals(TaskState.ACTIVE) || currentState.equals(TaskState.PAUSED)) {
				profile.stopTask(taskName);
			}

		}

		// Flush the task dashboard.
		Set<Node> allTaskContainers = this.lookupAll("#taskBox");
		this.getChildren().removeAll(allTaskContainers);

		System.out.println(this.getChildren().toString());
		// Replace the profile.
		this.profile = newProfile;

		// Repopulate the task dashboard.
		taskNames = profile.getAllTaskNames();
		for (String taskName : taskNames) {
			displayTask(taskName);
		}

	}

	/**
	 * Check to see if this task name is valid. WIP possible features: having
	 * character limit have at least 1 char that isn't a space, name shouldn't start
	 * or end with a space , no more than 1 space in between words,
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
