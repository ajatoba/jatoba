<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=iso-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title> :: BlogLog ::  </title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%@ include file="/blogs/admin/includes/menu.jsp"%>
<br>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center">
    <logic:present name="error">
  	 	<font color="red"><bean:write name="error"/></font>
    </logic:present> 
    
    Entre com as palavras separadas por ";".
  	<br>
  		<html:form method="post" action="/blogs/admin/word.do?act=edit">
		<html:hidden property="path" value="<%=request.getParameter("path")%>"/>
	    
	    <br/>
	    <html:textarea property="content" cols="40" rows="10" value="${content}"/>	    
	    <br/>
	    
    <br/>
	   <html:submit value="Enviar"/>
	</html:form>
  	
  	</td>
  <tr>
</table>

<%@ include file="/blogs/admin/includes/footer.html"%>
</body>
</html>
