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

import org.apache.struts.tiles.ControllerSupport;
import org.apache.struts.tiles.ComponentContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.*;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.bean.*;

/**
 * <p>A simple tile-controller that puts a string into the request scope.
*     Check out the tiles-defs to see the definition that uses the controller</p>
 *
 * @author <a href="mailto:marinoj@centrum.is"/>Marinó A. Jónsson</a>
 * @version $Id: MyTileController.java 477914 2006-11-21 21:52:11Z henning $
 */

public class MasterTileController extends ControllerSupport {

    public MasterTileController() {
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
    	
    	Blog blog = (Blog)request.getAttribute("blog");
    	Collection posts =  (Collection) request.getAttribute("posts");
    	
    	Boolean busca = null;
    	
    	if(request.getAttribute("search") != null)
    		busca = (Boolean)request.getAttribute("search"); 
    	else
    		busca = new Boolean(false);
    	
    	request.setAttribute("blogOwner", blog.getBloggerUser().getFirstName());
        request.setAttribute("blogTitle", blog.getName());
        request.setAttribute("blogInsertDate", blog.getInsertDate().getDate() + "/" + ((blog.getInsertDate().getMonth())+1) + "/" + ((blog.getInsertDate().getYear())+1900));
        request.setAttribute("blogDescription", blog.getDescription());
        request.setAttribute("blogId", String.valueOf(blog.getId()));
        request.setAttribute("blogStatus",String.valueOf(blog.getStatus()));
        request.setAttribute("blogPath",String.valueOf(blog.getPath()));        
        request.setAttribute("blogPosts", blog.getPosts()!= null?blog.getPosts():posts);
        
        
        
        if(busca.booleanValue()){
        	request.setAttribute("busca", busca);
        	request.setAttribute("qtdResults", String.valueOf(blog.getPosts().size()));
        	request.setAttribute("keyWord", request.getAttribute("keyWord"));        		
        }
        
        String urlRss = "";
        
        if(request.getParameter("act")!= null && request.getParameter("act").equals("loadSite"))
        {
        	String mes = request.getParameter("mes")!= null?request.getParameter("mes"):"";
        	String ano = request.getParameter("ano")!= null?request.getParameter("ano"):"";
        	
        	urlRss = "rss.rss?id="+blog.getId()+"&act=loadSite&mes="+mes+"&ano="+ano;
        }
        else
        {
        	urlRss = "rss.rss?id="+blog.getId();
        }
        request.setAttribute("urlRss",urlRss);
        
        BlogManager.getInstance().setRanking(request);
		BlogManager.getInstance().setHistory(request,String.valueOf(blog.getId()));		
		BlogManager.getInstance().setFavorites(request,blog.getBlogUser().getId());
        
    }

}