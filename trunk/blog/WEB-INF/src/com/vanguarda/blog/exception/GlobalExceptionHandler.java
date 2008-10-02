package com.vanguarda.blog.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;


/**
 * GlobalExceptionHandler
 */
public class GlobalExceptionHandler extends ExceptionHandler
{
	public ActionForward execute(Exception exc, ExceptionConfig arg1, ActionMapping mapping, ActionForm arg3, HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		exc.printStackTrace();
		req.setAttribute("mensagem_erro", exc.getMessage());
		return mapping.findForward("error");
	}
}
