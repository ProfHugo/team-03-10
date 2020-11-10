package edu.team10.lifetime.backend;

/**
 * A class representing a Task in the application. Each task has a name, its own
 * timer, and a set of triggers which would automatically start the task.
 * 
 * @author Hugo Wong
 *
 */
public class Task implements Comparable<Task>{

	private String name;

	private Timer timer;

	private boolean isActive;
	
	public Task(String name) {
		this.name = name;
		timer = new Timer();
	}

	/**
	 * Start the task.
	 */
	public void startTask() {
		this.timer.start();
		this.isActive = true;
	}

	/**
	 * Stop the task.
	 */
	public void stopTask() {
		this.timer.stop();
		this.isActive = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public int hashCode() {
		return this.name.hashCode();
	}

	public boolean equals(Object o) {
		return this.compareTo((Task) o) == 0;
	}
	
	@Override
	public int compareTo(Task o) {
		return this.name.compareTo(o.getName());
	}

}
