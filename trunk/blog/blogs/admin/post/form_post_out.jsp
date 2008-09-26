<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
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
-->
</style>
</head>

<body>

<div id="top_repeat">
	<div id="top">
		
		<div class="logo"></div>
	<div class="links"><img src="/bloglog/admin/imgs/btn_posts.jpg" /><a href="/blog/blogs/admin/favorites.do?act=list"><img src="/bloglog/admin/imgs/btn_bloglogs.jpg" border="0" style="border:none;" /></a><img src="/bloglog/admin/imgs/btn_ajuda.jpg" style="display:none;" /></div>		<div class="login"></div>
	</div>
</div>
<div id="barra_repeat">
	<div id="barra">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
		<th align="left" scope="row">
		
<a href="/blog/blogs/admin/post.do?act=list" style="text-decoration:none;"><span>&nbsp;SEUS POSTS&nbsp;</span></a>&nbsp;&nbsp;&nbsp;<span class="clicado">&nbsp;CRIAR NOVO POST&nbsp;</span>
		
		</th>
		<td align="right">
		
<span style=" vertical-align:middle; line-height:41px;"><a href="/blog/blogs/blogUser.do?act=logout" ><img src="/bloglog/admin/imgs/logout.jpg" style="margin-top:6px; border:none;"/></a></span>
			
		</td>
	  </tr>
	</table>

    </div>
</div>
<div id="meio_repeat">
	<div id="meio">

<table width="780" border="0" cellpadding="0" cellspacing="0" style=" margin-left:auto; margin-right:auto;">
  <tr>
    <td>
	
		<table width="100%" height="294" border="0" cellpadding="0" cellspacing="0">
         	<tr>
			<td align="center" style="font-size:16px; font-family:Trebuchet MS; font-weight:bold; color:#147ABA;">
				Seu post foi cadastrado com sucesso, clique no botão abaixo para publicar.
			    <br /><br />
			    <a href="/blog/blogs/admin/post.do?act=publish&id=${blogId}"><img src="/bloglog/admin/imgs/publicar.jpg" width="77" height="26" border="0" /></a></td>
			</tr>
        </table>	
		</td>
  </tr>
</table>


<p>  
	<center>
	  <logic:present name="mensagem_erro">
	  	<font color="red"><bean:write name="mensagem_erro"/></font>
	  </logic:present>
	</center>
</p>

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
</html:html>
