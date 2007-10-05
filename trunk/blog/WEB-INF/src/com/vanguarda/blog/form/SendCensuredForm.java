package com.vanguarda.blog.form;

import org.apache.struts.action.ActionForm;

public class SendCensuredForm extends ActionForm {
	
	private int postId;
	
	private int commentId;
	
	private String name;
	
	private String email;
	
	private String message;
	
	
	
	/**
	 * @return Returns the commentId.
	 */
	public int getCommentId() {
		return commentId;
	}
	/**
	 * @param commentId The commentId to set.
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the postId.
	 */
	public int getPostId() {
		return postId;
	}
	/**
	 * @param postId The postId to set.
	 */
	public void setPostId(int postId) {
		this.postId = postId;
	}
}
 
