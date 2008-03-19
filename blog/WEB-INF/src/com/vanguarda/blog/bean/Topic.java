package com.vanguarda.blog.bean;

import java.util.Collection;
import java.util.Date;

public class Topic {
 
	private int id;
	 
	private String title;
	 
	private String description;
	 
	private Date insertDate;
	 
	private int status;

	private Category category;
	 
	private Collection messages;
	
	private Forum forum;


	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Collection getMessages() {
		return messages;
	}

	public void setMessages(Collection message) {
		this.messages = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("id=");
		buffer.append(id);
		buffer.append(";title=");
		buffer.append(title);
		buffer.append(";description=");
		buffer.append(description);
		buffer.append(";insertDate=");
		buffer.append(insertDate!= null?insertDate.toString():null);
		buffer.append(";status=");
		buffer.append(status);
		
		return buffer.toString();
	}
	 
}
 
