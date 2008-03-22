<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html:html>

<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="/bloglog/admin/css/stylle.css" type="text/css">
</head>

<table cellpadding="0" cellspacing="0">
	<tr>
	<td><img src="/bloglog/admin/img/header.jpg" border="0"></td>
	<td><img src="/bloglog/admin/img/header-02.jpg" border="0"></td>
	<td><img src="/bloglog/admin/img/header-03.jpg" border="0"></td>
	<td><img src="/bloglog/admin/img/header-04.jpg" border="0"></td>
	</tr>
</table>

<br><br>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<form name="adminUserForm" method="post" action="/blog/admin/adminUser.do?act=login">
  <table width="100" border="0" align="center">
    <tr> 
      <td>Login:</td>
      <td>&nbsp;</td>
      <td> 
        <input type="text" name="login" >
      </td>
    </tr>
    <tr> 
      <td>Senha:</td>
      <td>&nbsp;</td>
      <td> 
        <input type="text" name="password">
      </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>
        <input type="submit" name="Submit" value="Entrar">
      </td>
    </tr>
  </table>
  <center>
  <logic:present name="mensagem_erro">
  	<font color="red"><bean:write name="mensagem_erro"/></font>
  </logic:present>
  </center>
</form>
<br>
<%@ include file="/blogs/admin/includes/footer.html"%>
</body>
</html:html>
