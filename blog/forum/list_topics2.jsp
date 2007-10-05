<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<link rel="stylesheet" href="/blog/forum/includes/css/stylle.css" type="text/css">
	<title>Fórum Bloglog</title>
</head>

<logic:present name="mensagem_erro">
	<font color="red"><bean:write name="mensagem_erro"/></font>
</logic:present> 

<logic:present name="topic_sucesso">
	<font color="red"><bean:write name="topic_sucesso"/></font>
</logic:present>  	

<H1>== FÓRUM BLOGLOG ==</H1>

<H2>TÓPICOS</H2>

<table border ="0" >

  <logic:iterate name="topics" id="t" indexId="i">
  <tr>   
    <td>
    	<a href="/blog/message.do?act=list&topic=${t.id}">
    	<bean:write name="t" property="title"/>
    	</a>
    	<br/>
    	<bean:write name="t" property="description"/>
    	
    	<br/><br/>
    </td>
  </tr>
  </logic:iterate>
</table>
</body>
</html>
