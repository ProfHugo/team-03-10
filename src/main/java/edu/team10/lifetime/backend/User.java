package edu.team10.lifetime.backend;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * A class representing a user. A user has a name and a set of tasks associated
 * with them.
 * 
 * @author Hugo Wong
 *
 */
public class User {

	private HashSet<ITrigger> triggers;
	private TreeSet<Task> allTasks;
	private String username;

	public User(String username) {
		this.username = username;
		this.allTasks = new TreeSet<>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
			if (t.getName().equals(taskName)) {
				return t;
			}
		}
		return null;
	}

	public boolean addTask(Task task) {
		return this.allTasks.add(task);
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
	 * Postcondition: The task is stopped and remains in the system.
	 * 
	 * @return Whether or not the task ends successfully.
	 */
	public boolean endTask(String taskName) {
		Task task = this.getTaskByName(taskName);
		if (!task.isActive()) {
			return false;
		}
		task.stopTask();
		return !task.isActive();
	}

	/**
	 * 
	 * @param trigger The trigger to add to the set of all triggers to check for.
	 * @return Whether or not this trigger was added successfully.
	 */
	public boolean addTrigger(ITrigger trigger) {
		return this.triggers.add(trigger);
	}

	/**
	 * 
	 * @param trigger The trigger to remove from the set of all triggers to check
	 *                for.
	 * @return Whether or not this trigger was removed successfully.
	 */
	public boolean removeTrigger(ITrigger trigger) {
		return this.triggers.remove(trigger);
	}

	/**
	 * Check to see if any trigger's condition is fulfilled. If so, start the task
	 * attached to the trigger if it isn't already active.
	 */
	public void checkTriggers() {
		for (ITrigger trigger : triggers) {
			Task attachedTask = trigger.getAttachedTask();
			if (trigger.checkForCondition() && this.allTasks.contains(attachedTask) && !attachedTask.isActive()) {
				attachedTask.startTask();
			}
		}
	}
}
