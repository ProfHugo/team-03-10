package edu.team10.lifetime.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import edu.team10.lifetime.util.ITrigger;
import edu.team10.lifetime.util.Task;

/**
 * This class acts as an aggregator and processor of triggers.
 * 
 * @author Hugo Wong
 *
 */
public class TriggerBus implements Serializable {

	private static final long serialVersionUID = 7739226075621597980L;
	HashSet<ITrigger> triggers;
	
	public TriggerBus() {
		triggers = new HashSet<>();
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
	public void checkTriggers(Collection<Task> tasks) {
		for (ITrigger trigger : triggers) {
			Task attachedTask = trigger.getAttachedTask();
			if (trigger.checkForCondition() && tasks.contains(attachedTask) && !attachedTask.isActive()) {
				attachedTask.startTask();
			}
		}
	}
	
}
