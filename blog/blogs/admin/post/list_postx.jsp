<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Untitled Document</title>
<link rel="stylesheet" href="../../bloglog/admin/css/estilo.css"/>
<style type="text/css">
<!--
.style3 {
	font-size: 12px;
	font-weight: bold;
	color: #F77527;
}
.style14 {font-size: 11px; color: #777777; font-weight: bold; }
.style15 {font-size: 9px}
-->
</style>
</head>
<body>
<logic:present name="mensagem_erro">
<font color="red"><bean:write name="mensagem_erro"/></font>
</logic:present> 

<logic:present name="mensagem_sucesso">
<font color="red"><bean:write name="mensagem_sucesso"/></font>
</logic:present> 

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
	<div id="barra"><a href="/blog/blogs/admin/post/add_post_in.jsp"><img src="/bloglog/admin/imgs/post_btnCriarPost.jpg" border="0" /></a>&nbsp;&nbsp;<a href="/blog/blogs/admin/post.do?act=publish&id=${blogId}"><img src="/bloglog/admin/imgs/btnAtualizar.jpg" border="0" /></a></div>
</div>
<div id="meio_repeat">
	<div id="meio">	
	
<pg:pager url="post.do" maxIndexPages="5" maxPageItems="10">

<table width="780" border="0" cellpadding="0" cellspacing="0" style=" margin-left:auto; margin-right:auto; margin-top:4px;" >
  <tr>
    <td>
		<table width="780" border="0" cellpadding="0" cellspacing="3">
		  <tr>
			<td width="305" height="33" bgcolor="#FFFFFF"><span class="style3">&nbsp;&nbsp;T&Iacute;TULO</span></td>
			<td width="68" bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">STATUS</span></td>
			<td width="104" bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">DATA</span></td>
			<td width="143" bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">COMENT&Aacute;RIOS</span></td>
		    <td width="142" bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">POSTS</span></td>
		  </tr>
		</table>	</td>
	</tr>
	
	
	<logic:iterate name="posts" id="p" indexId="i" scope="request">
    <pg:item>
	<tr>
		<td>
		<table width="780" border="0" cellpadding="0" cellspacing="0" style="border-bottom:1px solid #147AB8;">
		  <tr>
			<td width="310" height="42"><span class="style14">&nbsp;&nbsp;<bean:write name="p" property="title"/></span></td>
			<td width="70"><span class="style15">&nbsp;&nbsp;&nbsp;
		<c:choose>
			<c:when test="${p.status == 1}">
				<img src="/bloglog/admin/img/ativo.jpg" border="0" />			</c:when>
			<c:otherwise>	    
				<img src="/bloglog/admin/img/inativo.jpg" border="0" />
			</c:otherwise>
      	</c:choose></span></td>
			<td width="113"><span class="style14">&nbsp;&nbsp;<bean:write name="p" property="insertDate" format="dd/MM/yyyy"/></span></td>
			<td width="45" valign="middle"><span class="style14">&nbsp;&nbsp;(1120)</span></span></td>
		    <td width="242" valign="middle"><a href="/blog/blogs/admin/comment.do?act=list&postId=<bean:write name="p" property="id"/>"><img src="/bloglog/admin/imgs/post_btnListar.jpg" width="42" height="20" border="0" /></a><a href="/blog/blogs/admin/post.do?act=load&id=<bean:write name="p" property="id"/>"><img src="/bloglog/admin/imgs/post_btnAlterar.jpg" width="50" height="20" border="0" style="margin-left:64px;"/></a><a href="/blog/blogs/admin/post.do?act=delete&id=<bean:write name="p" property="id"/>"><img src="/bloglog/admin/imgs/post_btnExcluir.jpg" width="49" height="20" border="0" style="margin-left:4px;"/></a></td>
		  </tr>
		</table>		
		</td>
  	</tr>
  	</pg:item>
    </logic:iterate>
  
  

</table>
<pg:index>
	<pg:prev>
		<a href="<%=pageUrl%>&act=list">[<< Prev]</a>	
	</pg:prev>
	
	<pg:pages>
		<a href="<%= pageUrl %>&act=list"><%= pageNumber %></a>
	</pg:pages>
	
	<pg:next>
		<a href="<%= pageUrl%>&act=list">[Next >>]</a>
	</pg:next>
</pg:index>

</pg:pager>
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
