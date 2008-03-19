<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=iso-8859-1"%>


<%@page import="com.vanguarda.blog.util.*"%>


 <%
 String rndCrypt = RandomTool.createCrypted();
 %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title> :: BlogLog ::  </title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
 
</head>
<script>
var maximo = 1024;
function contaText(input)
{
	var getValInput = input.value.length;
	if (maximo >= getValInput)
	{
		var divMostraNumero = document.getElementById("nRestantes").innerHTML = maximo - getValInput;
		
	}else{
		var divMostraNumero = document.getElementById("nRestantes").innerHTML = 0;
		input.value = input.value.substring(0,maximo);
		alert('número maximo de caracteres atingido');
	
	}
	
	divMostraNumero = null;
	nDigitados = null;
}
</script>

<body style="background-color:#E3F0F9; font-family:Trebuchet MS; margin:0px;">
	
<html:form method="post" action="/comment.do?act=add">
		<html:hidden property="postId" value="<%=request.getParameter("postId")%>"/>
		<html:hidden property="countComments" value="<%=request.getParameter("countComments")%>"/>
		<html:hidden property="path" value="<%=request.getParameter("path")%>"/>
		<html:hidden property="from" value="site"/>
		<html:hidden property="status" value="<%=request.getParameter("status")%>"/>
		<html:hidden property="wordEnc" value="<%=rndCrypt%>"/>
		<html:hidden property="blogId"  value="<%=request.getParameter("blogId")%>"/>
		
<div style=" margin-left:5px; margin-right:5px; width:570px; background-color:#FFFFFF; height:395px;">

	<logic:present name="mensagem_erro">
  	 	<font style="font-size:11px; font-weight:bold; color:red; font-family:Trebuchet MS;">
		<bean:write name="mensagem_erro"/>
		</font>
    </logic:present> 
    
	<logic:present name="comment_sucesso"> 
		<font style="font-size:11px; font-weight:bold; color:red; font-family:Trebuchet MS;">
		<bean:write name="comment_sucesso"/>
		</font>
	</logic:present>
	
		<div style="height:23px; background-color:#147ABA;"><img src="/bloglog/content/add_coment/img_add/tit_add.jpg" style="margin-left:12px;"/></div>
		
		<div style=" width:452px; display:table; margin-left:auto; margin-right:auto; margin-top:65px;">
			<div style="width:240px; float:left;">
				<dt style="font-size:11px; font-weight:bold; color:#147ABA;">Nome</dt>
				<dt><html:text property="commentatorName" readonly="true" value="${user.firstName}" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></dt>
			</div>
			<div style="float:left; width:210px;">
				<dt style="font-size:11px; font-weight:bold; color:#147ABA;">E-mail</dt>
				<dt><html:text property="commentatorEmail" readonly="true" value="${user.email}" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></dt>
			</div>
		</div>
		<div style="width:452px; display:table; margin-left:auto; margin-right:auto; margin-top:8px;">
			<div style="float:left; width:452px;">
				<dt style="font-size:11px; font-weight:bold; color:#147ABA;">Comentário</dt>
				<dt><html:textarea property="content" cols="40" rows="10" style='background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; width:448px; height:80px; font-family:Trebuchet MS;' onkeyup='contaText(this);'/>	</dt>
			</div>
		</div>
		
		<%--
		<div style="width:450px; margin-left:auto; margin-right:auto; font-size:10px; color:#848484; height:27px; background:url(../img_add/ptos2.jpg) bottom repeat-x;">limite de 1024 caracteres | restantes : <span id="nRestantes"><script>document.write(maximo);</script></span></div>
		<img src="/blog/servlet/rndImage?rndimg=<%=rndCrypt%>" border="1" style='float:left; margin-top:8px; margin-left:58px; margin-right:12px;'/>
		<div style="margin-right:auto; margin-top:15px; float:left; font-size:10px; color:#757575;">
		Digite oque se vê<br /> escrito na imagem:
		</div>				
		<html:text property="imageword" maxlength="6" value="<%=rndCrypt%>" style='float:left; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:13px; line-height:16px; font-family:Trebuchet MS; margin-top:20px; margin-left:12px; width:44px; text-align:center;' />
		--%>
		
		<html:submit value="Enviar"  style="float:right; margin-right:58px; margin-top:18px; background-color:#FF6600; color:#FFFFFF; font-weight:bold; border:1px solid #FF6600; cursor:hand;"/>
		 <font color="red">
    
    <%--
    if(request.getParameter("message2")!= null && request.getParameter("message2").length() > 0)
    {
    	out.println("<br><br><br><center>Entre com o valor da imagem corretamente.</center>");
    }
    --%>
    </font>
	</div>

      
	</html:form>
	</body>