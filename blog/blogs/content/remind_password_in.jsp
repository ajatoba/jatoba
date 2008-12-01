<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@page import="com.vanguarda.blog.util.*"%>

<%String rndCrypt = RandomTool.createCrypted();%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html:html>
<head>
<title>:: Bloglog ::</title>
<link href="/bloglog/admin/css/recuperar.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>

<body>
<div style="height:19px;"><img src="/bloglog/admin/login/img/top_login.jpg" width="794" height="19" /></div>

    <div style="margin-left:26px; margin-right:19px; width:748px;  height:339px; background-color:#C9DEE9;">

<form method="post" action="/blog/blogs/blogUser.do?act=remindPassword">
<html:hidden property="wordEnc" value="<%=rndCrypt%>"/>
<table width="266" align="center" border="0" cellspacing="0" cellpadding="0" style="margin-top:55px;">
  <tr>
    <td colspan="2"><img src="/bloglog/img/header_recuperar.jpg" width="266" height="43" /></td>
  </tr>
  <tr>
    <td height="40" colspan="2" class="txt">Digite o email que vc cadastrou no bloglog</td>
  </tr>
  <tr>
    <td colspan="2" class="txt2">Digite aqui seu email
    <br />
    <input name="email" type="text" class="form">
    </td>
  </tr>
  <tr>
    <td colspan="2">
    <div class="image">
    <dt style="font-size:14px; font-weight:bold; color:#414041; margin-left:5px; margin-top:5px;">Imagem de segurança</dt>
    <dt class="digite">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="/blog/servlet/rndImage?rndimg=<%=rndCrypt%>"  /></td>
    <td>Digite o que se vê <br />escrito na imagem: </td>
    <td><input type="text"  name="imageword" maxlength="6" class="form2" /></td>
  </tr>
</table>

     </dt>
    <center>
          <logic:present name="error">
            <font style="font-size:11px; font-weight:bold; color:#414041; font-family:Arial, Helvetica, sans-serif;"><bean:write name="error"/></font>
          </logic:present>
          </center> 
    </div>
    </td>
  </tr>
  <tr>
    <td><html:image src="/bloglog/img/enviar_recuperar.jpg" value="Enviar"/></td>
    <td>&nbsp;</td>
  </tr>
</table>
</form>
</div>
<div><img src="/bloglog/admin/login/img/bottom_login.jpg" width="794" height="14" /></div>

</body>
</html:html>
