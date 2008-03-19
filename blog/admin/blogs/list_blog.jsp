<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <body>
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

<pg:pager url="blog.do" maxIndexPages="5" maxPageItems="150">

<table border ="0" ><tr><td><a href="/blog/admin/blog.do?act=addForm">[[NOVO]]</a></td></tr></table>
<table width="770" border="0">
  <tr bgcolor="#FFFFCC"> 
    <td width="5%"><b>Id</b></td>
    <td><b>Nome</b></td>
    <td width="10%"><b>Data</b></td>
    <td width="5%" align="center"><b>Status</b></td>
    <td width="20%"><b></b></td>
  </tr>
  
  <logic:iterate name="blogs" id="b" indexId="i">
  <pg:item>
  <tr>   
    <td width="5%"><bean:write name="b" property="id"/></td>
    <td><bean:write name="b" property="name"/></td>
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
    <td width="20%"><a href="/blog/admin/blog.do?act=load&id=<bean:write name="b" property="id"/>">[[ALTERAR]]</a> <a href="#" onclick="confirmaDelecao('/blog/admin/blog.do?act=delete&id=<bean:write name="b" property="id"/>')">[[DELETAR]]</a>
    <a href="/blog/blogs/admin/post.do?act=list&blogId=<bean:write name="b" property="id"/>">[[POSTS]]</a>
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
