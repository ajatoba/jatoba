// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name: PostAction.java

package com.vanguarda.blog.action;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.bean.Post;
import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.dao.BlogDAO;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.dao.PostDAO;
import com.vanguarda.blog.form.PostForm;
import com.vanguarda.blog.helper.FileHelper;
import com.vanguarda.blog.util.CacheManager;
import com.vanguarda.blog.util.Constants;

public class PostAction extends DispatchAction {

	public PostAction() {
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("blogUser");

		if (user == null) {
			user = (User) session.getAttribute("userAdmin");
		}
		org.apache.struts.util.MessageResources messageResources = null;

		try {
			messageResources = getResources(req);
			Post post = new Post();
			PostForm postForm = (PostForm) form;
			post.setBlog(user.getBlog());
			post.setContent(postForm.getContent());
			post.setControll(postForm.isControll());
			post.setStatus(postForm.getStatus());
			post.setTitle(postForm.getTitle());
			post.setAuthor(postForm.getAuthor());
			dao.add(post);

			req.setAttribute("blogId",String.valueOf(user.getBlog().getId()));

			return mapping.findForward("post_add");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());
		}

		return mapping.findForward("post_add");
	}

	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id");
		if (id != null)
			try {
				Post post = dao.load(Integer.parseInt(id), -1);
				req.setAttribute("post", post);
				return mapping.findForward("post_load");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("");
			}
		else
			return mapping.findForward("");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("blogUser");

		if (user == null) {
			user = (User) session.getAttribute("userAdmin");
		}

		org.apache.struts.util.MessageResources messageResources = null;
		try {
			messageResources = getResources(req);
			Post post = new Post();
			PostForm postForm = (PostForm) form;
			post.setId(postForm.getId());
			post.setBlog(user.getBlog());
			post.setContent(postForm.getContent());
			post.setControll(postForm.isControll());
			post.setStatus(postForm.getStatus());
			post.setTitle(postForm.getTitle());
			post.setAuthor(postForm.getAuthor());
			dao.update(post);
			
			req.setAttribute("blogId",String.valueOf(user.getBlog().getId()));

			return mapping.findForward("post_add");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());
		}
		return mapping.findForward("post_add");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		org.apache.struts.util.MessageResources messageResources = null;
		try {
			messageResources = getResources(req);
			Post post = new Post();
			PostForm postForm = (PostForm) form;
			post.setId(postForm.getId());

			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("blogUser");

			if (user == null) {
				user = (User) session.getAttribute("userAdmin");
			}

			dao.delete(post);

			return new ActionForward("/blogs/admin/post.do?act=publish&id="
					+ user.getBlog().getId());

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());
		}
		return mapping.findForward("");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		java.util.Collection list = null;
		org.apache.struts.util.MessageResources messageResources = null;
		HttpSession session = req.getSession();
		User user = null;
		String blogId = req.getParameter("blogId");

		try {
			messageResources = getResources(req);
			if (session.getAttribute("blogUser") != null) {
				user = (User) session.getAttribute("blogUser");
				list = dao.listPostsByBlog(-1, user.getBlog());
				req.setAttribute("blogId", String.valueOf(user.getBlog()
						.getId()));
			} else if (blogId != null) {
				user = (User) session.getAttribute("userAdmin");

				BlogDAO blogDAO = (BlogDAO) DaoFactory
						.getInstance(Constants.MAPPING_BLOG_DAO);
				Blog blog = blogDAO.load(Integer.parseInt(blogId));
				user.setBlog(blog);

				session.setAttribute("userAdmin", user);

				list = blog.getPosts();
				req.setAttribute("blogId", blogId);
			} else {
				user = (User) session.getAttribute("userAdmin");
				if (user != null && user.getBlog() != null) {
					list = dao.listPostsByBlog(-1, user.getBlog());
					req.setAttribute("blogId", String.valueOf(user.getBlog()
							.getId()));
				} else {
					throw new Exception("Blog não identificado");
				}

			}

			req.setAttribute("posts", list);

			messageResources = getResources(req);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return mapping.findForward("post_list");
	}

	public ActionForward loadSite(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id");
		String permalinkString = req.getParameter("permalink");
		boolean isPermalink = permalinkString != null ? (new Boolean(
				permalinkString)).booleanValue() : false;

		if (id != null)
			try {
				ArrayList parameters = new ArrayList();

				parameters.add(new Integer(id));
				parameters.add(new Integer(1));
				Post post = (Post) CacheManager.getInstance().hitCache(
						DaoFactory.getInstance("POSTDAO"), "load", parameters);
				//Post post = dao.load(Integer.parseInt(id), 1);
				java.util.Collection comments = post.getComments();
				Blog blog = post.getBlog();
				User user = blog.getBloggerUser();
				req.setAttribute("blog", blog);
				req.setAttribute("post", post);
				req.setAttribute("status", new Integer(post.isControll() ? 1
						: 0));
				;
				req.setAttribute("blogUser", user);

				req.setAttribute("comments", comments);
				req.setAttribute("comment_sucesso", req
						.getAttribute("comment_sucesso"));
				req.setAttribute("mensagem_erro", req
						.getAttribute("mensagem_erro"));

				if (isPermalink) {
					ArrayList posts = new ArrayList();
					posts.add(post);

					BlogManager.getInstance().setRanking(req,30);
					BlogManager.getInstance().setHistory(req, id);
					req.setAttribute(Constants.BLOG_BEAN, blog);
					req.setAttribute("posts", posts);
					req.setAttribute("blogPath", blog.getPath());
					req.setAttribute(Constants.BLOGGER_USER_BEAN, user);

					return new ActionForward(blog.getTemplate().getBlogPath());
				} else {
					return new ActionForward("/blogs/content/comments.jsp");
				}

			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("");
			}
		else
			return mapping.findForward("");
	}

	public ActionForward publish(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		org.apache.struts.util.MessageResources messageResources = null;

		String id = req.getParameter("id");
		String mes = req.getParameter("mes");
		String ano = req.getParameter("ano");

		Date insertDate = null;
		Date endDate = null;

		ArrayList parameters = new ArrayList();
		Integer intId = new Integer(id);

		CacheManager.getInstance().remotePostsClearCache(intId.intValue());
		CacheManager.getInstance().remoteHistoryClearCache(intId.intValue());
		CacheManager.getInstance().remoteRankingClearCache();

		parameters.add(intId);

		Blog blog = null;

		Integer qtdPosts = new Integer((String) BlogManager.getInstance()
				.getProperties().get("posts_on_index"));
		//		parameters.add(qtdPosts);

		//		blog = (Blog) CacheManager.getInstance().hitCache(
		//				DaoFactory.getInstance(Constants.MAPPING_BLOG_DAO),
		//				"loadLastPosts", parameters);

		blog = bDao.loadLastPosts(intId.intValue(), qtdPosts.intValue());

		String blogPath = blog.getPath();

		try {

			messageResources = getResources(req);

			File fdir = new File(blogPath);
			if (!fdir.exists()) {
				if (!fdir.mkdirs()) {
					req.setAttribute("mensagem_erro", messageResources
							.getMessage("publish_blog_error"));
				}
			}

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
			ArrayList p = new ArrayList();
			p.add(new Integer(0));
			context.put("ranking", (Collection) CacheManager.getInstance()
					.hitCache(DaoFactory.getInstance("RANKINGDAO"),
							"getRanking", p));

			context.put("history", req.getAttribute("history"));
			context.put("favorites", req.getAttribute("favorites"));
			context.put("urlRss", urlRss);

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

			StringWriter writer = new StringWriter();
			t.merge(context, writer);

			try {

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

				FileHelper.createStaticHome(blogPath, writer);

			} catch (Exception e) {
				req.setAttribute("mensagem_erro",
						"Erro na escrita da home estática." + e.getMessage());
				return new ActionForward("/blogs/admin/post.do?act=list");
			} finally {
				if (writer != null)
					writer.close();
			}

		} catch (Exception e) {
			req.setAttribute("mensagem_erro", e.getMessage());
			return new ActionForward("/blogs/admin/post.do?act=list");
		}

		req.setAttribute("mensagem_sucesso", messageResources
				.getMessage("publish_blog_sucesso"));
		return new ActionForward("/blogs/admin/post.do?act=list");
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String id = req.getParameter("id");
		String keyWord = req.getParameter("palavra_chave");

		Blog blog = null;

		if (id != null) {
			try {

				int intId = Integer.parseInt(id);

				blog = dao.searchPosts(keyWord, intId);

				if (blog != null) {

					Collection posts = blog.getPosts();
					User user = blog.getBloggerUser();

					BlogManager.getInstance().setRanking(req,0);
					BlogManager.getInstance().setHistory(req, id);
					BlogManager.getInstance().setFavorites(req, user.getId());

					req.setAttribute(Constants.BLOG_BEAN, blog);
					req.setAttribute("posts", posts);
					req.setAttribute("blogPath", blog.getPath());
					req.setAttribute(Constants.BLOGGER_USER_BEAN, user);
					
					req.setAttribute("keyWord", keyWord);
					req.setAttribute("search", new Boolean(true));
					
					//return new ActionForward(blog.getTemplate().getBlogPath());

					return new ActionForward("/servlet/content");
				} else {
					return mapping.findForward("");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("");
			}
		} else {
			return mapping.findForward("");
		}

	}

	private static PostDAO dao;

	private static BlogDAO bDao;

	static {
		if (dao == null)
			dao = (PostDAO) DaoFactory.getInstance("POSTDAO");

		if (bDao == null)
			bDao = (BlogDAO) DaoFactory.getInstance("BLOGDAO");
	}
}
