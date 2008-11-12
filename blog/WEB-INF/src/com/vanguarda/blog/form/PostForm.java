package com.vanguarda.blog.form;
import java.util.Date;

import org.apache.struts.action.ActionForm;

public class PostForm extends ActionForm {
 
	private int id;
	 
	private String title;
	 
	private String content;
	 
	private boolean controll;
	 
	private Date insertDate;
	 		 
	private int bloggerUser;
	 
	private BlogForm blog;
	 
	private CommentForm lastComments;
	
	private int coutComments;
	
	private int status = 1;
	
	private String author;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}	 
	/**
	 * @return Returns the blog.
	 */
	public BlogForm getBlog() {
		return blog;
	}
	/**
	 * @param blog The blog to set.
	 */
	public void setBlog(BlogForm blog) {
		this.blog = blog;
	}
	/**
	 * @return Returns the bloggerUser.
	 */
	public int getBloggerUser() {
		return bloggerUser;
	}
	/**
	 * @param bloggerUser The bloggerUser to set.
	 */
	public void setBloggerUser(int bloggerUser) {
		this.bloggerUser = bloggerUser;
	}
	/**
	 * @return Returns the content.
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content The content to set.
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return Returns the controll.
	 */
	public boolean isControll() {
		return controll;
	}
	/**
	 * @param controll The controll to set.
	 */
	public void setControll(boolean controll) {
		this.controll = controll;
	}
	/**
	 * @return Returns the coutComments.
	 */
	public int getCoutComments() {
		return coutComments;
	}
	/**
	 * @param coutComments The coutComments to set.
	 */
	public void setCoutComments(int coutComments) {
		this.coutComments = coutComments;
	}
	/**
	 * @return Returns the id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return Returns the insertDate.
	 */
	public Date getInsertDate() {
		return insertDate;
	}
	/**
	 * @param insertDate The insertDate to set.
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	/**
	 * @return Returns the lastComments.
	 */
	public CommentForm getLastComments() {
		return lastComments;
	}
	/**
	 * @param lastComments The lastComments to set.
	 */
	public void setLastComments(CommentForm lastComments) {
		this.lastComments = lastComments;
	}
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * @return Returns the status.
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(int status) {
		this.status = status;
	}
}
 
