package edu.team10.lifetime.application;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import edu.team10.lifetime.backend.DataEntry;
import edu.team10.lifetime.core.Profile;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/** 
 * displays history of all stopped tasks 
 * */
public class TaskHistory extends VBox {
	
		Profile currentProfile;
	
		public TaskHistory(Profile profile) {
			this.setId("historyDisplay");
			
			Label title = new Label("Task History");
			title.setId("historyPageTitle");
			
			Label columnLabel = new Label("task\tstart\tend\ttotal time");
			columnLabel.setId("columnLabel");
			
			this.getChildren().addAll(title, columnLabel);
			
			setProfile(profile);
		}
		
		/** display task history for a profile*/
		public void setProfile(Profile newProfile) {
			// if switching profiles, remove data of previous profile
			if (currentProfile != null) {
				Set<Node> previousTaskData = this.lookupAll("#taskData");
				this.getChildren().removeAll(previousTaskData);
			}

			// set profile
			currentProfile = newProfile;	
			
			// add display of data
			for (DataEntry entry : newProfile.getTaskRecord()) {
				displayTaskData(entry);
			}
		}
		
		/** displays info about a task's starting, ending, and total time */
		public void displayTaskData(DataEntry entry) {
			Label taskData = new Label(entry.getTaskName() 
														+ "\t" + entry.getStartTime().format(DateTimeFormatter.ofPattern("hh:mm"))
														+ "\t" + entry.getEndTime().format(DateTimeFormatter.ofPattern("hh:mm"))
														+ "\t" + durationFormat(entry.getElapsedTime())
														);
			taskData.setId("taskData");
			
			this.getChildren().add(taskData);
		}
		
		// turns a duration into a readable time format
		public String durationFormat(Duration duration) {
			return duration.toHours() + ":" + duration.toMinutes() % 60 + ":" + duration.getSeconds() % 60;
		}

}
