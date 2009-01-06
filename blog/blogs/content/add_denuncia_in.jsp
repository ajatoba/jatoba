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

  	<logic:present name="mensagem_erro">
  	 	<font color="red"><bean:write name="mensagem_erro"/></font>
    </logic:present> 
    
    <logic:present name="comment_sucesso">
  		<font color="red"><bean:write name="comment_sucesso"/></font>
    </logic:present>  

<div class="add_den">
		<html:form method="post" action="/censure.do?act=censured">
		<html:hidden property="postId" value="<%=request.getParameter("id")%>"/>
		<html:hidden property="commentId" value="<%=request.getParameter("commentId")%>"/>
		<div class="topadd"><img src="/bloglog/content/add_coment/img_add/tit_denuncie.jpg" /></div>
		
		<div class="inputs">
			<div class="iptnome">
				<dt>Nome</dt>
				<dt><html:text property="name" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></dt>
			</div>
			<div class="iptemail">
				<dt>E-mail</dt>
				<dt><html:text property="email" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></dt>
			</div>
		</div>
		<div class="inputs">
			<div class="iptcoment">
				<dt>Comentário</dt>
				<dt><html:textarea property="message" style='background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; width:448px; height:80px; font-family:Trebuchet MS;'/></dt>
			</div>
		</div>
		
		<html:submit value="Enviar" style="float:right; margin-right:13px; margin-top:10px; background-color:#FF6600; color:#FFFFFF; font-weight:bold; border:1px solid #FF6600; cursor:hand;"/>
		</html:form>
		
</div>



</body>
</html>
