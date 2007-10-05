package com.vanguarda.blog.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;


public class CommunityForm extends ActionForm {

	private int id;
	 
	private String title;
	 
	private int status;
	 
	private String description;
 
	private int communityOwnerId;
	
	private int blogId;
	
	private FormFile imagePath;
	
			
	public FormFile getImagePath() {
		return imagePath;
	}

	public void setImagePath(FormFile imagePath) {
		this.imagePath = imagePath;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public int getCommunityOwnerId() {
		return communityOwnerId;
	}

	public void setCommunityOwnerId(int communityOwnerId) {
		this.communityOwnerId = communityOwnerId;
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
