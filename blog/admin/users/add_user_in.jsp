<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"> 
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>


<html:html>
  <head>

<link rel="stylesheet" href="/blog/admin/includes/css/stylle.css" type="text/css">

</head>  
  
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table align="center" border="0" cellpadding="0" cellspacing="0">
<tr><td>
<%@ include file="/admin/includes/menu.jsp"%>
</td></tr>
<tr>
<td>
<br>
<br>

<html:form method="post" action="/admin/blogUser.do?act=add">
   <html:hidden property="id" value="${blogUser.id}"/>
   
  <p><br/>:::: USUARIO :::::<br/><br/>
    Nome: 
    <html:text property="firstName" value="${blogUser.firstName}" />
    <br/>
    Sobrenome: 
     <html:text property="lastName" value="${blogUser.lastName}"/>
    <br/>
    E -mail: 
    <html:text property="email" value="${blogUser.email}" />
    <br/>
    Status : 
    <html:select property="status" value="${blogUser.status}">
      <html:option value="-1">Selecione</html:option>
      <html:option value="1">Ativo</html:option>
      <html:option value="0">Inativo</html:option>
    </html:select>
    <br/>
    Login: 
    <html:text property="login" value="${blogUser.login}"/>
    <br/>
    Senha: 
    <html:password property="password" value="${blogUser.password}"/>
  </p>
  <p>
    <html:submit  value="Cadastrar"/>
  </p>
  
</html:form>

</td>
</tr>
<tr><td>
<p>  <center>
  <logic:present name="mensagem_erro">
  	<font color="red"><bean:write name="mensagem_erro"/></font>
  </logic:present>
  </center></p>
  </td>
  </tr>
  </table>
  <table align="center" border="0" cellpadding="0" cellspacing="0">
<tr><td>   
<%@ include file="/admin/includes/footer.html"%>
</td></tr></table>
</body>
</html:html>
