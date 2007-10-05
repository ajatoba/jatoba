package com.vanguarda.blog.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.vanguarda.blog.bean.AbstractForum;
import com.vanguarda.blog.bean.Topic;

public interface TopicDAO {

	public int add(Topic topic) throws SQLException, Exception;

	public Topic load(int id) throws SQLException,Exception;

	public void update(Topic topic) throws SQLException,Exception;	

	public void delete(Topic topic) throws SQLException,Exception;
		
	public Collection listTopics(int status,AbstractForum forum) throws SQLException, Exception;
	
	public void updateStatus(int topicId, int status) throws SQLException,Exception;
}
