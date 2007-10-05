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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.vanguarda.blog.bean.AdminUser;
import com.vanguarda.blog.dao.AdminUserDAO;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.exception.InvalidPasswordException;
import com.vanguarda.blog.exception.LoginNotExistsException;
import com.vanguarda.blog.form.AdminUserForm;
import com.vanguarda.blog.util.Constants;

public class AdminUserAction extends DispatchAction {

	private static ArrayList tabela;

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		return mapping.findForward("");

	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MessageResources messageResources = getResources(req);

		AdminUserForm userForm = (AdminUserForm) form;

		AdminUserDAO dao = (AdminUserDAO) DaoFactory
				.getInstance(Constants.MAPPING_ADMIN_DAO);

		AdminUser user = null;

		try {

			user = (AdminUser) dao.login(userForm.getLogin(), userForm
					.getPassword());

			HttpSession session = req.getSession();
			session.setAttribute(Constants.ADMIN_USER_BEAN, user);

		} catch (InvalidPasswordException ip) {
			ip.printStackTrace();
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("senha_incorreta"));
			return mapping.findForward(Constants.ADMIN_LOGIN_ERROR_FORWARD);

		} catch (LoginNotExistsException lne) {
			lne.printStackTrace();
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("login_inexistente"));
			return mapping.findForward(Constants.ADMIN_LOGIN_ERROR_FORWARD);

		} catch (ClassCastException e) {
			e.printStackTrace();
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("permissao"));
			return mapping.findForward(Constants.ADMIN_LOGIN_ERROR_FORWARD);

		} catch (Exception e) {

			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());
			return mapping.findForward(Constants.ADMIN_LOGIN_ERROR_FORWARD);
		}

		return mapping.findForward(Constants.LOGIN_FORWARD);

	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		return mapping.findForward("");

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		return mapping.findForward("");

	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		return mapping.findForward("");

	}
	
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

			HttpSession session = req.getSession();
			session.removeAttribute(Constants.ADMIN_USER_BEAN);
			
			return mapping.findForward(Constants.ADMIN_LOGIN_ERROR_FORWARD);
			
	}

}
