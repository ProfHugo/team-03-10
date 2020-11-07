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
	
	public void start() {
		startTime = Instant.now();
	}
	
	public void stop() {
		stopTime = Instant.now();
	}
	
	public Duration getDelta() {
		Duration delta = Duration.between(startTime, stopTime);
		return delta;
	}
	
	public void reset() {
		startTime = null;
		stopTime = null;
	}
}
