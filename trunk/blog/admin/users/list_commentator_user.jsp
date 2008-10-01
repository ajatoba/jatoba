<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/pager-taglib" prefix="pg"%>
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
<body>
<table align="center" border="0" cellpadding="0" cellspacing="0">
<tr><td>
<%@ include file="/admin/includes/menu.jsp"%>
</td></tr></table>
<br>

<pg:pager url="blogUser.do" maxIndexPages="5" maxPageItems="150">

	
	<br />

<table width="857" align="center" height="37" border="0" cellpadding="0" cellspacing="0" background="/bloglog/admin/img/admin_master_fundo_01.jpg">
  <tr>
    <th width="140" scope="col"><a href="users/add_user_in.jsp"><img src="/bloglog/admin/img/criar_blogueiro.jpg" width="123" height="24" border="0" /></a></th>
    <th>
    <html:form action="/admin/userCommentator.do?act=search" method="post">
     <table width="100%">
        <tr>
            <th><table border="0" align="center">
        <tr>
        <td>${countUsers} usuários</d>
        <td></td>

        </tr>
    </table></th>
            <th scope="col"><div align="right"><span style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #2D363C;">nome : <html:text property="firstName" size="10"/></span></div> 
    <th width="180" scope="col"><div align="left"><span style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #2D363C;">sobrenome : 
      <html:text property="lastName" size="10"/>
    </span></div></th>
    <th width="140" scope="col"><div align="left"><span style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #2D363C;">email : 
      <html:text property="email" size="10"/>
    </span></div></th>
    <th width="80" scope="col"><html:submit value="Buscar"/></th>
        </tr>
     </table>
     </html:form>
    </th>
  </tr>
</table>	
	

	<table width="857" border="0" cellpadding="0" cellspacing="0" align="center">
		<tr bgcolor="#51585E">
			<td width="200"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">blogueiro</span></td>
			<td width="300"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">email</span></td>
			<td width="150"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">login</span></td>
			<td width="50"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">status</span></td>
			<td width="50"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">excluir</span></td>
			<td widht="50"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">editar</span></td>
			<td widht="57"><span style="font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#FFFFFF">ação</span></td>
			
		</tr>

		<logic:iterate name="users" id="u" indexId="i">
			<pg:item>
				<tr>
					<td width="200"><span style="font-family:Arial, Helvetica, sans-serif; font-size:16px; font-weight:bold; color:#2f3439"><bean:write name="u" property="firstName" /></span></td>
					<td width="300"><bean:write name="u" property="email" /></td>
					<td width="150"><bean:write name="u" property="login" /></td>
					<td width="50"><c:choose>
						<c:when test="${u.status == 1}">
							<img src="/bloglog/admin/img/ativo.jpg" border="0" />
						</c:when>
						<c:otherwise>
							<img src="/bloglog/admin/img/inativo.jpg" border="0" />
						</c:otherwise>
					</c:choose></td>
					<td width="50"><a
                        href="/blog/admin/blogUser.do?act=delete&id=<bean:write name="u" property="id"/>"><img src="/bloglog/admin/img/btn_exc.jpg" border="0"/></a></td>
					<td width="50"><a
                        href="/blog/admin/blogUser.do?act=load&id=<bean:write name="u" property="id"/>"><img src="/bloglog/admin/img/btn_edit.jpg" border="0"/></a></td>
					
					<td width="57"><c:choose>
						<c:when test="${u.status == 1}">
							<a
								href="/blog/admin/userCommentator.do?act=blockUser&userId=<bean:write name="u" property="id"/>&action=2">[[BLOQUEAR]]</a>
						</c:when>
						<c:otherwise>
							<a
								href="/blog/admin/userCommentator.do?act=blockUser&userId=<bean:write name="u" property="id"/>&action=1">[[ATIVAR]]</a>
						</c:otherwise>
					</c:choose></td>

				</tr>
			</pg:item>
		</logic:iterate>
	</table>

	<p align="center"><pg:index>
		<pg:prev>
			<a href="<%=pageUrl%>&act=list"><img src="/bloglog/admin/img/admin_ant.jpg" border="0"/></a>
		</pg:prev>

		<pg:pages>
			<span style="font-family:Arial, Helvetica, sans-serif; font-size:14px; font-weight:bold; color:#2f3439"><a href="<%= pageUrl %>&act=list"><%= pageNumber%></a></span>
		</pg:pages>

		<pg:next>
			<a href="<%= pageUrl%>&act=list"><img src="/bloglog/admin/img/admin_prox.jpg" border="0"/></a>
		</pg:next>
	</pg:index></p>

</pg:pager>

<table align="center" border="0" cellpadding="0" cellspacing="0">
<tr><td>   
<%@ include file="/admin/includes/footer.html"%>
</td></tr></table>
</body>
</html>
