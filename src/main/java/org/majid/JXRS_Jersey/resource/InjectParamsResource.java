package org.majid.JXRS_Jersey.resource;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("injectparams")
public class InjectParamsResource {

	@Path("getheadertoken")
	@GET 
	@Produces(MediaType.TEXT_PLAIN)
	//HeaderParams(nameofheader) get header value
	public String getHeader(@HeaderParam("token") String token) {
		return token;
	}
	
	@Path("content")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getContent(@Context UriInfo urlInfo,
			@Context HttpHeaders headers) {
		return urlInfo.getQueryParameters().toString() + "/n" + headers.getLanguage().toString();
	}
}
