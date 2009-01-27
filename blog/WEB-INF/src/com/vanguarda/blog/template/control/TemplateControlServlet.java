package com.vanguarda.blog.template.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.bean.Post;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.util.CacheManager;
import com.vanguarda.blog.util.Constants;

public class TemplateControlServlet extends HttpServlet{

	protected void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		
		Blog blog = (Blog)req.getAttribute(Constants.BLOG_BEAN);
		
		StringWriter writer = null;
		try {

			BlogManager.getInstance().setHistory(req,
					String.valueOf(blog.getId()));

			BlogManager.getInstance().setFavorites(req,
					blog.getBlogUser().getId());

			//Usando a Velocity para gerar as páginas
			VelocityEngine ve = new VelocityEngine();
			Properties props = new Properties();

			String docroot = (String) BlogManager.getInstance().getProperties().get("docroot");
			
			props.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, 
					docroot + blog.getTemplate().getDescription());
			
			ve.init(props);

			//pegando o template
			String templateFile = "index.vm";

			org.apache.velocity.Template t = ve.getTemplate(templateFile);

			//criação do contexto
			VelocityContext context = new VelocityContext();

			String urlRss = "rss.rss?id=" + blog.getId();

			//criação de dados e trasnferências dos mesmos para o contexto
			context.put("blogOwner", blog.getBloggerUser().getFirstName());
			context.put("blogTitle", blog.getName());
			context.put("blogInsertDate", blog.getInsertDate().getDate() + "/"
					+ ((blog.getInsertDate().getMonth()) + 1) + "/"
					+ ((blog.getInsertDate().getYear()) + 1900));
			context.put("blogDescription", blog.getDescription());
			context.put("blogId", String.valueOf(blog.getId()));
			context.put("blogStatus", String.valueOf(blog.getStatus()));
			context.put("blogPath", String.valueOf(blog.getPath()));
			context.put("blogPosts", blog.getPosts());
			
			ArrayList<Post> ps = (ArrayList<Post>)blog.getPosts();
			
			System.out.println(ps.get(0).getInsertDate());
			
			ArrayList p = new ArrayList();
			p.add(new Integer(0));
			
			/*
			context.put("ranking", (Collection) CacheManager.getInstance()
					.hitCache(DaoFactory.getInstance("RANKINGDAO"),
							"getRanking", p));
			*/
			context.put("history", req.getAttribute("history"));
			context.put("favorites", req.getAttribute("favorites"));
						
			context.put("urlRss", urlRss);

			//Parâmetros de busca
			context.put("busca", req.getAttribute("search"));
			context.put("keyWord", req.getAttribute("keyWord"));
			context.put("qtdResults", String.valueOf(blog.getPosts().size()));
			
			// verificando se no mês existe mais de
			Collection posts = blog.getPosts();
			int count = 0;
			Date today = new Date();
			for (Iterator iter = posts.iterator(); iter.hasNext();) {
				Post element = (Post) iter.next();

				if (element.getInsertDate().getMonth() == today.getMonth())
					count++;
			}

			if (count >= 5) {
				context.put("hasMorePosts", "true");
			}

			writer = new StringWriter();
			t.merge(context, writer);

			
			try {
				/*
				if (BlogManager.getInstance().getProperties().get(
						Constants.ROOTPATH_PROPERTY) != null
						&& ((String) BlogManager.getInstance().getProperties()
								.get(Constants.ROOTPATH_PROPERTY)).length() > 0) {
					blogPath = (String) BlogManager.getInstance()
							.getProperties().get(Constants.ROOTPATH_PROPERTY)
							+ blogPath;
				} else {
					blogPath = req.getRealPath("/blogs/" + blogPath);
				}
				*/
				PrintWriter out = res.getWriter();
				out.println(writer.toString());
				//FileHelper.createStaticHome(blogPath, writer);

			} catch (Exception e) {
				req.setAttribute("mensagem_erro",
						"Erro na escrita da home estática." + e.getMessage());
				throw e;
			} finally {
				if (writer != null)
					writer.close();
			}

		} catch (Exception e) {
			req.setAttribute("mensagem_erro", e.getMessage());
			
		}

		
		
	}
	
}
