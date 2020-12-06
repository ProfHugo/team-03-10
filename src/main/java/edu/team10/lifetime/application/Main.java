package edu.team10.lifetime.application;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import edu.team10.lifetime.backend.SavefileManager;
import edu.team10.lifetime.core.Profile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	static Scene scene;
	static String currentColorTheme;

	private static BorderPane root;
	private static ScrollPane scrollpane;
	private static VBox sidePanel;
	private static IApplicationElement taskDb, settingsView, taskHistory;

	private static Profile currentProfile;
	static Set<Profile> profiles;
	private SavefileManager saveManager;

	@Override
	public void start(Stage primaryStage) {
		try {

			profiles = new HashSet<>();

			saveManager = new SavefileManager();

			sidePanelInit();

			root = new BorderPane();
			root.setLeft(sidePanel);

			scene = new Scene(root, 1280, 720);

			boolean loadSuccessful = false;

			if (saveManager.saveFileExists()) {
				// try loading the thing
				profiles = saveManager.readFromSaveFile();
				if (profiles != null && profiles.size() > 0) {
					// prompt for starting profile
					ChoiceDialog<Profile> popUp = new ChoiceDialog<>(currentProfile, profiles);
					popUp.setTitle("Profile Choices");
					popUp.setHeaderText("Choose a profile");
					popUp.setContentText("Profiles: ");

					Optional<Profile> result = popUp.showAndWait();

					result.ifPresent(profileChosen -> {
						currentProfile = profileChosen;
					});

					if (!result.isPresent()) {
						Profile profileChosen = profiles.iterator().next();
						currentProfile = profileChosen;
					}

					loadSuccessful = currentProfile != null;
				}
			}

			if (!loadSuccessful) {
				TextInputDialog popUp = new TextInputDialog("Profile Name");
				popUp.setTitle("Initial New Profile");
				popUp.setHeaderText("Give a name for your first profile.");

				// get profile input
				Optional<String> result = popUp.showAndWait();

				// keeps asking user for an account name if no input is given at beginning (user
				// clicks CANCEL or CLOSE)
				// to prevent application from running without a profile instantiated
				while (!result.isPresent()) {
					result = popUp.showAndWait();
				}

				result.ifPresent(profileName -> {
					// create new profile
					Profile profile = new Profile(profileName);
					profiles.add(profile);
					currentProfile = profile;
				});
			}

			taskDb = new TaskDashboard(currentProfile);

			settingsView = new SettingsView(currentProfile);

			taskHistory = new TaskHistory(currentProfile);

			// allow scrolling
			scrollpane = new ScrollPane();
			setPage(taskDb.getView()); // shows task dashboard on default
			root.setCenter(scrollpane); // add scrollpane (containg task dashboard) to root

			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/css/taskDashboard.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/css/sidePanel.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/css/settingsPage.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/css/historyDisplay.css").toExternalForm());

			// set green theme as default
			currentColorTheme = getClass().getResource("/css/colors/green.css").toExternalForm();
			scene.getStylesheets().add(currentColorTheme);

			primaryStage.setScene(scene);
			primaryStage.show();

			// save the current profiles on exit.
			primaryStage.setOnCloseRequest(event -> {
				for (Profile p : profiles) {
					p.stopAllActiveTasks();
				}
				saveManager.save(profiles);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// replaces old color theme with new theme passed in as a link to its CSS
	public static void changeColorTheme(String chosenTheme) {

		if (currentColorTheme != null) {
			// remove current color theme
			scene.getStylesheets().remove(currentColorTheme);
		}

		// add new
		currentColorTheme = chosenTheme; // record this as current color theme
		scene.getStylesheets().add(chosenTheme);
	}

	/** sets the content on the screen to display a given input page */
	public static void setPage(VBox content) {
		scrollpane.setContent(content);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void sidePanelInit() {
		sidePanel = new VBox();
		sidePanel.setId("sidePanel");

		// make buttons that appear on side panel
		Button taskDashboardBtn = new Button("Tasks");
		taskDashboardBtn.setId("taskDashboardBtn");
		taskDashboardBtn.setOnAction(e -> {
			Main.setPage(taskDb.getView());
			taskDb.refresh();
			
		});

		Button taskHistoryBtn = new Button("History");
		taskHistoryBtn.setId("taskDashboardBtn");
		taskHistoryBtn.setOnAction(e -> {
			Main.setPage(taskHistory.getView());
			taskHistory.refresh();
		});

		Button settingsBtn = new Button("Settings");
		settingsBtn.setId("settingsBtn");
		settingsBtn.setOnAction(e -> {
			Main.setPage(settingsView.getView());
			settingsView.refresh();
		});

//		Label accountName = new Label(currentProfile.getProfileName());	// displays which profile is being displayed
//		accountName.setId("accountNameLabel");

		Button newAccountBtn = new Button("New\nProfile"); // allows user to add an account
		newAccountBtn.setId("accountBtn");
		newAccountBtn.setOnAction(e -> {
			TextInputDialog popUp = new TextInputDialog("profile name");
			popUp.setTitle("New Account");
			popUp.setHeaderText("Give a name for your profile");

			// get profile input
			Optional<String> result = popUp.showAndWait();

			result.ifPresent(profileName -> {
				boolean profileExists = false;

				for (Profile p : profiles) {
					if (p.getProfileName().equals(profileName)) {
						profileExists = true;
						break;
					}
				}

				if (!profileExists) {
					Profile profile = new Profile(profileName);
					profiles.add(profile);
				}
			});
		});

		Button chooseAccountBtn = new Button("Switch\nProfile"); // allows user to change account being displayed
		chooseAccountBtn.setId("accountBtn");
		chooseAccountBtn.setOnAction(e -> {
			ChoiceDialog<Profile> popUp = new ChoiceDialog<>(currentProfile, profiles);
			popUp.setTitle("Profile Choices");
			popUp.setHeaderText("Choose a profile");
			popUp.setContentText("Profiles: ");

			Optional<Profile> result = popUp.showAndWait();

			result.ifPresent(profileChosen -> {
				if (!profileChosen.equals(currentProfile)) {
					// display chosen profile
					taskDb.setProfile(profileChosen);
					taskDb.refresh();
					settingsView.setProfile(profileChosen);
					settingsView.refresh();
					taskHistory.setProfile(profileChosen);
					taskHistory.refresh();
					currentProfile = profileChosen;
				}
			});
		});

		Button deleteAccountBtn = new Button("Delete\nProfile");
		deleteAccountBtn.setId("accountBtn");
		deleteAccountBtn.setOnAction(e -> {
			// profile options to delete
			Set<Profile> deletionChoices = new HashSet<>(profiles);
			deletionChoices.remove(currentProfile);

//			System.out.println("current profile referenced by profiles? "+profiles.contains(currentProfile));
//			System.out.println("can delete current profile? "+profileChoices.contains(currentProfile));

			if (deletionChoices.size() > 1) {
				ChoiceDialog<Profile> popUp = new ChoiceDialog<>(deletionChoices.iterator().next(), deletionChoices);
				popUp.setTitle("Profile Deletion Choices");
				popUp.setHeaderText("Choose a profile to delete");
				popUp.setContentText("Profiles: ");

				Optional<Profile> result = popUp.showAndWait();

				result.ifPresent(deleteMe -> {
					profiles.remove(deleteMe);
				});
			} else {
				Alert noneRemovable = new Alert(AlertType.WARNING);
				noneRemovable.setContentText("Cannot remove the only active profile.");
				noneRemovable.show();
			}

		});

		sidePanel.getChildren().addAll(taskDashboardBtn, taskHistoryBtn, settingsBtn, chooseAccountBtn, newAccountBtn,
				deleteAccountBtn);
	}

	/** makes a button to be placed on the side panel */
	public static Button makeSidePanelBtn(String btnDisplayName, String id, VBox destination) {
		Button sidePanelBtn = new Button(btnDisplayName);
		sidePanelBtn.setId(id); // CSS styling
		// System.out.println("constructor passed");
		System.out.println(destination);
		// will change application's center view when clicked
		sidePanelBtn.setOnAction(e -> {
			Main.setPage(destination);
		});

		return sidePanelBtn;
	}

}
