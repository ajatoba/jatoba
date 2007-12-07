<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Admin Posts BLOGLOG</title>
<link rel="stylesheet" href="/bloglog/admin/css/estilo.css"/>
<style type="text/css">
<!--
.style2 {
	font-size: 12px;
	font-weight: bold;
	color: #147ABA;
}
.style3 {
	font-size: 12px;
	font-weight: bold;
	color: #F77527;
}
-->
</style>
</head>

<body>

<div id="top_repeat">
	<div id="top">
	  <div class="logo"></div>
	<div class="links"><img src="/bloglog/admin/imgs/btn_posts.jpg" /><a href="/blog/blogs/admin/favorites.do?act=list"><img src="/bloglog/admin/imgs/btn_bloglogs.jpg" border="0" style="border:none;" /></a><img src="/bloglog/admin/imgs/btn_ajuda.jpg" style="display:none;" /></div>		
		<div class="login"></div>
	</div>
</div>
<div id="barra_repeat">
	<div id="barra">
    	<a href="/blog/blogs/admin/post.do?act=list" style="text-decoration:none;"><span>&nbsp;SEUS POSTS&nbsp;</span></a>&nbsp;&nbsp;&nbsp;<a href="/blog/blogs/admin/post/add_post_in.jsp" style="text-decoration:none;"><span>&nbsp;CRIAR NOVO POST&nbsp;</span></a>&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1);" style="text-decoration:none;"><span>&nbsp;VOLTAR AOS COMENTÁRIOS&nbsp;</span></a>
        
        	<span style="font-size:12px; color:#FFFFFF; line-height:10px; margin-left:113px; font-weight:normal;">Usuário:&nbsp;</span><span style="font-size:13px; font-weight:bold; color:#FFFFFF;">Login</span>&nbsp;&nbsp;&nbsp;
            <span style=" vertical-align:middle; line-height:41px;"><a href="/blog/blogs/blogUser.do?act=logout" ><img src="/bloglog/admin/imgs/logout.jpg" style="margin-top:6px; border:none;"/></a></span>      </div>
</div>
<div id="meio_repeat">
	<div id="meio">
<html:form method="post" action="/blogs/admin/comment.do?act=update">
	<html:hidden property="from" value="admin"/>
	<html:hidden property="postId" value="${comment.post.id}"/>
	<html:hidden property="id" value="${comment.id}"/>
<table width="780" border="0" cellpadding="0" cellspacing="0" style=" margin-left:auto; margin-right:auto;">
  	<tr>
		<td height="32" valign="bottom" class="style3">ALTERAR COMENT&Aacute;RIO		</td>
	</tr>
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
						  <html:option value="1" style="color:#00CC00; font-weight:bold;">Ativo</html:option>
						  <html:option value="0" style="color:#FF0000; font-weight:bold;">Inativo</html:option>
						</html:select>	 
                        &nbsp;&nbsp;&nbsp; <span class="style2" style="display:none;">Censurado </span>
                        <html:checkbox property="censured" value="${!comment.censured}" style="display:none;"/>         </th>
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
			<dt>Algum problema? Entre em contato com a equipe BlogLog.</dt>
			<dt>E-mail: <a href="mailto:texto@bloglog.com.br">texto@bloglog.com.br</a> | Tel.: (21) 3385-4434</dt>
		</div>
	</div>
</div>

</body>
</html>
