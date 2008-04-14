<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"> 
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html:html>
<body>
 <%@ include file="/admin/includes/menu.jsp"%>
 
 <br/>
   
<html:form method="post" styleId="quadrantForm" action="/admin/quadrant.do?act=add" enctype="multipart/form-data">
 <br/>
<html:hidden property="code"/>
    Titulo: 
    <html:text property="title" size="80"/>
    <br/>
    Descrição: 
    <html:textarea cols="55" rows="20" property="description"/>
    <br/>   
    URL: 
    <html:text property="url"/>
    <br/>
    Objeto Flash: 
    <html:textarea cols="55" rows="10" property="other"/>
    <br/>   
    Status : 
    <html:select property="status">
      <html:option value="-1">Selecione</html:option>
      <html:option value="1">Ativo</html:option>
      <html:option value="0">Inativo</html:option>
    </html:select>
    <br/>
    Blog: 
    <html:select property="blogId">
    	<logic:iterate name="blogs" id="b" indexId="i">
    		<html:option value="${b.id}"><bean:write name="b" property="name"/></html:option>
    	</logic:iterate>
    </html:select>
    <br/>
    Imagem: 
     <logic:notEmpty  name="quadrantForm" property="urlFoto">
     	<img src="<bean:write name="quadrantForm" property="urlFoto" />" />
     </logic:notEmpty>
    <html:text property="urlFoto"/>
    <html:file property="image"></html:file>
    <br/> 
    
    <logic:equal name="quadrantForm" property="acao" value="inserir">
    	<html:submit value="Cadastrar"/>
    </logic:equal>
    
    <logic:equal name="quadrantForm" property="acao" value="alterar">
    	<script>
    		var form = document.getElementById('quadrantForm');
    		form.action = '/blog/admin/quadrant.do?act=update';
    	</script>
    	<html:submit value="Alterar"/>
    </logic:equal>
    
    
    
  <logic:messagesPresent property="erro"> 
			<html:messages property="erro" id="erro" >
				<script>
					alert('<bean:write name="erro"/>');
				</script>			    
			</html:messages>
		</logic:messagesPresent>
</html:form>

 
	
<%@ include file="/admin/includes/footer.html"%>
</body>
</html:html>
