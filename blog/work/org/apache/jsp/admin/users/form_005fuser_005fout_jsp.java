package org.apache.jsp.admin.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class form_005fuser_005fout_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/admin/includes/menu.jsp");
  }

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/blog/admin/includes/css/stylle.css\" type=\"text/css\">\r\n");
      out.write("  </head>\r\n");
      out.write("  \r\n");
      out.write("  \r\n");
      out.write("<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\r\n");
      out.write("<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" bgcolor=\"#CCCCCC\" bordercolor=\"#000000\">\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td width=\"10%\"><b><a href=\"/blog/admin/blog.do?act=list\">Blogs</a></b></td>\r\n");
      out.write("    <td width=\"10%\"><b><a href=\"/blog/admin/blogUser.do?act=list\">Usuarios</a></b></td>\r\n");
      out.write("    <!--td width=\"10%\"><b><a href=\"/blog/admin/topic.do?act=list\">Tópicos</a></b></td-->\r\n");
      out.write("\t<td width=\"84%\"><b><a href=\"/blog/adminUser.do?act=logout\">Logout</a></b</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<center>Operação efetuada com sucesso</center>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
