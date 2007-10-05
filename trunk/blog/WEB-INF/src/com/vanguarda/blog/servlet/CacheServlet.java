/*
 * Created on 05/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vanguarda.blog.util.CacheManager;



/**
 * @author Fabio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CacheServlet extends HttpServlet {
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String key = req.getParameter("act");
		String blogId = req.getParameter("blogId");
		String postId = req.getParameter("postId");
		
		PrintWriter out = res.getWriter();
		
		if(key!= null&& key.length() > 0 )
		{			
			if("commentsClearCache".equals(key) && postId != null)
			{				
				CacheManager.getInstance().commentsClearCache(Integer.parseInt(postId));
				out.print("Limpeza de cache de comentários do postId "+postId );				
			}			
			else if("postsClearCache".equals(key) && blogId != null)
			{
				CacheManager.getInstance().postsClearCache(Integer.parseInt(blogId));
				out.print("Limpeza de cache de posts do blogId "+blogId );
			}
			else if("historyClearCache".equals(key)&& blogId != null)
			{
				CacheManager.getInstance().historyClearCache(Integer.parseInt(blogId));
				out.print("Limpeza de cache de historico do blogId "+blogId );
			}
			else if("rankingClearCache".equals(key))
			{
				CacheManager.getInstance().rankingClearCache();
				out.print("Limpeza de cache do ranking ");
			}			
		}
		else
		{
			CacheManager.getInstance().clear();
			out.print("Limpeza de cache");
		}		
	}
		
}
