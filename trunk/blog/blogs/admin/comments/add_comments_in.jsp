<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"> 
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<html:html>
<head>
<title>Comentários</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/blogs/admin/includes/menu.jsp"%>
	<p>&nbsp;</p>
	<html:form method="post" action="/blogs/admin/comment.do?act=add">
		<html:hidden property="postId" value="<%=request.getParameter("postId")%>"/>
		<html:hidden property="countComments" value="<%=request.getParameter("countComments")%>"/>
		<html:hidden property="from" value="admin"/>
	    <b>Coment&aacute;rio:</b><br>
	    <br/>
	    <html:textarea property="content" cols="50" rows="20"/>	    
	    <br/>
	    Nome:
	    <br/>
	    <html:text property="commentatorName" value="${blogUser.firstName}"/>
	    <br/>
	    Email:
	    <br/>
	    <html:text property="commentatorEmail" value="${blogUser.email}" />
	    <br/>
	    Homepage:
	    <br/>
	    <html:text property="commentatorHomepage" />
	    <br/>
	     Status : 
	    <html:select property="status">
	      <html:option value="-1">Selecione</html:option>
	      <html:option value="1">Ativo</html:option>
	      <html:option value="0">Inativo</html:option>
	    </html:select>
	    <br/>  
	    Censurado: 
       <html:checkbox property="censured" />  
    
    <br/>
	   <html:submit value="Enviar"/>
	</html:form>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<%@ include file="/blogs/admin/includes/footer.html"%>
	</body>

</html:html>
