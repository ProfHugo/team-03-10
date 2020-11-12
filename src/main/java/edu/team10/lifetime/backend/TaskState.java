package edu.team10.lifetime.backend;

/**
 * This enum pack represents a set of states which a task can be in at any given
 * time.
 * 
 * @author Hugo Wong
 *
 */
public enum TaskState {
	ACTIVE, PAUSED, INACTIVE;

	public String toString() {
		switch (this) {
		case ACTIVE:
			return "Active";
		case PAUSED:
			return "Paused";
		case INACTIVE:
			return "Inactive";
		default:
			return "Stateless";

		}
	}
}
