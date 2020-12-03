package edu.team10.lifetime.application;

import java.util.Timer;
import java.util.TimerTask;

import edu.team10.lifetime.backend.Task;
import javafx.application.Platform;
import javafx.scene.control.Label;

// displays live time of how long the task has been going on
public class LiveTimer {
	Timer timer;
	long totalCenti, lastPaused;
	TimerTask incrementTask;

	public LiveTimer() {
		totalCenti = 0;
		lastPaused = 0;
	}

	/**
	 * Start the timer.
	 */
	public void startTimer() {
		totalCenti = lastPaused;
		timer = new Timer();
		incrementTask = new TimerTask() {
			@Override
			public void run() {
				totalCenti++;
			}
		};
		timer.schedule(incrementTask, 0, 10);
	}

	/**
	 * Pause the timer.
	 */
	public void pauseTimer() {
		timer.cancel();
		lastPaused = totalCenti;
	}

	/**
	 * Stop the timer.
	 */
	public void stopTimer() {
		if (timer != null) {
			timer.cancel();
			totalCenti = 0;
			lastPaused = 0;
		}
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

	public String toString() {
		long totalSeconds = totalCenti / 100;
		String minutes = formatTimeUnit(totalSeconds / 60);
		String hours = formatTimeUnit(totalSeconds / 3600);
		String seconds = formatTimeUnit(totalSeconds % 60);
		return hours + ":" + minutes + ":" + seconds;
	}

}
