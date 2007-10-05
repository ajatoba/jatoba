package com.vanguarda.blog.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class BlogForm extends ActionForm{
 
	private int id;
	 
	private String name;
	 
	private Date insertDate;
	 
	private int status;
	 
	private String description;
	 	 
	private int bloggerUser;
	
	private String path;
	
	private int templateId;
		
	
	 
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
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
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
	
	
	/**
	 * @return Returns the path.
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path The path to set.
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	
	/**
	 * @return Returns the templateId.
	 */
	public int getTemplateId() {
		return templateId;
	}
	/**
	 * @param templateId The templateId to set.
	 */
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
}
 
