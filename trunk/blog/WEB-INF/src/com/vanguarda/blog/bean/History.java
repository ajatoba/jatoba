/*
 * Created on 28/03/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.bean;

import java.util.Date;

/**
 * @author Fabio
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class History {

	private String labelDate;

	private Date date;

	private Integer postCount;
	
	private int blogID;
	
	private int month;
	
	private int year;
	
	

	/**
	 * @return Returns the date.
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date The date to set.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return Returns the labeldate.
	 */
	public String getLabelDate() {
		return labelDate;
	}
	/**
	 * @param labeldate The labeldate to set.
	 */
	public void setLabelDate(String labeldate) {
		labelDate = labeldate;
	}
	/**
	 * @return Returns the postCount.
	 */
	public Integer getPostCount() {
		return postCount;
	}
	/**
	 * @param postCount The postCount to set.
	 */
	public void setPostCount(Integer postCount) {
		this.postCount = postCount;
	}
	
	
	/**
	 * @return Returns the blogID.
	 */
	public int getBlogID() {
		return blogID;
	}
	/**
	 * @param blogID The blogID to set.
	 */
	public void setBlogID(int blogID) {
		this.blogID = blogID;
	}
	
	
	/**
	 * @return Returns the month.
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month The month to set.
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return Returns the year.
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year The year to set.
	 */
	public void setYear(int year) {
		this.year = year;
	}
}
