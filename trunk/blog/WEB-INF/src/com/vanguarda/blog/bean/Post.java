package com.vanguarda.blog.bean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Post {
 
	private int id;
	 
	private String title;
	 
	private String content;
	 
	private boolean controll;
	 
	private Date insertDate;	 		 
 	 
	private Comment lastComments;
	
	private int countComments;
	
	private int status;
	
	private Blog blog;
	
	private Collection comments;
	
	private String author;
	
	


	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
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
	 * @return Returns the controll.
	 */
	public boolean isControll() {
		return controll;
	}
	/**
	 * @param controll The controll to set.
	 */
	public void setControll(boolean controll) {
		this.controll = controll;
	}
	/**
	 * @return Returns the coutComments.
	 */
	public int getCountComments() {
		return countComments;
	}
	/**
	 * @param coutComments The coutComments to set.
	 */
	public void setCountComments(int coutComments) {
		this.countComments = coutComments;
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
	 * @return Returns the lastComments.
	 */
	public Comment getLastComments() {
		return lastComments;
	}
	/**
	 * @param lastComments The lastComments to set.
	 */
	public void setLastComments(Comment lastComments) {
		this.lastComments = lastComments;
	}
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return Returns the comments.
	 */
	public Collection getComments() {
		if(comments == null)
		{
			comments = new ArrayList();
		}
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(Collection comments) {
		this.comments = comments;
	}
	

	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
				
		buffer.append("id=");
		buffer.append(id+";");
		buffer.append("title=");
		buffer.append(title+";");
		buffer.append("content=");
		buffer.append(content+";");
		buffer.append("controll=");
		buffer.append(controll+";");
		buffer.append("insertDate=");
		buffer.append(insertDate+";");
		buffer.append("status=");
		buffer.append(status+";");
				
		
		return buffer.toString();
	}
	
}
 
