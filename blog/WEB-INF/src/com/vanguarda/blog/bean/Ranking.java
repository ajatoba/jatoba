/*
 * Created on 15/03/2007
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
public class Ranking {
	
	private Blog blog;
	
	private int ranking;
	
	private int countComments;	
	
	
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
	 * @return Returns the countComments.
	 */
	public int getCountComments() {
		return countComments;
	}
	/**
	 * @param countComments The countComments to set.
	 */
	public void setCountComments(int countComments) {
		this.countComments = countComments;
	}
	/**
	 * @return Returns the ranking.
	 */
	public int getRanking() {
		return ranking;
	}
	/**
	 * @param ranking The ranking to set.
	 */
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
}
