package com.vanguarda.blog.bean;

public class BlogUser extends User {
 
	private Blog blog;
	 
	
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
}
 
