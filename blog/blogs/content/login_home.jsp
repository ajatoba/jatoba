<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%
String postId = (String) request.getAttribute("postId");
String countComments =(String) request.getAttribute("countComments");
String path =(String) request.getAttribute("path");
String from = "site";
String status = (String)request.getAttribute("status");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html:html>
<head>
<title>:: Bloglog ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="home/css/estilo.css" rel="stylesheet" type="text/css" />    
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
			
			<form name="userCommentatorForm" method="post" action="/blog/user.do?act=login">
		<input type="hidden" name="postId" value="<%=postId%>"/>
		<input type="hidden" name="countComments" value="<%=countComments%>"/>
		<input type="hidden" name="path" value="<%=path%>"/>
		<input type="hidden" name="from" value="site"/>
		<input type="hidden" name="status" value="<%=status%>"/>

			<div class="log_up1">



                    	<dt>login</dt>



						<dt class="inp"><input type="text" name="login"/></dt>



                    </div>



                    <div class="log_up2">



                    	<dt>senha</dt>



						<dt class="inp"><input type="password" name="password" />



						</dt>



                    </div>



                    <div class="log_up3">



                    	<div> <input type="submit" name="Submit" value="OK" src="home/img/log_ok.jpg" style=" background-color:none;"/></div>



                    </div>



                  <div class="log_up4"><a href="#">esqueci</a></div>
				  
				  </form>		



  <center>
  <logic:present name="mensagem_erro">
  	<font color="red"><bean:write name="mensagem_erro"/></font>
  </logic:present>
  <br>

  </center>

</div>
</body>
</html:html>
