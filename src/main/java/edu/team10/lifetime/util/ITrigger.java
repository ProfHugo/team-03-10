package edu.team10.lifetime.util;

import java.io.Serializable;

public interface ITrigger extends Serializable {
	
	/**
	 * Check if the condition to fire this trigger is true.
	 * 
	 * @return If this is true, the trigger should fire.
	 */
	public boolean checkForCondition();
	
	/**
	 * @return The task attached to this trigger.
	 */
	public Task getAttachedTask();
	
	/**
	 * @param attachedTask The new task to attach this trigger to.
	 */
	public void setAttachedTask(Task attachedTask);
	
}
