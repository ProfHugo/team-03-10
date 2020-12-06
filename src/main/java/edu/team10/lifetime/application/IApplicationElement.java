package edu.team10.lifetime.application;

import edu.team10.lifetime.core.Profile;
import javafx.scene.layout.VBox;

/**
 * Every main app viewable element must implement this interface.
 * 
 * @author Hugo Wong
 *
 */
public interface IApplicationElement {
	
	public void setProfile(Profile newProfile);
	
	public void refresh();
	
	public VBox getView();
	
}
