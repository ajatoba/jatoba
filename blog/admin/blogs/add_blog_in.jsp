<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"> 
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<html:html>
<body>
 <%@ include file="/admin/includes/menu.jsp"%>
 
 <br/>
   
<html:form method="post" action="/admin/blog.do?act=add">
    Nome: 
    <html:text property="name" size="80"/>
    <br/>
    Descrição: 
    <html:textarea cols="55" rows="20" property="description"/>
    <br/>   
    Path: 
    <html:text property="path"/>
    <br/>
    Status : 
    <html:select property="status">
      <html:option value="-1">Selecione</html:option>
      <html:option value="1">Ativo</html:option>
      <html:option value="0">Inativo</html:option>
    </html:select>
    <br/>
    Usuario: 
    <html:select property="bloggerUser">
    	<logic:iterate name="users" id="u" indexId="i">
    		<html:option value="${u.id}"><bean:write name="u" property="firstName"/></html:option>
    	</logic:iterate>
    </html:select>
    <br/> 
    <br/>
    Template: 
    <html:select property="templateId">
    	<logic:iterate name="templates" id="t" indexId="i">
    		<html:option value="${t.id}"><bean:write name="t" property="name"/></html:option>
    	</logic:iterate>
    </html:select>
    <br/>   
    <html:submit value="Cadastrar"/>
  
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
