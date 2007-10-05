<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<body>
<%@ include file="/admin/includes/menu.jsp"%>
<br>


<logic:present name="mensagem_erro">
	<font color="red"><bean:write name="mensagem_erro"/></font>
</logic:present> 

<logic:present name="topic_sucesso">
	<font color="red"><bean:write name="topic_sucesso"/></font>
</logic:present>  	


<table border ="0" ><tr><td><a href="/blog/admin/topic.do?act=addForm">[[NOVO]]</a></td></tr></table>
<table width="770" border="0">
  <tr bgcolor="#FFFFCC"> 
    <td><b>Id</b></td>
    <td><b>Titulo</b></td>
    <td><b>Descrição</b></td>
    <td><b>Status</b></td>
    <td><b></b></td>
  </tr>
  
  <logic:iterate name="topics" id="t" indexId="i">
  <tr>   
    <td><bean:write name="t" property="id"/> </td>
    <td><a href="/blog/admin/message.do?act=list&topic=${t.id}"><bean:write name="t" property="title"/></a></td>
    <td><bean:write name="t" property="description"/></td>
    <td>
    <c:choose>
	    <c:when test="${t.status == 1}">
		    <a href="/blog/admin/topic.do?act=updateStatus&id=<bean:write name="t" property="id"/>&status=0" alt="Clique para desativar">
		    <img src="/bloglog/admin/img/ativo.jpg" border="0" />
		    </a>
	    </c:when>
	    <c:otherwise>
		    <a href="/blog/admin/topic.do?act=updateStatus&id=<bean:write name="t" property="id"/>&status=1" alt="Clique para ativar">	    
		    <img src="/bloglog/admin/img/inativo.jpg" border="0" />
		    </a>
	    </c:otherwise>
    </c:choose>
    </td>
    <td width="18%"><a href="/blog/admin/topic.do?act=load&id=<bean:write name="t" property="id"/>">[[ALTERAR]]</a> <a href="/blog/admin/topic.do?act=delete&id=<bean:write name="t" property="id"/>">[[DELETAR]]</a></td>
  </tr>
  </logic:iterate>
</table>
<%@ include file="/admin/includes/footer.html"%>
</body>
</html>
