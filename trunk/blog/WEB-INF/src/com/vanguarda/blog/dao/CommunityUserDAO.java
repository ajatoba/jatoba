package com.vanguarda.blog.dao;

import com.vanguarda.blog.bean.*;
import java.util.*;
import java.sql.*;

public interface CommunityUserDAO {

	
	public void participate(Community c,CommunityUser user) throws SQLException,Exception;
	public void unParticipate(Community c, CommunityUser user) throws SQLException,Exception;
	public Collection listCommunities(CommunityUser user, int status) throws SQLException,Exception;
	
	
	
}
