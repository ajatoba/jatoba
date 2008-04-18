<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>
<%@ page contentType="text/html;charset=iso-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
   

<title> :: BlogLog :: - <bean:write name="blogUser" property="firstName"/> </title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="/bloglog/content/add_coment/css/add_comentario.css" rel="stylesheet" type="text/css" /> 

</head>

<body>

<script>
    	opener.document.location.reload();
    	</script>
		
<div class="princ">
	<div class="topo"><img src="/bloglog/content/add_coment/img_add/logo.jpg" width="189" height="94" /></div>
	

	<div class="titulos">
		<div class="esq"></div>
		<div class="txt">COMENTÁRIOS</div>
		<div class="dir"></div>
	</div>
		
	<div class="tit_branco">
		<dt class="tp_laranja">Por: <bean:write name="blog" property="name"/></dt>
		<dt class="tp_azul"><bean:write name="post" property="title" /> (<bean:write name="post" property="insertDate" format="dd/MM"/>)</dt>
	</div>
	<div class="posts_top"></div>

	<pg:pager url="post.do" maxIndexPages="5" maxPageItems="100">
      <logic:present name="comments"> 
      	<logic:iterate name="comments" id="c" indexId="i">
      	<pg:item>
			<div class="post">
				<dt>
				
				<c:if test="${c.user.group.id == 2}">				
					<a href="/${c.user.blog.path}" border="0" target="_blank"><img src="/bloglog/img/user_icon.gif" border="0"/></a>
				</c:if>
				<bean:write name="c" property="commentatorName" /> | <bean:write name="c" property="insertDate" format="dd/MM"/> <a href="#"><!--bean:write name="c" property="commentatorEmail" /--></a> |                      
		            <a href="#" onClick="window.open('/blog/blogs/content/add_denuncia_in.jsp?postId=<bean:write name="post" property="id"/>&commentId=<bean:write name="c" property="id" />','','scrollbars=no,height=258,width=500')">Denuncie</a></dt>
				<div><bean:write name="c" property="content"/>
				<logic:notEmpty name="c" property="answer">
					<br/>
					<table  width="100%">
						<tr>
							<td width="10%"></td>
							<td width="90%"><i><b>Resposta de ${blog.blogUser.firstName} ${blog.blogUser.lastName}:</b> <bean:write name="c" property="answer"/> </i></td>
						</tr>
					</table>
				</logic:notEmpty>
				</div>
				
				
			</div>
		    <div class="postsep"></div>
    	</pg:item>
        </logic:iterate>
	</logic:present>       
      
	<p align="center" style="font-size:11px; font-weight:bold; color:blue; font-family:Trebuchet MS;">
	<pg:index>
		<pg:prev>
		<a href="<%=pageUrl%>&act=loadSite&id=${post.id}">[<< Anterior]</a>	
		</pg:prev>
			
		<pg:pages>
			<a href="<%= pageUrl %>&act=loadSite&id=${post.id}"><%= pageNumber %></a>
		</pg:pages>
		
		<pg:next>
			<a href="<%= pageUrl%>&act=loadSite&id=${post.id}">[Próxima >>]</a>
		</pg:next>
	</pg:index>
	</p>
	</pg:pager>


  	<logic:present name="mensagem_erro">
  	 	<font color="red"><bean:write name="mensagem_erro"/></font>
    </logic:present> 
    
    <logic:present name="comment_sucesso">
  		<font color="red"><bean:write name="comment_sucesso"/></font>
    </logic:present>  	
  
       <iframe name=arquivos src="/blog/blogs/content/add_comment_in.jsp?blogId=<bean:write name="blog" property="id"/>&postId=<bean:write name="post" property="id"/>&countComments=<bean:write name="post" property="countComments"/>&status=<bean:write name="status"/>&path=<bean:write name="blog" property="path"/>&message2=<%=request.getParameter("message2")!= null?request.getParameter("message2"):""%>" frameBorder="0" width="580" height="400" scrolling="no" align="center"></iframe>
  	<br>
    	<logic:present name="mensagem_erro">
  	 	<font color="red"><bean:write name="mensagem_erro"/></font>
    </logic:present> 
 
  
</div>	  	

</body>
</html>
