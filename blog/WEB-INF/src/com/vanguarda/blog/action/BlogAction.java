/*
 * Created on 14/03/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.action;

/**
 * @author Fabio
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.Post;
import com.vanguarda.blog.bean.Template;
import com.vanguarda.blog.dao.BlogDAO;
import com.vanguarda.blog.dao.BlogUserDAO;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.dao.TemplateDAO;
import com.vanguarda.blog.exception.BlogPathExistsException;
import com.vanguarda.blog.form.BlogForm;
import com.vanguarda.blog.util.CacheManager;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.DataUtil;
import com.vanguarda.blog.util.LoggerUtil;


public class BlogAction extends DispatchAction {
	
	private static BlogDAO dao;
	
	static{
		
		if(dao == null)
		{
			dao = (BlogDAO) DaoFactory.getInstance(Constants.MAPPING_BLOG_DAO);
		}
	}
	
	public ActionForward addForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {	
				
		
		try {
			
			BlogUserDAO	daoUsers = (BlogUserDAO) DaoFactory.getInstance(Constants.MAPPING_BLOGGERUSER_DAO);
			TemplateDAO daoTemplate = (TemplateDAO) DaoFactory.getInstance(Constants.MAPPING_TEMPLATE_DAO);	
			
			Collection list = daoUsers.listUsersByGroupForBlog(Constants.STATUS_ACTIVE,Constants.USER_BLOGGER);
			Collection templates = daoTemplate.list();
			
			req.setAttribute("users",list);	
			req.setAttribute("templates",templates);
			
		} catch (Exception e) {
			e.printStackTrace();//TODO tratar erro
		}
				
		return mapping.findForward(Constants.BLOG_FORM_FORWARD);

	}
	
	public ActionForward add(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
		
		MessageResources messageResources = null;
		try {
			
			messageResources = getResources(req);
			Blog blog = new Blog();
			BlogForm blogForm = (BlogForm) form;

			blog.setName(blogForm.getName());
			blog.setPath(blogForm.getPath());
			blog.setDescription(blogForm.getDescription());
			blog.setStatus(blogForm.getStatus());
			
			BlogUser user = new BlogUser();
			user.setId(blogForm.getBloggerUser());
			
			blog.setBlogUser(user);
			
			Template t = new Template();
			t.setId(blogForm.getTemplateId());
			
			blog.setTemplate(t);
			
			int id = dao.add(blog);
		
						
			return new ActionForward("/admin/blog.do?act=list");
					
		} catch (BlogPathExistsException bpe) {

			bpe.printStackTrace();
			req.setAttribute("mensagem_erro", messageResources.getMessage("path_existente"));
			return mapping.findForward(Constants.BLOG_ERROR_FORWARD);

			
		} catch (Exception e) {

			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());
			return mapping.findForward(Constants.BLOG_ERROR_FORWARD);

		}

	}
	
	public ActionForward load(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		
		String id = req.getParameter("id");		
		
		if(id!= null)
		{
			try {
				
				Blog blog = dao.load(Integer.parseInt(id));				
				req.setAttribute(Constants.BLOG_BEAN,blog);
				
				Template t = blog.getTemplate();
				
				BlogUserDAO	daoUsers = (BlogUserDAO) DaoFactory.getInstance(Constants.MAPPING_BLOGGERUSER_DAO);
				
				Collection list = daoUsers.listUsersByGroupForBlog(Constants.STATUS_ACTIVE,Constants.USER_BLOGGER);
				
				TemplateDAO daoTemplate = (TemplateDAO) DaoFactory.getInstance(Constants.MAPPING_TEMPLATE_DAO);	
							
				Collection templates = daoTemplate.list();				
				
				req.setAttribute("templates",templates);
				
				req.setAttribute("template",t);
				
				req.setAttribute("users",list);		
				
				return mapping.findForward(Constants.BLOG_LOAD_FORWARD);
				
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("");
			}
		}
		else
		{
			return mapping.findForward("");
		}

	}
	
	public ActionForward update(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		
		MessageResources messageResources = null;
		try {
			
			messageResources = getResources(req);
			Blog blog = new Blog();
			BlogForm blogForm = (BlogForm) form;

			blog.setId(blogForm.getId());
			blog.setName(blogForm.getName());			
			blog.setDescription(blogForm.getDescription());
			blog.setStatus(blogForm.getStatus());
			
			BlogUser user = new BlogUser();
			user.setId(blogForm.getBloggerUser());
			
			blog.setBlogUser(user);
			
			Template t = new Template();
			t.setId(blogForm.getTemplateId());
			
			blog.setTemplate(t);
			
			dao.update(blog);			
			
			CacheManager.getInstance().clear();
			
			return new ActionForward("/admin/blog.do?act=list");
					
			
		} catch (Exception e) {

			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());
			return new ActionForward("/admin/blog.do?act=addForm");

		}


	}
	
	public ActionForward delete(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		
		MessageResources messageResources = null;
		try {
			
			messageResources = getResources(req);
			Blog blog = new Blog();
			BlogForm blogForm = (BlogForm) form;

			blog.setId(blogForm.getId());

			dao.delete(blog);
			CacheManager.getInstance().clear();
			
			return new ActionForward("/admin/blog.do?act=list");
					
			
		} catch (Exception e) {

			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());
			
			return mapping.findForward("");
		}

	}

	
	public ActionForward list(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		try {
			Collection blogs = dao.listBlogs(Constants.STATUS_ALL);
			req.setAttribute("blogs",blogs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
				
		return mapping.findForward(Constants.BLOG_LIST_FORWARD);

	}
	
	
	public ActionForward loadSite(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		
		String id = req.getParameter("id");
		String mes = req.getParameter("mes");
		String ano = req.getParameter("ano");
		
		String permalinkString = req.getParameter("permalink");
		boolean isPermalink = permalinkString != null ? (new Boolean(
				permalinkString)).booleanValue() : false;

		
		if(id!= null)
		{
			try {
				
				Date insertDate = null;
				Date endDate = null;
				
				ArrayList parameters = new ArrayList();				
				parameters.add(new Integer(id));
				
				Blog blog = null;
						
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
				
				
				Collection posts = null;
				
				parameters.add(insertDate);
				parameters.add(endDate);
				//LoggerUtil.debug("CARREGANDO BLOG "+id+ "POSTS DO DIA: "+insertDate.getDate()+" DO: "+insertDate.getMonth()+" ATÉ O DIA : "+endDate.getDate()+" DE:" +endDate.getMonth()+"de: "+endDate.getYear());
				System.out.println("CARREGANDO BLOG "+id+ "\nPOSTS DO DIA: "+insertDate.getDate()+" DO: "+insertDate.getMonth()+" ATÉ O DIA : "+endDate.getDate()+" DE:" +endDate.getMonth()+"de: "+endDate.getYear());
				
				blog = (Blog) CacheManager.getInstance().hitCache(DaoFactory.getInstance(Constants.MAPPING_BLOG_DAO),"loadByDate",parameters);
				
				if(isPermalink){
					System.out.println("PERMALINK");
					
					//Lendo o Post do Permalink
					
					//Obtendo id do Post
					String postId = req.getParameter("postId");
					
					ArrayList parametersPost = new ArrayList();
					parametersPost.add(new Integer(postId));
					parametersPost.add(new Integer(1));
					posts = new ArrayList();
					Post post = (Post) CacheManager.getInstance().hitCache(
							DaoFactory.getInstance("POSTDAO"), "load", parametersPost);
					posts.add(post);
					blog.setPosts(posts);
					
				}
					
				posts = blog.getPosts();				
				
				System.out.println("CARREGADO");
				BlogUser user = blog.getBloggerUser();
				
				/*
				BlogManager.getInstance().setRanking(req,0);
				*/
				BlogManager.getInstance().setHistory(req,id);
								
				req.setAttribute(Constants.BLOG_BEAN,blog);
				req.setAttribute("posts",posts);				
				req.setAttribute("blogPath",blog.getPath());
				req.setAttribute(Constants.BLOGGER_USER_BEAN,user);
				
				System.out.println("REDIRECIONANDO");
				//return new ActionForward(blog.getTemplate().getBlogPath());
				
				return new ActionForward("/servlet/content");
				
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("");
			}
		}
		else
		{
			return mapping.findForward("");
		}

	}

}
