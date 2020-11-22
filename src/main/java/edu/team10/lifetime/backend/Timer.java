package edu.team10.lifetime.backend;

import java.time.Duration;
import java.time.Instant;

/**
 * A class which tracks and stores timing information.
 * 
 * @author Hugo Wong
 *
 */
public class Timer {

	public Timer() {
		reset();
	}

	private Instant startTime, stopTime, lastPaused, lastResumed;

	private Duration totalTimeElapsed;

	private TimerState currentState;

	private enum TimerState {
		ACTIVE, PAUSED, STOPPED;
	}

	/**
	 * Start the timer.
	 */
	public void start() {
		reset();
		startTime = Instant.now();
		lastPaused = startTime;
		lastResumed = startTime;
		currentState = TimerState.ACTIVE;
	}

	/**
	 * Pause/unpause the timer, depending on the current state of the timer.
	 */
	public void togglePause() {
		switch (this.currentState) {
		case ACTIVE:
			lastPaused = Instant.now();
			currentState = TimerState.PAUSED;
			totalTimeElapsed = Duration.between(lastResumed, lastPaused).plus(totalTimeElapsed);
			break;
		case PAUSED:
			lastResumed = Instant.now();
			currentState = TimerState.ACTIVE;
			break;
		default:
			System.out.println("Warning: Cannot pause/unpause an inactive timer.");
			break;
		}
	}

	/**
	 * Stop the timer.
	 */
	public void stop() {
		stopTime = Instant.now();
		if (this.currentState == TimerState.ACTIVE) {
			totalTimeElapsed = Duration.between(lastResumed, stopTime).plus(totalTimeElapsed);
		}
		// lastResume is startTime if the task was never paused
		currentState = TimerState.STOPPED;
	}

	
	/**
	 * @return the startTime
	 */
	public Instant getStartTime() {
		return startTime;
	}

	/**
	 * @return the stopTime
	 */
	public Instant getStopTime() {
		return stopTime;
	}

	/**
	 * 
	 * @return The difference between the time when the timer was started and when
	 *         it was stopped.
	 * 
	 */
	public Duration getDelta() {
		Duration delta = Duration.between(startTime, stopTime);
		return delta;
	}

	/**
	 * 
	 * @return The amount of elapsed time on this timer for the current session.
	 * 
	 */
	public Duration getElapsed() {
		return totalTimeElapsed;
	}

	/**
	 * Reset all the instance fields to their defaults (null, 0, and stopped).
	 */
	public void reset() {
		startTime = null;
		lastPaused = null;
		lastResumed = null;
		stopTime = null;
		totalTimeElapsed = Duration.ofMillis(0);
		currentState = TimerState.STOPPED;
	}
}
