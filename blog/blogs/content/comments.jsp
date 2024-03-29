<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>
<%@ page contentType="text/html;charset=iso-8859-1"%>
<%
java.util.Collection comentarios = null;
comentarios = (java.util.ArrayList)request.getAttribute("comments");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Coment&aacute;rios - Bloglog</title>
<link href="/bloglog/admin/comments/coments.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script>
        opener.document.location.reload();
        
function submitSearch(){
    document.buscaComentarios.submit();
}
        </script>
<div class="comments">
<div class="top">
  <dt class="ini_logo"></dt>	
  <dt class="logo"><img src="/bloglog/admin/comments/img/logo_top_comments.jpg" width="265" height="102" /></dt>
  <dt class="banner">
  <table class="table">
  <tr>
    <td><iframe src="/${blog.path}/bnn_comments.html" width="468" height="60" frameborder="0" allowtransparency="0" scrolling="no"></iframe>
</td>
  </tr>
</table>
</dt>	
</div>
<div class="sep"></div>
<div class="tit">
<dt style="height:80px; width:27px; float:left;"></dt>
  <dt class="titulo"> <span class="post_for">postado por:
    <bean:write name="blog" property="name"/>
    |
    <bean:write name="post" property="insertDate" format="dd/MM"/>
    </span><br />
    <span class="title_post">
    <bean:write name="post" property="title" />
    </span></dt>
  <dt class="btn">
    <c:if test="${user.id > 0}">
    	<%if(comentarios.size()>0){%> 
		    <a href="comment.do?act=showMyComments&id=${post.id}&userId=${user.id}">
		    <img src="/bloglog/admin/comments/img/meus_comentarios.jpg" width="161" height="29" border="0" />
		    </a>
	    <%}%> 
    </c:if>
  </dt>
</div>
<!--
VERSAO DE 05-03-2009 - Revision 239
-->
<div id="busca">
<dt class="ini_busca"></dt>
  <dt class="qtd"><%=comentarios.size()%> coment&aacute;rio<%if(comentarios.size()>1){%>s<%}%> </dt>
  <dt class="search">
  <%if(comentarios.size()>0){%>
  <form action="comment.do?act=search&id=${post.id}" name="buscaComentarios" method=POST>
  <table width="40%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><input type="text" name="palavra_chave" class="form_search"></td>
    <td><a href="javascript:submitSearch();"> <img src="/bloglog/admin/comments/img/buscar.jpg" width="31" height="27" border="0"></a></td>
  </tr>
  </table>
  </form>
  <%}%>
   
  </dt>
</div>
<div style="margin-left:20px; display:table; margin-top:10px;">
    <logic:present name="busca"> <a href="/blog/post.do?act=loadSite&id=${post.id}"><img src="/bloglog/admin/comments/img/back.jpg" width="98" height="31" border="0" /></a> </logic:present> 
</div>
<pg:pager url="post.do" maxIndexPages="5" maxPageItems="30">
<logic:present name="comments">
<logic:iterate name="comments" id="c" indexId="i">
<pg:item>
<div class="coment">
<dt class="ini_boneco"></dt>
  <dt class="nome_boneco"><bean:write name="c" property="insertDate" format="dd/MM"/>
- <c:if test="${c.user.group.id == 2}"> <a href="/${c.user.blog.path}" border="0" target="_blank"><img src="/bloglog/admin/comments/img/${c.user.gender}.gif" border="0"/></a> </c:if>
<bean:write name="c" property="commentatorName" /></dt>

  <dt class="denuncie"><a href="#" onClick="javascript:window.open('/blog/blogs/content/add_denuncia_in.jsp?id=${post.id}&commentId=${c.id}','Denuncie','scrollbars=no,height=258,width=500');"><img src="/bloglog/admin/comments/img/btn_denuncie.jpg" border="0" width="65" height="19" style="margin-top:20px;" /></a></dt>

  <dt class="txt_comments">
  <c:if test="${c.user.id == user.id}">
                <font color="#0C90FF">
                </c:if>
                <bean:write name="c" property="content"/>
                <c:if test="${c.user.id == user.id}">
                </font>
                </c:if>
                <logic:notEmpty name="c" property="answer">
                    <br/>
                    <table  width="742" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
                        <tr>
                            <td><img src="/bloglog/img/top_resposta.jpg" width="742" height="38" /></td>
                        </tr>
                        <tr>
                            <td><span style="margin-left:15px;"><b> ${blog.blogUser.firstName} ${blog.blogUser.lastName}:</b></span> <br /><br /><dt class="margin_resp"><bean:write name="c" property="answer"/> </dt></td>
                        </tr>
                        <tr>
                            <td><img src="/bloglog/img/bottom_resposta.jpg" width="742" height="18" /></td>
                        </tr>
                    </table>
                </logic:notEmpty>
                
                <logic:empty name="c" property="answer">
                    
                    <c:if test="${blogUser.id == user.id}">
                    
                    <html:form method="post" action="comment.do?act=addAnswer">
                    <table  width="742" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
                        <tr>
                            <td><img src="/bloglog/img/top_resposta.jpg" width="742" height="38" /></td>
                        </tr>
                        <tr>
                            <td align="center">
                            <html:hidden property="commentId" value="${c.id}"/>
                            <html:hidden property="postId" value="${post.id}"/>
                            <html:textarea property="answer" cols="50" rows="5" style="background-color:#C9DEE9; border:1px solid #A5C1D2; font-size:12px; color:#404043; width:710px;"/></td>
                            </tr>
                            <tr>
                            <td>
                            <html:image onclick="javascript:submit();" style=" margin-left:630px;" src="/bloglog/admin/comments/img/btn_enviar.jpg" border="0"/>
                        </td>
                        </tr>
                        <tr>
                            <td><img src="/bloglog/img/bottom_resposta.jpg" width="742" height="18" /></td>
                        </tr>
                    </table>
                    </html:form>
                    </c:if>
                </logic:empty>
 </dt>
   <dt class="separator"><center><img src="/bloglog/admin/comments/img/separator.jpg" border="0" width="750" height="5" /></center></dt>
 </div>
  </pg:item>
 
        </logic:iterate>
          
    </logic:present>     
  

</div>
<div class="paginacao">
<dt class="ini_paginacao"></dt>
  <dt class="left">
  <pg:index>
        <pg:prev>
        <a href="<%=pageUrl%>&act=loadSite&id=${post.id}">
  <img src="/bloglog/admin/comments/img/seta_esq.jpg" border="0" width="50" height="30" />
  </a>  
        </pg:prev></dt>
  <dt class="num"><pg:pages>
            <a href="<%= pageUrl %>&act=loadSite&id=${post.id}"><%= pageNumber %></a>
        </pg:pages> </dt>
  <dt class="right"><pg:next>
            <a href="<%= pageUrl%>&act=loadSite&id=${post.id}"><img src="/bloglog/admin/comments/img/seta_dir.jpg" border="0" width="50" height="30" /></a>
        </pg:next></dt>
        
        </pg:index>
    </p>
    </pg:pager>
</div>
<div class="msg_resposta">
    <logic:present name="mensagem_erro">
        <bean:write name="mensagem_erro"/>
    </logic:present> 
    
    <logic:present name="comment_sucesso">
        <bean:write name="comment_sucesso"/>
    </logic:present>  
    
    <logic:present name="comment_sucesso2">
        <bean:write name="comment_sucesso2"/>
    </logic:present> 
</div>       
<div class="box">
  <div>
   <dt style="background-color:#E4EFF5;"><iframe width="800px" name=arquivos src="/blog/blogs/content/add_comment_in.jsp?postId=<bean:write name="post" property="id"/>&countComments=<bean:write name="post" property="countComments"/>&status=<bean:write name="status"/>&path=<bean:write name="blog" property="path"/>&message2=<%=request.getParameter("message2")!= null?request.getParameter("message2"):""%>" frameBorder="0" width="580" height="400" scrolling="no" align="center"></iframe></dt>
  </div>
</div>

</body>
</html>
