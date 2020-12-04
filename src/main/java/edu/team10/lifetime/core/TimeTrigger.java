package edu.team10.lifetime.core;

import java.time.OffsetTime;

import edu.team10.lifetime.util.ITrigger;
import edu.team10.lifetime.util.Task;

public class TimeTrigger implements ITrigger {

	private static final long serialVersionUID = 4975601686017051538L;
	
	private Task attachedTask;
	private OffsetTime timeToTrigger;

	@Override
	public boolean checkForCondition() {
		if (OffsetTime.now().isEqual(timeToTrigger)) {
			return true;
		}
		return false;
	}

	@Override
	public Task getAttachedTask() {
		return attachedTask;
	}

	@Override
	public void setAttachedTask(Task attachedTask) {
		this.attachedTask = attachedTask;
	}

	public boolean equals(Object o) {
		TimeTrigger other = (TimeTrigger)o;
		return this.attachedTask.equals(other.getAttachedTask()) && this.timeToTrigger.equals(other.timeToTrigger);
	}
	
	@Override
	public int hashCode() {
		return attachedTask.hashCode() + timeToTrigger.hashCode();
	}
}
