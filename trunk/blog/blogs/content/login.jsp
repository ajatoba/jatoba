<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%
String postId = (String) request.getAttribute("postId");
String countComments =(String) request.getAttribute("countComments");
String path =(String) request.getAttribute("path");
String from = "site";
String status = (String)request.getAttribute("status");
String blogId = (String)request.getAttribute("blogId");
%>
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
<br />
<br>
<form name="userCommentatorForm" method="post" action="/blog/user.do?act=login">
		<input type="hidden" name="postId" value="<%=postId%>"/>
		<input type="hidden" name="countComments" value="<%=countComments%>"/>
		<input type="hidden" name="path" value="<%=path%>"/>
		<input type="hidden" name="from" value="site"/>
		<input type="hidden" name="status" value="<%=status%>"/>
		<input type="hidden" name="blogId" value="<%=blogId%>"/>

  <table width="100" border="0" align="center">
    <tr> 
      <td style="font-size:11px; font-weight:bold; color:#147ABA; font-family:Trebuchet MS;">Login:</td>
      <td>&nbsp;</td>
      <td> 
        <input type="text" name="login" style="height:18px; width:100px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;">
      </td>
    </tr>
    <tr> 
      <td height="44" style="font-size:11px; font-weight:bold; color:#147ABA; font-family:Trebuchet MS;">Senha:</td>
      <td>&nbsp;</td>
      <td> 
        <input type="password" name="password" style="height:18px; width:100px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;">
      </td>
    </tr>
    <tr>
      <td height="57">&nbsp;</td>
      <td>&nbsp;</td>
      <td>
        <input type="submit" name="Submit" value="Entrar" style="background-color:#147ABA; color:#FFFFFF; font-weight:bold; border:1px solid #147ABA; cursor:hand;">
      </td>
    </tr>
  </table>
  <center>
  <logic:present name="mensagem_erro">
  	<font color="red"><bean:write name="mensagem_erro"/></font>
  </logic:present>
  <br>
<a href="/blog/blogs/content/add_user_in.jsp?postId=<%=postId%>&countComments=<%=countComments%>&path=<%=path%>&from=site&status=<%=status%>" style="font-size:10px; color:#F47320; text-decoration:underline; font-family:Trebuchet MS;"> Cadastre-se</a>		
&nbsp;&nbsp;&nbsp;
<a href="/blog/blogs/content/remind_password_in.jsp" style="font-size:10px; color:#F47320; text-decoration:underline; font-family:Trebuchet MS;"> Esqueci minha senha</a>		
  </center>
</form>
</div>
</body>
</html:html>
