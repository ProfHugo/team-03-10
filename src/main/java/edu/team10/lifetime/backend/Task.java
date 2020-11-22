package edu.team10.lifetime.backend;

import java.time.Duration;
import java.time.Instant;

/**
 * A class representing a Task in the application. Each task has a name, its own
 * timer, and a set of triggers which would automatically start the task.
 * 
 * @author Hugo Wong
 *
 */
public class Task implements Comparable<Task> {

	private String name;

	private Timer timer;

	private TaskState currentState;

	public Task(String name) {
		this.name = name;
		timer = new Timer();
		this.currentState = TaskState.INACTIVE;
	}

	/**
	 * Start the task.
	 */
	public void startTask() {
		this.timer.start();
		this.currentState = TaskState.ACTIVE;
	}

	/**
	 * Toggle between pause/unpause for this task.
	 */
	public boolean togglePauseTask() {
		if (this.isActive()) {
			this.timer.togglePause();
			if (this.getState() == TaskState.ACTIVE) {
				this.currentState = TaskState.PAUSED;
			} else {
				this.currentState = TaskState.ACTIVE;
			}
			return true;
		} else {
			System.out.println("Warning: Cannot pause/unpause an inactive task.");
			return false;
		}

	}

	/**
	 * Stop the task.
	 */
	public void stopTask() {
		this.timer.stop();
		this.currentState = TaskState.INACTIVE;
	}

	/**
	 * @return the startTime
	 */
	public Instant getStartTime() {
		return timer.getStartTime();
	}

	/**
	 * @return the stopTime
	 */
	public Instant getStopTime() {
		return timer.getStopTime();
	}
	
	/**
	 * 
	 * @return The difference between the time when this task was started and when
	 *         it was stopped.
	 */
	public Duration getDelta() {
		return this.timer.getDelta();
	}

	/**
	 * 
	 * @return The amount of active time elapsed for this task for this session.
	 */
	public Duration getTimeElapsed() {
		return this.timer.getElapsed();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TaskState getState() {
		return this.currentState;
	}

	/**
	 * For a task to be active, it's either ongoing or it's paused.
	 * @return
	 */
	public boolean isActive() {
		return this.currentState != TaskState.INACTIVE;
	}
	
	public int hashCode() {
		return this.name.hashCode();
	}

	public boolean equals(Object o) {
		return this.compareTo((Task) o) == 0;
	}

	@Override
	public int compareTo(Task o) {
		return this.name.compareToIgnoreCase((o.getName()));
	}

}
