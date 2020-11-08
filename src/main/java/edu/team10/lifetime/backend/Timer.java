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
	
	private Instant startTime, stopTime;
	
	/**
	 * Start the timer.
	 */
	public void start() {
		startTime = Instant.now();
	}
	
	/**
	 * Stop the timer.
	 */
	public void stop() {
		stopTime = Instant.now();
	}
	
	/**
	 * 
	 * @return The difference between the time when the timer was started and when it was stopped.
	 * 
	 */
	public Duration getDelta() {
		Duration delta = Duration.between(startTime, stopTime);
		return delta;
	}
	
	/**
	 * Flush the startTime and stopTime.
	 */
	public void reset() {
		startTime = null;
		stopTime = null;
	}
}
