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

<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <div style="margin-left:auto; margin-right:auto; width:748px; background-color:#C9DEE9; height:395px;">
        
<br/><br/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center">     
    <logic:present name="error">
        <font style="font-family:Arial, Helvetica, sans-serif; color:#2D353D;"><bean:write name="error"/></font>
    </logic:present> 
    
    <logic:present name="sucesso">
        <font style="font-family:Arial, Helvetica, sans-serif; color:#2D353D;"><bean:write name="sucesso"/></font>
    </logic:present> 
    
    </td>
  <tr>
</table>


</body>
</html>