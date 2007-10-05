/*
 * Created on 06/03/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.dao;

import java.util.Hashtable;
import java.util.Map;


import com.vanguarda.blog.dao.impl.*;
import com.vanguarda.blog.util.Constants;

/**
 * @author Fabio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DaoFactory {
	
	
	public static Map daos;
	
	/*static
	{
		daos = new Hashtable();
		daos.put(Constants.MAPPING_ADMIN_DAO, new AdminUserDAOImpl());
		daos.put(Constants.MAPPING_BLOG_DAO, new BlogDAOImpl());
		daos.put(Constants.MAPPING_BLOGGERUSER_DAO, new BlogUserDAOImpl());
		daos.put(Constants.MAPPING_COMMENTS_DAO, new CommentsDAOImpl());
		daos.put(Constants.MAPPING_POST_DAO, new PostDAOImpl());
		daos.put(Constants.MAPPING_RANKING_DAO, new RankingDAOImpl());
		daos.put(Constants.MAPPING_TEMPLATE_DAO, new TemplateDAOImpl());
		daos.put(Constants.MAPPING_USERCOMMENTATOR_DAO, new UserCommentatorDAOImpl());
		
	}*/
	
	public static AbstractDAO getInstance(String mapping)
	{
		
		if(mapping != null)
		{
			
			if(mapping.equals(Constants.MAPPING_ADMIN_DAO))
			{
				return new AdminUserDAOImpl();
			}
			else if(mapping.equals(Constants.MAPPING_BLOG_DAO))
			{
				return new BlogDAOImpl();
			}
			else if(mapping.equals(Constants.MAPPING_BLOGGERUSER_DAO))
			{
				return new BlogUserDAOImpl();
			}
			else if(mapping.equals(Constants.MAPPING_COMMENTS_DAO))
			{
				return new CommentsDAOImpl();
			}
			else if(mapping.equals(Constants.MAPPING_POST_DAO))
			{
				return new PostDAOImpl();
			}
			else if(mapping.equals(Constants.MAPPING_RANKING_DAO))
			{
				return new RankingDAOImpl();
			}
			else if(mapping.equals(Constants.MAPPING_TEMPLATE_DAO))
			{
				return new TemplateDAOImpl();
			}
			else if(mapping.equals(Constants.MAPPING_USERCOMMENTATOR_DAO))
			{
				return new UserCommentatorDAOImpl();
			}else if(mapping.equals(Constants.MAPPING_FAVORITE_DAO))
			{
				return new FavoriteDAOImpl();
			}
			else
				return null;
						
			//return (AbstractDAO) daos.get(mapping);
		}
		else
		{
			return null;
		}
		
	}

}
