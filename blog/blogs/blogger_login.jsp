<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html:html>

<head>
<title>:: Bloglog ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<br>
<br>
<br>
<br>
<br>
<br>
<form method="post" action="/blog/blogs/communityUserLogin.do?act=login">
  <table width="100" border="0" align="center">
  <input type="hidden" name="returnPath" value="${returnPath}">
    <tr> 
      <td>Login:</td>
      <td>&nbsp;</td>
      <td> 
        <input type="text" name="login">
      </td>
    </tr>
    <tr> 
      <td>Senha:</td>
      <td>&nbsp;</td>
      <td> 
        <input type="password" name="password">
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
</body>
</html:html>
