<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/pager-taglib" prefix="pg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Admin Posts BLOGLOG</title>
<link rel="stylesheet" href="/bloglog/admin/css/estilo.css" />
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

<script>
function EscreveBox(){
	var Ccombo = document.getElementById("combo");
	document.write(Ccombo.innerHTML); }
</script>

<body style=" color:#147ABA; font-weight:bold; font-family:Trebuchet MS; font-size:11px;">
<div id="top_repeat">
<div id="top">

<div class="logo"></div>
<div class="links"><a href="post.do?act=list"><img
	src="/bloglog/admin/imgs/btn_posts2.jpg" border="0"
	style="border:none;" /></a><img
	src="/bloglog/admin/imgs/btn_bloglogs2.jpg" border="0"
	style="border:none;" /><img src="/bloglog/admin/imgs/btn_ajuda.jpg"
	style="display:none;" /></div>
<div class="login"></div>
</div>
</div>
<div id="barra_repeat">
<div id="barra"><span
	style="font-size:12px; color:#FFFFFF; line-height:10px; margin-left:550px; font-weight:normal;">Usuário:&nbsp;</span><span
	style="font-size:13px; font-weight:bold; color:#FFFFFF;">Login</span>&nbsp;&nbsp;&nbsp;
<span style=" vertical-align:middle; line-height:41px;"><a
	href="/blog/blogs/blogUser.do?act=logout"><img
	src="/bloglog/admin/imgs/logout.jpg"
	style="margin-top:6px; border:none;" /></a></span></div>
</div>
<div id="meio_repeat2">
<div id="meio"><br />
<br />
<logic:present name="error">
<bean:write name="error"/>
</logic:present>

<form method="post" action="/blog/blogs/admin/favorites.do?act=add">

<table width="100%" border="0" cellspacing="10" cellpadding="10" >
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<%int i = 0;%>
	<logic:iterate name="blogs" id="b" >
<%if (i % 4 == 0) {%>
	</tr>
	<tr>
		<td>
			<input type="checkbox" name="favorites"
				value="${b.id}"
				<logic:iterate name="favorites" id="f" indexId="fav"><c:if test="${b.id == f.blog.id}">CHECKED</c:if></logic:iterate>>
			  &nbsp; <bean:write name="b" property="name" />
		</td>
	<%} else {%>
		<td>
			<input type="checkbox" name="favorites"
				value="${b.id}"
				<logic:iterate name="favorites" id="f" indexId="fav"><c:if test="${b.id == f.blog.id}">CHECKED</c:if></logic:iterate>>
			  &nbsp; <bean:write name="b" property="name" />
		</td>
	<%}
	
	i++;%>
	</logic:iterate>

</table>

<input type="submit" value="Enviar" /></form>


</div>
</div>

<div id="down_repeat">
<div class="down">
<div class="img">
<dt id="roesp"></dt>
<dt>Algum problema? Entre em contato com a equipe BlogLog.</dt>
<dt>E-mail: <a href="mailto:texto@bloglog.com.br">texto@bloglog.com.br</a>
| Tel.: (21) 3385-4434</dt>
</div>
</div>
</div>

</body>
</html:html>
