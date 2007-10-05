package com.vanguarda.blog.dao;


import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.exception.BlogPathExistsException;


public interface BlogDAO {
 
	public int add(Blog blog) throws SQLException, BlogPathExistsException,Exception;

	public Blog load(int id) throws SQLException,Exception;

	public void update(Blog blog) throws SQLException, BlogPathExistsException,Exception;	

	public void delete(Blog blog) throws SQLException,Exception;
		
	public Collection listBlogs(int status) throws SQLException,Exception;
	
	public Map getHistory(int blogId) throws SQLException,Exception;
	
	public Blog loadByDate(int id, Date startDate, Date endDate) throws SQLException,Exception;
	
	public Blog loadLastPosts(int id, int qtdPosts) throws SQLException,Exception;
	
	public Collection listBlogsToFavorite(int status) throws SQLException,Exception ;

	 
}
 
