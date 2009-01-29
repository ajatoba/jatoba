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
<link href="/bloglog/admin/css/insert.css" rel="stylesheet" type="text/css" />
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

<logic:present name="reload">  
	<script>
	window.parent.location.reload();	
	</script>
</logic:present> 

<body style="background-color:#E3F0F9; font-family:Trebuchet MS; margin:0px;">

<c:if test="${user.group.id == 3 }">
<a href="/blog/user.do?act=loadSite"></a>
</c:if>

    <logic:present name="comment_sucesso">    
         <script>
         
        //window.parent.location.href='/blog/post.do?act=loadSite&id=<%=request.getParameter("postId")%>';
        window.parent.location.reload();
        alert('<bean:write name="comment_sucesso"/>');

        </script>
    </logic:present> 

<html:form method="post" action="/comment.do?act=add">
        <html:hidden property="postId" value="<%=request.getParameter(\"postId\")%>"/>
        <html:hidden property="countComments" value="<%=request.getParameter(\"countComments\")%>"/>
        <html:hidden property="path" value="<%=request.getParameter(\"path\")%>"/>
        <html:hidden property="from" value="site"/>
        <html:hidden property="status" value="<%=request.getParameter(\"status\")%>"/>
        <html:hidden property="wordEnc" value="<%=rndCrypt%>"/>
        <html:hidden property="blogId"  value="<%=request.getParameter(\"blogId\")%>"/>
<div class="ini_form"></div>        
<div class="form">        
<table width="750" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="/bloglog/admin/cadastro/img/top_cadastro.jpg" width="748" height="19" /></td>
  </tr>
  <tr>
    <td><div class="cad_01">
<dt class="cadastre">Inserir comentário</dt>
<dt class="edit"><a href="/blog/user.do?act=loadSite&id=${user.id}" style="font-weight:bold; font-size:14px; color:#2B3538;">Editar meus dados</a></dt>

</div>
<div class="msg">
<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>Serão aceitos apenas comentários objetivos e que tenham relaçao direta com o assunto em questão. Não serão aceitos comentários com palavras de baixo calão tampouco os que ameacem algum blogueiro ou usuário.</td>
  </tr>
</table>

</div>
<div class="content">
<table width="700" border="0" align="center">
  <tr>
    <td colspan="3">Nome</td>
    <td width="271">Email</td>
  </tr>
  <tr>
    <td colspan="3"><html:text property="commentatorName" readonly="true" value="${user.firstName}" style='height:16px; width:300px; background-color:#F8F8F8; border:1px solid #A2C2CF; font-size:12px; color:#1296FF; line-height:16px; '/></td>
    <td><html:text property="commentatorEmail" readonly="true" value="${user.email}" style='height:16px; width:245px; background-color:#F8F8F8; border:1px solid #A2C2CF; font-size:12px; color:#1296FF; line-height:16px;'/></td>
  </tr>
  <tr>
    <td colspan="3">Comentário</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4"><html:textarea property="content" cols="40" rows="10" style='background-color:#F8F8F8; border:1px solid #A2C2CF; font-family:Arial; font-size:12px; color:#1296FF; line-height:16px; width:680px; height:80px; ' onkeyup='contaText(this);'/>    </td>
  </tr>
  <tr>
    <td colspan="4" class="limite">Limite de 1024 caracteres | restantes : <span id="nRestantes"><script>document.write(maximo);</script></span></td>
  </tr>
  <tr>
    <td colspan="4"><img src="/bloglog/img/sep_insert.jpg" width="700" height="15" /></td>
  </tr>
  <tr>
    <td width="191"><img src="/blog/captcha.jpg" border="1" style='float:left;  margin-right:12px;'/></td>
    <td width="211" "width:40%">Digite o que se vê escrito na imagem:</td>
    <td width="15"><html:text property="captcha" maxlength="3" style=" width:65px; height:18px; border:1px solid #A1C4E0; background-color:#FFFFFF; text-align:center; color:#1679BA; line-height:18px;" onkeypress="mascara(this,soNumeros)"/></td>
    <td> <html:image src="/bloglog/img/btn_comentar.jpg" value="Enviar"  style="float:right; margin-right:13px; margin-top:18px; background-color:#FF6600; color:#FFFFFF; font-weight:bold; border:0px; cursor:hand;"/></td>
  </tr>
</table></td>
  </tr>
  <tr>
    <td><img src="/bloglog/admin/login/img/bottom_login.jpg" width="748" height="14" /></td>
  </tr>
</table>
      </div>
         <logic:present name="message">
            <br><br><br><center>Entre com o valor da imagem corretamente.</center>
         </logic:present>  
    </font>
    </div>

      
    </html:form>
    </body>