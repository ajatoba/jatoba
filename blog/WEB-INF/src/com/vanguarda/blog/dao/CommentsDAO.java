package com.vanguarda.blog.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.vanguarda.blog.bean.Comment;
import com.vanguarda.blog.bean.Post;

public interface CommentsDAO {
 
	public void add(Comment comment) throws SQLException,Exception;
	
	public void update(Comment comment) throws SQLException,Exception;
	
	public Comment load(int id) throws SQLException,Exception;
	
	public void delete(Comment comment) throws SQLException,Exception;
	
	public Collection listCommentsByPost(int status,Post post) throws SQLException,Exception;
	
	public void addAnswer(Comment comment) throws SQLException,Exception;

	
	 
}
 
