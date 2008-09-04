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
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.vanguarda.blog.bean.History;
import com.vanguarda.blog.dao.BlogDAO;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.DataUtil;


public class HistoryAction extends DispatchAction {

	
	private static BlogDAO dao;

	static {

		if (dao == null) {
			dao = (BlogDAO) DaoFactory.getInstance(Constants.MAPPING_BLOG_DAO);
		}

	}	
	
	
	public ActionForward history(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
		
		String id = req.getParameter("id");
		int blogId = Integer.parseInt(id);
		
		Map historys = dao.getHistory(blogId);
		
		Collection arquivo = new ArrayList();
		

		Date today = new Date();
		int month = today.getMonth();
		int year  = today.getYear() + 1900;
		
		String key = "";
		
		//Mostra os arquivos dos últimos 36 meses, conforme requisitos
		for(int i = 0; i < 36; i++ )
		{
			History history = new History();
			
			if(month == 0)
			{
				month = 12;
				year = year - 1;
			}
			
			DataUtil d = new DataUtil(1,month,year,0,0);			
						
			key = String.valueOf(month)+String.valueOf(year);
			
			
			if(historys.get(key)!= null)
			{
				history.setPostCount((Integer)historys.get(key));
			}
			
			history.setBlogID(blogId);
			history.setLabelDate(d.nomeMes()+"/"+year);
			history.setDate(d.toDate());
			
			arquivo.add(history);
			
			month --;
		}
		
		req.setAttribute("history",arquivo);
		return mapping.findForward(Constants.HISTORY_FORWARD);

	}
	
}
