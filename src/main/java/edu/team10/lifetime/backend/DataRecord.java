package edu.team10.lifetime.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This class represents the user's task history as a collection of data
 * entries.
 * 
 * @author Hugo Wong
 *
 */
public class DataRecord implements Iterable<DataEntry>, Serializable {

	private static final long serialVersionUID = -3570350698166388647L;
	List<DataEntry> taskHistory;

	public DataRecord() {
		taskHistory = new ArrayList<>();
	}

	/**
	 *
	 * @param entry The data entry to add.
	 * @return Whether or not the entry was successfully added.
	 */
	public boolean addToRecord(DataEntry entry) {
		return taskHistory.add(entry);
	}

	/**
	 *
	 * @param entry The data entry to remove.
	 * @return Whether or not the entry was successfully removed.
	 */
	public boolean removeFromRecord(DataEntry entry) {
		return taskHistory.remove(entry);
	}

	/**
	 * Purge a task from the record by removing all data entires related to the
	 * task.
	 * 
	 * @return Whether or not this operation is successful.
	 */
	public boolean purgeTaskFromRecord(String taskName) {
		return taskHistory.removeAll(this.getTaskHistory(taskName));
	}

	/**
	 * Wipe the record clean.
	 * 
	 * @return Whether or not this operation is successful.
	 */
	public boolean clearRecord() {
		taskHistory = new LinkedList<>();
		return taskHistory.size() == 0;
	}

	/**
	 * 
	 * @param taskName The name of the task to search for.
	 * @return A linked list containing the data entry history for the given task.
	 */
	public LinkedList<DataEntry> getTaskHistory(String taskName) {
		LinkedList<DataEntry> history = new LinkedList<>();

		for (DataEntry entry : taskHistory) {
			if (entry.getTaskName().equals(taskName)) {
				history.add(entry);
			}
		}

		return history;
	}

	@Override
	public Iterator<DataEntry> iterator() {
		return taskHistory.iterator();
	}

	public int size() {
		return this.size();
	}

	/**
	 * 
	 * @return A set containing the names of all tasks in the record.
	 */
	public Set<String> getTaskSet() {
		Set<String> taskNames = new HashSet<>();
		for (DataEntry entry : this) {
			String taskName = entry.getTaskName();
			taskNames.add(taskName);
		}

		return taskNames;
	}
	
	public List<DataEntry> getTaskHistory() {
		return this.taskHistory;
	}

	

}
