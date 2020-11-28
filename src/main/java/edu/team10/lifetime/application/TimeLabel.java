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
	long seconds;
	TimerTask incrementTask;
	
	public TimeLabel(String taskName) {
		super("0:00");
		setVisibility(Main.settingsPage.liveTimerSetting.timersVisible);
		seconds = 0;
	}
	
	public TimeLabel(Task task) {
		super("0:00");
		this.task = task;
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
		
//		setVisibility(true);
//		System.out.println("visible: " + getVisibility());
	}

	/**
	 * Pause the timer.
	 */
	public void pauseTimer() {
//		setVisibility(false);
//		System.out.println("visible: " + getVisibility());
		timer.cancel();
	}
	
	/**
	 * Stop the timer.
	 */
	public void stopTimer() {
		timer.cancel();
		seconds = 0;
	}
	
	/** 
	 * Make the timer appear or disappear 
	 * */
	public void setVisibility(boolean value) {
		setVisible(value);	// determines if it can be seen
		setManaged(value);		// determines if it takes up space 
	}
	/**
	 *  check whether the timer appears on the screen
	 * */
	public boolean getVisibility() {
		return isManaged() && isVisible();
	}

}
