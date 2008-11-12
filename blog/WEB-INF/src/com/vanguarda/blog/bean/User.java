package com.vanguarda.blog.bean;

import java.util.Date;

public abstract class User {
 
	private int id;
	 
	private String firstName;
	 
	private String lastName;
	 
	private String email;
	 
	private Date insertDate;
	 
	private int status;
	
	private String password;
	
	private Group group;
	
	private String login;	
	
	private Blog blog;
	
	private String gender;
	
	
	
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
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
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Returns the group.
	 */
	public Group getGroup() {
		return group;
	}
	/**
	 * @param group The group to set.
	 */
	public void setGroup(Group group) {
		this.group = group;
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
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Returns the login.
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login The login to set.
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
				
		buffer.append("id=");
		buffer.append(id+";");
		buffer.append("firstName=");
		buffer.append(firstName+";");
		buffer.append("lastName=");
		buffer.append(lastName+";");
		buffer.append("email=");
		buffer.append(email+";");
		buffer.append("insertDate=");
		buffer.append(insertDate+";");
		buffer.append("status=");
		buffer.append(status+";");
		buffer.append("password=");
		buffer.append(password+";");		
		buffer.append("login=");
		buffer.append(login+";");		
		
		return buffer.toString();
	}
}
 
