/*
 * Created on 23/10/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.action.rss;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.vanguarda.blog.bean.Post;
import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.action.rss.parameters.ActionHandlerParameters;
import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.dao.BlogDAO;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.util.CacheManager;
import com.vanguarda.blog.util.Constants;

/**
 * @author Fabio
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RssAllBlogsHandler extends ActionHandler {
	
	private static final Integer countPost = new Integer(3);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vanguarda.blog.action.rss.ActionHandler#handle(com.vanguarda.blog.action.rss.parameters.ActionHandlerParameters,
	 *      java.io.PrintWriter)
	 */
	public void handle(ActionHandlerParameters parameters, PrintWriter out)
			throws Exception {

		

		List blogs = (List) CacheManager.getInstance().hitCache(
				DaoFactory.getInstance(Constants.MAPPING_BLOG_DAO),
				"listBlogs", new ArrayList());

		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		/*out.println("<?xml-stylesheet type=\"text/css\" href=\"http://"
				+ BlogManager.getInstance().getProperties().getProperty(
						"localhost") + ":" + parameters.getPorta()
				+ "//blog/blogs/content/rss.css\"?>");*/

		out.println("<rss version=\"2.0\">");

		out.println("<channel>");
		out.println("<title><![CDATA[Bloglog]]></title>");

		out.println("<link>");
		out.println("<![CDATA[http://"
				+ BlogManager.getInstance().getProperties().getProperty(
						"localhost") + ":" + parameters.getPorta()
				+ "//index.html" + "]]>");
		out.println("</link>");
		out.println("<language>pt-br</language>");
		out.println("<description><![CDATA[ Lista dos blogs ]]></description>");
		 
		//out.println("<pubDate><![CDATA[" + new Date().toString()+"]]></pubDate>");

		for (int i = 0; i < blogs.size(); i++) {
			Blog blog = (Blog) blogs.get(i);

			out.println("<item>");

			out.println("<title>");
			out.println("<![CDATA[" + blog.getName() + "]]>");
			out.println("</title>");

			out.println("<link>");
			out.println("<![CDATA[http://"
					+ BlogManager.getInstance().getProperties().getProperty(
							"localhost") + ":" + parameters.getPorta() + "/"
					+ blog.getPath() + "]]>");
			out.println("</link>");
			
			out.print("<author>");
				out.print("<![CDATA["+blog.getBlogUser().getFirstName()+"]]>");
			out.print("</author>");
			
			out.println("<description>");
			out.println("<![CDATA[" + blog.getDescription() + "<br>]]>");
			
			/** Ultimos posts **/
			
			
			ArrayList p = new ArrayList();
			p.add(new Integer(blog.getId()));
			p.add(countPost);
			Blog b = (Blog) CacheManager.getInstance().hitCache(
			DaoFactory.getInstance(Constants.MAPPING_BLOG_DAO),
			"loadLastPosts", p);
			
			Collection posts = b.getPosts();
			Iterator it = posts.iterator();
			while(it.hasNext())
			{
				Post post = (Post) it.next();
				
				StringBuffer buffer = new StringBuffer();
				
				
				buffer.append("<![CDATA[");
				buffer.append("<b>");
				buffer.append(post.getTitle());
				buffer.append("</b>");
				buffer.append("]]>");
				
				out.println(buffer.toString());
				
				out.println("<![CDATA[" + post.getContent() + "]]>");

				
				
			}
			
			
			/** Ultimos posts **/
			
			out.println("</description>");
			out.println("<pubDate>");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(blog.getInsertDate());
			
			out.println("<![CDATA[" +calendar.getTime() + "]]>");
			out.println("</pubDate>");


			out.println("</item>");
		}

		out.println("");

		/*
		 * <item> <title> <![CDATA[Viajandão: A noite dos camisas-pretas]]>
		 * </title>
		 * <link>http://oglobo.globo.com/blogs/viajandao/post.asp?cod_post=54739
		 * </link> <description>&lt;p&gt; <![CDATA[ Tradicionalmente, a noite do
		 * sábado do Abril Pro Rock é voltada para os camisas-pretas, fãs de um
		 * rock'n'roll mais pesado, um público fiel que, desde cedo, enche o
		 * pavilhão do Centro de Convenções do Recife, onde acontece o evento.
		 * Antes mesmo das... ]]>&lt;/p&gt;&lt;p&gt;&lt;a
		 * href=&quot;http://oglobo.globo.com/blogs/viajandao/post.asp?cod_post=54739&quot;&gt;Leia
		 * mais&lt;/a&gt;&lt;/p&gt; </description> <author>
		 * <![CDATA[Viajandão]]> </author> <pubDate>Sun, 15 Apr 2007 02:48:00
		 * -0300 </pubDate> </item>
		 *  
		 */

		out.println("</channel>");
		out.println("</rss>");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vanguarda.blog.action.rss.ActionHandler#createParameters(javax.servlet.http.HttpServletRequest)
	 */
	public ActionHandlerParameters createParameters(HttpServletRequest request)
	{
		ActionHandlerParameters parameters = new ActionHandlerParameters();
		parameters.setPorta(request.getLocalPort());

		return parameters;
	}

}
