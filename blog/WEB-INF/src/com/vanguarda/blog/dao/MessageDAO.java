package com.vanguarda.blog.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.vanguarda.blog.bean.Message;
import com.vanguarda.blog.bean.Topic;

public interface MessageDAO {
	
	public int add(Message message) throws SQLException,Exception;

	public void delete(Message message) throws SQLException,Exception;
		
	public Collection listMessages(int status, Topic topic) throws SQLException,Exception;
}
