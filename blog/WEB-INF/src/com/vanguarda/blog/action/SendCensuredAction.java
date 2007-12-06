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



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.bean.Post;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.dao.PostDAO;
import com.vanguarda.blog.form.SendCensuredForm;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.SendMail;


public class SendCensuredAction extends DispatchAction {

	
	public ActionForward censured(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		SendCensuredForm form2 = (SendCensuredForm) form;
		try {
			
			PostDAO dao = (PostDAO) DaoFactory.getInstance(Constants.MAPPING_POST_DAO);
			Post post = dao.load(form2.getPostId(),Constants.STATUS_ACTIVE);
						
			SendMail mail = new SendMail();
			
			String smtpServer = (String) BlogManager.getInstance().getProperties().get("smtp");
			String mailTo = (String) BlogManager.getInstance().getProperties().get("destinatario_denuncia");
			
			mail.setFrom(form2.getEmail());
			mail.setSmtpServer(smtpServer);
						
			mail.setContentType("txt");
			mail.setSubject(Constants.CENSURED);
			//mail.setTo(post.getBlog().getBlogUser().getEmail());
			mail.setTo(mailTo);
			mail.setMessage(form2.getMessage() + "\n A denúncia é referente ao blog:" + post.getBlog().getBlogUser().getFirstName());
			
			mail.send();
		req.setAttribute("comment_sucesso","Mensagem enviada com sucesso.");
			
						
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
		}
						
		return mapping.findForward(Constants.CENSURE_SUCCESS_FORWARD);

	}
	
}
