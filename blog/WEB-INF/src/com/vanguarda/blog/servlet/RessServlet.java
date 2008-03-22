/*
 * Created on 15/04/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.action.rss.ActionHandler;
import com.vanguarda.blog.action.rss.RssAllBlogsHandler;
import com.vanguarda.blog.action.rss.parameters.ActionHandlerParameters;
import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.Post;
import com.vanguarda.blog.dao.BlogDAO;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.util.CacheManager;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.DataUtil;
import com.vanguarda.blog.util.LoggerUtil;

/**
 * @author Fabio
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RessServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/xml");

		String id = req.getParameter("id");
		String mes = req.getParameter("mes");
		String ano = req.getParameter("ano");
		
		String act = req.getParameter("act");
		
		if(act!= null && "allBlogs".equals(act))
		{
			ActionHandler handler = new RssAllBlogsHandler();
			ActionHandlerParameters parameters = handler.createParameters(req);
			
			
			try {
				handler.handle(parameters,out);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
			
		}

		BlogDAO dao = (BlogDAO) DaoFactory
				.getInstance(Constants.MAPPING_BLOG_DAO);		
		

		if (id != null) {
			try {

				Date insertDate = null;
				Date endDate = null;
				
				ArrayList parameters = new ArrayList();
				parameters.add(new Integer(id));


				
				Blog blog = null;
				
				if(act != null && act.equals("loadSite"))
				{
					
					if(mes == null || mes.length() < 1 || ano == null || ano.length() < 1)
					{
					
						Date d = new Date();					
						DataUtil datautil = new DataUtil(1,d.getMonth()+ 1 ,d.getYear()+ 1900);				
						
						insertDate = datautil.toDate();
						datautil.somaDias(datautil.numeroDiasMes());
						endDate = datautil.toDate();
						
						
					}
					else
					{					
						DataUtil datautil = new DataUtil(1,Integer.parseInt(mes),Integer.parseInt(ano));				
						
						insertDate = datautil.toDate();
						datautil.somaDias(datautil.numeroDiasMes());
						endDate = datautil.toDate();
					}			
					
					
					parameters.add(insertDate);
					parameters.add(endDate);
					LoggerUtil.debug("CARREGANDO BLOG "+id+ "POSTS DO DIA: "+insertDate.getDate()+" DO: "+insertDate.getMonth()+" ATÉ O DIA : "+endDate.getDate()+" DE:" +endDate.getMonth()+"de: "+endDate.getYear());
					blog = (Blog) CacheManager.getInstance().hitCache(DaoFactory.getInstance(Constants.MAPPING_BLOG_DAO),"loadByDate",parameters);
					
				}
				else
				{

					Integer qtdPosts = new Integer((String) BlogManager.getInstance()
							.getProperties().get("posts_on_index"));
					parameters.add(qtdPosts);

					blog = (Blog) CacheManager.getInstance().hitCache(
							DaoFactory.getInstance(Constants.MAPPING_BLOG_DAO),
							"loadLastPosts", parameters);
					
				}
				
				
				ArrayList posts = (ArrayList) blog.getPosts();
				BlogUser user = blog.getBloggerUser();

				out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>");
				out.println("<?xml-stylesheet type=\"text/css\" href=\"http://"
						+ BlogManager.getInstance().getProperties().getProperty("localhost") + ":" + req.getLocalPort()
						+ "//blog/blogs/content/rss.css\"?>");

				out.println("<rss version=\"2.0\">");

				out.println("<channel>");
				out.println("<title><![CDATA[Bloglog]]></title>");
				
				if(act != null && act.equals("loadSite"))
				{
				out.println("<link>");
				out.println("<![CDATA[http://" + BlogManager.getInstance().getProperties().getProperty("localhost") + ":"
						+ req.getLocalPort()
						+ "//blog/blog.do?act=loadSite&id=" + blog.getId()+"&mes="+mes+"&ano="+ano
						+ "]]>");
				out.println("</link>");
				}
				else
				{
					out.println("<link>");
					out.println("<![CDATA[http://" + req.getLocalAddr() + ":"
							+ req.getLocalPort()
							+ "//"+blog.getPath()+"/index.html"
							+ "]]>");
					out.println("</link>");
				}
				
				
				out.println("<description><![CDATA[Lista com os últimos posts do blog do "
								+ blog.getName() + "]]></description>");
				
				out.println("<pubDate><![CDATA[" + blog.getInsertDate().toString() + "]]></pubDate>");

				for (int i = 0; i < posts.size(); i++) {
					Post post = (Post) posts.get(i);

					out.println("<item>");

					out.println("<title>");
					out.println("<![CDATA[" + post.getTitle() + "]]>");
					out.println("</title>");

					out.println("<link>");
					out.println("<![CDATA[http://" + BlogManager.getInstance().getProperties().getProperty("localhost") + ":"
							+ req.getLocalPort()
							+ "/blog/post.do?act=loadSite&id=" + post.getId()+"&permalink=true"
							+ "]]>");
					out.println("</link>");
					out.print("<author>");
					out.print("<![CDATA["+blog.getBlogUser().getFirstName()+"]]>");
					out.print("</author>");

					out.println("<pubDate>");
					out.println("<![CDATA[" + post.getInsertDate().toString() + "]]>");
					out.println("</pubDate>");
					
					out.println("<description>");
					out.println("<![CDATA[" + post.getContent() + "]]>");
					out.println("</description>");


					out.println("</item>");
				}

				out.println("");

				/*
				 * <item> <title> <![CDATA[Viajandão: A noite dos
				 * camisas-pretas]]> </title>
				 * <link>http://oglobo.globo.com/blogs/viajandao/post.asp?cod_post=54739
				 * </link> <description>&lt;p&gt; <![CDATA[ Tradicionalmente, a
				 * noite do sábado do Abril Pro Rock é voltada para os
				 * camisas-pretas, fãs de um rock'n'roll mais pesado, um público
				 * fiel que, desde cedo, enche o pavilhão do Centro de
				 * Convenções do Recife, onde acontece o evento. Antes mesmo
				 * das... ]]>&lt;/p&gt;&lt;p&gt;&lt;a
				 * href=&quot;http://oglobo.globo.com/blogs/viajandao/post.asp?cod_post=54739&quot;&gt;Leia
				 * mais&lt;/a&gt;&lt;/p&gt; </description> <author>
				 * <![CDATA[Viajandão]]> </author> <pubDate>Sun, 15 Apr 2007
				 * 02:48:00 -0300 </pubDate> </item>
				 *  
				 */

				out.println("</channel>");
				out.println("</rss>");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
