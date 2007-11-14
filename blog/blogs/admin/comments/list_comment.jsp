<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Admin Posts BLOGLOG</title>
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
	  <div class="logo"></div>
	<div class="links"><img src="/bloglog/admin/imgs/btn_posts.jpg" /><a href="/blog/blogs/admin/favorites.do?act=list"><img src="/bloglog/admin/imgs/btn_bloglogs.jpg" border="0" style="border:none;" /></a><img src="/bloglog/admin/imgs/btn_ajuda.jpg" style="display:none;" /></div>		

	</div>
</div>
<div id="barra_repeat">
	<div id="barra">
    	<a href="/blog/blogs/admin/post.do?act=list" style="text-decoration:none;"><span>&nbsp;SEUS POSTS&nbsp;</span></a>&nbsp;&nbsp;&nbsp;<a href="/blog/blogs/admin/post/add_post_in.jsp" style="text-decoration:none;"><span>&nbsp;CRIAR NOVO POST&nbsp;</span></a>
        
        	<span style="font-size:12px; color:#FFFFFF; line-height:10px; margin-left:318px; font-weight:normal;">Usuário:&nbsp;</span><span style="font-size:13px; font-weight:bold; color:#FFFFFF;">Login</span>&nbsp;&nbsp;&nbsp;
            <span style=" vertical-align:middle; line-height:41px;"><a href="/blog/blogs/blogUser.do?act=logout" ><img src="/bloglog/admin/imgs/logout.jpg" style="margin-top:6px; border:none;"/></a></span>      </div>
</div>
<div id="meio_repeat">
	<div id="meio">	

<pg:pager url="comment.do" maxIndexPages="5" maxPageItems="150">	

<table width="780" border="0" cellpadding="0" cellspacing="0" style=" margin-left:auto; margin-right:auto; margin-top:4px;" >
  <tr>
    <td>
		<table width="780" border="0" cellpadding="0" cellspacing="3">
		  <tr>
			<td width="435" height="33" bgcolor="#FFFFFF"><span class="style3">&nbsp;&nbsp;COMENT&Aacute;RIOS</span></td>
			<td width="105" bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">DATA</span></td>
			<td width="93" bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">STATUS</span></td>
			<td bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">EDI&Ccedil;&Atilde;O</span></td>
		  </tr>
		</table>	</td>
	</tr>
<logic:iterate name="comments" id="c" indexId="i">
<pg:item>

	<tr>
		<td>
		<table width="780" border="0" cellpadding="0" cellspacing="0" style="border-bottom:1px solid #147AB8;">
		  <tr>
			<td width="440" height="42"><span class="style14">&nbsp;&nbsp;
			    <bean:write name="c" property="content"/> </span></span></td>
			<td width="107"><span class="style14">&nbsp;&nbsp;<bean:write name="c" property="insertDate" format="dd/MM/yyyy"/></span></td>
			<td width="97">&nbsp;&nbsp;&nbsp;
	<c:choose>
    <c:when test="${c.status == 1}">
	    <img src="/bloglog/admin/img/ativo.jpg" border="0" />    </c:when>
    <c:otherwise>	    
	    <img src="/bloglog/admin/img/inativo.jpg" border="0" />    </c:otherwise>
    </c:choose></td>
			<td width="136" valign="middle">&nbsp;<a href="/blog/blogs/admin/comment.do?act=delete&id=<bean:write name="c" property="id"/>&postId=<bean:write name="p" property="id"/>"><img src="/bloglog/admin/imgs/post_btnExcluir.jpg" width="49" height="20" border="0" style="margin-left:4px;"/></a><select name="select" onChange="document.location=this.value;" style="width:120px; font-size:10px; display:none;">
  <option value="">Escolha uma opção</option>
  <option value='/blog/blogs/admin/comment.do?act=load&id=<bean:write name="c" property="id"/>'>Alterar</option>
  <option value='/blog/blogs/admin/comment.do?act=delete&id=<bean:write name="c" property="id"/>&postId=<bean:write name="p" property="id"/>'>Excluir</option>
</select>
</td>
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
			<dt>Algum problema? Entre em contato com a equipe BlogLog.</dt>
			<dt>E-mail: <a href="mailto:texto@bloglog.com.br">texto@bloglog.com.br</a> | Tel.: (21) 3385-4434</dt>
		</div>
	</div>
</div>

</body>
</html>
