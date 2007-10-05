package com.vanguarda.blog.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;

import com.vanguarda.blog.dao.CommunityDAO;
import com.vanguarda.blog.dao.CommunityDAOFactory;
import com.vanguarda.blog.util.CommunityConstants;

import com.vanguarda.blog.bean.Community;
import com.vanguarda.blog.bean.CommunityUser;
import com.vanguarda.blog.form.CommunityForm;

import java.io.*;

public class CommunityAction extends DispatchAction {

	
		private static CommunityDAO dao;
		
		static{
			
			if(dao == null)
			{
				dao = (CommunityDAO) CommunityDAOFactory.getInstance(CommunityConstants.MAPPING_COMMUNITY_DAO);
			}
		}
		
		public ActionForward addForm(ActionMapping mapping,
				ActionForm form, HttpServletRequest req, HttpServletResponse resp)
				throws Exception {	
					
			
			try {
			
				req.setAttribute("status","1");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
					
			return mapping.findForward("community_form");

		}
		
		
		public ActionForward add(ActionMapping mapping,
				ActionForm form, HttpServletRequest req, HttpServletResponse resp)
				throws Exception {
			
			
			MessageResources messageResources = null;
			try {
				
				messageResources = getResources(req);
				
				Community community = new Community();
				
				CommunityUser owner = new CommunityUser();
				
				CommunityForm communityForm = (CommunityForm) form;

				community.setTitle(communityForm.getTitle());
				community.setDescription(communityForm.getDescription());
				community.setStatus(communityForm.getStatus());
				
				owner.setId(communityForm.getCommunityOwnerId());
				
				community.setCommunityOwner(owner);
								
				int id = dao.add(community);
				
				/**** Subindo a imagem da Comunidade *****/
				
				FormFile imagePath = communityForm.getImagePath();
				
			    String contentType = imagePath.getContentType();
			    
			    if(!"image/jpeg".equals(contentType)){
			    	req.setAttribute("mensagem_erro", messageResources.getMessage("uncompatible_image_format"));
					
					return new ActionForward("/blogs/admin/community.do?act=list");
			    	
			    }
			    
			    String fileName    = id + ".jpg";
			    
			    byte[] fileData    = imagePath.getFileData();
			    
			    String fileRealPath = req.getRealPath("/blogs/content/communities/images/")+File.separator;;
				
			    
			    if(!fileName.equals("")){  
			    	
			    	File fileToCreate = new File(fileRealPath, fileName);
				                              
				    if(!fileToCreate.exists()){
				    	FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
				        fileOutStream.write(imagePath.getFileData());
				        fileOutStream.flush();
				        fileOutStream.close();
				    }
			    }
				req.setAttribute("fileName",fileName);				
				
				/*****************************************/
				
				req.setAttribute("community_sucesso", messageResources.getMessage("add_community_sucesso"));
				
				return new ActionForward("/blogs/admin/community.do?act=list");

						
			} catch (Exception e) {
				req.setAttribute("mensagem_erro", e.getMessage());
				e.printStackTrace();
				return new ActionForward("/blogs/admin/community.do?act=list");
			}

		}
		
		public ActionForward load(ActionMapping mapping,
				ActionForm form, HttpServletRequest req, HttpServletResponse resp)
				throws Exception {

			CommunityForm communityForm = (CommunityForm) form;
			
			
			if(communityForm.getId() > 0){
				try {
					
					Community community = dao.load(communityForm.getId());				
					req.setAttribute(CommunityConstants.COMMUNITY_BEAN,community);
					
					return mapping.findForward(CommunityConstants.COMMUNITY_LOAD_FORWARD);
					
				} catch (Exception e) {
					req.setAttribute("mensagem_erro", e.getMessage());
					return new ActionForward("/blogs/admin/community.do?act=list");
				}
			}else{
				req.setAttribute("mensagem_erro", MessageResources.getMessageResources("load_community_erro"));
				return new ActionForward("/blogs/admin/community.do?act=list");
			}

		}
		
		public ActionForward update(ActionMapping mapping,
				ActionForm form, HttpServletRequest req, HttpServletResponse resp)
				throws Exception {

			
			MessageResources messageResources = null;
			try {
				
				messageResources = getResources(req);
				Community community = new Community();
				
				CommunityForm communityForm = (CommunityForm) form;

				CommunityUser owner = new CommunityUser();
				
				community.setId(communityForm.getId());
				community.setTitle(communityForm.getTitle());
				community.setDescription(communityForm.getDescription());
				community.setStatus(communityForm.getStatus());
				
				owner.setId(communityForm.getCommunityOwnerId());
				
				community.setCommunityOwner(owner);
				
				dao.update(community);
				
				req.setAttribute("community_sucesso", messageResources.getMessage("update_community_sucesso"));
				
				return new ActionForward("/blogs/admin/community.do?act=list");
				
			} catch (Exception e) {

				req.setAttribute("mensagem_erro", e.getMessage());
				return new ActionForward("/blogs/admin/community.do?act=update");

			}

		}
		
		public ActionForward delete(ActionMapping mapping,
				ActionForm form, HttpServletRequest req, HttpServletResponse resp)
				throws Exception {

			
			MessageResources messageResources = null;
			try {
				
				messageResources = getResources(req);
				Community community = new Community();
				CommunityForm communityForm = (CommunityForm) form;
				
				community.setId(communityForm.getId());
				
				dao.delete(community);
				
				req.setAttribute("community_sucesso", messageResources.getMessage("delete_community_sucesso"));
				
				return new ActionForward("/blogs/admin/community.do?act=list");					
				
			} catch (Exception e) {

				req.setAttribute("mensagem_erro", e.getMessage());
				
				return new ActionForward("/blogs/admin/community.do?act=list");
			}

		}

		
		public ActionForward list(ActionMapping mapping,
				ActionForm form, HttpServletRequest req, HttpServletResponse resp)
				throws Exception {

			MessageResources messageResources = null;
			messageResources = getResources(req);
			try {
				messageResources = getResources(req);
				Collection communities = dao.listCommunities(CommunityConstants.STATUS_ALL);
				req.setAttribute("communities",communities);
				
				if(communities.size() == 0) 
					req.setAttribute("no_communities",messageResources.getMessage("no_communities"));
					return mapping.findForward("list_communities");
					
			} catch (Exception e) {
				System.out.println("ERRO : " + e.getMessage());
			}
			
			return mapping.findForward("list_communities");

		}

		public ActionForward updateStatus(ActionMapping mapping,
				ActionForm form, HttpServletRequest req, HttpServletResponse resp)
				throws Exception {

			
			MessageResources messageResources = null;
			try {
				
				messageResources = getResources(req);
				
				CommunityForm communityForm = (CommunityForm) form;
				
				dao.updateStatus(communityForm.getId(),communityForm.getStatus());
				
				req.setAttribute("community_sucesso", messageResources.getMessage("update_community_status_sucesso"));
				
				return new ActionForward("/blogs/admin/community.do?act=list");
				
			} catch (Exception e) {

				req.setAttribute("mensagem_erro", e.getMessage());
				return new ActionForward("/blogs/admin/community.do?act=list");

			}

		}

		
		public ActionForward detail(ActionMapping mapping,
				ActionForm form, HttpServletRequest req, HttpServletResponse resp)
				throws Exception {

			CommunityForm communityForm = (CommunityForm) form;
			
			if(communityForm.getId() > 0){
				try {
					
					System.out.println("DETALHANDO COMUNIDADE " + communityForm.getId());
					
					Community community = dao.load(communityForm.getId());				
					
					req.setAttribute(CommunityConstants.COMMUNITY_BEAN,community);
					
					System.out.println("COMUNIDADE DETALHADA");
					
					return new ActionForward("/bloglog/community_template.do");
					
					
				} catch (Exception e) {
					req.setAttribute("mensagem_erro", e.getMessage());
					return new ActionForward("/bloglog/community_template.do");
					
				}
			}else{
				req.setAttribute("mensagem_erro", MessageResources.getMessageResources("load_community_erro"));
				return new ActionForward("/bloglog/community_template.do");
			}

		}
		
		
	}

