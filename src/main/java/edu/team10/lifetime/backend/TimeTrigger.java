package edu.team10.lifetime.backend;

import java.time.OffsetTime;

public class TimeTrigger implements ITrigger {

	private Task attachedTask;
	private OffsetTime timeToTrigger;

	@Override
	public boolean checkForCondition() {
		if (OffsetTime.now().equals(timeToTrigger)) {
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

	@Override
	public int hashCode() {
		return attachedTask.hashCode() + timeToTrigger.hashCode();
	}
}
