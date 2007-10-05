package com.vanguarda.blog.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.vanguarda.blog.bean.Forum;

public interface ForumDAO {

	public void add(Forum forum) throws SQLException,Exception;

	public Forum load(int id) throws SQLException,Exception;

	public void update(Forum forum) throws SQLException,Exception;	

	public void delete(Forum forum) throws SQLException,Exception;
		
	public Collection listForums(int status) throws SQLException,Exception;

}
