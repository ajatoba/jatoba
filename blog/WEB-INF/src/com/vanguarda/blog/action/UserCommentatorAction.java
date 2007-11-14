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
import java.util.Iterator;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

//import com.sun.corba.se.internal.core.Request;
import com.vanguarda.blog.bean.Group;
import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.bean.UserCommentator;
import com.vanguarda.blog.dao.BlogUserDAO;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.dao.UserCommentatorDAO;
import com.vanguarda.blog.exception.EmailExistsException;
import com.vanguarda.blog.exception.InvalidPasswordException;
import com.vanguarda.blog.exception.LoginExistsException;
import com.vanguarda.blog.exception.LoginNotExistsException;
import com.vanguarda.blog.exception.UserBlockedException;
import com.vanguarda.blog.form.UserCommentatorForm;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.RandomTool;

public class UserCommentatorAction extends DispatchAction {

	//private static ArrayList tabela;

	private static UserCommentatorDAO dao;

	static {

		if (dao == null) {
			dao = (UserCommentatorDAO) DaoFactory
					.getInstance(Constants.MAPPING_USERCOMMENTATOR_DAO);
		}

	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MessageResources messageResources = getResources(req);

		UserCommentatorForm userForm = (UserCommentatorForm) form;

		User user = null;

		try {

			user = (User) dao
					.login(userForm.getLogin(), userForm.getPassword());

			HttpSession session = req.getSession();
			session.setAttribute(Constants.USER_BEAN, user);

			Map map = req.getParameterMap();
			Iterator iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String parameter = (String) iterator.next();
				req.setAttribute(parameter, req.getParameter(parameter));
			}

		} catch (InvalidPasswordException ip) {

			req.setAttribute("mensagem_erro", messageResources
					.getMessage("senha_incorreta"));
			return mapping.findForward(Constants.BLOGUSER_LOGIN_ERROR_FORWARD);

		} catch (LoginNotExistsException lne) {

			req.setAttribute("mensagem_erro", messageResources
					.getMessage("login_inexistente"));
			return mapping.findForward(Constants.BLOGUSER_LOGIN_ERROR_FORWARD);

		} catch (UserBlockedException ube) {
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("login_bloqueado"));
			return mapping.findForward(Constants.BLOGUSER_LOGIN_ERROR_FORWARD);

		} catch (ClassCastException e) {

			req.setAttribute("mensagem_erro", messageResources
					.getMessage("permissao"));
			return mapping.findForward(Constants.BLOGUSER_LOGIN_ERROR_FORWARD);

		} catch (Exception e) {

			req.setAttribute("mensagem_erro", e.getMessage());
			return mapping.findForward(Constants.BLOGUSER_LOGIN_ERROR_FORWARD);
		}

		return mapping.findForward(Constants.LOGIN_FORWARD);

	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MessageResources messageResources = null;
		String origin = req.getParameter("from");
		try {

			messageResources = getResources(req);
			UserCommentator user = new UserCommentator();
			UserCommentatorForm userForm = (UserCommentatorForm) form;

			String word = userForm.getImageword();
			String encryptWord = req.getParameter("wordEnc");

			if (!RandomTool.compare(word, encryptWord)) {

				req.setAttribute("mensagem_imagem_incorreta",
						"Entre com o valor da imagem corretamente");

				if ("home".equals(origin))
					return mapping.findForward("add_user_home_in");
				else
					return mapping.findForward("add_user_error");

			}

			user.setFirstName(userForm.getFirstName());
			user.setEmail(userForm.getEmail());
			user.setGroup(new Group(Constants.USER_BLOGGER));
			user.setLastName(userForm.getLastName());
			user.setLogin(userForm.getLogin());
			user.setPassword(userForm.getPassword());
			user.setStatus(1);
			user.setBirthDate(new Date(userForm.getAno() - 1900, userForm
					.getMes() - 1, userForm.getDia()));
			user.setGender(userForm.getGender());
			user.setState(userForm.getState());
			user.setCity(userForm.getCity());

			dao.add(user);
			HttpSession session = req.getSession();
			session.setAttribute(Constants.USER_BEAN, user);

			if ("home".equals(origin))
				return mapping.findForward("add_user_out");
			else
				return mapping.findForward(Constants.USER_ADD_FORWARD);

		} catch (LoginExistsException lee) {
			lee.printStackTrace();
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("login_existente"));

			if ("home".equals(origin))
				return mapping.findForward("add_user_home_in");
			else
				return mapping.findForward("add_user_error");

		} catch (EmailExistsException eee) {
			eee.printStackTrace();
			req.setAttribute("mensagem_erro", messageResources
					.getMessage("email_existente"));
			if ("home".equals(origin))
				return mapping.findForward("add_user_home_in");
			else
				return mapping.findForward("add_user_error");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("mensagem_erro", e.getMessage());

			if ("home".equals(origin))
				return mapping.findForward("add_user_home_in");
			else
				return mapping.findForward("add_user_error");

		}

	}

	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String id = req.getParameter("id");

		if (id != null) {
			try {

				UserCommentator user = dao.load(Integer.parseInt(id));
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
			UserCommentator user = new UserCommentator();
			UserCommentatorForm userForm = (UserCommentatorForm) form;

			user.setId(userForm.getId());
			user.setFirstName(userForm.getFirstName());
			user.setEmail(userForm.getEmail());
			user.setGroup(new Group(Constants.USER_COMENTATOR));
			user.setLastName(userForm.getLastName());
			user.setLogin(userForm.getLogin());
			user.setPassword(userForm.getPassword());
			user.setStatus(1);

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

	public ActionForward listCommentador(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		BlogUserDAO dao = (BlogUserDAO) DaoFactory.getInstance(Constants.MAPPING_BLOGGERUSER_DAO);
		Collection list = null;
		MessageResources messageResources = null;
		try {

			messageResources = getResources(req);

			list = dao.listUsersByGroup(Constants.STATUS_ALL,
					Constants.USER_COMENTATOR);

			int countUsers = dao.countUser(Constants.USER_COMENTATOR);
			req.setAttribute("countUsers", String.valueOf(countUsers));
			
			req.setAttribute("users", list);
			messageResources = getResources(req);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(Constants.USER_LIST_FORWARD);

	}
	public ActionForward blockUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
		try {
			
			String userId = req.getParameter("userId");
			String action  = req.getParameter("action");
			
			dao.block(Integer.parseInt(userId), Integer.parseInt(action));
			
					
			
			return listCommentador(mapping,
					 form, req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	
	public ActionForward search(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		BlogUserDAO dao = (BlogUserDAO) DaoFactory.getInstance(Constants.MAPPING_BLOGGERUSER_DAO);
		Collection list = null;
		MessageResources messageResources = null;
		try {
			
			
			UserCommentatorForm userCommentatorForm = (UserCommentatorForm) form;

			messageResources = getResources(req);

			list = dao.searchUsersByGroup(Constants.USER_COMENTATOR,userCommentatorForm.getFirstName(),userCommentatorForm.getLastName(),userCommentatorForm.getEmail());
			int countUsers = dao.countUser(Constants.USER_COMENTATOR);
			req.setAttribute("countUsers", String.valueOf(countUsers));
			
			req.setAttribute("users", list);
			
			messageResources = getResources(req);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(Constants.USER_LIST_FORWARD);

	}
	

}
