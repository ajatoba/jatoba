<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=iso-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<link rel="stylesheet" href="/blog/admin/includes/css/stylle.css" type="text/css">
</head>

<body>
<%@ include file="/admin/includes/menu.jsp"%>
<br>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>  	
   
  	<br>
  		<html:form method="post" action="/admin/topic.do?act=update">
		<html:hidden property="forum" value="${topic.id}"/>
		<html:hidden property="category" value="${topic.category}"/>
		<html:hidden property="status" value="${topic.status}"/>
		<html:hidden property="id" value="${topic.id}"/>
		
		Título : <html:text property="title" value="${topic.title}"/>
	    <br/>
	    Description : <br>
	    <html:textarea property="description" cols="40" rows="10" value="${topic.description}"/>	    
	    <br/>
	   <html:submit value="Enviar"/>
	</html:form>
  	
  	</td>
  <tr>
</table>


</body>
</html>
