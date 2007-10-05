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

import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.util.Constants;

public class LoginCommunityUserVerifyFilter implements Filter {

		public void init(FilterConfig filterConfig) throws ServletException
	    {
	    }  

	    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
	    {

	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) resp;
	        HttpSession session = request.getSession(true);
	        
	        BlogUser blogUser = (BlogUser) session.getAttribute(Constants.BLOGGER_USER_BEAN);
	        
	        String destination = request.getRequestURI() + "?act="+ request.getParameter("act") + "&id=" + request.getParameter("id");
	        
	        request.setAttribute("returnPath", destination.replaceAll("/blog",""));
	       if(blogUser !=  null)
	        {
	            chain.doFilter(req, resp);
	        }        
	        else
	        {            
	            request.getRequestDispatcher("/blogs/blogger_login.jsp").forward(request, response);
	        }

	    }

	    public void destroy()
	    {
	    }
		
	}
