/*
 * Created on 01/04/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.bean;

/**
 * @author Fabio
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Template {

	private int id;

	private String name;

	private String description;

	private String blogPath;

	private String communityPath;

	private String forumPath;

	private int status;
	
	

	/**
	 * @return Returns the blogPath.
	 */
	public String getBlogPath() {
		return blogPath;
	}
	/**
	 * @param blogPath The blogPath to set.
	 */
	public void setBlogPath(String blogPath) {
		this.blogPath = blogPath;
	}
	/**
	 * @return Returns the communityPath.
	 */
	public String getCommunityPath() {
		return communityPath;
	}
	/**
	 * @param communityPath The communityPath to set.
	 */
	public void setCommunityPath(String communityPath) {
		this.communityPath = communityPath;
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
	 * @return Returns the forumPath.
	 */
	public String getForumPath() {
		return forumPath;
	}
	/**
	 * @param forumPath The forumPath to set.
	 */
	public void setForumPath(String forumPath) {
		this.forumPath = forumPath;
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
}
