package edu.team10.lifetime.application;

import java.util.Timer;
import java.util.TimerTask;

import edu.team10.lifetime.backend.Task;
import javafx.application.Platform;
import javafx.scene.control.Label;

// displays live time of how long the task has been going on
public class TimeLabel extends Label {
	Task task;
	Timer timer;
	int seconds;
	TimerTask incrementTask;
	
	public TimeLabel(String taskName) {
		super(taskName);
		seconds = 0;
		
	}

	/**
	 * Start the timer.
	 */
	public void startTimer() {
		timer = new Timer();
		incrementTask = new TimerTask() {
			@Override
			public void run() {
				seconds++;
				Platform.runLater(() -> {
					// format and display
					long minutes = seconds / 60;
					TimeLabel.this.setText(minutes + ":" + seconds % 60);
				});
			}
		};
		timer.schedule(incrementTask, 0, 1000);
	}

	/**
	 * Pause the timer.
	 */
	public void pauseTimer() {
		timer.cancel();
	}
	
	/**
	 * Stop the timer.
	 */
	public void stopTimer() {
		timer.cancel();
		seconds = 0;
	}

}
