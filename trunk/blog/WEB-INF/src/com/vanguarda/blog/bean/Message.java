package com.vanguarda.blog.bean;

import java.util.Date;

public class Message {
 
	private int id;
	 
	private String title;
	 
	private String content;
	 
	private int status;
	 
	private Topic topic;

	private Date insertDate;
	
	private ForumUser user;
	
	public ForumUser getUser() {
		return user;
	}

	public void ForumUser(ForumUser user) {
		this.user = user;
	}

	public void setUser(ForumUser user) {
		this.user = user;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	 
}
 
