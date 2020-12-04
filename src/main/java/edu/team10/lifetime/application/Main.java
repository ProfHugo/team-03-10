package edu.team10.lifetime.application;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import edu.team10.lifetime.core.Profile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
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
	private static VBox taskDb, sidePanel, settingsView;

	private static Profile currentProfile;
	static Set<Profile> profiles;

	@Override
	public void start(Stage primaryStage) {
		try {

			profiles = new HashSet<>();

			TextInputDialog popUp = new TextInputDialog("Account Name");
			popUp.setTitle("Initial New Account");
			popUp.setHeaderText("Give a name for your account");

			// get profile input
			Optional<String> result = popUp.showAndWait();

			result.ifPresent(profileName -> {
				// TODO create new profile
				Profile profile = new Profile(profileName);
				profiles.add(profile);
				currentProfile = profile;
			});
			
			sidePanelInit();

			root = new BorderPane();
			root.setLeft(sidePanel);

			taskDb = new TaskDashboard(currentProfile);

			settingsView = new SettingsView(currentProfile);

			// allow scrolling
			scrollpane = new ScrollPane();
			setPage(taskDb); // shows task dashboard on default
			root.setCenter(scrollpane); // add scrollpane (containg task dashboard) to root

			scene = new Scene(root, 1280, 720);

			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/css/taskDashboard.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/css/sidePanel.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/css/settingsPage.css").toExternalForm());

			// set green theme as default
			currentColorTheme = getClass().getResource("/css/colors/green.css").toExternalForm();
			scene.getStylesheets().add(currentColorTheme);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// replaces old color theme with new theme passed in as a link to its CSS
	public static void changeColorTheme(String chosenTheme) {
		// remove current color theme
		scene.getStylesheets().remove(currentColorTheme);

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
			Main.setPage(taskDb);
		});
		
		Button settingsBtn = new Button("Settings");
		settingsBtn.setId("settingsBtn");
		settingsBtn.setOnAction(e -> {
			Main.setPage(settingsView);
		});
		
//		Label accountName = new Label(currentProfile.getProfileName());	// displays which profile is being displayed
//		accountName.setId("accountNameLabel");
		
		Button newAccountBtn = new Button("New\nAccount"); // allows user to add an account
		newAccountBtn.setId("accountBtn");
		newAccountBtn.setOnAction(e -> {
			TextInputDialog popUp = new TextInputDialog("account name");
			popUp.setTitle("New Account");
			popUp.setHeaderText("Give a name for your account");

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

		Button chooseAccountBtn = new Button("Switch\nAccount"); // allows user to change account being displayed
		chooseAccountBtn.setId("accountBtn");
		chooseAccountBtn.setOnAction(e -> {
			ChoiceDialog<Profile> popUp = new ChoiceDialog<>(currentProfile, profiles);
			popUp.setTitle("Profile Choices");
			popUp.setHeaderText("Choose an account");
			popUp.setContentText("Accounts: ");

			Optional<Profile> result = popUp.showAndWait();

			result.ifPresent(profileChosen -> {
				if (!profileChosen.equals(currentProfile)) {
					// display chosen profile
					((TaskDashboard) taskDb).setProfile(profileChosen);
					((SettingsView) settingsView).setProfile(profileChosen);
					profiles.add(profileChosen);
					currentProfile = profileChosen;
				}
			});
		});

		sidePanel.getChildren().addAll(taskDashboardBtn, settingsBtn, chooseAccountBtn, newAccountBtn);
	}

	public static void taskDbInit() {
		sidePanel = new VBox();
		sidePanel.setId("sidePanel");

		// make buttons that appear on side panel
		// Button taskDashboardBtn = makeSidePanelBtn("Tasks", "taskDashboardBtn",
		// Main.taskDb);
		Button taskDashboardBtn = new Button("Tasks");
		taskDashboardBtn.setId("taskDashboardBtn");
		taskDashboardBtn.setOnAction(e -> {
			Main.setPage(taskDb);
		});
//		System.out.println("settingsView: " + Main.settingsView);
//	    System.out.println(Main.settingsView.view.getId());
//		Button settingsBtn = makeSidePanelBtn("SettingsView", "settingsBtn", Main.settingsView.view);
		Button settingsBtn = new Button("SettingsView");
		settingsBtn.setId("settingsBtn");
		settingsBtn.setOnAction(e -> {
			Main.setPage(settingsView);
		});

		sidePanel.getChildren().addAll(taskDashboardBtn, settingsBtn);
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
