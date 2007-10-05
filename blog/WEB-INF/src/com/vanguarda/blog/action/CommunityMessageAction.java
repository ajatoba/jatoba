package com.vanguarda.blog.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.vanguarda.blog.bean.*;
import com.vanguarda.blog.form.*;
import com.vanguarda.blog.dao.*;
import com.vanguarda.blog.util.CommunityConstants;

public class CommunityMessageAction extends DispatchAction{
	
private static MessageDAO dao;
	
	static{
		
		if(dao == null)
		{
			dao = (CommunityMessageDAO) CommunityDAOFactory.getInstance(CommunityConstants.MAPPING_COMMUNITY_MESSAGE_DAO);
		}
	}

	public ActionForward addForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {	
				
		
		try {
		
			CommunityMessageForm f = (CommunityMessageForm) form;
			
			Topic t = new CommunityTopic();
			
			t.setId(f.getTopic());
			
			req.setAttribute("topic",t);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return mapping.findForward("list_community_message");

	}

	public ActionForward add(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {	
				
				
		MessageResources messageResources = null;
		try {
			
			messageResources = getResources(req);
			Message m = new Message();
			Topic topic = new Topic();
			ForumUser user = new ForumUser();
			
			MessageForm messageForm = (MessageForm) form;

			topic.setId(messageForm.getTopic());
			user.setName(messageForm.getUser());
			
			m.setTopic(topic);
			m.setUser(user);
			m.setContent(messageForm.getContent());
			m.setTitle(messageForm.getTitle());
			m.setStatus(messageForm.getStatus());
			
			int id = dao.add(m);
			
			req.setAttribute("message_sucesso", messageResources.getMessage("add_message_sucesso"));
			
			return new ActionForward("/communityMessage.do?act=list&topic=" + m.getTopic().getId());

					
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());
			return mapping.findForward("list_community_messages");

		}
	}
		public ActionForward delete(ActionMapping mapping,
				ActionForm form, HttpServletRequest req, HttpServletResponse resp)
				throws Exception {	
					
					
			MessageResources messageResources = null;
			try {
				
				messageResources = getResources(req);
				Message m = new Message();
				Topic t = new Topic();

				MessageForm messageForm = (MessageForm) form;
				
				t.setId(messageForm.getTopic());
				
				m.setId(messageForm.getId());
				m.setStatus(messageForm.getStatus());
				m.setTopic(t);
				
				dao.delete(m);
				
				req.setAttribute("message_sucesso", messageResources.getMessage("delete_message_sucesso"));
				
				return new ActionForward("/admin/message.do?act=list&topic=" + m.getTopic().getId());

						
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("mensagem_erro", e.getMessage());
				return mapping.findForward("list_messages");

			}

	}

	
	public ActionForward list(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		Topic t = new Topic();
		
		CommunityMessageForm messageForm = (CommunityMessageForm) form;
		
		t.setId(messageForm.getTopic());
		
		//MANTENDO NO REQUEST O TÓPICO
		
		req.setAttribute("topic",t);
		
		MessageResources messageResources = null;
		
		try {
			
			messageResources = getResources(req);
			
			Collection messages = dao.listMessages(CommunityConstants.STATUS_ACTIVE,t);
			req.setAttribute("messages",messages);

			if(messages.size() == 0) {
				req.setAttribute("no_messages",messageResources.getMessage("no_messages"));
				return mapping.findForward("list_community_messages");
				
			}
			
		} catch (Exception e) {
			
			req.setAttribute("mensagem_erro",messageResources.getMessage("list_messages_erro"));
			
			return mapping.findForward("list_community_messages");
		}
		
		return mapping.findForward("list_community_messages");

	}
	
}
