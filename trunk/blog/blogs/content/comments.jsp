<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags/pager-taglib" prefix="pg" %>
<%@ page contentType="text/html;charset=iso-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
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
  <dt class="logo"><img src="/bloglog/admin/comments/img/logo_top_comments.jpg" width="265" height="102" /></dt>
</div>
<div class="sep"></div>
<div class="tit">
  <dt class="titulo"> <span class="post_for">postado por:
    <bean:write name="blog" property="name"/>
    |
    <bean:write name="post" property="insertDate" format="dd/MM"/>
    </span><br />
    <span class="title_post">
    <bean:write name="post" property="title" />
    </span></dt>
  <dt class="btn">
    <c:if test="${user.id > 0}"> <a href="comment.do?act=showMyComments&id=${post.id}&userId=${user.id}"><img src="/bloglog/admin/comments/img/meus_comentarios.jpg" width="161" height="29" border="0" /></a> </c:if>
  </dt>
</div>
<div class="busca">
  <dt class="qtd">50 comentários</dt>
  <dt class="search">
    <form action="comment.do?act=search&id=${post.id}" name="buscaComentarios" method=POST>
      <input type="text" name="palavra_chave">
      <input type="image" src"/bloglog/admin/comments/img/buscar.jpg" value="OK" onClick="javascript:submitSearch();">
    </form>
  </dt>
</div>
<pg:pager url="post.do" maxIndexPages="5" maxPageItems="100">
<logic:present name="comments">
<logic:iterate name="comments" id="c" indexId="i">
<pg:item>
<div class="coment">
  <dt class="nome_boneco"> ${c.user.gender}
    <c:if test="${c.user.group.id == 2}"> <a href="/${c.user.blog.path}" border="0" target="_blank"><img src="/bloglog/img/user_icon.gif" border="0"/></a> </c:if>
    <bean:write name="c" property="commentatorName" />
    |
    <bean:write name="c" property="insertDate" format="dd/MM"/>
  </dt>
  <dt class="denuncie"><a href="#" onClick="window.open('/blog/blogs/content/add_denuncia_in.jsp?postId=<bean:write name="post" property="id"/>&commentId=
    <bean:write name="c" property="id" />
    ','','scrollbars=no,height=258,width=500')"><img src="/bloglog/admin/comments/img/btn_denuncie.jpg" border="0" width="65" height="19" style="margin-top:20px;" /></a></dt>
 
 
 
  <dt class="txt_comments">
  <c:if test="${c.user.id == user.id}">
                <font color="red">
                </c:if>
                <bean:write name="c" property="content"/>
                <c:if test="${c.user.id == user.id}">
                </font>
                </c:if>
                <logic:notEmpty name="c" property="answer">
                    <br/>
                    <table  width="100%">
                        <tr>
                            <td width="10%"></td>
                            <td width="90%"><i><b> ${blog.blogUser.firstName} ${blog.blogUser.lastName}:</b> <bean:write name="c" property="answer"/> </i></td>
                        </tr>
                    </table>
                </logic:notEmpty>
                
                <logic:empty name="c" property="answer">
                    <br/>
                    <c:if test="${blogUser.id == user.id}">
                    
                    <html:form method="post" action="comment.do?act=addAnswer">
                    <table  width="100%">
                        <tr>
                            <td height="23" colspan="2" valign="bottom" class="style2">Responder</td>
                        </tr>
                        <tr>
                            <td height="178" colspan="2" valign="top">
                            <html:hidden property="commentId" value="${c.id}"/>
                            <html:hidden property="postId" value="${post.id}"/>
                            <html:textarea property="answer" cols="50" rows="5" style=" background-color:#FFFFFF; border:1px solid #B7D3E9; width:450px;"/>
                            <html:submit value="Responder"/>
                            </td>
                        </tr>
                    </table>
                    
                    </html:form>
                    </c:if>
                </logic:empty>
  
  </dt>
  
  </pg:item>
        </logic:iterate>
    </logic:present>     
  
  <dt class="resposta"><img src="/bloglog/admin/comments/img/btn_responder.jpg" border="0" width="104" height="38" /></dt>
  <dt class="separator"></dt>
</div>
<div class="paginacao">
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
    <logic:present name="mensagem_erro">
        <font color="red"><bean:write name="mensagem_erro"/></font>
    </logic:present> 
    
    <logic:present name="comment_sucesso">
        <font color="red"><bean:write name="comment_sucesso"/></font>
    </logic:present>    
    
<div class="area_comment"><img src="/bloglog/admin/comments/img/top_com.jpg" width="800" height="37" /></div>
<div class="box">
  <div class="box_comment">
   <dt> <iframe name=arquivos src="/blog/blogs/content/add_comment_in.jsp?postId=<bean:write name="post" property="id"/>&countComments=<bean:write name="post" property="countComments"/>&status=<bean:write name="status"/>&path=<bean:write name="blog" property="path"/>&message2=<%=request.getParameter("message2")!= null?request.getParameter("message2"):""%>" frameBorder="0" width="580" height="400" scrolling="no" align="center"></iframe></dt>
  </div>
  <div class="fim_comment"></div>
</div>

<logic:present name="mensagem_erro">
        <font color="red"><bean:write name="mensagem_erro"/></font>
    </logic:present> 
</body>
</html>
