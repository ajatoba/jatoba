<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<!--
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
-->
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
			
			
<logic:present name="mensagem_erro">
	<center>
	<br/><br/>
	<font style="font-size:11px; font-weight:bold; color:red; font-family:Trebuchet MS;"><bean:write name="mensagem_erro"/></font>
	</center>
</logic:present> 

<logic:present name="topic_sucesso">
	<center>
	<br/><br/>
	<font style="font-size:11px; font-weight:bold; color:red; font-family:Trebuchet MS;"><bean:write name="topic_sucesso"/></font>
	</center>
</logic:present> 

<pg:pager url="forum.do" maxIndexPages="5" maxPageItems="5">

<logic:iterate name="topics" id="t" indexId="i">
<pg:item>
<div id="posts" align="left">
<div id="6">

	<p class="titulo"> <bean:write name="t" property="title"/></p>
	<p align="justify"><bean:write name="t" property="description"/> </p>
	<div class="coment"><a href="/blog/message.do?act=list&topic=${t.id}">Comentários</a></div>

</div>
</div>
</pg:item>
</logic:iterate>
			</div>
		</div>

<p align="center" style="font-size:11px; font-weight:bold; color:blue; font-family:Trebuchet MS;">
	<pg:index>
		<pg:prev>
		<a href="<%=pageUrl%>&act=list">[<< Anterior]</a>	
		</pg:prev>
			
		<pg:pages>
			<a href="<%= pageUrl %>&act=list"><%= pageNumber %></a>
		</pg:pages>
		
		<pg:next>
			<a href="<%= pageUrl%>&act=list">[Próxima >>]</a>
		</pg:next>
	</pg:index>
</p>
</pg:pager>

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