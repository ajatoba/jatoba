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

<body bgcolor="#E4EFF5" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <div style="margin-left:auto; margin-right:auto; width:794px;  height:395px;display:table;">
        <div style="height:19px;"><img src="/bloglog/admin/login/img/top_login.jpg" width="794" height="19" /></div>

<div style="width:748px; height:339px; margin-left:26px;  display:table; margin-right:20px; background-color:#C9DEE9; background:url(/bloglog/admin/login/img/bottom_login.jpg) #C9DEE9 no-repeat bottom;">
<form name="userCommentatorForm" method="post" action="/blog/user.do?act=login" >
        <input type="hidden" name="postId" value="<%=postId%>"/>
        <input type="hidden" name="countComments" value="<%=countComments%>"/>
        <input type="hidden" name="path" value="<%=path%>"/>
        <input type="hidden" name="from" value="site"/>
        <input type="hidden" name="status" value="<%=status%>"/>
        <input type="hidden" name="blogId" value="<%=blogId%>"/>

      <table width="271" border="0" align="center" cellpadding="1" cellspacing="1" style="margin-top:55px;" >
        <tr> 
          <td colspan="2" align="left"><img src="/bloglog/admin/login/img/identifique_login.jpg" /></td>
        </tr>
        <tr> 
          <td width="265" style="font-size:12px; color:#2D3941; font-family:Arial;"><a href="/blog/blogs/content/add_user_in.jsp?postId=<%=postId%>&countComments=<%=countComments%>&path=<%=path%>&from=site&status=<%=status%>" style="font-size:12px; color:#2D3941; text-decoration:underline; font-family:Trebuchet MS;"> 
            Clique aqui</a> para se cadastrar</td>
        </tr>
        <tr>
          <td height="37" valign="bottom" style="font-size:12px;  color:#2D3941; font-family:Arial;">Login:</td>
        </tr>
        <tr> 
          <td><input type="text" name="login" style="height:26px; width:258px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:26px; font-family:Trebuchet MS;"> 
          </td>
        </tr>
        <tr> 
          <td height="24" valign="bottom" style="font-size:12px;  color:#2D3941; font-family:Arial;">Senha:</td>
        </tr>
        <tr> 
          <td><input type="password" name="password" style="height:26px; width:258px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; border:1px; border-color:#A5C1D2; line-height:26px; font-family:Trebuchet MS;"> 
          </td>
        </tr>
        <tr> 
          <td height="57" colspan="2">
          
          <table width="254" border="0" cellpadding="0" cellspacing="0">
              <tr><td width="126"><input  type="image" src="/bloglog/admin/login/img/entrar_login.jpg" name="Submit" value="Entrar" style="background-color:#147ABA; color:#FFFFFF; font-weight:bold; border:0px solid #147ABA; cursor:hand;" /></td><td width="116"><a href="/blog/blogs/content/remind_password_in.jsp" style="font-size:12px; color:#F47320; text-decoration:underline; font-family:Trebuchet MS;"> lembrar/login senha</a>       
</td></tr></table></td>
        </tr>
      </table>
  <center>
  <logic:present name="mensagem_erro">
    <font color="red"><bean:write name="mensagem_erro"/></font>
  </logic:present>
  </center>
 
</form>
</div>
</body>
</html:html>
