package edu.team10.lifetime.backend;

import java.time.OffsetTime;

public class TimeTrigger implements ITrigger {

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
