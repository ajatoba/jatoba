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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.bean.Quadrante;
import com.vanguarda.blog.bean.Topic;
import com.vanguarda.blog.dao.BlogDAO;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.dao.ForumDaoFactory;
import com.vanguarda.blog.dao.QuadranteDAO;
import com.vanguarda.blog.dao.TopicDAO;
import com.vanguarda.blog.dao.impl.BlogDAOImpl;
import com.vanguarda.blog.form.QuadrantForm;
import com.vanguarda.blog.helper.FileHelper;
import com.vanguarda.blog.util.CacheManager;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.ForumConstants;

public class QuadranteAction extends DispatchAction {

	// private static ArrayList tabela;

	private static QuadranteDAO dao;

	static {

		if (dao == null) {
			dao = (QuadranteDAO) DaoFactory
					.getInstance(Constants.MAPPING_QUADRANT_DAO);
		}

	}

	public ActionForward form(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		try {
		
			BlogDAO blogDAO = (BlogDAO) DaoFactory.getInstance(Constants.MAPPING_BLOG_DAO);
			Collection blogs = blogDAO.listBlogs(Constants.STATUS_ACTIVE);
			
			req.setAttribute(Constants.BLOGS_BEAN,blogs);
						 			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(Constants.QUADRANT_FORM_FORWARD);
				
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		try {

			
			Quadrante quadrante = new Quadrante();
			QuadrantForm quadrantForm = (QuadrantForm) form;
			String path = "img";
			
			quadrante.setDescription(quadrantForm.getDescription());
			quadrante.setTitle(quadrantForm.getTitle());
			quadrante.setUrl(quadrantForm.getUrl());
			
			quadrante.setStatus(quadrantForm.getStatus());
			quadrante.setBlog(quadrantForm.getBlog());		
			quadrante.setOther(quadrantForm.getOther());
		
			
			FormFile myFile = quadrantForm.getImage();
	        String contentType = myFile.getContentType();
	        String fileName    = myFile.getFileName();
	        int fileSize       = myFile.getFileSize();
	        byte[] fileData    = myFile.getFileData();
	        
	        if(myFile!= null && myFile.getFileData().length > 0)
	        {
	        	quadrante.setUrlFoto(path+"/"+fileName);
		        String imagePath = (String) BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY)+ path+File.separator;	        
		        FileHelper.createImg(imagePath,fileData,fileName);
	        }
	    

			dao.add(quadrante);
			
			ActionMessages mensagens = new ActionMessages();
			mensagens.add(Constants.SUCESS_PARAMETER, new ActionMessage(Constants.QUADRANT_INSERT_MESSAGE_SUCESS));
			saveMessages(req, mensagens);
			
			Collection quadrantes = dao.listaQuadrantes(Constants.STATUS_ALL);
			req.setAttribute(Constants.QUADRANTS_BEAN,quadrantes);
			
			return mapping.findForward(Constants.QUADRANT_LIST_FORWARD);

		} catch (Exception e) {
			e.printStackTrace();
			
			ActionMessages errors = new ActionMessages();
			errors.add(Constants.ERROR_PARAMETER, new ActionMessage(Constants.QUADRANT_INSERT_MESSAGE_ERROR));
			saveMessages(req, errors);

			
			return mapping.findForward(Constants.QUADRANT_FORM_FORWARD);

		}
		
	}

	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String id = req.getParameter("id");
		Quadrante quadrante = null;
		if (id != null) {
			try {
				quadrante = dao.load(Integer.parseInt(id));
				buildFormView(quadrante,(QuadrantForm)form);
				
				((QuadrantForm)form).setAcao("alterar");
				
				BlogDAO blogDAO = (BlogDAO) DaoFactory.getInstance(Constants.MAPPING_BLOG_DAO);
				Collection blogs = blogDAO.listBlogs(Constants.STATUS_ACTIVE);
				
				req.setAttribute(Constants.BLOGS_BEAN,blogs);
				
				return mapping.findForward(Constants.QUADRANT_FORM_FORWARD);
				
			} catch (Exception e) {
				e.printStackTrace();
				
				ActionMessages errors = new ActionMessages();
				errors.add(Constants.ERROR_PARAMETER, new ActionMessage(Constants.QUADRANT_MESSAGE_ERROR));
				saveMessages(req, errors);

				
				return mapping.findForward(Constants.QUADRANT_FORM_FORWARD);
			}
			
		} else {
			
			ActionMessages errors = new ActionMessages();
			errors.add(Constants.ERROR_PARAMETER, new ActionMessage(Constants.QUADRANT_MESSAGE_ERROR));
			saveMessages(req, errors);

			return mapping.findForward(Constants.QUADRANT_FORM_FORWARD);
			
		}

	}
	
	
	public ActionForward publish(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		try {
			
			String page = "http://"+(String)BlogManager.getInstance().getProperties().get(Constants.LOCALHOST_PROPERTY)+"/index.html";
			String path = (String)BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY);
			FileHelper.writerStaticFile(path,"index.html","http://"+(String)BlogManager.getInstance().getProperties().get(Constants.LOCALHOST_PROPERTY)+":"+req.getLocalPort()+"/blog/quadrant.do?act=loadSite");
			
			ActionMessages mensagens = new ActionMessages();
			mensagens.add(Constants.SUCESS_PARAMETER, new ActionMessage(Constants.QUADRANT_PUBLISH_MESSAGE_SUCESS));
			saveMessages(req, mensagens);
			
			Collection quadrantes = dao.listaQuadrantes(Constants.STATUS_ALL);
			req.setAttribute(Constants.QUADRANTS_BEAN,quadrantes);
			req.setAttribute(Constants.PAGE_HOME,page);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return mapping.findForward(Constants.QUADRANT_LIST_FORWARD);
	}
	
	public ActionForward preview(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		try {
			String page = "http://"+(String)BlogManager.getInstance().getProperties().get(Constants.LOCALHOST_PROPERTY)+"/index_teste.html";
			String path = (String)BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY);
			FileHelper.writerStaticFile(path,"index_teste.html","http://"+(String)BlogManager.getInstance().getProperties().get(Constants.LOCALHOST_PROPERTY)+":"+req.getLocalPort()+"/blog/quadrant.do?act=loadSite");
			
			ActionMessages mensagens = new ActionMessages();
			mensagens.add(Constants.SUCESS_PARAMETER, new ActionMessage(Constants.QUADRANT_PREVIEW_MESSAGE_SUCESS));
			saveMessages(req, mensagens);
			
			Collection quadrantes = dao.listaQuadrantes(Constants.STATUS_ALL);
			req.setAttribute(Constants.QUADRANTS_BEAN,quadrantes);
			req.setAttribute(Constants.PAGE_HOME,page);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return mapping.findForward(Constants.QUADRANT_LIST_FORWARD);
		
	}


	public ActionForward loadSite(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		try {
			ArrayList quadrantes = (ArrayList) dao.listaQuadrantes(Constants.STATUS_ACTIVE);
			Map map = new HashMap();
			
			for (int i = 0; i < quadrantes.size(); i++) {
				
				Quadrante quadrante = (Quadrante) quadrantes.get(i);
				
				map.put(Integer.valueOf(quadrante.getCode()),quadrante);
				
			}
			
			HttpSession session = req.getSession();
			
			Collection rankings= (Collection) CacheManager.getInstance().hitCache(DaoFactory.getInstance("RANKINGDAO"),"getRanking",null);
			
			Topic topic = ((TopicDAO)(ForumDaoFactory.getInstance(ForumConstants.MAPPING_TOPIC_DAO))).loadTopTopic();
			
			
			session.setAttribute(Constants.QUADRANTS_MAP_BEAN,map);
			req.setAttribute(Constants.RANKING_BEAN,rankings);
			req.setAttribute("topic",topic);
			req.setAttribute("messages",topic.getMessages());
			
			return mapping.findForward(Constants.QUADRANT_HOME_FORWARD);
			
		} catch (Exception e) {
			
			ActionMessages errors = new ActionMessages();
			errors.add(Constants.ERROR_PARAMETER, new ActionMessage(Constants.QUADRANT_MESSAGE_ERROR));
			saveMessages(req, errors);
			
			return mapping.findForward("");
		}
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		try {
			Collection quadrantes = dao.listaQuadrantes(Constants.STATUS_ALL);
			req.setAttribute(Constants.QUADRANTS_BEAN,quadrantes);
			
			return mapping.findForward(Constants.QUADRANT_LIST_FORWARD);
			
		} catch (Exception e) {
			
			ActionMessages errors = new ActionMessages();
			errors.add(Constants.ERROR_PARAMETER, new ActionMessage(Constants.QUADRANT_MESSAGE_ERROR));
			saveMessages(req, errors);
			
			return mapping.findForward(Constants.QUADRANT_LIST_FORWARD);
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		try {

			
			Quadrante quadrante = new Quadrante();
			QuadrantForm quadrantForm = (QuadrantForm) form;
			String path = "img";
			
			quadrante.setCode(quadrantForm.getCode());			
			quadrante.setDescription(quadrantForm.getDescription());
			quadrante.setTitle(quadrantForm.getTitle());
			quadrante.setUrl(quadrantForm.getUrl());
			
			quadrante.setStatus(quadrantForm.getStatus());
			quadrante.setBlog(quadrantForm.getBlog());	
			quadrante.setUrlFoto(quadrantForm.getUrlFoto());
			quadrante.setOther(quadrantForm.getOther());
		
			
			FormFile myFile = quadrantForm.getImage();
	        String contentType = myFile.getContentType();
	        String fileName    = myFile.getFileName();
	        int fileSize       = myFile.getFileSize();
	        byte[] fileData    = myFile.getFileData();
	        
	        if(myFile!= null && myFile.getFileData().length > 0)
	        {
	        	quadrante.setUrlFoto(path+"/"+fileName);
		        String imagePath = (String) BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY)+ path+File.separator;	        
		        FileHelper.createImg(imagePath,fileData,fileName);
	        }
	        
	        

			dao.update(quadrante);
			
			ActionMessages mensagens = new ActionMessages();
			mensagens.add(Constants.SUCESS_PARAMETER, new ActionMessage(Constants.QUADRANT_UPDATE_MESSAGE_SUCESS));
			saveMessages(req, mensagens);
			
			Collection quadrantes = dao.listaQuadrantes(Constants.STATUS_ALL);
			req.setAttribute(Constants.QUADRANTS_BEAN,quadrantes);
			
			return mapping.findForward(Constants.QUADRANT_LIST_FORWARD);

		} catch (Exception e) {
			e.printStackTrace();
			
			ActionMessages errors = new ActionMessages();
			errors.add(Constants.ERROR_PARAMETER, new ActionMessage(Constants.QUADRANT_UPDATE_MESSAGE_ERROR));
			saveMessages(req, errors);

			
			return mapping.findForward(Constants.QUADRANT_FORM_FORWARD);

		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String id = req.getParameter("id");
		
		if (id != null) {
			try {
				
				dao.delete(Integer.parseInt(id));
				
				return mapping.findForward(Constants.QUADRANT_DELETE_FORWARD);
				
			} catch (Exception e) {
				e.printStackTrace();
				
				ActionMessages errors = new ActionMessages();
				errors.add(Constants.ERROR_PARAMETER, new ActionMessage(Constants.QUADRANT_MESSAGE_ERROR));
				saveMessages(req, errors);
				
				Collection quadrantes = dao.listaQuadrantes(Constants.STATUS_ALL);
				req.setAttribute(Constants.QUADRANTS_BEAN,quadrantes);
				
				return mapping.findForward(Constants.QUADRANT_LIST_FORWARD);
			}
			
		} else {
			
			ActionMessages errors = new ActionMessages();
			errors.add(Constants.ERROR_PARAMETER, new ActionMessage(Constants.QUADRANT_MESSAGE_ERROR));
			saveMessages(req, errors);

			return mapping.findForward(Constants.QUADRANT_DELETE_FORWARD);
		}
	}
	
	private void buildFormView(Quadrante quadrante, QuadrantForm form)
	{
		form.setBlogId(quadrante.getBlog().getId());
		form.setCode(quadrante.getCode());
		form.setDescription(quadrante.getDescription());
		form.setStatus(quadrante.getStatus());
		form.setUrl(quadrante.getUrl());
		form.setOther(quadrante.getOther());
		if(quadrante.getUrlFoto()!= null && quadrante.getUrlFoto().length()>0)
		{
			if(quadrante.getUrlFoto().startsWith("http"))
				form.setUrlFoto(quadrante.getUrlFoto());
			else
				form.setUrlFoto("/"+quadrante.getUrlFoto());
		}
		
		form.setTitle(quadrante.getTitle());
		
	}

}
