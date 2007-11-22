<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html:html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>BLOGLOG | Administrador</title>
</head>

<body style="margin:0px;">

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EAF3FA">
<form name="blogUserForm" method="post" action="/blog/blogs/blogUser.do?act=login">
  <tr>
    <th align="center" valign="middle" scope="row"><table width="302" height="251" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <th width="34" height="251" scope="row"></th>
        <th width="268" align="left" valign="top" scope="row"><table width="268" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="268" height="65" valign="bottom" style="background:url(/bloglog/admin/img/logo.jpg) no-repeat bottom left;"></td>
          </tr>
          <tr>
            <td height="27" valign="bottom" style="background:url(/bloglog/admin/img/login.jpg) no-repeat bottom left;"></td>
          </tr>
          <tr>
            <td height="33" valign="bottom">
         
                <input name="login" type="text" id="login" style=" height:28px; border:1px solid #B7D3E9; width:232px; background-color:#F8F8F8; font-size:19px; padding-left:3px; color:#147ABA;"></td>
          </tr>
          <tr>
            <td height="23" valign="bottom" style="background:url(/bloglog/admin/img/senha.jpg) no-repeat bottom left;"></td>
          </tr>
          <tr>
            <td height="33" valign="bottom"><input name="password" type="password" id="password" style=" height:28px; border:1px solid #B7D3E9; width:163px; background-color:#F8F8F8; font-size:18px; padding-left:3px; color:#147ABA;" onKeyDown="if(event.keyCode==13) blogUserForm.submit();"><img src="/bloglog/admin/img/entrar.jpg" width="63" height="29" style="vertical-align:bottom; margin-bottom:1px; margin-left:6px; cursor:hand;" onClick="blogUserForm.submit();"></td>
          </tr>
          <tr>
            <td height="17" valign="bottom"><logic:present name="mensagem_erro">
  	<font style="font-size:10px; color:#FF0000; font-family:Trebuchet MS, Tahoma;"><bean:write name="mensagem_erro"/></font>
  </logic:present></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
        </table></th>
      </tr>
    </table></th>
  </tr>
  </form>
</table>

</body>
</html:html>
