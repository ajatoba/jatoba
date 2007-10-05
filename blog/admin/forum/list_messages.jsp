<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<link rel="stylesheet" href="/blog/admin/includes/css/stylle.css" type="text/css">
</head>
<body>
<%@ include file="/admin/includes/menu.jsp"%>
<br>

<H2>Mensagens</H2>

<a href="/blog/admin/topic.do?act=list">[[Voltar aos Tópicos]]</a><br/><br/>

<logic:present name="mensagem_erro">
<font color="red"><bean:write name="mensagem_erro"/><br/></br></font>
</logic:present> 

<logic:present name="message_sucesso">
<font color="red"><bean:write name="message_sucesso"/><br/></br></font>
</logic:present>  	

<logic:present name="no_messages">
<font color="red"><bean:write name="no_messages"/><br/></br></font>
</logic:present>  	


<table border ="0" width="50%">

  <logic:iterate name="messages" id="m" indexId="i">
  <tr>   
    <td bgcolor="#FFFFCC" >
    	<b><bean:write name="m" property="title"/></b><br/>
    	<i>
    	<c:if test="${m.user.name != null}" >
    	de ${m.user.name}
    	</c:if>
    	em ${m.insertDate.date}/${m.insertDate.month+1}/${m.insertDate.year+1900} <!--às ${m.insertDate.hours}:${m.insertDate.minutes}--></i><br/>
    	<bean:write name="m" property="content"/><br/>
    	<br/>
    	<a href="/blog/admin/message.do?act=delete&id=${m.id}&topic=${m.topic.id}">[[EXCLUIR]]</a>
    </td>
  </tr>
  </logic:iterate>
</table>
</body>
</html>
