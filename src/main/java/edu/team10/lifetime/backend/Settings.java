package edu.team10.lifetime.backend;

import java.util.HashMap;
import java.util.Map;

/**
 * A class which stores a set of settings in a Map. To get the value of a
 * certain setting, simply pass it the name of the setting to fetch.
 * 
 * 
 * @author Hugo Wong
 *
 */
public class Settings {

	private Map<String, String> settings;

	public static final String TRUE = "true", FALSE = "false";

	public Settings(Map<String, String> initialSettings) {
		settings = initialSettings;
	}

	/**
	 * @param settingName
	 * @return The value associated with the given setting name.
	 */
	public String getSettingValue(String settingName) {
		return settings.get(settingName);
	}

	/**
	 * Set the setting associated with the given name to this value. If this setting
	 * does not previously exists, make a new entry containing this pair.
	 * 
	 * @param settingName
	 * @param newValue
	 */
	public void changeSettingValue(String settingName, String newValue) {
		settings.put(settingName, newValue);
	}

}
