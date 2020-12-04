package edu.team10.lifetime.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

import edu.team10.lifetime.core.Profile;

public class SavefileManager {

	private static String fileName = "/lifetimeData.dat";

	public SavefileManager() {
		fileName = System.getProperty("user.dir") + "/lifetimeData.dat";
		System.out.println(new File(fileName).getAbsolutePath());
	}

	@SuppressWarnings("unchecked")
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

	public void save(Set<Profile> profile) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream profilesOut = new ObjectOutputStream(fileOut);
			profilesOut.writeObject(profile);
			profilesOut.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean saveFileExists() {
		return new File(fileName).exists();
	}

}
