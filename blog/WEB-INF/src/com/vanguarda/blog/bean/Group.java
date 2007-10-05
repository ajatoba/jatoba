/*
 * Created on 06/03/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.bean;

/**
 * @author Fabio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Group {
	
	private int id;
	private String name;
	private String description;
	
	public Group(int id_)
	{
		this.setId(id_);
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
}
