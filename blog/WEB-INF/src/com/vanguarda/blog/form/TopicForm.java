package com.vanguarda.blog.form;

import java.util.Collection;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.vanguarda.blog.bean.Category;
import com.vanguarda.blog.bean.Forum;

public class TopicForm extends ActionForm{
	
	private int id;
	 
	private String title;
	 
	private String description;
	 
	private int status;

	private int category;
	
	private int forum;

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getForum() {
		return forum;
	}

	public void setForum(int forum) {
		this.forum = forum;
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


}
