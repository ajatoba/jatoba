package com.vanguarda.blog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.dao.BlogUserDAO;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.exception.InvalidPasswordException;
import com.vanguarda.blog.exception.LoginNotExistsException;
import com.vanguarda.blog.form.CommunityUserLoginForm;
import com.vanguarda.blog.util.CommunityConstants;

public class CommunityUserLoginAction extends DispatchAction{
	private static BlogUserDAO dao;

	static {

		if (dao == null) {
			dao = (BlogUserDAO) DaoFactory
					.getInstance(CommunityConstants.MAPPING_BLOGGERUSER_DAO);
		}

	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MessageResources messageResources = getResources(req);

		CommunityUserLoginForm userForm = (CommunityUserLoginForm) form;

		BlogUser user = null;
		
		try {

			user = (BlogUser) dao.login(userForm.getLogin(), userForm.getPassword());

			HttpSession session = req.getSession();
			session.setAttribute(CommunityConstants.BLOGGER_USER_BEAN, user);

		} catch (InvalidPasswordException ip) {

			req.setAttribute("mensagem_erro", messageResources.getMessage("senha_incorreta"));
			return mapping.findForward(CommunityConstants.BLOGUSER_LOGIN_ERROR_FORWARD);

		} catch (LoginNotExistsException lne) {

			req.setAttribute("mensagem_erro", messageResources.getMessage("login_inexistente"));
			return mapping.findForward(CommunityConstants.BLOGUSER_LOGIN_ERROR_FORWARD);

		} catch (ClassCastException e) {

			req.setAttribute("mensagem_erro", messageResources.getMessage("permissao"));
			return mapping.findForward(CommunityConstants.BLOGUSER_LOGIN_ERROR_FORWARD);

		} catch (Exception e) {

			req.setAttribute("mensagem_erro", e.getMessage());
			return mapping.findForward(CommunityConstants.BLOGUSER_LOGIN_ERROR_FORWARD);
		}

		System.out.println("REDIRECIONANDO!");
		return new ActionForward(userForm.getReturnPath());

	}

}