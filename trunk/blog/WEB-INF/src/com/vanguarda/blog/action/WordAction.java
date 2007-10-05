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
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.util.Constants;

public class WordAction extends DispatchAction {

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		try {

			String path = req.getParameter("path");
			
			if(path == null || path.length() == 0)
			{
				HttpSession session = req.getSession();
				path = (String) session.getAttribute("path");
			}
			
			if(BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY)!= null && ((String)BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY)).length() > 0)
			{
				path = (String) BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY) + path;
			}
			else
			{
				path = req.getRealPath("/blogs/" + path);
			}
			
			String content = req.getParameter("content");		
			
			BlogManager.getInstance().setDeniedWords(content,path);

			req.setAttribute("content", content);

		} catch (Exception e) {

			req.setAttribute("error", e.getMessage());
		}
		
		return mapping.findForward("sucess");

	}

	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		try {
			String path = req.getParameter("path");
			
			if(path == null || path.length() == 0)
			{
				HttpSession session = req.getSession();
				path = (String) session.getAttribute("path");
			}
			
			if(BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY)!= null && ((String)BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY)).length() > 0)
			{
				path = (String) BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY) + path;
			}
			else
			{
				path = req.getRealPath("/blogs/" + path);
			}
						
			String content = BlogManager.getInstance().getDeniedWords(path);

			req.setAttribute("content", content);
		} catch (Exception e) {

			req.setAttribute("error", e.getMessage());
		}

		return mapping.findForward("load");

	}

}
