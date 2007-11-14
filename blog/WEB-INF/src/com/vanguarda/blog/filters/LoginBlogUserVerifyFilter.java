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
import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.LoggerUtil;
  
/** 
 * @author Fabio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LoginBlogUserVerifyFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
    {

    	LoggerUtil.debug("USUARIO ENTRANDO NO ADMIN DE BLOGS");
    	
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(true);
        
                
        User blogUser = (User) session.getAttribute(Constants.BLOGGER_USER_BEAN);
        
        if(blogUser == null)
        {
        	blogUser =  (User) session.getAttribute(Constants.ADMIN_USER_BEAN);
        }
        
          

       if(blogUser !=  null && (blogUser instanceof BlogUser || blogUser instanceof AdminUser))
        {
       	    LoggerUtil.debug(" FILTRANDO USUARIO "+ blogUser + " NO ADMIN DE BLOGS");
            chain.doFilter(req, resp);
        }        
        else
        {       
        	LoggerUtil.debug("FILTRANDO USUARIO NÃO AUTENTICADO ENTRANDO NO ADMIN DE BLOGS, REDIRECIONANDO PARA TELA DE LOGIN");
            request.getRequestDispatcher("/blogs/gerenciador.jsp").forward(request, response);
        }

    }

    public void destroy()
    {
    }
	
}
