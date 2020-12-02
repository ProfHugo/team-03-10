package edu.team10.lifetime.application;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

// controls visibility of live timers
public class LiveTimerSetting {

	private boolean timersVisible;

	Set<TimeLabel> timers = new HashSet<>(); // set of timers from existing tasks

	HBox view; // what user sees: 2 buttons to turn on / off

	public LiveTimerSetting() {
		timersVisible = false; // all timers invisible on default

		view = makeView();
	}

	public HBox makeView() {
		HBox settingBox = new HBox(); // contains label and 2 buttons
		settingBox.setId("liveTimerSetting");

		Label settingName = new Label("Live timer visibility");

		Button on = new Button("on");
		on.setOnAction(e -> {
			if (!timersVisible)
				toggleVisibility(true);
		});

		Button off = new Button("off");
		off.setOnAction(e -> {
			if (timersVisible)
				toggleVisibility(false);
		});

		settingBox.getChildren().addAll(settingName, on, off);
		return settingBox;
	}

	/** change visibility of all live timers **/
	public void toggleVisibility(boolean choice) {
		timersVisible = choice;
		for (TimeLabel timer : timers) {
			timer.setVisibility(choice);
		}
	}

	/**
	 * @return the timersVisible
	 */
	public boolean isLifeTimerOn() {
		return timersVisible;
	}

}
