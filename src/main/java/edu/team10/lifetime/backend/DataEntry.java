package edu.team10.lifetime.backend;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

/**
 * This class represents a single entry in the user's task history. One such
 * entry includes the task name, the time at which the task was started and
 * finished, and the amount of time it took to complete this task.
 * 
 * @author Hugo Wong
 *
 */
public class DataEntry implements Serializable{
	
	private static final long serialVersionUID = -8471128509908443489L;

	private String taskName;
	
	private LocalTime startTime, endTime;
	
	private Duration elapsedTime;

	public DataEntry(String taskName, LocalTime startTime, LocalTime endTime, Duration elapsedTime) {
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
	public LocalTime getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public LocalTime getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalTime endTime) {
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
	
	public String toString() {
		return String.format("Task Name: %s\nStart Time: %s\nEnd Time: %s\nElapsed Time: %s\n\n", taskName, startTime.toString(), endTime.toString(), elapsedTime.toString());
	}
	
	
}
