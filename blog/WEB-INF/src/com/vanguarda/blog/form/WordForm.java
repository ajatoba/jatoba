/*
 * Created on 16/04/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.form;

import org.apache.struts.action.ActionForm;

/**
 * @author Fabio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WordForm extends ActionForm{
	
	private String path;
	
	private String content;
	
		

	/**
	 * @return Returns the content.
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content The content to set.
	 */
	public void setContent(String content) {
		this.content = content;
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
}
