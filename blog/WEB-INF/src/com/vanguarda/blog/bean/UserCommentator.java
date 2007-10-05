package com.vanguarda.blog.bean;

import java.util.Date;

public class UserCommentator extends User {
 
	private Blog blog;
	private String gender;
	private String state;
	private String city;
	private Date birthDate;
	
	/**
	 * @return Returns the blog.
	 */
	public Blog getBlog() {
		return blog;
	}
	/**
	 * @param blog The blog to set.
	 */
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
 
