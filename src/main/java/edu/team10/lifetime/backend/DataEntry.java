package edu.team10.lifetime.backend;

import java.time.Duration;
import java.time.LocalDate;

/**
 * This class represents a single entry in the user's task history. One such
 * entry includes the task name, the time at which the task was started and
 * finished, and the amount of time it took to complete this task.
 * 
 * @author Hugo Wong
 *
 */
public class DataEntry {
	
	private String taskName;
	
	private LocalDate startTime, endTime;
	
	private Duration elapsedTime;

	public DataEntry(String taskName, LocalDate startTime, LocalDate endTime, Duration elapsedTime) {
		this.taskName = taskName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.elapsedTime = elapsedTime;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the startTime
	 */
	public LocalDate getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public LocalDate getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the elapsedTime
	 */
	public Duration getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * @param elapsedTime the elapsedTime to set
	 */
	public void setElapsedTime(Duration elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	
	
	
}
