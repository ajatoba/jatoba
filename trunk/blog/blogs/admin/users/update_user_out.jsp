<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Admin Posts BLOGLOG</title>
<link rel="stylesheet" href="/bloglog/admin/css/estilo.css"/>
<style type="text/css">
<!--
.style3 {
	font-size: 12px;
	font-weight: bold;
	color: #F77527;
}
.style14 {font-size: 11px; color: #777777; font-weight: bold; }
.style15 {font-size: 9px}
-->
</style>


</head>
<body>
<logic:present name="mensagem_erro">
<font color="red"><bean:write name="mensagem_erro"/></font>
</logic:present> 

<logic:present name="mensagem_sucesso">
<font color="red"><bean:write name="mensagem_sucesso"/></font>
</logic:present> 

<div id="top_repeat">
	<div id="top">
		
		<div class="logo"></div>
		<div class="links"><img src="/bloglog/admin/imgs/btn_posts.jpg" /><a href="/blog/blogs/admin/favorites.do?act=list"><img src="/bloglog/admin/imgs/btn_bloglogs.jpg" border="0" style="border:none;" /></a><img src="/bloglog/admin/imgs/btn_ajuda.jpg" style="display:none;" /></div>
		<div class="login"></div>
	</div>
</div>
<div id="barra_repeat">
	<div id="barra">
    	<a href="/blog/blogs/admin/post.do?act=list" style="text-decoration:none;"><span>&nbsp;SEUS POSTS&nbsp;</span></a>&nbsp;&nbsp;&nbsp;<a href="/blog/blogs/admin/post/add_post_in.jsp" style="text-decoration:none;"><span>&nbsp;CRIAR NOVO POST&nbsp;</span></a>&nbsp;&nbsp;&nbsp;
     &nbsp;&nbsp;&nbsp;
        	<span style="font-size:12px; color:#FFFFFF; line-height:10px; margin-left:190px; font-weight:normal;">Usuário:&nbsp;</span><span style="font-size:13px; font-weight:bold; color:#FFFFFF;">Login</span>&nbsp;&nbsp;&nbsp;
            <span style=" vertical-align:middle; line-height:41px;"><a href="/blog/blogs/blogUser.do?act=logout" ><img src="/bloglog/admin/imgs/logout.jpg" style="margin-top:6px; border:none;"/></a></span>     
            <a href="/blog/admin/blog.do?act=list" style="text-decoration:none;"><span>LISTAR OUTROS BLOG</span></a>
            
             </div>
            
            
            
</div>
<div id="meio_repeat">
	<div id="meio">	
	<a href="/blog/blogs/admin/blogUser.do?act=load" style="text-decoration:none;"><span>ATUALIZAR DADOS</span></a>
	<center>Sua senha foi alterada com sucesso.</center>
</div>

<div id="down_repeat">
	<div class="down">
		<div class="img">
			<dt id="roesp"></dt>
			<dt>Algum problema? Entre em contato com a equipe BlogLog.</dt>
			<dt>E-mail: <a href="mailto:texto@bloglog.com.br">texto@bloglog.com.br</a> | Tel.: (21) 3385-4434</dt>
		</div>
	</div>
</div>

</body>
</html>
