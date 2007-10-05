/*
 * Created on 05/03/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vanguarda.blog.bean.AdminUser;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.LoggerUtil;

/**   
 * @author Fabio

 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LoginAdminVerifyFilter implements Filter{
	
	public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
    {

    	LoggerUtil.debug("FILTRANDO USUARIO NO ADMIN DE SUPER-USUARIO");
    	
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(true);
        
        AdminUser login  = (AdminUser) session.getAttribute(Constants.ADMIN_USER_BEAN);
         

       if(login !=  null)
        {
       		LoggerUtil.debug(" FILTRANDO USUARIO "+ login.getFirstName() + " NO ADMIN DE SUPER-USUARIO");
            chain.doFilter(req, resp);
        }        
        else
        {       
        	LoggerUtil.debug("FILTRANDO USUARIO NÃO AUTENTICADO ENTRANDO NO ADMIN DE SUPER-USUARIO, REDIRECIONANDO PARA TELA DE LOGIN");
            request.getRequestDispatcher("/gerenciador.jsp").forward(request, response);
        }

    }

    public void destroy()
    {
    }


}
