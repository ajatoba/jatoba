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
	<link href="/bloglog/content/add_coment/css/cadastro.css" rel="stylesheet" type="text/css" />
</HEAD>

<BODY>

<center>
	<logic:present name="mensagem_erro">
		<font color="red"><bean:write name="mensagem_erro"/></font>
	</logic:present>
</center>

<html:form method="post" action="user.do?act=add">
<html:hidden property="id" />
<html:hidden property="postId" value="<%=postId%>"/>
		<html:hidden property="countComments" value="<%=countComments%>"/>
		<html:hidden property="path" value="<%=path%>"/>
		<html:hidden property="from" value="site"/>
		<html:hidden property="status" value="<%=status%>"/>
		<html:hidden property="wordEnc" value="<%=rndCrypt%>"/>

<div id="r_meio_int">
  <div class="meio_int">
  
<div class="titulos"><img src="/bloglog/content/add_coment/img_add/tit_cadastro.jpg" style="margin-left:12px; margin-bottom:none;"/>    </div>
	<div class="tit_internas">Participe da comunidade Bloglog!  Fa&ccedil;a seus coment&aacute;rios, deixe sua opini&atilde;o, converse com seu &iacute;dolo </div>
	
	<div class="itCad">
	  <div class="frst_inp">
			<dt class="label">Nome</dt>
			<dt><html:text property="firstName" style=" width:239px; height:18px; border:1px solid #A1C4E0; background-color:#F8F8F8;"/></dt>
			<dt></dt>
		</div>
		<div class="sep1_inp"></div>
		<div class="seco_inp">
			<dt class="label">E-mail</dt>
			<dt><html:text property="email" style=" width:250px; height:18px; border:1px solid #A1C4E0; background-color:#F8F8F8;"/></dt>
			<dt class="check"><input name="" type="checkbox" value="" /> Desejo receber informações do bloglog.</dt>
		</div>
	</div>
 	<div class="itCad2">
 	  <div class="frst_inp2">
			<dt class="label">Sexo</dt>
			<dt class="check"><input type="radio" name="gender" value="M" selected/> Masculino&nbsp;&nbsp;
		    <input type="radio"  name="gender" value="F"/> Feminino</dt>
			<dt></dt>
		</div>
		<div class="sep2_inp"></div>
		<div class="seco_inp">
			<dt class="label">Data de Nascimento </dt>
			<dt><input name="dia" type="text" style=" width:30px; height:18px; border:1px solid #A1C4E0; background-color:#F8F8F8;"/> 
			/
			  <input name="mes" type="text" style=" width:30px; height:18px; border:1px solid #A1C4E0; background-color:#F8F8F8;"/> 
			  /
			  <input name="ano" type="text" style=" width:39px; height:18px; border:1px solid #A1C4E0; background-color:#F8F8F8;"/></dt>
			<dt class="check">dia&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; mês &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ano</dt>
		</div>
	</div>
	
	<div class="itCad3">
	  <div class="frst_inp">
			<dt class="label">Estado</dt>
			<dt><html:select property="state" style="width:239px; height:18px; border:1px solid #A1C4E0; background-color:#F8F8F8;">
			<html:option  value="AC">Acre</html:option>
			<html:option  value="AL">Alagoas</html:option>
			<html:option  value="AP">Amapá</html:option>
			<html:option  value="AM">Amazonas</html:option>
			<html:option  value="BA">Bahia</html:option>

			<html:option  value="CE">Ceará</html:option>
			<html:option  value="DF">Distrito Federal</html:option>
			<html:option  value="ES">Espírito Santo</html:option>
			<html:option  value="GO">Goias</html:option>
			<html:option  value="MA">Maranhão</html:option>

			<html:option  value="MT">Mato Grosso</html:option>
			<html:option  value="MS">Mato Grosso do Sul</html:option>
			<html:option  value="MG">Minas Gerais</html:option>
			<html:option  value="PA">Pará</html:option>
			<html:option  value="PB">Paraíba</html:option>
			<html:option  value="PR">Paraná</html:option>

			<html:option  value="PE">Pernambuco</html:option>
			<html:option  value="PI">Piauí</html:option>
			<html:option  value="RJ">Rio de Janeiro</html:option>
			<html:option  value="RN">Rio Grande do Norte</html:option>
			<html:option  value="RS">Rio Grande do Sul</html:option>
			<html:option  value="RO">Rondônia</html:option>

			<html:option  value="RR">Roraima</html:option>
			<html:option  value="SC">Santa Catarina</html:option>
			<html:option  value="SP">São Paulo</html:option>
			<html:option  value="SE">Sergipe</html:option>
			<html:option  value="TO">Tocantins</html:option>
		</html:select> </dt>
	  </div>
		<div class="sep1_inp"></div>
		<div class="seco_inp">
			<dt class="label">Cidade</dt>
			<dt><html:text property="city" style=" width:250px; height:18px; border:1px solid #A1C4E0; background-color:#F8F8F8;"/></dt>
		  <dt class="check"></dt>
		</div>
	</div>
	<div class="itCad4">
	  <div class="frst_inp4">
			<dt class="label2">Crie seu Login</dt>
			<dt><html:text property="login" style=" width:200px; height:18px; border:1px solid #FCB283; background-color:#F8F8F8;"/></dt>
			<dt class="checks">Só serão aceitos: números e letras sem acento</dt>
		</div>
		<div class="sep3_inp"></div>
		<div class="seco_inp2">
			<dt class="label2">Crie sua Senha</dt>
			<dt><html:password property="password" style=" width:168px; height:18px; border:1px solid #FCB283; background-color:#F8F8F8;"/></dt>
		  <dt class="checks">Só serão aceitos: números e letras sem acento</dt>
		</div>
	</div>	
		<div class="canto14"></div>
		<div class="confere">	
			<div class="canto10"></div>
			<div class="img_conf"><img src="/blog/servlet/rndImage?rndimg=<%=rndCrypt%>" border="0"/></div>
			<div class="digite">Digite o número como ele aparece ao lado:</div>
			<div class="campo"><html:text property="imageword" maxlength="6" style=" width:65px; height:18px; border:1px solid #A1C4E0; background-color:#FFFFFF; text-align:center; color:#1679BA; line-height:18px;"/></div>
			<div class="obs">
			<dt class="checkx">Obs: Este número é importante para garantir a segurança de seu cadastro.<br> Se não conseguir visualizar, <a href="#" style="color:#E86E21;">clique aqui</a></dt>		
		
			</div>
			
		</div>
		<html:submit  value="Cadastrar"  style=" margin-top:32px; float:left; display:table; margin-left:8px; cursor:hand; background-color:#FF6600; color:#FFFFFF; font-weight:bold; border:1px solid #FF6600;"/>
  </div>
</div>
	<logic:present name="mensagem_imagem_incorreta">
				<font color="red"><bean:write name="mensagem_imagem_incorreta"/></font>
			</logic:present>

</html:form>

</BODY>

</html:html>
