// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommentAction.java

package com.vanguarda.blog.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.Comment;
import com.vanguarda.blog.bean.Post;
import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.dao.CommentsDAO;
import com.vanguarda.blog.dao.DaoFactory;
import com.vanguarda.blog.form.CommentForm;
import com.vanguarda.blog.helper.PreparedCommentHelper;
import com.vanguarda.blog.util.CacheManager;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.RandomTool;

public class CommentAction extends DispatchAction
{

    public CommentAction()
    {
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp)
        throws Exception
    {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        CommentForm cForm = (CommentForm)form;
        MessageResources messageResources = null;
        
        String word = cForm.getImageword();
		String encryptWord = req.getParameter("wordEnc");
				
		String url="";		
        
        try
        {
            String path = cForm.getPath();   
            
            if(BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY)!= null && ((String)BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY)).length() > 0)
			{
				path = (String) BlogManager.getInstance().getProperties().get(Constants.ROOTPATH_PROPERTY) + path;
			}
			else
			{
				path = req.getRealPath("/blogs/" + path);
			}            
           
            messageResources = getResources(req);
            Comment c = new Comment();
            Post post = new Post();
            post.setId(cForm.getPostId());
            post.setCountComments(cForm.getCountComments());
            c.setPost(post);
            String content = null;
            
            if(!RandomTool.compare(word,encryptWord)){
    			
    			req.setAttribute("message","Entre com o valor corretamente" );
    			return new ActionForward("/blogs/content/add_comment_in.jsp?postId=" + post.getId() + "&countComments=" + post.getComments()+"");

    		}
            
            try
            {
                content = BlogManager.getInstance().getDeniedWords(path);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            if(content != null && content.length() > 0)
            {
                String palavroes[] = content.split(";");
                c.setContent(PreparedCommentHelper.getPreparedCommwnt(cForm.getContent(), palavroes));
            } else
            {
                c.setContent(cForm.getContent());
            }
            c.setCommentatorName(cForm.getCommentatorName());
            c.setCommentatorEmail(cForm.getCommentatorEmail());
            c.setCommentatorHomepage(cForm.getCommentatorHomepage());
            c.setCommentatorRemoteAddr(req.getRemoteAddr());
            c.setStatus(cForm.getStatus());
            c.setCensured(cForm.isCensured());
            if(user != null)
                c.setUser(user);
            dao.add(c);            
            
            CacheManager.getInstance().pushComments(c);
            CacheManager.getInstance().remoteCommentsClearCache(post.getId());
            
            
            if("admin".equals(cForm.getFrom()))
                return mapping.findForward("comment_add");
            
            if(c.getStatus() == 0)
                req.setAttribute("comment_sucesso", messageResources.getMessage("comment_sucesso2"));
            else
                req.setAttribute("comment_sucesso", messageResources.getMessage("comment_sucesso1"));
            
            
            return new ActionForward("/blogs/content/add_comment_in.jsp?postId=" + post.getId() + "&countComments=" + post.getComments()+"");
            
           
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            req.setAttribute("mensagem_erro", e.getMessage());
        }
        if("admin".equals(cForm.getFrom()))
            return mapping.findForward("comment_add");
        else
            return new ActionForward("/blogs/content/error.jsp");
    }

    public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp)
        throws Exception
    {
        String id = req.getParameter("id");
        if(id != null)
            try
            {
                Comment comment = dao.load(Integer.parseInt(id));
                req.setAttribute("comment", comment);
                return mapping.findForward("comment_load");
            }
            catch(Exception e)
            {
            	e.printStackTrace();
                return mapping.findForward("");
            }
        else
            return mapping.findForward("");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp)
        throws Exception
    {
        HttpSession session = req.getSession();
        BlogUser user = (BlogUser)session.getAttribute("blogUser");
        MessageResources messageResources = null;
        try
        {
            messageResources = getResources(req);
            CommentForm cForm = (CommentForm)form;
            Comment c = new Comment();
            Post post = new Post();
            post.setId(cForm.getPostId());
            post.setCountComments(cForm.getCountComments());
            c.setPost(post);
            c.setId(cForm.getId());
            c.setContent(cForm.getContent());
            c.setCommentatorName(cForm.getCommentatorName());
            c.setCommentatorEmail(cForm.getCommentatorEmail());
            c.setCommentatorHomepage(cForm.getCommentatorHomepage());
            c.setCommentatorRemoteAddr(req.getRemoteAddr());
            c.setStatus(cForm.getStatus());
            c.setCensured(cForm.isCensured());
            dao.update(c);
                        
            CacheManager.getInstance().clear();
            return mapping.findForward("comment_add");
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            req.setAttribute("mensagem_erro", e.getMessage());
        }
        return mapping.findForward("comment_add");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp)
        throws Exception
    {
        MessageResources messageResources = null;
        try
        {
            messageResources = getResources(req);
            Comment comment = new Comment();
            CommentForm commentForm = (CommentForm)form;
            String postId = req.getParameter("postId");
            comment.setId(commentForm.getId());
            dao.delete(comment);
            
            Properties prop = new Properties();
            InputStream stream = PostAction.class.getClassLoader().getResourceAsStream("resource.properties");
            prop.load(stream);
            String host = prop.getProperty("host");
                       
            
            CacheManager.getInstance().clear();
            return new ActionForward("/blogs/admin/comment.do?act=list&postId" + postId);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            req.setAttribute("mensagem_erro", e.getMessage());
        }
        return mapping.findForward("");
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp)
        throws Exception
    {
    	
    	try
        {
            Post post = new Post();
            post.setId(Integer.parseInt(req.getParameter("postId")));
                       
            Collection comments = dao.listCommentsByPost(-1, post);
            if(comments != null && comments.size() > 0)
            {
                ArrayList lista = (ArrayList)comments;
                Comment com = (Comment)lista.get(0);
                post = com.getPost();
            }
            req.setAttribute("comments", comments);
            req.setAttribute("p", post);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return mapping.findForward("comment_list");
    }

    private static CommentsDAO dao;

    static 
    {
        if(dao == null)
            dao = (CommentsDAO)DaoFactory.getInstance("COMMENTSDAO");
    }
}
