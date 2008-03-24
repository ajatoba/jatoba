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
.style15 {font-size: 9px}
-->
</style>

<script>

	var url;
	function chamadaConfirmaDelecao(url_)
	{
		url = url_;
		setTimeout('confirmaDelecao()', 500);
	}
	
	
	function confirmaDelecao()
	
	{

		if(url.indexOf('/blog/blogs/admin/post.do?act=delete') > -1)
		{
			if(confirm('Deseja realmente excluir esse post?'))
			{
				document.location=url;
			}
			else
			{
				return;
			}
		}
		else
		{
			document.location=url;
		}
	}
</script>
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
		
		<div class="logo"></div>
		<div class="links"><img src="/bloglog/admin/imgs/btn_posts.jpg" /><a href="/blog/blogs/admin/favorites.do?act=list"><img src="/bloglog/admin/imgs/btn_bloglogs.jpg" border="0" style="border:none;" /></a><img src="/bloglog/admin/imgs/btn_ajuda.jpg" style="display:none;" /></div>
		<div class="login"></div>
	</div>
</div>
<div id="barra_repeat">
<div id="barra">
    	<span class="clicado">&nbsp;SEUS POSTS&nbsp;</span>&nbsp;&nbsp;&nbsp;<a href="/blog/blogs/admin/post/add_post_in.jsp" style="text-decoration:none;"><span>&nbsp;CRIAR NOVO POST&nbsp;</span></a>&nbsp;&nbsp;&nbsp;<a href="/blog/blogs/admin/post.do?act=publish&id=${blogId}" style="text-decoration:none;"><span>ATUALIZAR BLOG</span></a>
     &nbsp;&nbsp;&nbsp;
<a href="/blog/admin/blog.do?act=list" style="text-decoration:none;"><span>LISTAR OUTROS BLOG</span></a>&nbsp;&nbsp;&nbsp;
	<a href="/blog/blogs/admin/blogUser.do?act=load" style="text-decoration:none;"><span>ATUALIZAR DADOS</span></a>&nbsp;&nbsp;&nbsp;
			
    <span style=" vertical-align:middle; line-height:41px;"><a href="/blog/blogs/blogUser.do?act=logout" ><img src="/bloglog/admin/imgs/logout.jpg" style="margin-top:6px; border:none;"/></a></span>
</div>
            
            
            
</div>
<div id="meio_repeat">
	<div id="meio">	
<pg:pager url="/blog/blogs/admin/post.do" maxIndexPages="5" maxPageItems="10">

<table width="780" border="0" cellpadding="0" cellspacing="0" style=" margin-left:auto; margin-right:auto; margin-top:4px;" >
  <tr>
    <td>
		<table width="780" border="0" cellpadding="0" cellspacing="2">
		  <tr>
			<td width="305" height="33" bgcolor="#FFFFFF"><span class="style3">&nbsp;&nbsp;T&Iacute;TULO DO POST</span></td>
			<td width="104" bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">DATA</span></td>
			<td width="116" bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">STATUS</span></td>
			<td width="135" bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">EDI&Ccedil;&Atilde;O</span></td>
		    <td width="108" bgcolor="#FFFFFF">&nbsp;&nbsp;<span class="style3">COMENT&Aacute;RIOS</span></td>
		  </tr>
		</table>	</td>
	</tr>
	
	
	<logic:iterate name="posts" id="p" indexId="i" scope="request">
    <pg:item>
<tr>
		<td>
		<table width="780" border="0" cellpadding="0" cellspacing="0" style="border-bottom:1px solid #147AB8;">
		  <tr>
			<td width="307" height="42"><span class="style14">&nbsp;&nbsp;
			  <bean:write name="p" property="title"/>
			  </span></td>
			<td width="106"><span class="style14">&nbsp;&nbsp;<bean:write name="p" property="insertDate" format="dd/MM/yyyy"/>
			</span></td>
			<td width="118"><span class="style14">&nbsp;
<c:choose>
			<c:when test="${p.status == 1}">
				<img src="/bloglog/admin/img/ativo.jpg" border="0" />			</c:when>
			<c:otherwise>	    
				<img src="/bloglog/admin/img/inativo.jpg" border="0" />
			</c:otherwise>
      	</c:choose>
		
			  </span></td>
			<td width="139" valign="middle">&nbsp;&nbsp;<select name="select" id="select" onChange="chamadaConfirmaDelecao(this.value);" style="width:120px; font-size:10px;">
  <option value="">Escolha uma opção</option>
  <option value='/blog/blogs/admin/post.do?act=load&id=<bean:write name="p" property="id"/>'>Alterar</option>
  <option value='/blog/blogs/admin/post.do?act=delete&id=<bean:write name="p" property="id"/>'>Excluir</option>
</select></td>
		    <td width="61" align="left" valign="middle"><span class="style14">&nbsp;&nbsp;</span></td>
		    
		    <td width="49" align="left" valign="middle"><a href='/blog/blogs/admin/comment.do?act=list&postId=<bean:write name="p" property="id"/>'><img src="/bloglog/admin/imgs/post_btnListar.jpg" width="42" height="20" border="0" /></a></td>
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
			<dt>Algum problema? Entre em contato com a equipe BlogLog.</dt>
			<dt>E-mail: <a href="mailto:texto@bloglog.com.br">texto@bloglog.com.br</a> | Tel.: (21) 3385-4434</dt>
		</div>
	</div>
</div>

</body>
</html>
