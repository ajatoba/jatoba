<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"> 
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<html:html>
<body>
 <%@ include file="/admin/includes/menu.jsp"%>
 
 <br/>
   
<html:form method="post" action="/admin/blog.do?act=update">
<html:hidden property="id" value="${blog.id}"/>
  <br/>:::: Blog :::::<br/><br/>
    Nome: 
    <html:text property="name" value="${blog.name}"/>
    <br/>
    Descrição: 
    <html:text property="description" value="${blog.description}"/>
    <br/>    
    Status : 
    <html:select property="status" value="${blog.status}">
      <html:option value="-1">Selecione</html:option>
      <html:option value="1">Ativo</html:option>
      <html:option value="0">Inativo</html:option>
    </html:select>
    <br/>
    Usuario: ${blog.bloggerUser.firstName}
    
    <br/>  
     <br/>
    Template: 
    <html:select property="templateId" value="${template.id}">
    	<logic:iterate name="templates" id="t" indexId="i">
    		<html:option value="${t.id}"><bean:write name="t" property="name"/></html:option>
    	</logic:iterate>
    </html:select>
    <br/>   
    <html:submit value="Alterar"/>
  
</html:form>

<p>  
	<center>
	  <logic:present name="mensagem_erro">
	  	<font color="red"><bean:write name="mensagem_erro"/></font>
	  </logic:present>
	</center>
</p>
<%@ include file="/admin/includes/footer.html"%>
</body>
</html:html>
