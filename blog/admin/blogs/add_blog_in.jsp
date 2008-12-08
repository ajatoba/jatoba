<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"> 
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<html:html>
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
<TABLE width="913" border="0" cellpadding="0" cellspacing="0" align="center">
  <TR>
    <th scope="col"> <%@ include file="/admin/includes/menu.jsp"%>
 
 <br/>
   
<html:form method="post" action="/admin/blog.do?act=add">
      <table width="859" border="0" cellpadding="0" cellspacing="0" align="center">
      <tr>
    <th colspan="4" scope="col"><img src="/bloglog/admin/img/header_criar_blog.jpg" width="859" height="41" /></th>
  </tr>
  <tr>
    <th scope="col"><div align="left" style="font-family:Arial, Helvetica, sans-serif; size:12px; color:#32332f">Nome:</div></th>
    <th scope="col"><div align="left" style="font-family:Arial, Helvetica, sans-serif; size:12px; color:#32332f">Path:</div></th>
    <th scope="col"><div align="left" style="font-family:Arial, Helvetica, sans-serif; size:12px; color:#32332f">Descrição:</div></th>
    <th scope="col"><div align="left" style="font-family:Arial, Helvetica, sans-serif; size:12px; color:#32332f">Status:</div></th>
  </tr>
  <tr>
    <th scope="col"><div align="left"><html:text property="name" style="border:1px #32332f solid; font-family:Arial, Helvetica, sans-serif; size:60px; font-size:10px;"/></div></th>
    <th scope="col"><div align="left"><html:text property="path" style="border:1px #32332f solid; font-family:Arial, Helvetica, sans-serif; size:60px; font-size:10px;"/></div></th>
    <th scope="col"><div align="left"><html:text property="description" style="border:1px #32332f solid; font-family:Arial, Helvetica, sans-serif; size:60px; font-size:10px;"/></div></th>
    <th scope="col"><div align="left"><html:select property="status" style="border:1px #32332f solid; font-family:Arial, Helvetica, sans-serif; font-size:10px;">
      <html:option value="-1">Selecione</html:option>
      <html:option value="1">Ativo</html:option>
      <html:option value="0">Inativo</html:option>
    </html:select></div></th>
  </tr>
  <tr>
    <th scope="col"><div align="left" style="font-family:Arial, Helvetica, sans-serif; size:12px; color:#32332f">Usuário:</div></th>
    <th scope="col"><div align="left" style="font-family:Arial, Helvetica, sans-serif; size:12px; color:#32332f">Template:</div></th>
    <th scope="col"></th>
    <th scope="col"></th>
  </tr>
  <tr>
    <th scope="col"><div align="left"><html:select property="bloggerUser">
        <logic:iterate name="users" id="u" indexId="i">
            <html:option value="${u.id}"><bean:write name="u" property="firstName"/></html:option>
        </logic:iterate>
    </html:select></div></th>
    <th scope="col"><div align="left"> <html:select property="templateId">
        <logic:iterate name="templates" id="t" indexId="i">
            <html:option value="${t.id}"><bean:write name="t" property="name"/></html:option>
        </logic:iterate>
    </html:select></div>
</th>
    <th scope="col"></th>
    <th scope="col"></th>
  </tr>
   <tr height="100">
    <th scope="row"></th>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th scope="row"><img src="/bloglog/admin/img/fundo_new_blog_01.jpg" width="215" height="73" /></th>
    <td><img src="/bloglog/admin/img/fundo_new_blog_02.jpg" width="215" height="73" /></td>
    <td><img src="/bloglog/admin/img/fundo_new_blog_03.jpg" width="289" height="73" /></td>
    <td><html:image src="/bloglog/admin/img/btn_enviar_new_blog.jpg" width="140" height="73" value="Cadastrar"/></td>
  </tr>
</table>
   
    
  
</html:form>

<p>  
	<center>
	  <logic:present name="mensagem_erro">
	  	<div style="font-family:Arial, Helvetica, sans-serif; size:12px; color:#32332f"><bean:write name="mensagem_erro"/></div>
	  </logic:present>
	</center>
</p>
<%@ include file="/admin/includes/footer.html"%></th>
  </TR>
</TABLE>
</body>
</html:html>
