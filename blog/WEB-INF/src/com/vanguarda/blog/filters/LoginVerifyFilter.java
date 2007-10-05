package com.vanguarda.blog.filters;

import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.util.LoggerUtil;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginVerifyFilter
    implements Filter
{

    public LoginVerifyFilter()
    {
    }

    public void init(FilterConfig filterconfig)
        throws ServletException
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
        throws IOException, ServletException
    {
    	LoggerUtil.debug("FILTRANDO USUARIO NA TELA DE COMENTARIO");
    	
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession(true);
        User user = null;
        if(session.getAttribute("user") != null)
            user = (User)session.getAttribute("user");
        else
        if(session.getAttribute("blogUser") != null)
            user = (User)session.getAttribute("blogUser");
        else
        if(session.getAttribute("userAdmin") != null)
            user = (User)session.getAttribute("userAdmin");
        if(user != null)
        {
        	LoggerUtil.debug(" FILTRANDO USUARIO "+ user.getFirstName() + " NA TELA DE COMENTARIO");
            chain.doFilter(req, resp);
        } else
        {
            Map map = request.getParameterMap();
            String parameter;
            for(Iterator iterator = map.keySet().iterator(); iterator.hasNext(); request.setAttribute(parameter, req.getParameter(parameter)))
                parameter = (String)iterator.next();

            LoggerUtil.debug("FILTRANDO USUARIO NÃO AUTENTICADO ENTRANDO NA TELA DE COMENTARIO, REDIRECIONANDO PARA TELA DE LOGIN");
            request.getRequestDispatcher("/blogs/content/login.jsp").forward(request, response);
        }
    }

    public void destroy()
    {
    }
}
