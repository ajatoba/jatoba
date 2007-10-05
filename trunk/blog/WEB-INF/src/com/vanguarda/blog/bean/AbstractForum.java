package com.vanguarda.blog.bean;

import java.util.Date;


public abstract class AbstractForum {
 
	private int id;
	 
	private String title;
	 
	private Date insertDate;
	 
	private int status;
	 
	private String description;
	 
	private Topic topic;
	 
	private Category category;

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

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
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
		buffer.append(";insertDate=");
		buffer.append(insertDate!= null?insertDate.toString():null);
		buffer.append(";status=");
		buffer.append(status);
		buffer.append(";description=");
		buffer.append(description);
		
		return buffer.toString();
	
	}
	 
}
 
