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

	function excluir(){
		var boxes = document.getElementsByName("comment");
		var idsToDelete = '';
		for(var i = 0; i < boxes.length; i++){
			if(boxes[i].checked){
				idsToDelete = (idsToDelete + ',' +  boxes[i].value) ;
				
			}
		}
		document.deleteComments.idsToDelete.value=idsToDelete.substring(1);
		document.deleteComments.submit();
	}
</script>
<table align="center" border="0" cellpadding="0" cellspacing="0">
<tr><td>   
<%@ include file="/admin/includes/menu.jsp"%>
</td></tr></table>
<br>
<%
String userEmail = request.getParameter("userEmail");
%>
<pg:pager url="/blog/admin/comment.do" maxIndexPages="5" maxPageItems="500">

<table id="Table_01" width="864" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<form action="comment.do?act=searchAll" name="buscaComentarios" method=POST>
				<input type="text" name="userEmail" value="Digite o E-mail do visitante" size="50"/><input type="submit" value="Buscar"/>
			</form>
		
		</td>
		
	</tr>
</table>

    <logic:present name="mensagem_erro">
        <bean:write name="mensagem_erro"/>
    </logic:present>
    <logic:present name="mensagem_sucesso">
        <bean:write name="mensagem_sucesso"/>
    </logic:present>
 

<form name="deleteComments" action="comment.do?act=deleteComments" method="POST"> 

<table width="864" align="center" border="0" cellpadding="0" cellspacing="0">
	
  <logic:iterate name="comments" id="comment" indexId="i">
  <pg:item>
 
  <tr>   
    <td width="2"><span style="font-family:Arial, Helvetica, sans-serif; font-size:18px; font-weight:bold; color:#3998f7"><input type="checkbox" name="comment" value="<bean:write name="comment" property="id"/>"/></span></td>
    <td width="100"><span style="font-family:Arial, Helvetica, sans-serif; font-size:18px; font-weight:bold; color:#3998f7"><bean:write name="comment" property="title"/></span></td>
    <td width="364"><span style="font-family:Arial, Helvetica, sans-serif; font-size:16px; font-weight:bold; color:#2f3439"><bean:write name="comment" property="content"/></span></td>
    <td width="364"><span style="font-family:Arial, Helvetica, sans-serif; font-size:16px; font-weight:bold; color:#2f3439"><bean:write name="comment" property="commentatorName"/></span></td>
    <td width="100"><bean:write name="comment" property="insertDate" format="dd/MM/yyyy"/></td>
  </tr>
  
  
  </pg:item>
  </logic:iterate>
<tr>
  	 <td colspan="5">
  	 <input type="hidden" name="idsToDelete"/>
  	 <input type="button" onClick="javascript:excluir();" value="Excluir"/>
  	 </td>
  </tr>
</table>

</form>

<p align="center">
<pg:index>
	<pg:prev>
		<a href="<%=pageUrl%>&act=searchAll&userEmail=<%=userEmail%>"><img src="/bloglog/admin/img/admin_ant.jpg" border="0"/></a>	
	</pg:prev>
	
	<pg:pages>
		<a href="<%= pageUrl %>&act=searchAll&userEmail=<%=userEmail%>"><%= pageNumber %></a>
	</pg:pages>
	
	<pg:next>
		<a href="<%= pageUrl%>&act=searchAll&userEmail=<%=userEmail%>"><img src="/bloglog/admin/img/admin_prox.jpg" border="0"/></a>
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
