package edu.team10.lifetime.application;

import java.util.Timer;
import java.util.TimerTask;

import edu.team10.lifetime.backend.Task;
import javafx.application.Platform;
import javafx.scene.control.Label;

// displays live time of how long the task has been going on
public class TimeLabel extends Label {
	Timer timer;
	long totalSeconds;
	TimerTask incrementTask;

	public TimeLabel(String taskName) {
		super("00:00:00");
		totalSeconds = 0;
	}


	/**
	 * Start the timer.
	 */
	public void startTimer() {
		timer = new Timer();
		incrementTask = new TimerTask() {
			@Override
			public void run() {
				totalSeconds++;
				Platform.runLater(() -> {
					// format and display
					String minutes = formatTimeUnit(totalSeconds / 60);
					String hours = formatTimeUnit(totalSeconds / 3600);
					String seconds = formatTimeUnit(totalSeconds % 60);
					TimeLabel.this.setText(hours + ":" + minutes + ":" + seconds);
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
		if (timer != null) {
			timer.cancel();
			totalSeconds = 0;
		}
	}

	/**
	 * Make the timer appear or disappear
	 */
	public void setVisibility(boolean value) {
		setVisible(value); // determines if it can be seen
		setManaged(value); // determines if it takes up space
	}

	/**
	 * check whether the timer appears on the screen
	 */
	public boolean getVisibility() {
		return isManaged() && isVisible();
	}

	/**
	 * 
	 * @param time
	 * @return If the number is less than 10, return it with a 0 appended to it.
	 *         Otherwise just return it as is as a string.
	 */
	public static String formatTimeUnit(long time) {
		return time < 10 ? "0" + time : "" + time;
	}

}
