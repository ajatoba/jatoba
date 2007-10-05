<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@page import="com.vanguarda.blog.util.*"%>

<%String rndCrypt = RandomTool.createCrypted();%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html:html>
<head>
<title>:: Bloglog ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>

<body bgcolor="#E3F0F9" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<div style="margin-left:auto; margin-right:auto; width:570px; background-color:#FFFFFF; height:395px;">
		<div style="height:23px; background-color:#147ABA;"><img src="/bloglog/content/add_coment/img_add/tit_login.jpg" style="margin-left:12px;"></div>

<br>
<br>
<br/>
<br>
<form method="post" action="/blog/blogs/blogUser.do?act=remindPassword">
<html:hidden property="wordEnc" value="<%=rndCrypt%>"/>
  <table width="100" border="0" align="center">
    <tr> 
      <td style="font-size:11px; font-weight:bold; color:#147ABA; font-family:Trebuchet MS;">Email:</td>
      <td>&nbsp;</td>
      <td> 
        <input type="text" name="email" style="height:18px; width:250px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;">
      </td>
    </tr>
    <tr> 
      <td style="font-size:11px; font-weight:bold; color:#147ABA; font-family:Trebuchet MS;" colspan="3" align="center">      	
		Digite o que se vê escrito na imagem:
		<img src="/blog/servlet/rndImage?rndimg=<%=rndCrypt%>" /><br>
		<input type="text" name="imageword" maxlength="6" style='float:center; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:13px; line-height:16px; font-family:Trebuchet MS; margin-top:20px; margin-left:12px; width:80px; text-align:center;' /><br>
		  <center>
		  <logic:present name="error">
		  	<font style="font-size:11px; font-weight:bold; color:red; font-family:Trebuchet MS;"><bean:write name="error"/></font>
		  </logic:present>
		  </center>		
      </td>
    </tr>
    <tr>
      <td colspan="3">
		<html:submit value="Enviar"  style="float:left; margin-right:58px; margin-top:18px; background-color:#FF6600; color:#FFFFFF; font-weight:bold; border:1px solid #FF6600; cursor:hand;"/>
      </td>
    </tr>
  </table>
</form>
</div>
</body>
</html:html>
