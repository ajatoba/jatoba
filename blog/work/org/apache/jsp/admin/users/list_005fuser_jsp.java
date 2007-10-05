package org.apache.jsp.admin.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class list_005fuser_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(6);
    _jspx_dependants.add("/WEB-INF/struts-bean.tld");
    _jspx_dependants.add("/WEB-INF/struts-logic.tld");
    _jspx_dependants.add("/WEB-INF/struts-html.tld");
    _jspx_dependants.add("/WEB-INF/pager-taglib.tld");
    _jspx_dependants.add("/admin/includes/menu.jsp");
    _jspx_dependants.add("/admin/includes/footer.html");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fpg_005fpager_005furl_005fmaxPageItems_005fmaxIndexPages;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flogic_005fiterate_005fname_005findexId_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fpg_005fitem;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fotherwise;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fpg_005findex;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fpg_005fprev;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fpg_005fpages;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fpg_005fnext;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fpg_005fpager_005furl_005fmaxPageItems_005fmaxIndexPages = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005flogic_005fiterate_005fname_005findexId_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fpg_005fitem = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fwhen_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fotherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fpg_005findex = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fpg_005fprev = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fpg_005fpages = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fpg_005fnext = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fpg_005fpager_005furl_005fmaxPageItems_005fmaxIndexPages.release();
    _005fjspx_005ftagPool_005flogic_005fiterate_005fname_005findexId_005fid.release();
    _005fjspx_005ftagPool_005fpg_005fitem.release();
    _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fotherwise.release();
    _005fjspx_005ftagPool_005fpg_005findex.release();
    _005fjspx_005ftagPool_005fpg_005fprev.release();
    _005fjspx_005ftagPool_005fpg_005fpages.release();
    _005fjspx_005ftagPool_005fpg_005fnext.release();
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("<body>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/bloglog/admin/css/stylle.css\" type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("<table cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("<tr>\r\n");
      out.write("<td><img src=\"/bloglog/admin/img/header.jpg\" border=\"0\"></td>\r\n");
      out.write("<td><img src=\"/bloglog/admin/img/header-02.jpg\" border=\"0\"></td>\r\n");
      out.write("<td><img src=\"/bloglog/admin/img/header-03.jpg\" border=\"0\"></td>\r\n");
      out.write("<td><img src=\"/bloglog/admin/img/header-04.jpg\" border=\"0\"></td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("<br><br>\r\n");
      out.write("\r\n");
      out.write("<table width=\"770\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" bgcolor=\"#CCCCCC\" bordercolor=\"#000000\">\r\n");
      out.write("  <tr> \r\n");
      out.write("    <td width=\"10%\"><b><a href=\"/blog/admin/blog.do?act=list\">Blogs</a></b></td>\r\n");
      out.write("    <td width=\"10%\"><b><a href=\"/blog/admin/blogUser.do?act=list\">Usuarios</a></b></td>\r\n");
      out.write("    <!--td width=\"10%\"><b><a href=\"/blog/admin/topic.do?act=list\">Tópicos</a></b></td-->\r\n");
      out.write("\t<td width=\"84%\"><b><a href=\"/blog/adminUser.do?act=logout\">Logout</a></b</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<br>\r\n");
      out.write("\r\n");
      //  pg:pager
      com.jsptags.navigation.pager.PagerTag _jspx_th_pg_005fpager_005f0 = (com.jsptags.navigation.pager.PagerTag) _005fjspx_005ftagPool_005fpg_005fpager_005furl_005fmaxPageItems_005fmaxIndexPages.get(com.jsptags.navigation.pager.PagerTag.class);
      _jspx_th_pg_005fpager_005f0.setPageContext(_jspx_page_context);
      _jspx_th_pg_005fpager_005f0.setParent(null);
      _jspx_th_pg_005fpager_005f0.setUrl("blogUser.do");
      _jspx_th_pg_005fpager_005f0.setMaxIndexPages(5);
      _jspx_th_pg_005fpager_005f0.setMaxPageItems(150);
      int _jspx_eval_pg_005fpager_005f0 = _jspx_th_pg_005fpager_005f0.doStartTag();
      if (_jspx_eval_pg_005fpager_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\r\n");
          out.write("<table border =\"0\" ><tr><td><a href=\"users/add_user_in.jsp\">[[NOVO]]</a></td></tr></table>\r\n");
          out.write("<table width=\"770\" border=\"0\">\r\n");
          out.write("  <tr bgcolor=\"#FFFFCC\"> \r\n");
          out.write("    <td width=\"30%\"><b>Nome</b></td>\r\n");
          out.write("    <td width=\"30%\"><b>E-mail</b></td>\r\n");
          out.write("    <td width=\"15%\"><b>Login</b></td>\r\n");
          out.write("    <td width=\"5%\"><b>Status</b></td>\r\n");
          out.write("    <td ><b></b></td>\r\n");
          out.write("  </tr>\r\n");
          out.write("  \r\n");
          out.write("  ");
          //  logic:iterate
          org.apache.struts.taglib.logic.IterateTag _jspx_th_logic_005fiterate_005f0 = (org.apache.struts.taglib.logic.IterateTag) _005fjspx_005ftagPool_005flogic_005fiterate_005fname_005findexId_005fid.get(org.apache.struts.taglib.logic.IterateTag.class);
          _jspx_th_logic_005fiterate_005f0.setPageContext(_jspx_page_context);
          _jspx_th_logic_005fiterate_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_pg_005fpager_005f0);
          _jspx_th_logic_005fiterate_005f0.setName("users");
          _jspx_th_logic_005fiterate_005f0.setId("u");
          _jspx_th_logic_005fiterate_005f0.setIndexId("i");
          int _jspx_eval_logic_005fiterate_005f0 = _jspx_th_logic_005fiterate_005f0.doStartTag();
          if (_jspx_eval_logic_005fiterate_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Object u = null;
            java.lang.Integer i = null;
            if (_jspx_eval_logic_005fiterate_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_logic_005fiterate_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_logic_005fiterate_005f0.doInitBody();
            }
            u = (java.lang.Object) _jspx_page_context.findAttribute("u");
            i = (java.lang.Integer) _jspx_page_context.findAttribute("i");
            do {
              out.write("\r\n");
              out.write("  ");
              if (_jspx_meth_pg_005fitem_005f0(_jspx_th_logic_005fiterate_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("  ");
              int evalDoAfterBody = _jspx_th_logic_005fiterate_005f0.doAfterBody();
              u = (java.lang.Object) _jspx_page_context.findAttribute("u");
              i = (java.lang.Integer) _jspx_page_context.findAttribute("i");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_logic_005fiterate_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.popBody();
            }
          }
          if (_jspx_th_logic_005fiterate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005flogic_005fiterate_005fname_005findexId_005fid.reuse(_jspx_th_logic_005fiterate_005f0);
            return;
          }
          _005fjspx_005ftagPool_005flogic_005fiterate_005fname_005findexId_005fid.reuse(_jspx_th_logic_005fiterate_005f0);
          out.write("\r\n");
          out.write("</table>\r\n");
          out.write("\r\n");
          out.write("<p align=\"center\">\r\n");
          //  pg:index
          com.jsptags.navigation.pager.IndexTag _jspx_th_pg_005findex_005f0 = (com.jsptags.navigation.pager.IndexTag) _005fjspx_005ftagPool_005fpg_005findex.get(com.jsptags.navigation.pager.IndexTag.class);
          _jspx_th_pg_005findex_005f0.setPageContext(_jspx_page_context);
          _jspx_th_pg_005findex_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_pg_005fpager_005f0);
          int _jspx_eval_pg_005findex_005f0 = _jspx_th_pg_005findex_005f0.doStartTag();
          if (_jspx_eval_pg_005findex_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write('\r');
              out.write('\n');
              out.write('	');
              //  pg:prev
              com.jsptags.navigation.pager.PrevTag _jspx_th_pg_005fprev_005f0 = (com.jsptags.navigation.pager.PrevTag) _005fjspx_005ftagPool_005fpg_005fprev.get(com.jsptags.navigation.pager.PrevTag.class);
              _jspx_th_pg_005fprev_005f0.setPageContext(_jspx_page_context);
              _jspx_th_pg_005fprev_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_pg_005findex_005f0);
              int _jspx_eval_pg_005fprev_005f0 = _jspx_th_pg_005fprev_005f0.doStartTag();
              if (_jspx_eval_pg_005fprev_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.String pageUrl = null;
                java.lang.Integer pageNumber = null;
                pageUrl = (java.lang.String) _jspx_page_context.findAttribute("pageUrl");
                pageNumber = (java.lang.Integer) _jspx_page_context.findAttribute("pageNumber");
                do {
                  out.write("\r\n");
                  out.write("\t\t<a href=\"");
                  out.print(pageUrl);
                  out.write("&act=list\">[<< Prev]</a>\t\r\n");
                  out.write("\t");
                  int evalDoAfterBody = _jspx_th_pg_005fprev_005f0.doAfterBody();
                  pageUrl = (java.lang.String) _jspx_page_context.findAttribute("pageUrl");
                  pageNumber = (java.lang.Integer) _jspx_page_context.findAttribute("pageNumber");
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
              }
              if (_jspx_th_pg_005fprev_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005fpg_005fprev.reuse(_jspx_th_pg_005fprev_005f0);
                return;
              }
              _005fjspx_005ftagPool_005fpg_005fprev.reuse(_jspx_th_pg_005fprev_005f0);
              out.write("\r\n");
              out.write("\t\r\n");
              out.write("\t");
              //  pg:pages
              com.jsptags.navigation.pager.PagesTag _jspx_th_pg_005fpages_005f0 = (com.jsptags.navigation.pager.PagesTag) _005fjspx_005ftagPool_005fpg_005fpages.get(com.jsptags.navigation.pager.PagesTag.class);
              _jspx_th_pg_005fpages_005f0.setPageContext(_jspx_page_context);
              _jspx_th_pg_005fpages_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_pg_005findex_005f0);
              int _jspx_eval_pg_005fpages_005f0 = _jspx_th_pg_005fpages_005f0.doStartTag();
              if (_jspx_eval_pg_005fpages_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.String pageUrl = null;
                java.lang.Integer pageNumber = null;
                if (_jspx_eval_pg_005fpages_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_pg_005fpages_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_pg_005fpages_005f0.doInitBody();
                }
                pageUrl = (java.lang.String) _jspx_page_context.findAttribute("pageUrl");
                pageNumber = (java.lang.Integer) _jspx_page_context.findAttribute("pageNumber");
                do {
                  out.write("\r\n");
                  out.write("\t\t<a href=\"");
                  out.print( pageUrl );
                  out.write("&act=list\">");
                  out.print( pageNumber );
                  out.write("</a>\r\n");
                  out.write("\t");
                  int evalDoAfterBody = _jspx_th_pg_005fpages_005f0.doAfterBody();
                  pageUrl = (java.lang.String) _jspx_page_context.findAttribute("pageUrl");
                  pageNumber = (java.lang.Integer) _jspx_page_context.findAttribute("pageNumber");
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_pg_005fpages_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.popBody();
                }
              }
              if (_jspx_th_pg_005fpages_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005fpg_005fpages.reuse(_jspx_th_pg_005fpages_005f0);
                return;
              }
              _005fjspx_005ftagPool_005fpg_005fpages.reuse(_jspx_th_pg_005fpages_005f0);
              out.write("\r\n");
              out.write("\t\r\n");
              out.write("\t");
              //  pg:next
              com.jsptags.navigation.pager.NextTag _jspx_th_pg_005fnext_005f0 = (com.jsptags.navigation.pager.NextTag) _005fjspx_005ftagPool_005fpg_005fnext.get(com.jsptags.navigation.pager.NextTag.class);
              _jspx_th_pg_005fnext_005f0.setPageContext(_jspx_page_context);
              _jspx_th_pg_005fnext_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_pg_005findex_005f0);
              int _jspx_eval_pg_005fnext_005f0 = _jspx_th_pg_005fnext_005f0.doStartTag();
              if (_jspx_eval_pg_005fnext_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.String pageUrl = null;
                java.lang.Integer pageNumber = null;
                pageUrl = (java.lang.String) _jspx_page_context.findAttribute("pageUrl");
                pageNumber = (java.lang.Integer) _jspx_page_context.findAttribute("pageNumber");
                do {
                  out.write("\r\n");
                  out.write("\t\t<a href=\"");
                  out.print( pageUrl);
                  out.write("&act=list\">[Next >>]</a>\r\n");
                  out.write("\t");
                  int evalDoAfterBody = _jspx_th_pg_005fnext_005f0.doAfterBody();
                  pageUrl = (java.lang.String) _jspx_page_context.findAttribute("pageUrl");
                  pageNumber = (java.lang.Integer) _jspx_page_context.findAttribute("pageNumber");
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
              }
              if (_jspx_th_pg_005fnext_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005fpg_005fnext.reuse(_jspx_th_pg_005fnext_005f0);
                return;
              }
              _005fjspx_005ftagPool_005fpg_005fnext.reuse(_jspx_th_pg_005fnext_005f0);
              out.write('\r');
              out.write('\n');
              int evalDoAfterBody = _jspx_th_pg_005findex_005f0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_pg_005findex_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fpg_005findex.reuse(_jspx_th_pg_005findex_005f0);
            return;
          }
          _005fjspx_005ftagPool_005fpg_005findex.reuse(_jspx_th_pg_005findex_005f0);
          out.write("\r\n");
          out.write("</p>\r\n");
          out.write("\r\n");
          int evalDoAfterBody = _jspx_th_pg_005fpager_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_pg_005fpager_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fpg_005fpager_005furl_005fmaxPageItems_005fmaxIndexPages.reuse(_jspx_th_pg_005fpager_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fpg_005fpager_005furl_005fmaxPageItems_005fmaxIndexPages.reuse(_jspx_th_pg_005fpager_005f0);
      out.write("\r\n");
      out.write("\r\n");
      out.write("<table border=0>\r\n");
      out.write("<tr>\r\n");
      out.write("<td>\r\n");
      out.write("<img src=\"/bloglog/admin/img/footer-admin.jpg\" border=\"0\"/>\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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

  private boolean _jspx_meth_pg_005fitem_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_005fiterate_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  pg:item
    com.jsptags.navigation.pager.ItemTag _jspx_th_pg_005fitem_005f0 = (com.jsptags.navigation.pager.ItemTag) _005fjspx_005ftagPool_005fpg_005fitem.get(com.jsptags.navigation.pager.ItemTag.class);
    _jspx_th_pg_005fitem_005f0.setPageContext(_jspx_page_context);
    _jspx_th_pg_005fitem_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_005fiterate_005f0);
    int _jspx_eval_pg_005fitem_005f0 = _jspx_th_pg_005fitem_005f0.doStartTag();
    if (_jspx_eval_pg_005fitem_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("  <tr>   \r\n");
        out.write("    <td width=\"30%\">");
        if (_jspx_meth_bean_005fwrite_005f0(_jspx_th_pg_005fitem_005f0, _jspx_page_context))
          return true;
        out.write(" </td>\r\n");
        out.write("    <td width=\"30%\">");
        if (_jspx_meth_bean_005fwrite_005f1(_jspx_th_pg_005fitem_005f0, _jspx_page_context))
          return true;
        out.write("</td>\r\n");
        out.write("    <td width=\"15%\">");
        if (_jspx_meth_bean_005fwrite_005f2(_jspx_th_pg_005fitem_005f0, _jspx_page_context))
          return true;
        out.write("</td>\r\n");
        out.write("    <td width=\"5%\">\r\n");
        out.write("    ");
        if (_jspx_meth_c_005fchoose_005f0(_jspx_th_pg_005fitem_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    </td>\r\n");
        out.write("    <td ><a href=\"/blog/admin/blogUser.do?act=load&id=");
        if (_jspx_meth_bean_005fwrite_005f3(_jspx_th_pg_005fitem_005f0, _jspx_page_context))
          return true;
        out.write("\">[[ALTERAR]]</a> <a href=\"/blog/admin/blogUser.do?act=delete&id=");
        if (_jspx_meth_bean_005fwrite_005f4(_jspx_th_pg_005fitem_005f0, _jspx_page_context))
          return true;
        out.write("\">[[DELETAR]]</a></td>\r\n");
        out.write("  </tr>\r\n");
        out.write("  ");
        int evalDoAfterBody = _jspx_th_pg_005fitem_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_pg_005fitem_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fpg_005fitem.reuse(_jspx_th_pg_005fitem_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fpg_005fitem.reuse(_jspx_th_pg_005fitem_005f0);
    return false;
  }

  private boolean _jspx_meth_bean_005fwrite_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_pg_005fitem_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_005fwrite_005f0 = (org.apache.struts.taglib.bean.WriteTag) _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_005fwrite_005f0.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fwrite_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_pg_005fitem_005f0);
    _jspx_th_bean_005fwrite_005f0.setName("u");
    _jspx_th_bean_005fwrite_005f0.setProperty("firstName");
    int _jspx_eval_bean_005fwrite_005f0 = _jspx_th_bean_005fwrite_005f0.doStartTag();
    if (_jspx_th_bean_005fwrite_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f0);
    return false;
  }

  private boolean _jspx_meth_bean_005fwrite_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_pg_005fitem_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_005fwrite_005f1 = (org.apache.struts.taglib.bean.WriteTag) _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_005fwrite_005f1.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fwrite_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_pg_005fitem_005f0);
    _jspx_th_bean_005fwrite_005f1.setName("u");
    _jspx_th_bean_005fwrite_005f1.setProperty("email");
    int _jspx_eval_bean_005fwrite_005f1 = _jspx_th_bean_005fwrite_005f1.doStartTag();
    if (_jspx_th_bean_005fwrite_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f1);
    return false;
  }

  private boolean _jspx_meth_bean_005fwrite_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_pg_005fitem_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_005fwrite_005f2 = (org.apache.struts.taglib.bean.WriteTag) _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_005fwrite_005f2.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fwrite_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_pg_005fitem_005f0);
    _jspx_th_bean_005fwrite_005f2.setName("u");
    _jspx_th_bean_005fwrite_005f2.setProperty("login");
    int _jspx_eval_bean_005fwrite_005f2 = _jspx_th_bean_005fwrite_005f2.doStartTag();
    if (_jspx_th_bean_005fwrite_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_pg_005fitem_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_pg_005fitem_005f0);
    int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
    if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_c_005fwhen_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_c_005fotherwise_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    _jspx_th_c_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${u.status == 1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f0 = _jspx_th_c_005fwhen_005f0.doStartTag();
    if (_jspx_eval_c_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t    <img src=\"/bloglog/admin/img/ativo.jpg\" border=\"0\" />\t\t    \r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    int _jspx_eval_c_005fotherwise_005f0 = _jspx_th_c_005fotherwise_005f0.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\t    \r\n");
        out.write("\t    <img src=\"/bloglog/admin/img/inativo.jpg\" border=\"0\" />\r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
    return false;
  }

  private boolean _jspx_meth_bean_005fwrite_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_pg_005fitem_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_005fwrite_005f3 = (org.apache.struts.taglib.bean.WriteTag) _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_005fwrite_005f3.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fwrite_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_pg_005fitem_005f0);
    _jspx_th_bean_005fwrite_005f3.setName("u");
    _jspx_th_bean_005fwrite_005f3.setProperty("id");
    int _jspx_eval_bean_005fwrite_005f3 = _jspx_th_bean_005fwrite_005f3.doStartTag();
    if (_jspx_th_bean_005fwrite_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f3);
    return false;
  }

  private boolean _jspx_meth_bean_005fwrite_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_pg_005fitem_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_005fwrite_005f4 = (org.apache.struts.taglib.bean.WriteTag) _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_005fwrite_005f4.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fwrite_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_pg_005fitem_005f0);
    _jspx_th_bean_005fwrite_005f4.setName("u");
    _jspx_th_bean_005fwrite_005f4.setProperty("id");
    int _jspx_eval_bean_005fwrite_005f4 = _jspx_th_bean_005fwrite_005f4.doStartTag();
    if (_jspx_th_bean_005fwrite_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fwrite_005fproperty_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f4);
    return false;
  }
}
