package com.vanguarda.blog.template.control;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.ControllerSupport;
import com.vanguarda.blog.bean.Blog;

import com.vanguarda.blog.bean.Community;
import com.vanguarda.blog.dao.CommunityDAO;
import com.vanguarda.blog.dao.BlogDAO;
import com.vanguarda.blog.dao.CommunityDAOFactory;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.util.CommunityConstants;
import com.vanguarda.blog.util.Constants;

public class CommunityTileController extends ControllerSupport {

	private static CommunityDAO dao;
	private static BlogDAO blogDao;
	
	static{
		
		if(dao == null)
		{
			dao = (CommunityDAO) CommunityDAOFactory.getInstance(CommunityConstants.MAPPING_COMMUNITY_DAO);
		}
		
		if(blogDao == null)
		{
			blogDao = (BlogDAO) DaoFactory.getInstance(Constants.MAPPING_BLOG_DAO);
		}
	}
	public void execute(ComponentContext tileContext,
            HttpServletRequest request,
            HttpServletResponse response,
            ServletContext servletContext)
	throws ServletException, IOException, Exception {
	
		Community community = (Community)request.getAttribute("community");
		Blog blog = community.getBlog();
		
		request.setAttribute("community",community);	
		request.setAttribute("blogOwner", blog.getBloggerUser().getFirstName());
		request.setAttribute("blogOwnerId", String.valueOf(blog.getBloggerUser().getId()));
        request.setAttribute("blogTitle", blog.getName());
        request.setAttribute("blogInsertDate", blog.getInsertDate().getDate() + "/" + ((blog.getInsertDate().getMonth())+1) + "/" + ((blog.getInsertDate().getYear())+1900));
        request.setAttribute("blogDescription", blog.getDescription());
        request.setAttribute("blogId", String.valueOf(blog.getId()));
        request.setAttribute("blogStatus",String.valueOf(blog.getStatus()));
        request.setAttribute("blogPath",String.valueOf(blog.getPath()));
       
		
		
	}
}
