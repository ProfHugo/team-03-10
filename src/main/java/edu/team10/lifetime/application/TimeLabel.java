package edu.team10.lifetime.application;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

import edu.team10.lifetime.backend.Task;
import edu.team10.lifetime.backend.TaskState;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

// displays live time of how long the task has been going on
public class TimeLabel extends Label{
	Task task;
	AnimationTimer timer;
	
	public TimeLabel(String text) {
		super(text);
	}
	
	public TimeLabel(Task task) {
		super();
		this.task = task;
		
		timer = new AnimationTimer() {
	         private LocalTime intervalStartTime;	// an interval measures from one ACTIVE state to the next PAUSED state
	         private Duration timeElapsed;		// total time elapsed from previous the ACTIVE states

	         @Override
	         public void handle(long now) {
	     		TaskState currentState = task.getState();
	    		if (currentState == TaskState.ACTIVE) {
	    			System.out.println("active");
	    		} 
	    		else if (currentState == TaskState.INACTIVE) {
	    			System.out.println("inactive");
	    		}
	    		else if (currentState == TaskState.PAUSED) {
	    			System.out.println("paused");
	    		}

	    		// get time
	             long elapsedSeconds = Duration.between(intervalStartTime, LocalTime.now()).plus(timeElapsed).getSeconds();
	             
	             // format and display
	             long minutes = elapsedSeconds / 60 ;
	             long seconds = elapsedSeconds % 60 ;
	            TimeLabel.this.setText(minutes +" : "+seconds);
	         }
	         
	         // start counting time for this interval
	         @Override
	         public void start() {
	        	 System.out.println("starting");
	             
	     		// if task was paused and needs to be resumed, retrieve time previously recorded
		        timeElapsed = task.getTimeElapsed();

	             intervalStartTime = LocalTime.now();

	             super.start();
	         }
			
		};
	}

}
