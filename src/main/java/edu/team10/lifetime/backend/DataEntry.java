package edu.team10.lifetime.backend;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import edu.team10.lifetime.util.Timer;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class represents a single entry in the user's task history. One such
 * entry includes the task name, the time at which the task was started and
 * finished, and the amount of time it took to complete this task.
 * 
 * @author Hugo Wong
 *
 */
public class DataEntry implements Serializable {

	private static final long serialVersionUID = -8471128509908443489L;

	private String taskName, startTimeStr, endTimeStr, elapsedTimeStr;

	private LocalTime startTime, endTime;

	private Duration elapsedTime;

	public DataEntry(String taskName, LocalTime startTime, LocalTime endTime, Duration elapsedTime) {
		this.taskName = taskName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.elapsedTime = elapsedTime;
		
		this.startTimeStr = startTime.format(DateTimeFormatter.ofPattern("hh:mm"));
		this.setEndTimeStr(endTime.format(DateTimeFormatter.ofPattern("hh:mm")));
		this.setElapsedTimeStr(Timer.durationFormat(elapsedTime));
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
		return String.format("Task Name: %s\nStart Time: %s\nEnd Time: %s\nElapsed Time: %s\n\n", taskName,
				startTime.toString(), endTime.toString(), elapsedTime.toString());
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getElapsedTimeStr() {
		return elapsedTimeStr;
	}

	public void setElapsedTimeStr(String elapsedTimeStr) {
		this.elapsedTimeStr = elapsedTimeStr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elapsedTime == null) ? 0 : elapsedTime.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataEntry other = (DataEntry) obj;
		if (elapsedTime == null) {
			if (other.elapsedTime != null)
				return false;
		} else if (!elapsedTime.equals(other.elapsedTime))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		return true;
	}
	
	

}
