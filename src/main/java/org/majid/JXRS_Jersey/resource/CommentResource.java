package org.majid.JXRS_Jersey.resource;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.majid.JXRS_Jersey.dto.Comment;
import org.majid.JXRS_Jersey.service.CommentService;

/*
 * This is a sub resource, so url look like /messages/21/comments 
 * we need the messages/<id> prefix
 * 
 * */
@Path("/")
public class CommentResource {
	private CommentService commentService = new CommentService();

	@GET
	public List<Comment> getComments(@PathParam("messageId") UUID messageId) {
		return commentService.getAllComments(messageId);
	}

	@Path("/{commentId}")
	@GET
	public Comment getComment(@PathParam("messageId") UUID messageId, @PathParam("commentId") UUID commentId) {
		return commentService.getCommentById(messageId, commentId);
	}

	@POST
	public Response createComment(@Context UriInfo urlInfo, 
			@PathParam("messageId") UUID messageId, 
			Comment comment) {
		 URI pathToCreated = urlInfo.getAbsolutePathBuilder().path(messageId.toString()).build();
		
		commentService.addComment(messageId, comment);
		
		return Response.created(pathToCreated).build();
	}

	@Path("/{commentId}")
	@PUT
	public Response updateComment(@PathParam("messageId") UUID messageId, @PathParam("commentId") UUID commentId,
			Comment comment) {
		comment.setId(commentId);
		boolean success = commentService.updateComment(messageId, commentId, comment);

		if (!success) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok().build();
	}
}
