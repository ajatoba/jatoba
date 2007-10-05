package com.vanguarda.blog.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.vanguarda.blog.bean.*;

public interface CommunityDAO {

	public int add(Community community) throws SQLException, Exception;

	public Community load(int id) throws SQLException,Exception;

	public void update(Community community) throws SQLException,Exception;	

	public void delete(Community community) throws SQLException,Exception;
		
	public Collection listCommunities(int status) throws SQLException, Exception;
	
	public void updateStatus(int communityId, int status) throws SQLException,Exception;
}
