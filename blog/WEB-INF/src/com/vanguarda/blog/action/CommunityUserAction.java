package com.vanguarda.blog.action;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;
import com.vanguarda.blog.dao.CommunityUserDAO;
import com.vanguarda.blog.dao.CommunityDAOFactory;
import com.vanguarda.blog.util.CommunityConstants;
import com.vanguarda.blog.util.Constants;

import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.Community;
import com.vanguarda.blog.bean.CommunityUser;
import com.vanguarda.blog.form.CommunityUserForm;
import com.vanguarda.blog.helper.FileHelper;

public class CommunityUserAction extends DispatchAction {

		
			private static CommunityUserDAO dao;
			
			static{				
				if(dao == null)
				{
					dao = (CommunityUserDAO) CommunityDAOFactory.getInstance(CommunityConstants.MAPPING_COMMUNITY_USER_DAO);
				}
			}
		
			
			public ActionForward add(ActionMapping mapping,
					ActionForm form, HttpServletRequest req, HttpServletResponse resp)
					throws Exception {
				
				HttpSession session  = req.getSession();
				MessageResources messageResources = null;
				try {
					
					messageResources = getResources(req);
					
					Community community = new Community();				
					CommunityUser user = new CommunityUser();					
					CommunityUserForm communityUserForm = (CommunityUserForm) form;

					BlogUser bUser = (BlogUser) session.getAttribute(Constants.BLOGGER_USER_BEAN);
					
					community.setId(communityUserForm.getCommunityId());
					user.setId(communityUserForm.getCommunityUserId());

					
					dao.participate(community, user);
					
//					FileHelper.writerStaticFile(req.getRealPath("/blogs/"+bUser.getBlog().getPath()),CommunityConstants.COMMUNITIES_FILE_NAME+".html", "http://"+req.getLocalAddr()+":"+req.getLocalPort()+"/blog/community.do?act=writeCommunities&communityUserId="+user.getId());
					
					req.setAttribute("community_sucesso", messageResources.getMessage("add_community_sucesso"));
					
					if(req.getParameter("origin") !=null && "blog".equals(req.getParameter("origin"))){
						return mapping.findForward("community_user_out");
					}else{		
						return new ActionForward("/blogs/admin/community.do?act=list");
					}

							
				} catch (Exception e) {					
					if(e.getMessage().indexOf("Duplicate entry") >=0){
						req.setAttribute("mensagem_erro", messageResources.getMessage("participant"));					
					}else{					
						req.setAttribute("mensagem_erro", e.getMessage());
					}
					
					if(req.getParameter("origin") !=null && "blog".equals(req.getParameter("origin"))){
						return mapping.findForward("community_user_out");
					}else{		
						return new ActionForward("/blogs/admin/community.do?act=list");
					}
					
					
				}

			}
			
			
			public ActionForward delete(ActionMapping mapping,
					ActionForm form, HttpServletRequest req, HttpServletResponse resp)
					throws Exception {

				HttpSession session  = req.getSession();
				MessageResources messageResources = null;
				try {
					
					messageResources = getResources(req);
					Community community = new Community();
					CommunityUser user = new CommunityUser();
					BlogUser bUser = (BlogUser) session.getAttribute(Constants.BLOGGER_USER_BEAN);
					CommunityUserForm communityUserForm = (CommunityUserForm) form;
					
					community.setId(communityUserForm.getCommunityId());
					user.setId(communityUserForm.getCommunityUserId());
										
					dao.unParticipate(community, user);
					
//					FileHelper.writerStaticFile(req.getRealPath("/blogs/"+bUser.getBlog().getPath()),CommunityConstants.COMMUNITIES_FILE_NAME+".html", "http://"+req.getLocalAddr()+":"+req.getLocalPort()+"/blog/community.do?act=writeCommunities&communityUserId="+user.getId());
					
					req.setAttribute("community_sucesso", messageResources.getMessage("unparticipate_sucesso"));
					
					if(req.getParameter("origin") !=null && "blog".equals(req.getParameter("origin"))){
						return mapping.findForward("community_user_out");
					}else{		
						return new ActionForward("/blogs/admin/communityUser.do?act=list");
					}				
					
				} catch (Exception e) {

					req.setAttribute("mensagem_erro", e.getMessage());
					
					if(req.getParameter("origin") !=null && "blog".equals(req.getParameter("origin"))){
						return mapping.findForward("community_user_out");
					}else{		
						return new ActionForward("/blogs/admin/communityUser.do?act=list");
					}

				}

			}

			
			public ActionForward list(ActionMapping mapping,
					ActionForm form, HttpServletRequest req, HttpServletResponse resp)
					throws Exception {

				MessageResources messageResources = null;
				messageResources = getResources(req);
				
				CommunityUser user = new CommunityUser();
				CommunityUserForm communityUserForm = (CommunityUserForm) form;
				
				try {
					messageResources = getResources(req);
					
					user.setId(communityUserForm.getCommunityUserId()); 
					
					Collection communities = dao.listCommunities(user,CommunityConstants.STATUS_ACTIVE);
					
					req.setAttribute("communities",communities);
					
					if(communities.size() == 0) 
						req.setAttribute("no_user_communities",messageResources.getMessage("no_user_communities"));
						return mapping.findForward("list_user_communities");
						
				} catch (Exception e) {
					System.out.println("ERRO : " + e.getMessage());
				}
				
				return mapping.findForward("list_user_communities");

			}

			public ActionForward writeCommunities(ActionMapping mapping,
					ActionForm form, HttpServletRequest req, HttpServletResponse resp)
					throws Exception {

				MessageResources messageResources = null;
				messageResources = getResources(req);
				
				CommunityUser user = new CommunityUser();
				CommunityUserForm communityUserForm = (CommunityUserForm) form;
				
				try {
					messageResources = getResources(req);
					
					user.setId(communityUserForm.getCommunityUserId()); 
					
					Collection communities = dao.listCommunities(user,CommunityConstants.STATUS_ACTIVE);
					
					req.setAttribute("communities",communities);
					
					if(communities.size() == 0) 
						req.setAttribute("no_user_communities",messageResources.getMessage("no_user_communities"));
						return mapping.findForward("show_user_communities");
						
				} catch (Exception e) {
					System.out.println("ERRO : " + e.getMessage());
				}
				
				return mapping.findForward("show_user_communities");

			}

			
		}

