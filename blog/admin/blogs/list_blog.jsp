<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
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
<table align="center" border="0" cellpadding="0" cellspacing="0">
<tr><td>   
<%@ include file="/admin/includes/menu.jsp"%>
</td></tr></table>
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
    <td width="100"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">id</span></td>
    <td width="364"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">blogueiro</span></td>
    <td width="100"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">cadastrado em</span></td>
    <td width="100" align="center"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">status</span></td>
    <td width="100"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">excluir</span></td>
    <td width="100"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">editar</span></td>
  </tr>
  
  <logic:iterate name="blogs" id="b" indexId="i">
  <pg:item>
  <tr>   
    <td width="100"><span style="font-family:Arial, Helvetica, sans-serif; font-size:18px; font-weight:bold; color:#3998f7"><bean:write name="b" property="id"/></span></td>
    <td width="364"><span style="font-family:Arial, Helvetica, sans-serif; font-size:16px; font-weight:bold; color:#2f3439"><bean:write name="b" property="name"/></span></td>
    <td width="100"><bean:write name="b" property="insertDate" format="dd/MM/yyyy"/></td>
    <td width="100" align="center">
    <c:choose>
    <c:when test="${b.status == 1}">
	    <img src="/bloglog/admin/img/ativo.jpg" border="0" />		    
    </c:when>
    <c:otherwise>	    
	    <img src="/bloglog/admin/img/inativo.jpg" border="0" />
    </c:otherwise>
    </c:choose>
    </td>
    <td width="100"><a href="#" onclick="confirmaDelecao('/blog/admin/blog.do?act=delete&id=<bean:write name="b" property="id"/>')"><img src="/bloglog/admin/img/btn_exc.jpg" border="0"/></a></td>
    <td width="100"><span style="color:#2f3439"><a href="/blog/blogs/admin/post.do?act=list&blogId=<bean:write name="b" property="id"/>">post</a></span> <span style="color:#2f3439"> <a href="/blog/admin/blog.do?act=load&id=<bean:write name="b" property="id"/>">blog</a> </span>
    
    </td>
  </tr>
  </pg:item>
  </logic:iterate>
</table>
<p align="center">
<pg:index>
	<pg:prev>
		<a href="<%=pageUrl%>&act=list"><img src="/bloglog/admin/img/admin_ant.jpg" border="0"/></a>	
	</pg:prev>
	
	<pg:pages>
		<a href="<%= pageUrl %>&act=list"><%= pageNumber %></a>
	</pg:pages>
	
	<pg:next>
		<a href="<%= pageUrl%>&act=list"><img src="/bloglog/admin/img/admin_prox.jpg" border="0"/></a>
	</pg:next>
</pg:index>
</p>
</pg:pager>
<table align="center" border="0" cellpadding="0" cellspacing="0">
<tr><td>   
<%@ include file="/admin/includes/footer.html"%>
</td></tr></table>
</body>
</html>
