<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"> 
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html>
  <head>
<link rel="stylesheet" href="/bloglog/content/add_coment/css/estilo.css" type="text/css">
  </head>
  
  
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<p>  <center>
  <logic:present name="mensagem_erro">
  	<font color="red"><bean:write name="mensagem_erro"/></font>
  </logic:present>
  
  <logic:notPresent name="mensagem_erro">
  	<font color="red"><center>Cadastro realizado com sucesso</center></font> 
  </logic:notPresent>
 </center></p>
	
</body>
</html>
