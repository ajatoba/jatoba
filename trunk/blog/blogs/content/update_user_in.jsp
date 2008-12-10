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
    <link href="/bloglog/admin/css/cadastro.css" rel="stylesheet" type="text/css" />
</HEAD>

<BODY>
<script>
function mascara(o,f){
    v_obj=o
    v_fun=f
    setTimeout("execmascara()",1)
}

function execmascara(){
    v_obj.value=v_fun(v_obj.value)
}

function soNumeros(v){
    return v.replace(/\D/g,"")
}


</script>
<center>
    <logic:present name="mensagem_erro">
        <font color="red"><bean:write name="mensagem_erro"/></font>
    </logic:present>
</center>

<html:form method="post" action="user.do?act=update">
<html:hidden property="id" />

<input type="hidden" name="postId" value="<%=postId%>" />
<input type="hidden" name="countComments" value="<%=countComments%>" />
<input type="hidden" name="path" value="<%=path%>" />
<input type="hidden" name="from" value="site" />
<input type="hidden" name="status" value="<%=status%>" />

<div style="width:800px;">
<div class="top"><img src="/bloglog/admin/cadastro/img/top_cadastro.jpg" width="748" height="19" /></div>
<div class="cad_01">
<dt class="cadastre">Cadastre-se</dt>
<dt class="participe">Participe da comunidade Bloglog! Faça seus comentários, deixe sua opinião, converse com seu ídolo </dt>
</div>
<div style=" width:748px; margin-left:26px;"><img src="/bloglog/admin/cadastro/img/separator.jpg" width="748" height="13" /></div>
<div class="infos">
<table width="90%" border="0" align="right">
  <tr>
    <td class="nome">Nome Completo</td>
    <td class="email">Email</td>
  </tr>
  <tr>
    <td><html:text property="firstName" style=" width:220px; height:18px; border:1px solid #A2C2CF; background-color:#F8F8F8;"/></td>
    <td><html:text property="email" style=" width:220px; height:18px; border:1px solid #A2C2CF; background-color:#F8F8F8;"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td class="email2"><input name="" type="checkbox" value="" />
      Desejo receber informações do bloglog.</td>
  </tr>
  <tr>
    <td class="nasc">Data de Nascimento</td>
    <td class="sexo">Sexo</td>
  </tr>
  <tr>
    <td>
    <html:text property="dia" style=" width:30px; height:18px; border:1px solid #A2C2CF; background-color:#F8F8F8;"></html:text>
            /
              <html:text property="mes" style=" width:30px; height:18px; border:1px solid #A2C2CF; background-color:#F8F8F8;"></html:text>
              /
              <html:text property="ano" style=" width:39px; height:18px; border:1px solid #A2C2CF; background-color:#F8F8F8;"></html:text></td>
    <td class="sexo2"><input type="radio" name="gender" value="M" selected/> Masculino&nbsp;&nbsp;
            <input type="radio"  name="gender" value="F"/> Feminino</td>
  </tr>
  <tr>
    <td class="estado">Estado</td>
    <td class="cidade">Cidade</td>
  </tr>
  <tr>
    <td><html:select property="state" style="width:239px; height:18px; border:1px solid #A2C2CF; background-color:#F8F8F8;">
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
        </html:select></td>
    <td><html:text property="city" style=" width:200px; height:18px; border:1px solid #A2C2CF; background-color:#F8F8F8;"/></td>
  </tr>
</table>
        


</div>
<div class="login">
<dt class="login_header"><img src="/bloglog/admin/cadastro/img/crie_ident.jpg" width="209" height="50" /></dt>
<dt class="login_txt">Login</dt>
<dt class="login_frm"><html:text property="login" style=" width:168px; height:18px; border:1px solid #A2C2CF; background-color:#F8F8F8;"/></dt>
<dt class="senha_txt">Senha</dt>
<dt class="senha_frm"><html:password property="password" style=" width:168px; height:18px; border:1px solid #A2C2CF; background-color:#F8F8F8;"/></dt>
<dt class="txt_msg">Só serão aceitos números e letras sem acento</dt>

</div>
<div style=" width:748px; margin-left:26px;"><img src="/bloglog/admin/cadastro/img/separator.jpg" width="748" height="13" /></div>

<div class="captcha">

<dt style="margin-left:26px; float:left;"><!--img src="/blog/servlet/rndImage?rndimg=<%//rndCrypt%>" border="0"/-->
</dt>
<table width="95%" border="0">
  <tr>
    <td width="17%"><!--img src="/blog/servlet/rndImage?rndimg=<%//rndCrypt%>" border="0"/--><img src="/blog/captcha.jpg" border="0"/></td>
    <td width="49%"><span style="font-size:10px; color:#293641;">Digite aqui o n&deg; de segurança</span>&nbsp;<html:text property="captcha" maxlength="3" style=" width:65px; height:18px; border:1px solid #A1C4E0; background-color:#FFFFFF; text-align:center; color:#1679BA; line-height:18px;" onkeypress="mascara(this,soNumeros)"/></td>
    <td width="19%">&nbsp;</td>
    <td width="15%" rowspan="2"><html:image src="/bloglog/admin/cadastro/img/cadastrar.jpg"  value="Cadastrar"  style=" margin-top:15px; float:left; display:table; margin-left:8px; cursor:hand; background-color:#FF6600; color:#FFFFFF; font-weight:bold; "/></td>
  </tr>
  <tr>
    <td colspan="2"><span style="font-size:10px; color:#293641;">Se não conseguir visualizar o n&deg; ,<a href="#" style="color:#E86E21;"> clique aqui</a></span></td>
    <td>&nbsp;</td>
    </tr>
</table>


</div>
<div class="bottom"><img src="/bloglog/admin/cadastro/img/bottom_cadastro.jpg" width="748" height="16" /></div>
    <logic:present name="mensagem_imagem_incorreta">
                <font color="red"><bean:write name="mensagem_imagem_incorreta"/></font>
            </logic:present>

</html:form>

</BODY>

</html:html>
