package org.majid.JXRS_Jersey.resource;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.majid.JXRS_Jersey.database.InMemoryDatabase;
import org.majid.JXRS_Jersey.dto.Profile;
import org.majid.JXRS_Jersey.service.ProfileService;

@Path("profile")
public class ProfileResource {
	private ProfileService profileService = new ProfileService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Profile> getAllProfiles(){
		return profileService.getAllProfiles();
	}
	
	@Path("/{profileName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfile(@PathParam("profileName") String profileName) {
		Profile profile = profileService.getProfileByName(profileName);
		
		if(profile == null) {
			return Response.status(404).build();
		}
		return Response.ok(profile).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProfile(Profile profile) {
		if(profile == null) {
			return Response.status(400).build();
		}
		profileService.addProfile(profile);
		return Response.ok(profile).build();
	}
	
	@DELETE
	@Path("/{profileName}")
	public Response deleteProfile(@PathParam("profileName") String profileName) {
		if(profileName == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Profile profile = profileService.getProfileByName(profileName);
		
		if(profile == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
	
		profileService.deleteProfile(profile);
		
		return Response.ok().build();
	}
	
	@Path("/{profileName}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editProfile(@PathParam("profileName") String profileName, 
			Profile profile) {
		if(profileName == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Profile toUpdateProfile = profileService.getProfileByName(profileName);
		
		if(toUpdateProfile == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		profile.setProfileName(toUpdateProfile.getProfileName());
		
		profileService.update(profile);
		
		return Response.ok().build();
	}
	
	
}
