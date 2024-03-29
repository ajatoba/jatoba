<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Admin Posts BLOGLOG</title>
<link rel="stylesheet" href="/bloglog/admin/css/estilo.css"/>
<style type="text/css">
<!--
.style2 {
	font-size: 12px;
	font-weight: bold;
	color: #147ABA;
}
-->
</style>


<script language="javascript" type="text/javascript" src="/blog/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript">
tinyMCE.init({
	mode : "textareas",
	theme : "advanced",
	plugins : "filemanager",
	theme_advanced_toolbar_location : "top"
});
</script>
</head>

<body>

<div id="top_repeat">
	<div id="top">
	  <div class="logo"></div>
	<div class="links"><img src="/bloglog/admin/imgs/btn_posts.jpg" /><a href="/blog/blogs/admin/favorites.do?act=list"><img src="/bloglog/admin/imgs/btn_bloglogs.jpg" border="0" style="border:none;" /></a><img src="/bloglog/admin/imgs/btn_ajuda.jpg" style="display:none;" /></div>		
		
	</div>
</div>
<div id="barra_repeat">
	<div id="barra">
    	<a href="/blog/blogs/admin/post.do?act=list" style="text-decoration:none;"><span>&nbsp;SEUS POSTS&nbsp;</span></a>&nbsp;&nbsp;&nbsp;<a href="/blog/blogs/admin/post/add_post_in.jsp" style="text-decoration:none;"><span>&nbsp;CRIAR NOVO POST&nbsp;</span></a>
        
        	<span style="font-size:12px; color:#FFFFFF; line-height:10px; margin-left:318px; font-weight:normal;">Usu�rio:&nbsp;</span><span style="font-size:13px; font-weight:bold; color:#FFFFFF;">Login</span>&nbsp;&nbsp;&nbsp;
            <span style=" vertical-align:middle; line-height:41px;"><a href="/blog/blogs/blogUser.do?act=logout" ><img src="/bloglog/admin/imgs/logout.jpg" style="margin-top:6px; border:none;"/></a></span>      </div>
</div>
<div id="meio_repeat">
	<div id="meio">
<html:form method="post" action="/blogs/admin/post.do?act=update">
<html:hidden property="id" value="${post.id}"/>
<table width="780" border="0" cellpadding="0" cellspacing="0" style=" margin-left:auto; margin-right:auto;">
  <tr>
    <td>
	
		<table width="780" height="294" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="32" colspan="2" valign="bottom"><span class="style2">Titulo do Post </span></td>
            </tr>
          <tr>
            <td height="25" colspan="2">
               <html:text property="title" value="${post.title}" style="line-height:18px; width:450px; background-color:#FFFFFF; border:1px solid #B8D4E9;"/>
                
            </td>
            </tr>
          <tr>
            <td height="23" colspan="2" valign="bottom" class="style2">Texto do Post </td>
          </tr>
          <tr>
            <td height="350" colspan="2" valign="bottom">

<html:textarea property="content" cols="50" rows="15" value="${post.content}" style="width: 776px; height:340px;"/>
 
</td>
          </tr>
          <tr>
            <td width="321" height="36" valign="bottom"><span class="style2">Status: </span>

        <html:select property="status"  value="${post.status}">
		    <html:option value="1" style=" color:#009900;">Ativo</html:option> 
		    <html:option value="0" style=" color:#FF0000;">Inativo</html:option>
		      
	    </html:select>

&nbsp;&nbsp;&nbsp;
                           <span class="style2" style="display:none;">Post com modera&ccedil;&atilde;o </span>
                        
                               <html:checkbox property="controll" value="${post.controll}" style="display:none;" />
                            
                        
                           </td>
            <td width="459" valign="bottom"><div align="right"><html:submit value="Atualizar"/></div></td>
          </tr>
        </table>	</td>
  </tr>
</table>
</html:form>

<p>  
	<center>
	  <logic:present name="mensagem_erro">
	  	<font color="red"><bean:write name="mensagem_erro"/></font>
	  </logic:present>
	</center>
</p>

	</div>
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
</html:html>
