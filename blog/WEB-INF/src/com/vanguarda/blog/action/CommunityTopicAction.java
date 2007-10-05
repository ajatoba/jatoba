package com.vanguarda.blog.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.vanguarda.blog.bean.*;

import com.vanguarda.blog.dao.CommunityTopicDAO;
import com.vanguarda.blog.dao.CommunityDAOFactory;

import com.vanguarda.blog.form.CommunityTopicForm;
import com.vanguarda.blog.util.CommunityConstants;


public class CommunityTopicAction extends DispatchAction {
	
	private static CommunityTopicDAO dao;
	
	static{
		
		if(dao == null)
		{
			dao = (CommunityTopicDAO) CommunityDAOFactory.getInstance(CommunityConstants.MAPPING_COMMUNITY_TOPIC_DAO);
		}
	}
	
	public ActionForward addForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {	
				
		
		try {
		
			//São sempre adicionados Tópicos ATIVOS
			req.setAttribute("status","1");
			req.setAttribute("communityId",req.getParameter("communityId"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(req.getParameter("origin") !=null && "blog".equals(req.getParameter("origin"))){
			return mapping.findForward("community_topic_site_form");
		}else{		
			return mapping.findForward("community_topic_form");
		}
	}
	
	
		public ActionForward add(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
		
		MessageResources messageResources = null;
		try {
			
			messageResources = getResources(req);
			CommunityTopic topic = new CommunityTopic();
			
			Community f = new Community();
			Category c = new Category();
			
			CommunityTopicForm topicForm = (CommunityTopicForm) form;

			topic.setTitle(topicForm.getTitle());
			topic.setDescription(topicForm.getDescription());
			topic.setStatus(topicForm.getStatus());
			
			f.setId(topicForm.getCommunityId());
			topic.setCommunity(f);
			
			c.setId(topicForm.getCategory());
			
			topic.setCategory(c);
	
			int id = dao.add(topic);
			
			req.setAttribute("topic_sucesso", messageResources.getMessage("add_topic_sucesso"));
			
			if(req.getParameter("origin") !=null && "blog".equals(req.getParameter("origin"))){
				return mapping.findForward("community_topic_site_form");
			}else{		
				return new ActionForward("/blogs/admin/communityTopic.do?act=list&communityId=" + f.getId() );
			}
			

					
		} catch (Exception e) {
			
			req.setAttribute("mensagem_erro", e.getMessage());
			
			if(req.getParameter("origin") !=null && "blog".equals(req.getParameter("origin"))){
				return mapping.findForward("community_topic_site_form");
			}else{		
				return new ActionForward("/admin/communityTopic.do?act=list");
			}
		}

	}
	
	public ActionForward load(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		CommunityTopicForm topicForm = (CommunityTopicForm) form;
		
		
		if(topicForm.getId() > 0){
			try {
				
				Topic topic = dao.load(topicForm.getId());				
				req.setAttribute(CommunityConstants.COMMUNITY_TOPIC_BEAN,topic);
				
				return mapping.findForward(CommunityConstants.COMMUNITY_TOPIC_LOAD_FORWARD);
				
			} catch (Exception e) {
				req.setAttribute("mensagem_erro", e.getMessage() + "-Erro1");
				return new ActionForward("/admin/topic.do?act=list");
			}
		}else{
			req.setAttribute("mensagem_erro", MessageResources.getMessageResources("load_topic_erro"));
			return new ActionForward("/admin/topic.do?act=list");
		}

	}
	
	public ActionForward update(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		
		MessageResources messageResources = null;
		try {
			
			messageResources = getResources(req);
			CommunityTopic topic = new CommunityTopic();
			
			Forum f = new Forum();
			Category c = new Category();

			CommunityTopicForm topicForm = (CommunityTopicForm) form;

			topic.setId(topicForm.getId());			
			topic.setTitle(topicForm.getTitle());
			topic.setDescription(topicForm.getDescription());
			topic.setStatus(topicForm.getStatus());
			
			f.setId(topicForm.getForum());
			topic.setForum(f);
			
			c.setId(topicForm.getCategory());
			topic.setCategory(c);
			
			dao.update(topic);
			
			req.setAttribute("topic_sucesso", messageResources.getMessage("update_topic_sucesso"));
			
			return new ActionForward("/admin/topic.do?act=list");
			
		} catch (Exception e) {

			req.setAttribute("mensagem_erro", e.getMessage());
			return new ActionForward("/admin/topic.do?act=update");

		}


	}
	
	public ActionForward delete(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		
		MessageResources messageResources = null;
		try {
			
			messageResources = getResources(req);
			CommunityTopic topic = new CommunityTopic();
			CommunityTopicForm topicForm = (CommunityTopicForm) form;
			
			topic.setId(topicForm.getId());
			
			dao.delete(topic);
			
			req.setAttribute("topic_sucesso", messageResources.getMessage("delete_topic_sucesso"));
			
			return new ActionForward("/admin/topic.do?act=list");					
			
		} catch (Exception e) {

			req.setAttribute("mensagem_erro", e.getMessage());
			
			return new ActionForward("/admin/topic.do?act=list");
		}

	}

	
	public ActionForward list(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		AbstractForum f = new Community();
		MessageResources messageResources = null;
		
		CommunityTopicForm topicForm = (CommunityTopicForm) form;
		
		f.setId(topicForm.getCommunityId());
		
		try {
			messageResources = getResources(req);
			Collection topics = dao.listTopics(CommunityConstants.STATUS_ALL,f);
			req.setAttribute("topics",topics);
			
			
			if(topics.size() == 0) {
				req.setAttribute("mensagem_erro", messageResources.getMessage("no_community_topics"));
				return mapping.findForward("list_community_topics");
			}
			
			
		} catch (Exception e) {
			System.out.println("ERRO : " + e.getMessage());
		}
		
		return mapping.findForward("list_community_topics");

	}

	public ActionForward updateStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		
		MessageResources messageResources = null;
		try {
			
			messageResources = getResources(req);
			
			CommunityTopicForm topicForm = (CommunityTopicForm) form;
			
			dao.updateStatus(topicForm.getId(),topicForm.getStatus());
			
			req.setAttribute("topic_sucesso", messageResources.getMessage("update_topic_status_sucesso"));
			
			return new ActionForward("/admin/topic.do?act=list");
			
		} catch (Exception e) {

			req.setAttribute("mensagem_erro", e.getMessage());
			return new ActionForward("/admin/topic.do?act=list");

		}

	}

	
	
}
