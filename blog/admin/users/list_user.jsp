<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
<%@ include file="/admin/includes/menu.jsp"%>
<br>

<pg:pager url="blogUser.do" maxIndexPages="5" maxPageItems="150">

<table border ="0" ><tr><td><a href="users/add_user_in.jsp">[[NOVO]]</a></td></tr></table>
<html:form action="/admin/blogUser.do?act=search" method="post">
		<table border="0">
			<tr>
				<td colspan="2"><b>Busca</b></td>
				
			</tr>
			<tr>
				<td>Nome:</td>
				<td><html:text property="firstName"/> </td>
			</tr>
			<tr>
				<td>Sobrenome:</td>
				<td><html:text property="lastName"/> </td>
			</tr>
			<tr>
				<td>Email</td>
				<td><html:text property="email"/></td>
			</tr>
			<tr>
				<td> <html:submit value="Buscar"/> </td>
				<td></td>
			</tr>
		</table>
	</html:form>
<table width="770" border="0">
  <tr bgcolor="#FFFFCC"> 
    <td width="30%"><b>Nome</b></td>
    <td width="30%"><b>E-mail</b></td>
    <td width="15%"><b>Login</b></td>
    <td width="5%"><b>Status</b></td>
    <td ><b></b></td>
  </tr>
  
  <logic:iterate name="users" id="u" indexId="i">
  <pg:item>
  <tr>   
    <td width="30%"><bean:write name="u" property="firstName"/> </td>
    <td width="30%"><bean:write name="u" property="email"/></td>
    <td width="15%"><bean:write name="u" property="login"/></td>
    <td width="5%">
    <c:choose>
    <c:when test="${u.status == 1}">
	    <img src="/bloglog/admin/img/ativo.jpg" border="0" />		    
    </c:when>
    <c:otherwise>	    
	    <img src="/bloglog/admin/img/inativo.jpg" border="0" />
    </c:otherwise>
    </c:choose>
    </td>
    <td ><a href="/blog/admin/blogUser.do?act=load&id=<bean:write name="u" property="id"/>">[[ALTERAR]]</a> <a href="/blog/admin/blogUser.do?act=delete&id=<bean:write name="u" property="id"/>">[[DELETAR]]</a></td>
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