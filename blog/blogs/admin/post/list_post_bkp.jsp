<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
	<link rel="stylesheet" href="/blog/blogs/admin/includes/css/stylle.css" type="text/css">
  </head>
  
  <body>
<%@ include file="/blogs/admin/includes/menu.jsp"%>
<br>

<table border ="0" ><tr><td><a href="post/add_post_in.jsp">[[NOVO]]</a></td></tr></table>
<table width="100%" border="0">
  <tr bgcolor="#FFFFCC"> 
    <td width="25%"><b>Id</b></td>
    <td width="25%"><b>Titulo</b></td>
    <td width="22%"><b>Data</b></td>
    <td width="10%"><b>Status</b></td>
    <td width="10%"><b>Comentarios</b></td>
    <td width="18%"><b></b></td>
  </tr>
  
  <logic:iterate name="posts" id="p" indexId="i">
  <tr>   
    <td width="25%"><bean:write name="p" property="id"/> </td>
    <td width="25%"><bean:write name="p" property="title"/></td>
    <td width="22%"><bean:write name="p" property="insertDate" format="dd/MM/yyyy HH:mm"/></td>
    <td width="10%"><bean:write name="p" property="status"/></td>
    <td width="10%"> <bean:write name="p" property="countComments"/>      
     <a href="/blog/blogs/admin/comment.do?act=list&postId=<bean:write name="p" property="id"/>">[[LISTAR]]</a>
     
    </td>
    <td width="18%"><a href="/blog/blogs/admin/post.do?act=load&id=<bean:write name="p" property="id"/>">[[ALTERAR]]</a> <a href="/blog/blogs/admin/post.do?act=delete&id=<bean:write name="p" property="id"/>">[[DELETAR]]</a></td>
  </tr>
  </logic:iterate>
</table>
</body>
</html>
