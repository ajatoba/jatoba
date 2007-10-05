package org.apache.jsp.blogs.content;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(3);
    _jspx_dependants.add("/WEB-INF/struts-bean.tld");
    _jspx_dependants.add("/WEB-INF/struts-logic.tld");
    _jspx_dependants.add("/WEB-INF/struts-html.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fhtml;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flogic_005fpresent_005fname;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fbean_005fwrite_005fname_005fnobody;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fhtml_005fhtml = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005flogic_005fpresent_005fname = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fbean_005fwrite_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fhtml_005fhtml.release();
    _005fjspx_005ftagPool_005flogic_005fpresent_005fname.release();
    _005fjspx_005ftagPool_005fbean_005fwrite_005fname_005fnobody.release();
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
      out.write("\r\n");
      out.write("\r\n");

String postId = (String) request.getAttribute("postId");
String countComments =(String) request.getAttribute("countComments");
String path =(String) request.getAttribute("path");
String from = "site";
String status = (String)request.getAttribute("status");

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("\r\n");
      //  html:html
      org.apache.struts.taglib.html.HtmlTag _jspx_th_html_005fhtml_005f0 = (org.apache.struts.taglib.html.HtmlTag) _005fjspx_005ftagPool_005fhtml_005fhtml.get(org.apache.struts.taglib.html.HtmlTag.class);
      _jspx_th_html_005fhtml_005f0.setPageContext(_jspx_page_context);
      _jspx_th_html_005fhtml_005f0.setParent(null);
      int _jspx_eval_html_005fhtml_005f0 = _jspx_th_html_005fhtml_005f0.doStartTag();
      if (_jspx_eval_html_005fhtml_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("<head>\r\n");
          out.write("<title>:: Bloglog ::</title>\r\n");
          out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n");
          out.write("\r\n");
          out.write("</head>\r\n");
          out.write("\r\n");
          out.write("<body bgcolor=\"#E3F0F9\" text=\"#000000\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\r\n");
          out.write("\t<div style=\"margin-top:30px; margin-left:auto; margin-right:auto; width:476px; background-color:#FFFFFF; height:287px; margin-bottom:10px;\">\r\n");
          out.write("\t\t<div style=\"height:23px; background-color:#147ABA;\"><img src=\"/bloglog/content/add_coment/img_add/tit_login.jpg\" style=\"margin-left:12px;\"></div>\r\n");
          out.write("\r\n");
          out.write("<br>\r\n");
          out.write("<br><br>\r\n");
          out.write("<form name=\"userCommentatorForm\" method=\"post\" action=\"/blog/user.do?act=login\">\r\n");
          out.write("\t\t<input type=\"hidden\" name=\"postId\" value=\"");
          out.print(postId);
          out.write("\"/>\r\n");
          out.write("\t\t<input type=\"hidden\" name=\"countComments\" value=\"");
          out.print(countComments);
          out.write("\"/>\r\n");
          out.write("\t\t<input type=\"hidden\" name=\"path\" value=\"");
          out.print(path);
          out.write("\"/>\r\n");
          out.write("\t\t<input type=\"hidden\" name=\"from\" value=\"site\"/>\r\n");
          out.write("\t\t<input type=\"hidden\" name=\"status\" value=\"");
          out.print(status);
          out.write("\"/>\r\n");
          out.write("\r\n");
          out.write("  <table width=\"100\" border=\"0\" align=\"center\">\r\n");
          out.write("    <tr> \r\n");
          out.write("      <td style=\"font-size:11px; font-weight:bold; color:#147ABA; font-family:Trebuchet MS;\">Login:</td>\r\n");
          out.write("      <td>&nbsp;</td>\r\n");
          out.write("      <td> \r\n");
          out.write("        <input type=\"text\" name=\"login\" style=\"height:18px; width:100px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;\">\r\n");
          out.write("      </td>\r\n");
          out.write("    </tr>\r\n");
          out.write("    <tr> \r\n");
          out.write("      <td style=\"font-size:11px; font-weight:bold; color:#147ABA; font-family:Trebuchet MS;\">Senha:</td>\r\n");
          out.write("      <td>&nbsp;</td>\r\n");
          out.write("      <td> \r\n");
          out.write("        <input type=\"password\" name=\"password\" style=\"height:18px; width:100px; background-color:#F8F8F8; border:1px solid #B7D3E9; font-size:12px; line-height:16px; font-family:Trebuchet MS;\">\r\n");
          out.write("      </td>\r\n");
          out.write("    </tr>\r\n");
          out.write("    <tr>\r\n");
          out.write("      <td>&nbsp;</td>\r\n");
          out.write("      <td>&nbsp;</td>\r\n");
          out.write("      <td>\r\n");
          out.write("        <input type=\"submit\" name=\"Submit\" value=\"Entrar\" style=\"background-color:#147ABA; color:#FFFFFF; font-weight:bold; border:1px solid #147ABA; cursor:hand;\">\r\n");
          out.write("      </td>\r\n");
          out.write("    </tr>\r\n");
          out.write("  </table>\r\n");
          out.write("  <center>\r\n");
          out.write("  ");
          if (_jspx_meth_logic_005fpresent_005f0(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("  <br>\r\n");
          out.write("<a href=\"/blog/blogs/content/add_user_in.jsp?postId=");
          out.print(postId);
          out.write("&countComments=");
          out.print(countComments);
          out.write("&path=");
          out.print(path);
          out.write("&from=site&status=");
          out.print(status);
          out.write("\" style=\"font-size:10px; color:#F47320; text-decoration:underline; font-family:Trebuchet MS;\"> Cadastre-se</a>\r\n");
          out.write("\r\n");
          out.write("\t\t\r\n");
          out.write("  </center>\r\n");
          out.write("</form>\r\n");
          out.write("</div>\r\n");
          out.write("</body>\r\n");
          int evalDoAfterBody = _jspx_th_html_005fhtml_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_html_005fhtml_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fhtml_005fhtml.reuse(_jspx_th_html_005fhtml_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fhtml_005fhtml.reuse(_jspx_th_html_005fhtml_005f0);
      out.write('\r');
      out.write('\n');
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

  private boolean _jspx_meth_logic_005fpresent_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  logic:present
    org.apache.struts.taglib.logic.PresentTag _jspx_th_logic_005fpresent_005f0 = (org.apache.struts.taglib.logic.PresentTag) _005fjspx_005ftagPool_005flogic_005fpresent_005fname.get(org.apache.struts.taglib.logic.PresentTag.class);
    _jspx_th_logic_005fpresent_005f0.setPageContext(_jspx_page_context);
    _jspx_th_logic_005fpresent_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_logic_005fpresent_005f0.setName("mensagem_erro");
    int _jspx_eval_logic_005fpresent_005f0 = _jspx_th_logic_005fpresent_005f0.doStartTag();
    if (_jspx_eval_logic_005fpresent_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("  \t<font color=\"red\">");
        if (_jspx_meth_bean_005fwrite_005f0(_jspx_th_logic_005fpresent_005f0, _jspx_page_context))
          return true;
        out.write("</font>\r\n");
        out.write("  ");
        int evalDoAfterBody = _jspx_th_logic_005fpresent_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_logic_005fpresent_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005flogic_005fpresent_005fname.reuse(_jspx_th_logic_005fpresent_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005flogic_005fpresent_005fname.reuse(_jspx_th_logic_005fpresent_005f0);
    return false;
  }

  private boolean _jspx_meth_bean_005fwrite_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_005fpresent_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_005fwrite_005f0 = (org.apache.struts.taglib.bean.WriteTag) _005fjspx_005ftagPool_005fbean_005fwrite_005fname_005fnobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_005fwrite_005f0.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fwrite_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_005fpresent_005f0);
    _jspx_th_bean_005fwrite_005f0.setName("mensagem_erro");
    int _jspx_eval_bean_005fwrite_005f0 = _jspx_th_bean_005fwrite_005f0.doStartTag();
    if (_jspx_th_bean_005fwrite_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fwrite_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fwrite_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f0);
    return false;
  }
}
