package edu.team10.lifetime.application;

import java.math.BigInteger;
import java.time.Duration;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

import edu.team10.lifetime.backend.DataEntry;
import edu.team10.lifetime.backend.DataRecord;
import edu.team10.lifetime.core.Profile;
import edu.team10.lifetime.util.Timer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


/**
 * displays history of all stopped tasks
 */
public class TaskHistory implements IApplicationElement {

	Profile currentProfile;
	VBox view;
	
	TableView<DataEntry> historyTable;

	public TaskHistory(Profile profile) {
		view = new VBox();
		view.setId("historyDisplay");

		Label title = new Label("Task History");
		title.setId("historyPageTitle");
		

		// set profile
		currentProfile = profile;
				
		// Create the table
		historyTable = new TableView<>();
		historyTable.setEditable(true);
		
		TableColumn<DataEntry, String> taskNameCol = new TableColumn<>("Task Name");
		taskNameCol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
		taskNameCol.setMinWidth(200);
		TableColumn<DataEntry, String> startCol = new TableColumn<>("Start Time");
		startCol.setCellValueFactory(new PropertyValueFactory<>("startTimeStr"));
		startCol.setMinWidth(100);
		TableColumn<DataEntry, String> endCol = new TableColumn<>("End Time");
		endCol.setCellValueFactory(new PropertyValueFactory<>("endTimeStr"));
		endCol.setMinWidth(100);
		TableColumn<DataEntry, String> totalTimeCol = new TableColumn<>("Total Time");
		totalTimeCol.setCellValueFactory(new PropertyValueFactory<>("elapsedTimeStr"));
		totalTimeCol.setMinWidth(100);
		historyTable.setMinSize(500, 450);
		

		ObservableList<DataEntry> tableData = FXCollections.observableList((currentProfile.getTaskRecord().getTaskHistory()));
		historyTable.setItems(tableData);
		historyTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		Button removeEntryBtn = new Button("Remove selected entry");
		removeEntryBtn.setOnAction(event -> {
			historyTable.getItems().removeAll(historyTable.getSelectionModel().getSelectedItems());
		});
		
		
		
		historyTable.getColumns().addAll(taskNameCol, startCol, endCol, totalTimeCol);
		
		

		view.getChildren().addAll(title, historyTable, removeEntryBtn);

		setProfile(profile);
	}

	/** display task history for a profile */
	public void setProfile(Profile newProfile) {
		// if switching profiles, remove data of previous profile
		if (currentProfile != null) {
			Set<Node> previousTaskData = view.lookupAll("#rowOfData");
			Node previousTaskAnalysisBtn = view.lookup("#taskAnalysisBtn");
//			previousTaskData.add(previousTaskAnalysisBtn);
			view.getChildren().remove(previousTaskAnalysisBtn);
			view.getChildren().removeAll(previousTaskData);
		}

		// set profile
		currentProfile = newProfile;

		// put button to analyze tasks
		Button analyzeButton = makeAnalyzeBtn();
		view.getChildren().add(1, analyzeButton);

	}

	// turns a duration into a readable time format
	public static String durationFormat(Duration duration) {
		return Timer.formatTimeUnit(duration.toHours()) + ":" + Timer.formatTimeUnit(duration.toMinutes() % 60)
				+ ":" + Timer.formatTimeUnit(duration.getSeconds() % 60);
	}

	public Button makeAnalyzeBtn() {
		// a LoD mess but whatever man
		DataRecord record = this.currentProfile.getTaskRecord();

//		Set<String> taskNames = record.getTaskSet();

		Button analyzeButton = new Button();
		analyzeButton.setId("taskAnalysisBtn");
		analyzeButton.setText("Analyze Task");

		// event handler: if button is clicked, window pops up
		analyzeButton.setOnAction(event -> {

			Set<String> taskNames = record.getTaskSet(); // list of existing tasks needs to be updated every time button
															// clicked

			if (taskNames.size() > 0) {
				ChoiceDialog<String> popUp = new ChoiceDialog<>(taskNames.iterator().next(), taskNames);
				popUp.setTitle("Tasks");
				popUp.setHeaderText("Choose a task to analyze");
				popUp.setContentText("Tasks: ");

				Optional<String> result = popUp.showAndWait();

				result.ifPresent(taskChosen -> {
					LinkedList<DataEntry> taskEntries = record.getTaskHistory(taskChosen);

					if (taskEntries.size() > 0) {

						Duration minTime = taskEntries.get(0).getElapsedTime();
						Duration maxTime = minTime;
						Duration averageTime, sampleVariance;
						long meanTimeMillis = 0;

						// Find min and max and perform the partial calculations needed for the mean.
						for (DataEntry entry : taskEntries) {
							Duration entryTime = entry.getElapsedTime();
							// update min
							if (entryTime.compareTo(minTime) < 0) {
								minTime = entryTime;
							}
							// update max
							if (entryTime.compareTo(maxTime) > 0) {
								maxTime = entryTime;
							}
							// sum up to average
							meanTimeMillis += entryTime.toMillis();
						}

						// divide to find mean
						meanTimeMillis /= taskEntries.size();
						averageTime = Duration.ofMillis(meanTimeMillis);

						// calculate sample variance
						if (taskEntries.size() > 1) {
							// general case
							// first order of business: calculate the sum of (x_i - x bar)^2 for all i.
							BigInteger sumOfSquares = BigInteger.valueOf(0);
							for (DataEntry entry : taskEntries) {
								long entryTime = entry.getElapsedTime().toMillis();

								BigInteger distanceFromMean = BigInteger.valueOf(entryTime - meanTimeMillis);
								sumOfSquares = sumOfSquares.add(distanceFromMean.multiply(distanceFromMean));
							}

							// last order of business: divide by n - 1
							BigInteger svarBigDec = sumOfSquares.divide(BigInteger.valueOf(taskEntries.size() - 1));
							sampleVariance = Duration.ofMillis(svarBigDec.divide(BigInteger.valueOf(1000)).longValue());
						} else {
							// trivial case: no variance for size = 1
							sampleVariance = Duration.ofMillis(0);
						}

						// displays the stats
						Alert statDisplay = new Alert(AlertType.INFORMATION);
						statDisplay.setTitle("Task Statistics");
						statDisplay.setHeaderText("");
						String info = String.format("Min Time: %s\nMax Time: %s\nMean Time: %s\nSample Variance: %s\n",
								durationFormat(minTime), durationFormat(maxTime), durationFormat(averageTime),
								durationFormat(sampleVariance));
						statDisplay.setContentText(info);
						statDisplay.show();

					}
				});
			} else {
				// do nothing.
			}

		});

		return analyzeButton;
	}

	@Override
	public void refresh() {
		ObservableList<DataEntry> tableData = FXCollections.observableList((currentProfile.getTaskRecord().getTaskHistory()));
		historyTable.setItems(tableData);
	}

	@Override
	public VBox getView() {
		return this.view;
	}

}
