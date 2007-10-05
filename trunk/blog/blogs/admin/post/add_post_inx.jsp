<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Untitled Document</title>
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
</head>

<body>

<div id="top_repeat">
	<div id="top">
		<div style="float:right; margin-top:25px;">
		<span style="font-size:12px; line-height:10px; color:#1A74B3;">Usuário:</span>
		<span style="font-size:13px; font-weight:bold; color:#F77527;">Login</span>
		&nbsp;&nbsp;<a href="/blog/blogs/blogUser.do?act=logout"><img src="/bloglog/admin/imgs/logout.jpg" width="46" height="23" style="display:table; border:none;" /></a></div>
		<div class="logo"></div>
		<div class="links"><img src="/bloglog/admin/imgs/btn_posts.jpg" /><img src="/bloglog/admin/imgs/btn_controle.jpg" /><img src="/bloglog/admin/imgs/btn_ajuda.jpg" /></div>
		<div class="login"></div>
	</div>
</div>
<div id="barra_repeat">
	<div id="barra">CRIANDO NOVO POST </div>
</div>
<div id="meio_repeat">
	<div id="meio">
<html:form method="post" action="/blogs/admin/post.do?act=add">
<table width="780" border="0" cellpadding="0" cellspacing="0" style=" margin-left:auto; margin-right:auto;">
  <tr>
    <td>
	
		<table width="780" height="294" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="32" colspan="2" valign="bottom"><span class="style2">Titulo do Post </span></td>
            </tr>
          <tr>
            <td height="25" colspan="2">
                <html:text property="title" style="line-height:18px; width:450px; background-color:#FFFFFF; border:1px solid #B8D4E9;"/>
                
            </td>
            </tr>
          <tr>
            <td height="23" colspan="2" valign="bottom" class="style2">Texto do Post </td>
          </tr>
          <tr>
            <td height="350" colspan="2" valign="bottom"><FCK:editor id="content" basePath="/FCKeditor/"
		fullPage="false" width="780" height="348"  
			imageBrowserURL="/FCKeditor/editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"
			linkBrowserURL="/FCKeditor/editor/filemanager/browser/default/browser.html?Connector=connectors/jsp/connector"
			flashBrowserURL="/FCKeditor/editor/filemanager/browser/default/browser.html?Type=Flash&Connector=connectors/jsp/connector"
			imageUploadURL="/FCKeditor/editor/filemanager/upload/simpleuploader?Type=Image"
			linkUploadURL="/FCKeditor/editor/filemanager/upload/simpleuploader?Type=File"
			flashUploadURL="/FCKeditor/editor/filemanager/upload/simpleuploader?Type=Flash">							
		</FCK:editor></td>
          </tr>
          <tr>
            <td width="321" height="36" valign="bottom"><span class="style2">Status: </span>
             
        <html:select property="status">
		    <html:option value="-1">Selecione</html:option>
		    <html:option value="1">Inativo</html:option>
		    <html:option value="0">Ativo</html:option>      
	    </html:select>
&nbsp;&nbsp;&nbsp;
                           <span class="style2">Post com modera&ccedil;&atilde;o </span>
                        
                               <html:checkbox property="controll" />
                             </label>
                        
                           </td>
            <td width="459" valign="bottom"><div align="right"><html:submit value="Cadastrar"/></div></td>
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
			<dt>2007 BlogLog.com. Todos os direitos reservados</dt>
			<dt><a href="#">About BlogLog</a> | <a href="#">Publicidade</a> | <a href="politica.html">Política de Privacidade</a></dt>
		</div>
	</div>
</div>

</body>
</html:html>
