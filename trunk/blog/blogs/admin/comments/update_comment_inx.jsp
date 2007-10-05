<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Untitled Document</title>
<link rel="stylesheet" href="/bloglog/admin/css/estilo.css"/>
<style type="text/css">
<!--
.style2 {
	font-size: 12px;
	font-weight: bold;
	color: #147ABA;
}
-->
</style>
</head>

<body>

<div id="top_repeat">
	<div id="top">
		<div style="float:right; margin-top:25px;">
		<span style="font-size:12px; line-height:10px; color:#1A74B3;">Usuário:</span>
		<span style="font-size:13px; font-weight:bold; color:#F77527;">Login</span>
		&nbsp;&nbsp;<a href="/blog/blogs/blogUser.do?act=logout"><img src="/bloglog/admin/imgs/logout.jpg" width="46" height="23" style="display:table; border:none;" /></a></div>
		<div class="logo"></div>
		<div class="links"><img src="/bloglog/admin/imgs/btn_posts.jpg" /><img src="/bloglog/admin/imgs/btn_controle.jpg" /><img src="/bloglog/admin/imgs/btn_ajuda.jpg" /></div>
		<div class="login"></div>
	</div>
</div>
<div id="barra_repeat">
	<div id="barra">ALTERAR COMENTÁRIO</div>
</div>
<div id="meio_repeat">
	<div id="meio">
<html:form method="post" action="/blogs/admin/comment.do?act=update">
	<html:hidden property="from" value="admin"/>
	<html:hidden property="postId" value="${comment.post.id}"/>
	<html:hidden property="id" value="${comment.id}"/>
<table width="780" border="0" cellpadding="0" cellspacing="0" style=" margin-left:auto; margin-right:auto;">
  <tr>
    <td>
		<table width="450" height="294" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="241" height="32" valign="bottom"><span class="style2">Nome</span></td>
			<td width="209" valign="bottom" class="style2">Email</td>
		  </tr>
		  <tr>
			<td height="25"><html:text property="commentatorName" value="${comment.commentatorName}" style=" background-color:#FFFFFF; border:1px solid #B7D3E9; width:210px"/></td>
			<td height="25"><html:text property="commentatorEmail" value="${comment.commentatorEmail}" style=" background-color:#FFFFFF; border:1px solid #B7D3E9; width:210px"/></td>
		  </tr>
		  <tr>
			<td height="23" colspan="2" valign="bottom" class="style2">Coment&aacute;rio</td>
		  </tr>
		  <tr>
			<td height="178" colspan="2" valign="bottom"><html:textarea property="content" cols="50" rows="20"  value="${comment.content}" style=" background-color:#FFFFFF; border:1px solid #B7D3E9; width:450px;"/></td>
		  </tr>
		  <tr>
			<td height="36" valign="bottom"  colspan="2">
			  <div align="left">
			    <table width="450" height="100%" border="0">
                    <tr>
                      <th width="323" scope="row"><span class="style2">Status: </span>
                        <html:select property="status" value="${comment.status}">
						  <html:option value="-1">Selecione</html:option>
						  <html:option value="1">Ativo</html:option>
						  <html:option value="0">Inativo</html:option>
						</html:select>	 
                        &nbsp;&nbsp;&nbsp; <span class="style2">Censurado </span>
                        <html:checkbox property="censured" value="${!comment.censured}" />         </th>
                      <td width="117" valign="bottom"><div align="right"><html:submit value="Alterar"/></div></td>
                    </tr>
                              </table>
			    </div></td>
		  </tr>
		</table>
	</td>
  </tr>
</table>
</html:form>
	
	</div>
</div>

<div id="down_repeat">
	<div class="down">
		<div class="img">
			<dt id="roesp"></dt>
			<dt>2007 BlogLog.com. Todos os direitos reservados</dt>
			<dt><a href="#">About BlogLog</a> | <a href="#">Publicidade</a> | <a href="politica.html">Política de Privacidade</a></dt>
		</div>
	</div>
</div>

</body>
</html>
