<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=iso-8859-1"%>
<HTML>
<HEAD>
    <TITLE>:: DISCUTELOG ::</TITLE>    
	<link href="/bloglog/discute/blog.css" rel="stylesheet" type="text/css" />    
</HEAD>


<BODY style="margin-top: 0px; margin-bottom: 0px; margin-left: 0px; margin-right: 0px; ">

<!-- BARRA GLOBO.COM-->
<div id="barra_patrocinador" align="left">
<iframe SRC="http://www.Globo.com/ssi/barra/parceiros.htm" width=100% height=35 MARGINWIDTH="0" MARGINHEIGHT="0" SCROLLING="NO" FRAMEBORDER="0" FRAMESPACING="0" BORDER="0"></iframe>
</div>
<div id="back">
<div id="fundo_maior">
<div id="blog">
		<!-- HEADER -->
		<div id="header_imagem_titulo" align="center"></div>
				
		<div id="meio">
			<div id="esquerda">
			<!-- BARRA LATERAL-->
			<script>
function submitForm(){ 
    window.open('','resultado','width=800,height=600,toolbar=0,scrollbars=1'); 
    var a = window.setTimeout('document.form_busca.submit();',250); 
} 

</script>
			<div id="busca">
	<div class="espproc1"></div>
	<div class="proc">PROCURAR</div>
	<div class="espproc2"></div>
	<form name="form_busca" action="post.do?act=search" method="POST" target="resultado">
		<input name="palavra_chave" type="text" class="txt"/>
		<input name="blogId" type="hidden" value="9"/>
		<a href="#"><img src="/bloglog/discute/img/btn_buscar.jpg" onClick="javascript:submitForm()"></a>
	</form>	
</div>

<div id="perfil">
	<img src="/bloglog/discute/img/queeh.jpg" width="224" height="23">	
	<p>O <strong>Discutlog</strong> &eacute; o espa&ccedil;o para  voc&ecirc; opinar sobre os assuntos do momento. Todos os dias estaremos propondo  debates entre a Comunidade BlogLog. E voc&ecirc;, claro, &eacute; nosso convidado de honra!</p>
    <p>Ent&atilde;o, puxe seu teclado e <em>discutlog</em> com a gente!</p>
</div>

<div id="perfil">
	<img src="/bloglog/discute/img/comofunciona.jpg" width="224" height="23">	
	<p>Como tudo por aqui, &eacute; muito simples  participar! Voc&ecirc; s&oacute; precisa fazer o seu cadastro e&nbsp; seguir algumas regrinhas b&aacute;sicas de boa conviv&ecirc;ncia, como manter  a boa educa&ccedil;&atilde;o no palavreado e respeitar a opini&atilde;o alheia, mesmo que seja  absolutamente diferente da sua.
</p><p>Lembre-se que uma Comunidade se auto-regula. Por isso, envie um e-mail  para <a href="mailto:denuncia@bloglog.com.br">denuncia@bloglog.com.br</a>, no  caso de encontrar algu&eacute;m andando fora da linha.</p></div>

<div id="rss">
	<a href="/blog/rss.rss?id=9" target="blank"><div id="cantoRSS"></div></a>
	<div id="cantoSep"></div>
	<div class="boldi">RSS |</div>
	<div id="oq"><a href="#">Oque é ?</a></div>
</div>			</div>
			
			<div id="direita">		
			<!-- BODY -->


<html:form method="post" action="/message.do?act=add">
		<html:hidden property="topic" value="${topic.id}"/>
		<html:hidden property="status" value="1"/>

<div class="add_den">
		
		<div class="topadd"><img src="/bloglog/content/add_coment/img_add/tit_add.jpg" /></div>
		
		<div class="inputs">
			<div class="iptnome">
				<dt id="txt2">Usuário</dt>
				<dt><html:text property="user" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></dt>
			</div>
			<div class="iptemail">
				<dt id="txt2">Titulo</dt>
				<dt><html:text property="title" style='height:16px; width:208px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;'/></dt>
			</div>
		</div>
		<div class="inputs">
			<div class="iptcoment">
				<dt id="txt2">Mensagem</dt>
				<dt>  <html:textarea property="content" style='background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; width:448px; height:80px; font-family:Trebuchet MS;'/></dt>
			</div>
		</div>
		
		<html:submit value="Enviar" style="float:right; margin-right:13px; margin-top:10px; background-color:#FF6600; color:#FFFFFF; font-weight:bold; border:1px solid #FF6600; cursor:hand;"/>

		
</div>

</html:form>

			</div>
		</div>
	
<!-- FOOTER -->
</div>
</div>
</div>
<div id="rodape">
	<div class="align">2007 BLOGLOG.COM. TODOS OS DIREITOS RESERVADOS</div>
	<div><a href="#">BLOGLOG</a> | <a href="#">PUBLICICDADE</a> | <a href="#">POLÍTICA DE PRIVACIDADE</a> | <a href="#">FALE CONOSCO</a></div>
</div>

</BODY>
</HTML>