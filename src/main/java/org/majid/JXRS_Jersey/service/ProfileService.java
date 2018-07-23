package org.majid.JXRS_Jersey.service;

import java.util.Collection;
import java.util.HashMap;

import org.majid.JXRS_Jersey.database.InMemoryDatabase;
import org.majid.JXRS_Jersey.dto.Profile;

public class ProfileService {
	
	private HashMap<String, Profile> database = InMemoryDatabase.getProfiles();
	
	public Collection<Profile> getAllProfiles(){
		return database.values();
	}
	
	public Profile getProfileByName(String profileName) {
		return database.get(profileName);
	}
	
	public void addProfile(Profile profile) {
		database.put(profile.getProfileName(), profile);
	}
	
	public void deleteProfile(Profile profile) {
		database.remove(profile.getProfileName());
	}

	public void update(Profile toUpdateProfile) {
		database.replace(toUpdateProfile.getProfileName(), toUpdateProfile);
	}

}
