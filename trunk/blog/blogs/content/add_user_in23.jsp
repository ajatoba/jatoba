<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="iso-8859-1"?>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@page import="com.vanguarda.blog.util.*"%>

<%
String postId =  request.getParameter("postId");
String countComments = request.getParameter("countComments");
String path = request.getParameter("path");
String from = "site";
String status = request.getParameter("status");

String rndCrypt = RandomTool.createCrypted();
%>


<html:html>
<HEAD>
    <TITLE>BlogLog</TITLE>    
	<link href="/bloglog/content/add_coment/css/add_comentario.css" rel="stylesheet" type="text/css" />
</HEAD>

<BODY>

<center>
	<logic:present name="mensagem_erro">
		<font color="red"><bean:write name="mensagem_erro"/></font>
	</logic:present>
</center>

<html:form method="post" action="user.do?act=add">

<html:hidden property="id" />
<html:hidden property="postId" value="0"/>
<html:hidden property="countComments" value="0"/>
<html:hidden property="path" value=""/>
<html:hidden property="from" value="home"/>
<html:hidden property="status" value="<%=status%>"/>
<html:hidden property="wordEnc" value="<%=rndCrypt%>"/>

<table width="450" align="center" style="font-size:11px; font-weight:bold; color:#147ABA;" border="0">
<tr>
	<td width="25%" >Nome:</td>
	<td><html:text property="firstName" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></td>
</tr>
<tr>
	<td width="25%">Email:</td>
	<td><html:text property="email" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;' /></td>
</tr>
<tr>
	<td width="25%">Sexo:</td>
	<td><input type="radio" name="gender" value="M" style='background-color:#F8F8F8; border:1px solid #B7D3E9; ' selected/> Masculino&nbsp;&nbsp;
	<input type="radio"  name="gender" value="F" style='background-color:#F8F8F8; border:1px solid #B7D3E9; '/> Feminino</td>
</tr>
<tr>
	<td width="25%">Data de Nascimento:</td>
	<td><input name="dia" type="text" size="2" style='height:16px; width:20px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/>/<input name="mes" type="text" size="2" style='height:16px; width:20px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/>/<input name="ano" type="text" size="4" style='height:16px; width:50px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></td>
</tr>
<tr>
	<td width="25%">Estado:</td>
	<td><html:select property="state" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'>
			
			<html:option  value="AC">Acre</html:option>
			<html:option  value="AL">Alagoas</html:option>
			<html:option  value="AP">Amap&#225;</html:option>
			<html:option  value="AM">Amazonas</html:option>
			<html:option  value="BA">Bahia</html:option>

			<html:option  value="CE">Cear&#225;</html:option>
			<html:option  value="DF">Distrito Federal</html:option>
			<html:option  value="ES">Esp&#237;rito Santo</html:option>
			<html:option  value="GO">Goias</html:option>
			<html:option  value="MA">Maranh&#227;o</html:option>

			<html:option  value="MT">Mato Grosso</html:option>
			<html:option  value="MS">Mato Grosso do Sul</html:option>
			<html:option  value="MG">Minas Gerais</html:option>
			<html:option  value="PA">Par&#225;</html:option>
			<html:option  value="PB">Para&#237;ba</html:option>
			<html:option  value="PR">Paran&#225;</html:option>

			<html:option  value="PE">Pernambuco</html:option>
			<html:option  value="PI">Piau&#237;</html:option>
			<html:option  value="RJ">Rio de Janeiro</html:option>
			<html:option  value="RN">Rio Grande do Norte</html:option>
			<html:option  value="RS">Rio Grande do Sul</html:option>
			<html:option  value="RO">Rond&#244;nia</html:option>

			<html:option  value="RR">Roraima</html:option>
			<html:option  value="SC">Santa Catarina</html:option>
			<html:option  value="SP">S&#227;o Paulo</html:option>
			<html:option  value="SE">Sergipe</html:option>
			<html:option  value="TO">Tocantins</html:option>


			
		</html:select>
	
	</td>
</tr>
<tr>
	<td width="25%">Cidade:</td>
	<td><html:text property="city" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></td>
</tr>

<tr>
	<td width="25%">Login:</td>
	<td><html:text property="login" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></td>
</tr>
<tr>
	<td width="25%">Senha:</td>
	<td><html:password property="password" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></td>
</tr>
<tr>
	<td colspan="2" align="center">
		Digite o que se vê escrito na imagem:<br>	
		<img src="/blog/servlet/rndImage?rndimg=<%=rndCrypt%>" border="0"/>	
		<br>
		<html:text property="imageword" maxlength="6" style='height:16px; width:70px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">

		<logic:present name="mensagem_imagem_incorreta">
			<font color="red"><bean:write name="mensagem_imagem_incorreta"/></font>
		</logic:present>
	
	</td>
</tr>
<tr>
<td colspan="2"><html:submit  value="Cadastrar" style="float:left; margin-right:13px; margin-top:18px; background-color:#FF6600; color:#FFFFFF; font-weight:bold; border:1px solid #FF6600; cursor:hand;"/></td>
</tr>
</table>


</html:form>

</BODY>

</html:html>
