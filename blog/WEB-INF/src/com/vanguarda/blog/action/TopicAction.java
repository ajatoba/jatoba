package com.vanguarda.blog.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.vanguarda.blog.bean.Category;
import com.vanguarda.blog.bean.Forum;
import com.vanguarda.blog.bean.Topic;
import com.vanguarda.blog.dao.ForumDaoFactory;
import com.vanguarda.blog.dao.TopicDAO;
import com.vanguarda.blog.form.TopicForm;
import com.vanguarda.blog.util.CacheManager;
import com.vanguarda.blog.util.ForumConstants;

public class TopicAction extends DispatchAction {
	
	private static TopicDAO dao;
	
	static{
		
		if(dao == null)
		{
			dao = (TopicDAO) ForumDaoFactory.getInstance(ForumConstants.MAPPING_TOPIC_DAO);
		}
	}
	
	public ActionForward addForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {	
				
		
		try {
		
			//São sempre adicionados Tópicos ATIVOS
			req.setAttribute("status","1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return mapping.findForward("topic_form");

	}
	
	
		public ActionForward add(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
		
		MessageResources messageResources = null;
		try {
			
			messageResources = getResources(req);
			Topic topic = new Topic();
			
			Forum f = new Forum();
			Category c = new Category();
			
			TopicForm topicForm = (TopicForm) form;

			topic.setTitle(topicForm.getTitle());
			topic.setDescription(topicForm.getDescription());
			topic.setStatus(topicForm.getStatus());
			
			f.setId(topicForm.getForum());
			topic.setForum(f);
			
			c.setId(topicForm.getCategory());
			
			topic.setCategory(c);
	
			int id = dao.add(topic);
			
			CacheManager.getInstance().remoteTopicClearCache(topicForm.getStatus());
			
			req.setAttribute("topic_sucesso", messageResources.getMessage("add_topic_sucesso"));
			
			return new ActionForward("/admin/topic.do?act=list");

					
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());
			return new ActionForward("/admin/topic.do?act=list");

		}

	}
	
	public ActionForward load(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		TopicForm topicForm = (TopicForm) form;
		
		
		if(topicForm.getId() > 0){
			try {
				
				Topic topic = dao.load(topicForm.getId());				
				req.setAttribute(ForumConstants.TOPIC_BEAN,topic);
				
				return mapping.findForward(ForumConstants.TOPIC_LOAD_FORWARD);
				
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
			Topic topic = new Topic();
			
			Forum f = new Forum();
			Category c = new Category();

			TopicForm topicForm = (TopicForm) form;

			topic.setId(topicForm.getId());			
			topic.setTitle(topicForm.getTitle());
			topic.setDescription(topicForm.getDescription());
			topic.setStatus(topicForm.getStatus());
			
			f.setId(topicForm.getForum());
			topic.setForum(f);
			
			c.setId(topicForm.getCategory());
			topic.setCategory(c);
			
			dao.update(topic);
			
			CacheManager.getInstance().remoteTopicClearCache(topicForm.getStatus());
			
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
			Topic topic = new Topic();
			TopicForm topicForm = (TopicForm) form;
			
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

		Forum f = new Forum();

		MessageResources messageResources = null;
		
		try {
			messageResources = getResources(req);
			
			//Collection topics = dao.listTopics(ForumConstants.STATUS_ALL,f);
			
			ArrayList parameters = new ArrayList();
			parameters.add(new Integer(ForumConstants.STATUS_ALL));
			parameters.add(f);
			
			Collection topics = (Collection) CacheManager.getInstance().hitCache(ForumDaoFactory.getInstance(ForumConstants.MAPPING_TOPIC_DAO),"listTopics",parameters);						
			req.setAttribute("topics",topics);
			
			if(topics.size() == 0) {
				req.setAttribute("mensagem_erro", messageResources.getMessage("no_topics"));
				return mapping.findForward("list_topics");
			}
			
		} catch (Exception e) {
			System.out.println("ERRO : " + e.getMessage());
		}
		
		return mapping.findForward("list_topics");

	}

	public ActionForward updateStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		
		MessageResources messageResources = null;
		try {
			
			messageResources = getResources(req);
			
			TopicForm topicForm = (TopicForm) form;
			
			dao.updateStatus(topicForm.getId(),topicForm.getStatus());
			
			req.setAttribute("topic_sucesso", messageResources.getMessage("update_topic_status_sucesso"));
			
			return new ActionForward("/admin/topic.do?act=list");
			
		} catch (Exception e) {

			req.setAttribute("mensagem_erro", e.getMessage());
			return new ActionForward("/admin/topic.do?act=list");

		}

	}

	
	
}
