package edu.team10.lifetime.backend;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * A class representing a user profile. A user has a name and a set of tasks associated
 * with them.
 * 
 * @author Hugo Wong
 *
 */
public class Profile {

	private TriggerBus triggers;
	
	private TreeSet<Task> allTasks;
	private String profileName;
	private DataRecord taskRecord;

	public Profile(String username) {
		this.triggers = new TriggerBus();
		this.profileName = username;
		this.allTasks = new TreeSet<>();
		this.taskRecord = new DataRecord();
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfilename(String profileName) {
		this.profileName = profileName;
	}

	// The following are a set of responsibilities that may be pulled out onto other
	// classes should that be a better option

	/**
	 * 
	 * @return The internet set of all tasks. Avoid if possible?
	 */
	public TreeSet<Task> getAllTasks() {
		return this.allTasks;
	}

	/**
	 * 
	 * @param taskName the name of the task to find.
	 * @return The task in the system with the given name.
	 */
	public Task getTaskByName(String taskName) {
		for (Task t : allTasks) {
			if (t.getName().equalsIgnoreCase(taskName)) {
				return t;
			}
		}
		return null;
	}

	public TaskState getTaskState(String taskName) {
		return this.getTaskByName(taskName).getState();
	}

	public boolean hasTask(String taskName) {
		return getTaskByName(taskName) != null;
	}

	public boolean addTask(Task task) {
		return this.allTasks.add(task);
	}

	public boolean addTask(String taskName) {
		return this.addTask(new Task(taskName));
	}

	public boolean removeTask(String taskName) {
		return this.removeTask(this.getTaskByName(taskName));
	}

	public boolean removeTask(Task task) {
		return this.allTasks.remove(task);
	}

	/**
	 * Precondition: The task is in the system and is currently not started.
	 * Postcondition: The task is started and remains in the system.
	 * 
	 * @return Whether or not the task properly starts.
	 */
	public boolean startTask(String taskName) {
		Task task = this.getTaskByName(taskName);
		if (task.isActive()) {
			return false;
		}
		task.startTask();
		return task.isActive();
	}

	/**
	 * Precondition: The task is in the system and is currently active.
	 * Postcondition: If the task was unpaused, it's paused, and vice versa.
	 * 
	 * @return Whether or not the active task was paused/unpaused.
	 */
	public boolean togglePauseTask(String taskName) {
		Task task = this.getTaskByName(taskName);
		if (!task.isActive()) {
			return false;
		}
		return task.togglePauseTask();
	}

	/**
	 * Precondition: The task is in the system and is currently active.
	 * Postcondition: The task is stopped and remains in the system. The task's
	 * completion history will be appended to the data record.
	 * 
	 * @return Whether or not the task ends successfully.
	 */
	public boolean stopTask(String taskName) {
		Task task = this.getTaskByName(taskName);
		if (!task.isActive()) {
			return false;
		}
		task.stopTask();

		// enter the task performance into record.
		DataEntry entry = new DataEntry(taskName, LocalTime.from(task.getStartTime().atZone(ZoneId.systemDefault())),
				LocalTime.from(task.getStopTime().atZone(ZoneId.systemDefault())), task.getTimeElapsed());
		this.taskRecord.addToRecord(entry);
		return !task.isActive();
	}

	/**
	 * 
	 * @param trigger The trigger to add to the set of all triggers to check for.
	 * @return Whether or not this trigger was added successfully.
	 */
	public boolean addTrigger(ITrigger trigger) {
		return triggers.addTrigger(trigger);
	}

	/**
	 * 
	 * @param trigger The trigger to remove from the set of all triggers to check
	 *                for.
	 * @return Whether or not this trigger was removed successfully.
	 */
	public boolean removeTrigger(ITrigger trigger) {
		return triggers.removeTrigger(trigger);
	}

	/**
	 * Check to see if any trigger's condition is fulfilled. If so, start the task
	 * attached to the trigger if it isn't already active.
	 */
	public void checkTriggers() {
		triggers.checkTriggers(allTasks);
	}

	/**
	 * 
	 * @return The task record of this user.
	 */
	public DataRecord getTaskRecord() {
		return taskRecord;
	}
}
