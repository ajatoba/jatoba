/*
 * Created on 05/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.vanguarda.blog.bean.History;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.helper.FileHelper;
import com.vanguarda.blog.util.CacheManager;
import com.vanguarda.blog.util.DataUtil;


/**
 * @author Fabio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BlogManager {
	
	public static BlogManager instance;
	
	private Properties properties = null;
	private Map deniedWords       = new Hashtable(); 

	private BlogManager()
	{
		super();
	}

	public static BlogManager getInstance()
	{
		if (instance == null)
		{
			instance = new BlogManager();
		}
		return instance;
	}
	
	public Properties getProperties() throws Exception
	{
		if(properties == null)
		{
			properties = new Properties();
	        InputStream stream = BlogManager.class.getClassLoader().getResourceAsStream("resource.properties");
	        properties.load(stream);         
            stream.close();
		}
		
		return properties;
	}
	
	public String getDeniedWords(String path) throws Exception
	{
		if(deniedWords.containsKey(path))
		{
			return  deniedWords.get(path)!=null?(String) deniedWords.get(path):null;			
		}
		else
		{
			String value = FileHelper.readControlWord(path, "palavras.txt");
			deniedWords.put(path,value);
			return value;
		}	
	}
	
	
	public void setDeniedWords(String content,String path) throws Exception {
		
		FileHelper.writeControlWord(path, "palavras.txt", content);
		deniedWords.put(path,content);
		
	}
	
	public void setRanking(HttpServletRequest req, int days) throws Exception
	{		
		ArrayList parameters = new ArrayList();
		parameters.add(new Integer(days));
		Collection ranking = (Collection) CacheManager.getInstance().hitCache(DaoFactory.getInstance("RANKINGDAO"),"getRanking",parameters);			
				
		req.setAttribute("ranking", ranking);
	}
	
	public void setHistory(HttpServletRequest req, String blogId ) throws Exception
	{
		ArrayList parameters = new ArrayList();
		parameters.add(Integer.valueOf(blogId));		
		
		Map historys = (Map) CacheManager.getInstance().hitCache(DaoFactory.getInstance("BLOGDAO"),"getHistory",parameters);
		
		Collection arquivo = new ArrayList();
		

		Date today = new Date();
		int month = today.getMonth()+1;
		int year  = today.getYear() + 1900;
		
		String key = "";
		
		for(int i = 0; i < 36; i++ )
		{
			History history = new History();
			
			if(month == 0)
			{
				month = 12;
				year = year - 1;
			}
			
			DataUtil d = new DataUtil(1,month,year,0,0);			
						
			key = String.valueOf(month)+String.valueOf(year);
			
			
			if(historys.get(key)!= null)
			{
				history.setPostCount((Integer)historys.get(key));
			}
			else
			{
				month --;
				continue;
				//history.setPostCount(new Integer(0));
			}
			
			history.setBlogID(Integer.parseInt(blogId));
			history.setLabelDate(d.nomeMes()+"/"+year);
			history.setDate(d.toDate());
			history.setMonth(month);
			history.setYear(year);
			
			
			arquivo.add(history);
			
			month --;
		}
		
		req.setAttribute("history",arquivo);
	}
	
	public void setFavorites(HttpServletRequest req, int userId) throws Exception
	{		
		
		ArrayList parameters = new ArrayList();
		parameters.add(new Integer(userId));
		
		Collection favorites = (Collection) CacheManager.getInstance().hitCache(DaoFactory.getInstance("FAVORITEDAO"),"getFavorites",parameters);			
				
		req.setAttribute("favorites", favorites);
	}
	
		
}
