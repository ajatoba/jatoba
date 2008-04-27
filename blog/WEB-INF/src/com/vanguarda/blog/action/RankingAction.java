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


import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.dao.RakingDAO;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.dao.*;


public class RankingAction extends DispatchAction {

		
	public ActionForward list(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
				
		try {	
			
			String days = req.getParameter("dias");
			int daysInt = 0; // valor padrào é 30. Listar o ranking dos ultimos 30 dias.
			
			if(days != null)
			{
				try {
					daysInt = Integer.parseInt(days);
				} catch (NumberFormatException nfe) {
					daysInt = 0;
				}
			}
			
			
					
			ArrayList parameters = new ArrayList();    
			parameters.add(new Integer(0));
			
        	//Collection ranking = (Collection) CacheManager.getInstance().hitCache(DaoFactory.getInstance("RANKINGDAO"),"getRanking",parameters);
			RakingDAO dao = (RakingDAO) DaoFactory.getInstance("RANKINGDAO");
			Collection ranking = dao.getRanking(daysInt);
			
			req.setAttribute("ranking", ranking);
			
			
		} catch (Exception e) {
			throw e;
		}
				
		return mapping.findForward(Constants.RANKING_FORWARD);

	}
	
}
