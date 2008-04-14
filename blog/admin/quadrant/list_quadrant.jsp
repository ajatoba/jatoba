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
		
		if(confirm('Deseja realmente excluir esse quadrante?'))
		{
			document.location=url;
		}
		else
		{
			return;
		}
		
	}
	
	function confirmaPublicacao(url)
	{
		
		if(confirm('Deseja realmente gerar a home? O Arquivo index.html será substituido'))
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

<pg:pager url="/blog/admin/quadrant.do" maxIndexPages="5" maxPageItems="150">
 <br/>
<table border ="0" >
<tr>
<td>
<a href="/blog/admin/quadrant.do?act=form">[[NOVO]]</a>
</td>
<td>
<a href="/blog/admin/quadrant.do?act=preview">[[Gerar preview]]</a>
</td>
<td>
<a href="javascript:confirmaPublicacao('/blog/admin/quadrant.do?act=publish');">[[Gerar Home]]</a>
</td>
<td>
<logic:present name="page">
<a href="<bean:write name="page" />" target="_blank">[[Visualizar home]]</a>
</logic:present>

</td>

</tr></table>
<html:messages id="sucesso" name="sucesso" message="true">
										
				<bean:write name="sucesso" />
				<br>
				<br>										

</html:messages>

<table width="770" border="0">
  <tr bgcolor="#FFFFCC"> 
    <td width="5%"><b>Codigo</b></td>
    <td><b>Nome</b></td>   
    <td width="5%" align="center"><b>Status</b></td>
    <td width="20%"><b></b></td>
  </tr>
  
  <logic:iterate name="quadrants" id="q" indexId="i">
  <pg:item>
  <tr>   
    <td width="5%"><bean:write name="q" property="code"/></td>
    <td><bean:write name="q" property="title"/></td>    
    <td width="5%" align="center">
    <c:choose>
    <c:when test="${q.status == 1}">
	    <img src="/bloglog/admin/img/ativo.jpg" border="0" />		    
    </c:when>
    <c:otherwise>	    
	    <img src="/bloglog/admin/img/inativo.jpg" border="0" />
    </c:otherwise>
    </c:choose>
    </td>
    <td width="20%"><a href="/blog/admin/quadrant.do?act=load&id=<bean:write name="q" property="code"/>">[[ALTERAR]]</a> <a href="#" onclick="confirmaDelecao('/blog/admin/quadrant.do?act=delete&id=<bean:write name="q" property="code"/>')">[[DELETAR]]</a>
    
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
