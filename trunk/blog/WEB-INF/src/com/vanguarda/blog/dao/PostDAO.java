package com.vanguarda.blog.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.bean.Post;


public interface PostDAO {
 
	public void add(Post post) throws SQLException,Exception;
	
	public Post load(int id,int status) throws SQLException,Exception;
	
	public void update(Post post) throws SQLException,Exception;
	
	public void delete(Post post) throws SQLException,Exception;
	
	public Collection listPosts(int status) throws SQLException,Exception;
	
	public Collection listPostsByBlog(int status, Blog blog) throws SQLException,Exception;
	
	public Blog searchPosts(String keyWord, int blogId) throws SQLException,Exception;
	
	
	 
}
 
