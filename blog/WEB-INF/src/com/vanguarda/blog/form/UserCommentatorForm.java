/*
 * Created on 05/03/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.vanguarda.blog.bean.Group;

/**
 * @author Fabio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserCommentatorForm  extends ActionForm{
	
	private int id;
	 
	private String firstName;
	 
	private String lastName;
	 
	private String email;
	 
	private String insertDate;
	 
	private int status;
	
	private String password;
	
	private Group group;
	
	private String login;
	
	private String gender;
	
	private String state;
	
	private String city;
	
	private int dia;
	
	private int mes;
	
	private int ano;
	
	private String imageword;
		
	
	public String getImageword() {
		return imageword;
	}
	public void setImageword(String imageword) {
		this.imageword = imageword;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getInsertDate() {
		return insertDate;
	}
	/**
	 * @param insertDate The insertDate to set.
	 */
	public void setInsertDate(String insertDate) {
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
}
