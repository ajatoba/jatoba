<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=iso-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title> :: BlogLog ::  </title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="/bloglog/content/add_coment/css/add_comentario.css" rel="stylesheet" type="text/css" /> 
</head>

<body bgcolor="#E3F0F9" text="#000000">

  	<logic:present name="error">
  	 	<font color="red"><bean:write name="error"/></font>
    </logic:present> 
    
    <logic:present name="sucesso">
  		<font color="red"><bean:write name="sucesso"/></font>
    </logic:present>  

<div class="add_den">
		<html:form method="post" action="/recomendation.do?act=sendRecomendation">
		<html:hidden property="postId" value="<%=request.getParameter("postId")%>"/>
		
		<div class="topadd"><img src="/bloglog/content/add_coment/img_add/tit_indique.jpg" /></div>
		
		<div class="inputs">
			<div class="iptnome">
				<dt>Nome</dt>
				<dt><html:text property="fromName" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></dt>
			</div>
			<div class="iptemail">
				<dt>E-mail</dt>
				<dt><html:text property="fromMail" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></dt>
			</div>
		</div>
		<div class="inputs">
			<div class="iptnome">
				<dt>Nome do Destinatario</dt>
				<dt><html:text property="toName" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></dt>
			</div>
			<div class="iptemail">
				<dt>E-mail  do Destinatario</dt>
				<dt><html:text property="toMail" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></dt>
			</div>
		</div>
		
		<html:submit value="Enviar" style="float:right; margin-right:13px; margin-top:10px; background-color:#FF6600; color:#FFFFFF; font-weight:bold; border:1px solid #FF6600; cursor:hand;"/>
		</html:form>
		
</div>



</body>
</html>
