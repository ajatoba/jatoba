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

<body bgcolor="#E3F0F9" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<div style="margin-left:auto; margin-right:auto; width:570px; background-color:#FFFFFF; height:395px;">
		<div style="height:23px; background-color:#147ABA;"><img src="/bloglog/content/add_coment/img_add/tit_login.jpg" style="margin-left:12px;"></div>
<br/><br/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center">  	
  	<logic:present name="error">
  	 	<font style="font-size:11px; font-weight:bold; color:red; font-family:Trebuchet MS;"><bean:write name="error"/></font>
    </logic:present> 
    
    <logic:present name="sucesso">
  		<font style="font-size:11px; font-weight:bold; color:red; font-family:Trebuchet MS;"><bean:write name="sucesso"/></font>
    </logic:present> 
  	
  	</td>
  <tr>
</table>


</body>
</html>