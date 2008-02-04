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

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.bean.AdminUser;
import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.Group;
import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.dao.BlogUserDAO;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.exception.EmailExistsException;
import com.vanguarda.blog.exception.InvalidPasswordException;
import com.vanguarda.blog.exception.LoginExistsException;
import com.vanguarda.blog.exception.LoginNotExistsException;
import com.vanguarda.blog.form.BlogUserForm;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.RandomTool;
import com.vanguarda.blog.util.SendMail;

public class BlogUserAction extends DispatchAction {

	//private static ArrayList tabela;

	private static BlogUserDAO dao;

	static {

		if (dao == null) {
			dao = (BlogUserDAO) DaoFactory
					.getInstance(Constants.MAPPING_BLOGGERUSER_DAO);
		}

	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MessageResources messageResources = getResources(req);

		BlogUserForm userForm = (BlogUserForm) form;

		User user = null;

		try {

			user = dao.login(userForm.getLogin(), userForm.getPassword());

			if (user instanceof BlogUser) {
				HttpSession session = req.getSession();
				session.setAttribute(Constants.BLOGGER_USER_BEAN, user);
				session.setAttribute("path", user.getBlog().getPath());
			} else if (user instanceof AdminUser) {
				HttpSession session = req.getSession();
				session.setAttribute(Constants.ADMIN_USER_BEAN, user);
				return new ActionForward("/admin/blog.do?act=list");

			} else {
				throw new ClassCastException();
			}

		} catch (InvalidPasswordException ip) {

			ip.printStackTrace();
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("senha_incorreta"));
			return mapping.findForward(Constants.BLOGUSER_LOGIN_ERROR_FORWARD);

		} catch (LoginNotExistsException lne) {
			lne.printStackTrace();
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("login_inexistente"));
			return mapping.findForward(Constants.BLOGUSER_LOGIN_ERROR_FORWARD);

		} catch (ClassCastException e) {

			e.printStackTrace();
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("permissao"));
			return mapping.findForward(Constants.BLOGUSER_LOGIN_ERROR_FORWARD);

		} catch (Exception e) {

			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());
			return mapping.findForward(Constants.BLOGUSER_LOGIN_ERROR_FORWARD);
		}

		//return mapping.findForward(Constants.LOGIN_FORWARD);
		return new ActionForward("/blogs/admin/post.do?act=list");

	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		HttpSession session = req.getSession();
		session.removeAttribute(Constants.BLOGGER_USER_BEAN);

		return mapping.findForward(Constants.BLOGUSER_LOGIN_ERROR_FORWARD);

	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MessageResources messageResources = null;
		try {

			messageResources = getResources(req);
			BlogUser user = new BlogUser();
			BlogUserForm userForm = (BlogUserForm) form;

			user.setFirstName(userForm.getFirstName());
			user.setEmail(userForm.getEmail());
			user.setGroup(new Group(Constants.USER_BLOGGER));
			user.setLastName(userForm.getLastName());
			user.setLogin(userForm.getLogin());
			user.setPassword(userForm.getPassword());
			user.setStatus(userForm.getStatus());

			dao.add(user);

			return mapping.findForward(Constants.USER_ADD_FORWARD);

		} catch (LoginExistsException lee) {

			lee.printStackTrace();
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("login_existente"));

			return mapping.findForward(Constants.USER_ADD_FORM_FORWARD);

		} catch (EmailExistsException eee) {

			eee.printStackTrace();
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("email_existente"));
			return mapping.findForward(Constants.USER_ADD_FORM_FORWARD);

		} catch (Exception e) {

			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());

			return mapping.findForward(Constants.USER_ADD_FORM_FORWARD);

		}

	}

	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String id = req.getParameter("id");

		if (id != null) {
			try {

				BlogUser user = dao.load(Integer.parseInt(id));
				req.setAttribute(Constants.BLOGGER_USER_BEAN, user);

				return mapping.findForward(Constants.USER_LOAD_FORWARD);

			} catch (Exception e) {
				return mapping.findForward("");
			}
		} else {
			return mapping.findForward("");
		}

	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MessageResources messageResources = null;
		try {

			messageResources = getResources(req);
			BlogUser user = new BlogUser();
			BlogUserForm userForm = (BlogUserForm) form;

			user.setId(userForm.getId());
			user.setFirstName(userForm.getFirstName());
			user.setEmail(userForm.getEmail());
			user.setGroup(new Group(Constants.USER_BLOGGER));
			user.setLastName(userForm.getLastName());
			user.setLogin(userForm.getLogin());
			user.setPassword(userForm.getPassword());
			user.setStatus(userForm.getStatus());

			dao.update(user);

			return mapping.findForward(Constants.USER_ADD_FORWARD);

		} catch (LoginExistsException lee) {

			req.setAttribute("mensagem_erro", messageResources
					.getMessage("login_existente"));

			return mapping.findForward(Constants.USER_UPDATE_FORM_FORWARD);

		} catch (EmailExistsException eee) {

			req.setAttribute("mensagem_erro", messageResources
					.getMessage("email_existente"));
			return mapping.findForward(Constants.USER_UPDATE_FORM_FORWARD);

		} catch (Exception e) {

			req.setAttribute("mensagem_erro", e.getMessage());

			return mapping.findForward(Constants.USER_UPDATE_FORM_FORWARD);

		}

	}

	public ActionForward updatePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		System.out.println("PSSWORD ACTION");
		MessageResources messageResources = getResources(req);

		BlogUserForm userForm = (BlogUserForm) form;

		User user = null;

		try {

			System.out.println("Fazendo Login");
			user = dao.login(userForm.getLogin(), userForm.getPassword());

			if (user instanceof BlogUser) {
				HttpSession session = req.getSession();
				session.setAttribute(Constants.BLOGGER_USER_BEAN, user);
				session.setAttribute("path", user.getBlog().getPath());
			} else if (user instanceof AdminUser) {
				HttpSession session = req.getSession();
				session.setAttribute(Constants.ADMIN_USER_BEAN, user);
				return new ActionForward("/admin/blog.do?act=list");

			} else {
				throw new ClassCastException();
			}

		} catch (InvalidPasswordException ip) {

			req.setAttribute("mensagem_erro", messageResources
					.getMessage("senha_incorreta"));
			return mapping.findForward("update_password_form");
			
		} catch (LoginNotExistsException lne) {
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("login_inexistente"));
			return mapping.findForward("update_password_form");

		} catch (ClassCastException e) {

			e.printStackTrace();
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("permissao"));
			return mapping.findForward("update_password_form");
			
		} catch (Exception e) {

			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());
			return mapping.findForward("update_password_form");
		}

		System.out.println("Atualizando");
		try {
			dao.updatePassword(user, userForm.getNewPassword());
		} catch (Exception e) {
			req.setAttribute("mensagem_erro", e.getMessage());
			return mapping.findForward("update_password_form");
		}
		
		
		req.setAttribute("mensagem_sucesso", messageResources
				.getMessage("senha_atualizada"));
		return mapping.findForward("update_password_form");
		
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MessageResources messageResources = null;
		try {

			messageResources = getResources(req);
			BlogUser user = new BlogUser();
			BlogUserForm userForm = (BlogUserForm) form;

			user.setId(userForm.getId());

			dao.delete(user);

			return new ActionForward("/admin/blogUser.do?act=list");

		} catch (Exception e) {

			req.setAttribute("mensagem_erro", e.getMessage());

			return mapping.findForward(Constants.USER_LIST_FORWARD);
		}

	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Collection list = null;
		MessageResources messageResources = null;
		try {

			messageResources = getResources(req);
			list = dao.listUsersByGroup(Constants.STATUS_ALL,
					Constants.USER_BLOGGER);

			req.setAttribute("users", list);
			messageResources = getResources(req);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(Constants.USER_LIST_FORWARD);

	}

	public ActionForward remindPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String email = req.getParameter("email");

		if (email != null) {
			MessageResources messageResources = null;
			try {

				messageResources = getResources(req);

				/* TESTANDO DIGITAÇÃO DA GIF DE SEGURANÇA */
				String word = req.getParameter("imageword");
				String encryptWord = req.getParameter("wordEnc");

				if (!RandomTool.compare(word, encryptWord)) {

					req.setAttribute("error", messageResources
							.getMessage("wrong_gif_text"));
					return new ActionForward(
							"/blogs/content/remind_password_in.jsp");

				}

				/** *********************************** */

				BlogUser user = dao.remindPassword(email);

				if (user == null) {
					req.setAttribute("error", messageResources
							.getMessage("unregistered_user"));
				} else {

					SendMail mail = new SendMail();

					String smtpServer = (String) BlogManager.getInstance()
							.getProperties().get("smtp");

					mail.setFrom(messageResources.getMessage("webmaster"));
					mail.setSmtpServer(smtpServer);

					mail.setContentType("txt");
					mail.setSubject(messageResources
							.getMessage("password_reminder_subject"));
					mail.setTo(user.getEmail());
					mail.setMessage(messageResources
							.getMessage("password_reminder_message")
							+ " " + user.getPassword());

					mail.send();

					req.setAttribute("sucesso", messageResources
							.getMessage("password_sent"));
				}

				return mapping.findForward("password_reminder_out");

			} catch (Exception e) {
				req.setAttribute("error", e.getMessage());
				return mapping.findForward("password_reminder_out");
			}
		} else {
			req.setAttribute("error", "Por favor, digite seu email");
			return mapping.findForward("password_reminder_out");
		}

	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		BlogUserDAO dao = (BlogUserDAO) DaoFactory
				.getInstance(Constants.MAPPING_BLOGGERUSER_DAO);
		Collection list = null;
		MessageResources messageResources = null;
		try {

			BlogUserForm userForm = (BlogUserForm) form;

			messageResources = getResources(req);

			list = dao.searchUsersByGroup(Constants.USER_BLOGGER, userForm
					.getFirstName(), userForm.getLastName(), userForm
					.getEmail());
		
			
			req.setAttribute("users", list);
			messageResources = getResources(req);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(Constants.USER_LIST_FORWARD);

	}

}