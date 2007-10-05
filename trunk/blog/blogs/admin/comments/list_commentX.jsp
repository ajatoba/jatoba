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
<link rel="stylesheet" href="/bloglog/admin/css/estilo.css"/>
<style type="text/css">
<!--
.style3 {
	font-size: 12px;
	font-weight: bold;
	color: #F77527;
}
.style14 {font-size: 11px; color: #777777; font-weight: bold; }
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
	<div id="barra">LISTAR TODOS COMENTÁRIOS</div>
</div>
<div id="meio_repeat">
	<div id="meio">	

<pg:pager url="comment.do" maxIndexPages="5" maxPageItems="150">	

<table width="780" border="0" cellpadding="0" cellspacing="0" style=" margin-left:auto; margin-right:auto; margin-top:4px;" >
  <tr>
    <td>
		<table width="780" border="0" cellpadding="0" cellspacing="3">
		  <tr>
			<td width="435" height="33" bgcolor="#FFFFFF"><span class="style3">&nbsp;&nbsp;COMENT&Aacute;RIO</span></td>
			<td width="105" bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">STATUS</span></td>
			<td width="93" bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">DATA</span></td>
			<td bgcolor="#FFFFFF">&nbsp;&nbsp;</td>
		  </tr>
		</table>	</td>
	</tr>
<logic:iterate name="comments" id="c" indexId="i">
<pg:item>

	<tr>
		<td>
		<table width="780" border="0" cellpadding="0" cellspacing="0" style="border-bottom:1px solid #147AB8;">
		  <tr>
			<td width="440" height="42"><span class="style14">&nbsp;&nbsp;<bean:write name="c" property="content"/> </span></span></td>
			<td width="107">
	<c:choose>
    <c:when test="${c.status == 1}">
	    &nbsp;&nbsp;&nbsp;<img src="/bloglog/admin/img/ativo.jpg" border="0" />    </c:when>
    <c:otherwise>	    
	    <img src="/bloglog/admin/img/inativo.jpg" border="0" />    </c:otherwise>
    </c:choose></td>
			<td width="94"><span class="style14">&nbsp;&nbsp;<bean:write name="c" property="insertDate" format="dd/MM/yyyy"/></span></td>
			<td width="139" valign="middle"><div align="center"><a href="#"></a><a href="#"></a><a href="/blog/blogs/admin/comment.do?act=load&id=<bean:write name="c" property="id"/>"><img src="/bloglog/admin/imgs/post_btnAlterar.jpg" width="50" height="20" border="0" style="margin-left:4px;"/></a><a href="/blog/blogs/admin/comment.do?act=delete&id=<bean:write name="c" property="id"/>&postId=<bean:write name="p" property="id"/>"><img src="/bloglog/admin/imgs/post_btnExcluir.jpg" width="49" height="20" border="0" style="margin-left:4px;"/></a></div></td>
		  </tr>
		</table>		
		</td>
  </tr>
</pg:item>
</logic:iterate>  

</table>
<p align="center">
<pg:index>
	<pg:prev>
		<a href="<%=pageUrl%>&act=list&postId=<bean:write name="p" property="id"/>">[<< Prev]</a>	
	</pg:prev>
	
	<pg:pages>
		<a href="<%= pageUrl %>&act=list&postId=<bean:write name="p" property="id"/>"><%= pageNumber %></a>
	</pg:pages>
	
	<pg:next>
		<a href="<%= pageUrl%>&act=list&postId=<bean:write name="p" property="id"/>">[Next >>]</a>
	</pg:next>
</pg:index>
</p>
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
