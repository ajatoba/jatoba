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
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center">  	
  	<logic:present name="mensagem_erro">
  	 	<font color="red"><bean:write name="mensagem_erro"/></font>
    </logic:present> 
  <br><a href='javascript:history.back();'>voltar</a>
  	
  	</td>
  <tr>
</table>


</body>
</html>
