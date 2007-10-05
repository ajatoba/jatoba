package com.vanguarda.blog.dao;


import java.util.Hashtable;
import java.util.Map;

import com.vanguarda.blog.dao.impl.*;
import com.vanguarda.blog.util.CommunityConstants;

public class CommunityDAOFactory {
	
	public static Map daos;
	
	static
	{
		daos = new Hashtable();
		daos.put(CommunityConstants.MAPPING_COMMUNITY_DAO, new CommunityDAOImpl());
		daos.put(CommunityConstants.MAPPING_COMMUNITY_TOPIC_DAO, new CommunityTopicDAOImpl());
		daos.put(CommunityConstants.MAPPING_COMMUNITY_USER_DAO, new CommunityUserDAOImpl());
		daos.put(CommunityConstants.MAPPING_COMMUNITY_MESSAGE_DAO, new CommunityMessageDAOImpl());
		

	}
	
	public static AbstractDAO getInstance(String mapping)
	{
		
		if(mapping != null)
		{
			return (AbstractDAO) daos.get(mapping);
		}
		else
		{
			return null;
		}
		
	}

}
