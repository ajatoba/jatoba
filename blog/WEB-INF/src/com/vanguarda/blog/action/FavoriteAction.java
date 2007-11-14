package com.vanguarda.blog.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.dao.BlogDAO;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.dao.FavoriteDAO;
import com.vanguarda.blog.util.CacheManager;
import com.vanguarda.blog.util.Constants;

public class FavoriteAction extends DispatchAction {

	private static FavoriteDAO dao;

	private static BlogDAO blogDao;

	static {

		if (dao == null) {
			dao = (FavoriteDAO) DaoFactory
					.getInstance(Constants.MAPPING_FAVORITE_DAO);
		}

		if (blogDao == null) {
			blogDao = (BlogDAO) DaoFactory
					.getInstance(Constants.MAPPING_BLOG_DAO);
		}
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		try {
			Collection blogs = blogDao
					.listBlogsToFavorite(Constants.STATUS_ALL);

			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("blogUser");
			
			if(user == null)
			{
				User userSession = (User) session.getAttribute("userAdmin");
				user =userSession.getBlog().getBloggerUser();
			}
			
			Collection favorites = (Collection) dao.getFavorites(user.getId());

			req.setAttribute("favorites", favorites);

			req.setAttribute("blogs", blogs);
			
			
		} catch (Exception e) {
			
			
			req.setAttribute("error", e.getMessage());			
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new ActionForward("/blogs/admin/favoritos/list_favoritos.jsp");

		}

		//return mapping.findForward("favorite_list");

		return new ActionForward("/blogs/admin/favoritos/list_favoritos.jsp");

	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MessageResources messageResources = null;
		HttpSession session = req.getSession();
		BlogUser user = (BlogUser) session.getAttribute("blogUser");
		
		if(user == null)
		{
			User userSession = (User) session.getAttribute("userAdmin");
			user =userSession.getBlog().getBloggerUser();
		}


		try {
			messageResources = getResources(req);

			dao.add(req.getParameterValues("favorites"), user);

			CacheManager.getInstance().remoteFavoritesClearCache(user.getId());
			req.setAttribute("sucesso", messageResources
					.getMessage("favorite_sucess"));
			return new ActionForward("/blogs/admin/favorites.do?act=list");

		} catch (Exception e) {

			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
			return new ActionForward("/blogs/admin/favorites.do?act=list");

		}

	}

	public ActionForward listFavorites(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		try {

			ArrayList parameters = new ArrayList();
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("blogUser");
			
			if(user == null)
			{
				User userSession = (User) session.getAttribute("userAdmin");
				user =userSession.getBlog().getBloggerUser();
			}
			
			parameters.add(new Integer(user.getId()));

			Collection ranking = (Collection) CacheManager.getInstance()
					.hitCache(DaoFactory.getInstance("FAVORITEDAO"),
							"getFavorites", parameters);

			req.setAttribute("favorites", ranking);

		} catch (Exception e) {
			//throw e;
			req.setAttribute("error", e.getMessage());
		}

		return mapping.findForward(Constants.RANKING_FORWARD);

	}
}
