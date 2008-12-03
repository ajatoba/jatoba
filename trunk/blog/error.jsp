<%@ taglib uri="/tags/struts-bean" prefix="bean" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="/bloglog/erro/estilo.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="logo">
<dt><img src="/bloglog/erro/img/logo.jpg" width="271" height="92" /></dt>
<dt><img src="/bloglog/erro/img/ops.jpg" width="271" height="71" /></dt>
<dt class="proc">Está procurando algum blog?<br />
  <a href="http://bloglog.globo.com/az_new.html" target="_blank" style="color:#0292FD; font-weight:bold;">Clique aqui</a> para ver todos os blogs </dt>
  <dt class="proc">Quer sugerir algum artista?<br />
  <a href="http://bloglog.globo.com/equipe" target="_blank" style="color:#0292FD; font-weight:bold;">Clique aqui</a> para entrar no blog da equipe </dt>
  <dt class="ir"><a href="http://bloglog.globo.com" target="_blank"><img src="/bloglog/erro/img/ir_portal.jpg" width="128" height="33" border="0" /></a></dt>


</div>

<bean:write name="mensagem_erro"/>
</body>
</html>
