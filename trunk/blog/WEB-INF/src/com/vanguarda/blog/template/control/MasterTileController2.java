package com.vanguarda.blog.template.control;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.ControllerSupport;

import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.bean.Post;

/**
 * <p>A simple tile-controller that puts a string into the request scope.
*     Check out the tiles-defs to see the definition that uses the controller</p>
 *
 * @author <a href="mailto:marinoj@centrum.is"/>Marin√≥ A. J√≥nsson</a>
 * @version $Id: MyTileController.java 477914 2006-11-21 21:52:11Z henning $
 */

public class MasterTileController2 extends ControllerSupport {

    public MasterTileController2() {
    }

    /**
     * Method associated to a tile and called immediately before the tile
     * is included.
     * @param tileContext Current tile context.
     * @param request Current request
     * @param response Current response
     * @param servletContext Current servlet context
     */
    public void execute(ComponentContext tileContext,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
        throws ServletException, IOException, Exception {
    	
    /*	//TESTE//
    	BlogUser u = new BlogUser();
    	u.setEmail("jatoba@jatoba.org");
    	u.setFirstName("Alessandro");
    	u.setLastName("Jatob·");
    	
    	Post p1 = new Post();
    	p1.setContent("Esse post È somente de teste");
    	p1.setCountComments(1);
    	p1.setId(1);
    	p1.setInsertDate(new Date());
    	p1.setTitle("teste 1");
    	
    	Post p2 = new Post();
    	p2.setContent("Esse post È somente de teste 2");
    	p2.setCountComments(1);
    	p2.setId(2);
    	p2.setInsertDate(new Date());
    	p2.setTitle("teste 2");
    	
    	ArrayList l = new ArrayList();
    	l.add(p1);
    	l.add(p2);
    	
    	Blog b = new Blog();
    	
    	b.setDescription("Blog de Teste");
    	b.setBlogUser(u);
    	b.setId(10);
    	b.setInsertDate(new Date());
    	b.setName("Blog do Jatob·");
    	b.setStatus(1);
    	b.setPosts(l);
    	
    	request.setAttribute("blog",b);
    	 */ 	
    	
    	Blog blog = (Blog)request.getAttribute("blog");
    	
        request.setAttribute("blogOwner", blog.getBloggerUser().getFirstName());
        request.setAttribute("blogTitle", blog.getName());
        request.setAttribute("blogInsertDate", blog.getInsertDate().getDate() + "/" + ((blog.getInsertDate().getMonth())+1) + "/" + ((blog.getInsertDate().getYear())+1900));
        request.setAttribute("blogDescription", blog.getDescription());
        request.setAttribute("blogId", String.valueOf(blog.getId()));
        request.setAttribute("blogStatus",String.valueOf(blog.getStatus()));
        request.setAttribute("blogPath",String.valueOf(blog.getPath()));
       
        String posts = "";        
       
        //String templateFile = "D:\\jatoba\\projetos\\Java\\bloglog\\pages\\post_template.html";
        String templateFile = request.getRealPath("/template1/post_template.html");
        	
        String template = "";
        
        ArrayList postList  = (ArrayList)blog.getPosts();
        Post p = null;
               
        if(postList != null && postList.size() > 0)
        {
	        for (int x=0;x<postList.size();x++){
	        	p = (Post)postList.get(x);
	        	
	        	template = HTMLReader.readHTML(templateFile);
				template = template.replaceAll("<POST_TITLE>", p.getTitle());
				template = template.replaceAll("<POST_CONTENT>", p.getContent());
				//template = template.replaceAll("<POST_COUNT_COMMENTS>", String.valueOf(p.getCountComments()));
				template = template.replaceAll("<POST_INSERT_DATE>",p.getInsertDate().getDate() + "/" + ((p.getInsertDate().getMonth())+1) + "/" + ((p.getInsertDate().getYear())+1900));			
				template = template.replaceAll("<CALL_COMMENTS>","<a href=\"#\" onClick=\"window.open('/blog/post.do?act=loadSite&id="+p.getId()+"','','scrollbars=yes,height=600,width=600')\">Ver coment·rios("+String.valueOf(p.getCountComments())+")</a><br><center>---------------</center>");
				
				
				posts += template;
			}  
        }
        
        if(request.getAttribute("post")!= null)
        {
        	Post post = (Post)request.getAttribute("post");
        	template = HTMLReader.readHTML(templateFile);
        	template = template.replaceAll("<POST_TITLE>", post.getTitle());
			template = template.replaceAll("<POST_CONTENT>", post.getContent());
			int status = post.isControll()?1:0;
			template = template.replaceAll("<CALL_ADD_COMMENTS>", "<br><a href=\"#\" onClick=\"window.open('/blog/blogs/content/add_comment_in.jsp?postId="+post.getId()+"&countComments="+post.getCountComments()+"&status="+status+"&path="+blog.getPath()+"','','scrollbars=no,height=500,width=500')\">Adicione seu coment·rio</a>");
		template = template.replaceAll("<COMMENTS_SEPARATOR>", "<br><br><font color=\"red\"><b>Coment·rios:</b></font>");
			
			
						
			posts += template;
//        	ArrayList comments = (ArrayList) post.getComments();
//        	Comment c = null;
//        	if(comments != null && comments.size() > 0)
//            {
//    	        for (int x=0;x<comments.size();x++){
//    	        	c = (Comment)comments.get(x);
//    	        	
//    	        	template = HTMLReader.readHTML(templateFile);
//    	        	
//    				template = template.replaceAll("<COMMENT_CONTENT>",  c.isCensured()?"<br><br>Coment·rio censurado":"<br><br>"+c.getContent());
//    				StringBuffer buffer = new StringBuffer();
//    				buffer.append("<br>Enviado por <b>");
//    				buffer.append(c.getCommentatorName());
//    				buffer.append("</b><br>");
//    				buffer.append("<a href=\"#\" onClick=\"window.open('/blog/blogs/content/add_denuncia_in.jsp?postId="+post.getId()+"&commentId="+c.getId()+"','','scrollbars=no,height=500,width=500')\">Denuncie</a>");
//    				
//    				template = template.replaceAll("<COMMENT_USER>",buffer.toString());
//    				
//    				posts += template;
//    			}  
//            }    	        	
        }
        
        request.setAttribute("blogPosts", posts);
    }

}