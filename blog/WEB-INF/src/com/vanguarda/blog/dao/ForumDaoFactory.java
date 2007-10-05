package com.vanguarda.blog.dao;


import java.util.Hashtable;
import java.util.Map;

import com.vanguarda.blog.dao.impl.*;
import com.vanguarda.blog.util.ForumConstants;

public class ForumDaoFactory  {
	
	public static Map daos;
	
	static
	{
		daos = new Hashtable();
		daos.put(ForumConstants.MAPPING_TOPIC_DAO, new TopicDAOImpl());
		daos.put(ForumConstants.MAPPING_MESSAGE_DAO, new MessageDAOImpl());
		
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
