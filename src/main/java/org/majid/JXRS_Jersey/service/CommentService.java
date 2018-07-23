package org.majid.JXRS_Jersey.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.majid.JXRS_Jersey.database.InMemoryDatabase;
import org.majid.JXRS_Jersey.dto.Comment;
import org.majid.JXRS_Jersey.dto.Message;

public class CommentService {
	private HashMap<UUID, Message> messages = InMemoryDatabase.getMessages();
	
	public List<Comment> getAllComments(UUID messageId){
		Message message = messages.get(messageId);
		
		if(messages != null) {
			return new ArrayList<Comment>(message.getComments().values());
		}
		
		return new ArrayList<>();
	}

	public Comment getCommentById(UUID messageId, UUID commentId) {
		return messages.get(messageId).getComments().get(commentId);
	}
	
	public void addComment(UUID messageId, Comment comment) {
		messages.get(messageId).getComments().put(comment.getId(), comment);
	}

	public boolean updateComment(UUID messageId, UUID commentId, Comment updateComment) {
		Message message = messages.get(messageId);
		
		if(message == null) {
			return false;
		}
		//if we try to replace a comment (commentId) which doesnt exisit in collection 
		//it returns null
		Comment oldComment = message.getComments().replace(commentId, updateComment);
		
		return oldComment == null ? false : true;
	}
	
}
