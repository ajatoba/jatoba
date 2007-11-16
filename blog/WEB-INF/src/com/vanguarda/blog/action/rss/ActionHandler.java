/*
 * Created on 23/10/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.action.rss;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import com.vanguarda.blog.action.rss.parameters.ActionHandlerParameters;

/**
 * @author Fabio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class ActionHandler
{

	public abstract void handle(ActionHandlerParameters parameters, PrintWriter out) throws Exception;
    
    public abstract ActionHandlerParameters createParameters(HttpServletRequest request);
	
}
