package org.majid.JXRS_Jersey.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.majid.JXRS_Jersey.bean.MessageFilterBean;
import org.majid.JXRS_Jersey.dto.Message;
import org.majid.JXRS_Jersey.service.MessageService;

@Path("messages")
public class MessagesResource {
	private MessageService messageServices = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessagesJSON(@BeanParam MessageFilterBean filter) {
		
		ArrayList<Message> messages = new ArrayList<Message>(messageServices.getAllMessages());
		
		if(filter.getOffset() != null && filter.getAmount() != null) {
			messages = (ArrayList<Message>) messageServices.filterByPagination(filter.getOffset(),filter.getAmount(),messages);
		}
		
		if(filter.getYear() != null) {
			messages = messageServices.filterByYear(filter.getYear(),messages);
		}
		
		return messages;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {
		return this.messageServices.addMessage(message);
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessageById(@PathParam("id") UUID id) {
		return this.messageServices.getMessage(id);
	}
	
	@Path("/{id}")
	@DELETE
	public Response deleteMessageById(@PathParam("id") UUID id) {
		Message message = this.messageServices.getMessage(id);
		
		if(message != null) {
			this.messageServices.removeMessage(message);
			return Response.noContent().build();
		}
		return Response.status(404).build();
	}
	
	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMessageById(@PathParam("id") UUID id, Message message) {
		Message findMessage = this.messageServices.getMessage(id);
		
		if(findMessage == null) {
			return Response.status(404).build();
		}
		message.setId(id);
		
		this.messageServices.updateMessage(message);
		
		return Response.ok().build();
	}
	
	//All http request types are handled by comment resource
	@Path("/{messageId}/comments")
	public CommentResource getCommentsResource() {
		//delegate responsibility of returning response to comment resource
		return new CommentResource();
	}
	
}
