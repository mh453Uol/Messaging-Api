package org.majid.JXRS_Jersey.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.majid.JXRS_Jersey.dto.Comment;
import org.majid.JXRS_Jersey.dto.Message;
import org.majid.JXRS_Jersey.dto.Profile;

public class InMemoryDatabase {

	private static HashMap<UUID, Message> messages = new HashMap<UUID, Message>();
	private static HashMap<String, Profile> profiles = new HashMap<String, Profile>();
	
	public static HashMap<UUID, Message> getMessages() {
		initMessages();
		return messages;
	}

	public static HashMap<String, Profile> getProfiles() {
		initProfiles();
		return profiles;
	}

	public static void initMessages() {
		if (messages.isEmpty()) {
			Message m1 = new Message("Majid", "Foobar");
			
			m1.setComments(generateComments(
					new String[] {"nice blog","lemon and lime","uol"},
					new String[] {"mh786","babo","mh453"}));
			
			Message m2 = new Message("Majid", "Hello Java");

			m2.setComments(generateComments(
					new String[] {"hello from ay"},
					new String[] {"mh786"}));
			
			Message m3 = new Message("Majid", "BYE");

			messages.put(m1.getId(), m1);
			messages.put(m2.getId(), m2);
			messages.put(m3.getId(), m3);
		}
	}
	
	public static void initProfiles() {
		if (profiles.isEmpty()) {
			Profile m1 = new Profile("mh786","Majid", "Foobar");
			Profile m2 = new Profile("babo","Suhan", "Ali");
			Profile m3 = new Profile("zia","Zain", "Shah");

			profiles.put(m1.getProfileName(), m1);
			profiles.put(m2.getProfileName(), m2);
			profiles.put(m3.getProfileName(), m3);
		}
	}
	
	public static HashMap<UUID,Comment> generateComments(String[] comments, String[] authors){
		HashMap<UUID,Comment> commentMap = new HashMap<>();
		
		for(int i=0;i < comments.length;i++) {
			Comment temp = new Comment(comments[i],authors[i]);
			
			commentMap.put(temp.getId(), temp);
		}
		return commentMap;
	}


}
