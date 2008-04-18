
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/bloglog-html" prefix="bloglog" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>BLOGLOG</title>

			<logic:iterate id="r" name="ranking" indexId="i">
				 <a href="http://bloglog.globo.com/${r.blog.path}" target="_blank">
		           <dt class="lists1" onmousemove="this.className='TTR1';" onmouseout="this.className='lists1';">            
		              <img src="img/bt_azul${i+1}.gif" style="float:left; margin-right:5px;"/><span style="margin-left:5px;">${r.blog.blogUser.firstName} ${r.blog.blogUser.lastName}</span><br/>
		              <span style="font-size:11px;  line-height:12px;color:#7C8187; font-weight:normal; margin-left:5px;">www.bloglog.com.br/${r.blog.path}</span>
		           </dt>
			     </a>			
			</logic:iterate>
			
</body>
</html>
