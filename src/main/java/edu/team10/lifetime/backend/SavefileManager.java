package edu.team10.lifetime.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

import edu.team10.lifetime.core.Profile;

/**
 * This class manages saving and loading sets of profiles from a file.
 * 
 * @author Hugo Wong
 *
 */
public class SavefileManager {

	public static String fileName = "/lifetimeData.dat";

	public SavefileManager() {
		fileName = System.getProperty("user.dir") + "/lifetimeData.dat";
	}

	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @return A set of profiles read from the manager's save file.
	 */
	public Set<Profile> readFromSaveFile() {
		Set<Profile> profiles = null;
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream profilesIn = new ObjectInputStream(fileIn);
			profiles = (Set<Profile>) profilesIn.readObject();
			profilesIn.close();
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		return profiles;
	}

	/**
	 * Save the given set of profiles to the manager's save file.
	 * 
	 * @param profile
	 */
	public void save(Set<Profile> profiles) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream profilesOut = new ObjectOutputStream(fileOut);
			profilesOut.writeObject(profiles);
			profilesOut.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return If a save file exists.
	 */
	public boolean saveFileExists() {
		return new File(fileName).exists();
	}

}
