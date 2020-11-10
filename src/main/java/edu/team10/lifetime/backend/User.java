package edu.team10.lifetime.backend;

import java.util.TreeSet;

/**
 * A class representing a user. A user has a list of 
 * @author Hugo Wong
 *
 */
public class User {

	private TreeSet<Task> allTasks;
	private String username;
	
	public User(String username) {
		this.username = username;
		this.allTasks = new TreeSet<>();
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 
	 * @return The internet set of all tasks. Avoid if possible?
	 */
	public TreeSet<Task> getAllTasks() {
		return this.allTasks;
	}
}
