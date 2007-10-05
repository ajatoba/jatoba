package com.vanguarda.blog.bean;

import java.util.Collection;
import java.util.Date;

public class Blog {
 
	private int id;
	 
	private String name;
	 
	private Date insertDate;
	 
	private int status;
	 
	private String description;
	 	 
	private BlogUser blogUser;
	
	private Collection posts;
	
	private String path;
	
	private Template template;
	
	
	 
	/**
	 * @return Returns the bloggerUser.
	 */
	public BlogUser getBloggerUser() {
		return blogUser;
	}
	/**
	 * @param bloggerUser The bloggerUser to set.
	 */
	public void setBlogUser(BlogUser bloggerUser) {
		this.blogUser = bloggerUser;
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
	 * @return Returns the posts.
	 */
	public Collection getPosts() {
		return posts;
	}
	/**
	 * @param posts The posts to set.
	 */
	public void setPosts(Collection posts) {
		this.posts = posts;
	}
	/**
	 * @return Returns the blogUser.
	 */
	public BlogUser getBlogUser() {
		return blogUser;
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
	 * @return Returns the template.
	 */
	public Template getTemplate() {
		return template;
	}
	/**
	 * @param template The template to set.
	 */
	public void setTemplate(Template template) {
		this.template = template;
	}
	
		
	public String toString() {
		StringBuffer buffer = new StringBuffer();
				
		buffer.append("id=");
		buffer.append(id+";");
		buffer.append("name=");
		buffer.append(name+";");
		buffer.append("insertDate=");
		buffer.append(insertDate+";");
		buffer.append("status=");
		buffer.append(status+";");
		buffer.append("description=");
		buffer.append(description+";");
		buffer.append("blogUser=");
		buffer.append(blogUser+";");
		buffer.append("path=");
		buffer.append(path+";");
		
		return buffer.toString();
	}
}
 
