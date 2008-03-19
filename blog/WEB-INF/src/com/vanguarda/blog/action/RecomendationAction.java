/*
 * Created on 14/03/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.form.RecomendationForm;
import com.vanguarda.blog.util.SendMail;


public class RecomendationAction extends DispatchAction {

	
	public ActionForward sendRecomendation(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		RecomendationForm form2 = (RecomendationForm) form;
		
		MessageResources messageResources = null;
		
		try {
			
			messageResources = getResources(req);
			
			SendMail mail = new SendMail();
			
			String smtpServer = (String) BlogManager.getInstance().getProperties().get("smtp");
			
			String message = messageResources.getMessage("recomendation_message") ;
			message = message.replaceAll("<TO_NAME>", form2.getToName());
			message = message.replaceAll("<FROM_NAME>", form2.getFromName());
			message = message.replaceAll("<TO_MAIL>", form2.getToMail());
			message = message.replaceAll("<FROM_MAIL>", form2.getFromMail());
			message = message.replaceAll("<POST_ID>",String.valueOf(form2.getPostId()));
						
			mail.setSmtpServer(smtpServer);
			mail.setContentType((String)BlogManager.getInstance().getProperties().get("mail_format"));
			
			mail.setFrom(form2.getFromMail());
			mail.setTo(form2.getToMail());
			
			mail.setSubject(messageResources.getMessage("recomendation_subject"));
			mail.setMessage(message);
			
			mail.send();
			
			req.setAttribute("sucesso",messageResources.getMessage("recomendation_success"));
									
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
		}
						
		return mapping.findForward("addRecomendation");

	}
	
}
