package edu.team10.lifetime.util;

import java.util.Timer;
import java.util.TimerTask;

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
	

	public String toString() {
		long totalSeconds = totalCenti / 100;
		String minutes = edu.team10.lifetime.util.Timer.formatTimeUnit(totalSeconds / 60);
		String hours = edu.team10.lifetime.util.Timer.formatTimeUnit(totalSeconds / 3600);
		String seconds = edu.team10.lifetime.util.Timer.formatTimeUnit(totalSeconds % 60);
		return hours + ":" + minutes + ":" + seconds;
	}

}
