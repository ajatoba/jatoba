<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <body border="0" cellpadding="0" cellspacing="0">
   <script>
	function confirmaDelecao(url)
	{
		
		if(confirm('Deseja realmente excluir esse blog?'))
		{
			document.location=url;
		}
		else
		{
			return;
		}
		
	}
</script>
   
<%@ include file="/admin/includes/menu.jsp"%>
<br>

<pg:pager url="/blog/admin/blog.do" maxIndexPages="5" maxPageItems="50">

<table id="Table_01" width="864" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<a href="/blog/admin/blog.do?act=addForm"><img src="/bloglog/admin/img/menu_criar_01.jpg" alt="" name="menu_criar_01" width="173" height="46" border="0" id="menu_criar_01" /></a></td>
<td>
			<img id="menu_criar_02" src="/bloglog/admin/img/menu_criar_02.jpg" width="173" height="46" alt="" /></td>
		<td>
			<img id="menu_criar_03" src="/bloglog/admin/img/menu_criar_03.jpg" width="172" height="46" alt="" /></td>
		<td>
			<img id="menu_criar_04" src="/bloglog/admin/img/menu_criar_04.jpg" width="173" height="46" alt="" /></td>
		<td>
			<img id="menu_criar_05" src="/bloglog/admin/img/menu_criar_05.jpg" width="173" height="46" alt="" /></td>
	</tr>
</table>

<table width="864" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr bgcolor="#51585E"> 
    <td width="5%"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF"><b>id</b></span></td>
    <td width="50%"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF"><b>blogueiro</b></span></td>
    <td width="10%"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF"><b>cadastrado em</b></span></td>
    <td width="5%" align="center"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF"><b>status</b></span></td>
    <td width="5%"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF"><b>excluir</b></span></td>
    <td width="20%"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF"><b>editar</b></span></td>
  </tr>
  
  <logic:iterate name="blogs" id="b" indexId="i">
  <pg:item>
  <tr>   
    <td width="5%"><bean:write name="b" property="id"/></td>
    <td width="50%"><bean:write name="b" property="name"/></td>
    <td width="10%"><bean:write name="b" property="insertDate" format="dd/MM/yyyy"/></td>
    <td width="5%" align="center">
    <c:choose>
    <c:when test="${b.status == 1}">
	    <img src="/bloglog/admin/img/ativo.jpg" border="0" />		    
    </c:when>
    <c:otherwise>	    
	    <img src="/bloglog/admin/img/inativo.jpg" border="0" />
    </c:otherwise>
    </c:choose>
    </td>
    <td width="5%"><a href="#" onclick="confirmaDelecao('/blog/admin/blog.do?act=delete&id=<bean:write name="b" property="id"/>')">X</a></td>
    <td width="20%"><a href="/blog/blogs/admin/post.do?act=list&blogId=<bean:write name="b" property="id"/>">post</a>  <a href="/blog/admin/blog.do?act=load&id=<bean:write name="b" property="id"/>">blog</a> 
    
    </td>
  </tr>
  </pg:item>
  </logic:iterate>
</table>
<p align="center">
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
</p>
</pg:pager>
<%@ include file="/admin/includes/footer.html"%>
</body>
</html>
