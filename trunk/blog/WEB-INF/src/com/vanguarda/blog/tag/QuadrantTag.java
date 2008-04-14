package com.vanguarda.blog.tag;



import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.bean.*;
/**
 * Classe responsável por construir a lista html de operações
 * 
 * @author fgayani
 */
public class QuadrantTag extends TagSupport {

	private static final long serialVersionUID = 1L;
			
	private int code;
	
	private String property;
	
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * @return EVAL_BODY_INCLUDE
	 * @throws JspException Exceções do tipo JspException
	 */
	public int doStartTag() throws JspException {
		try {
			
			HashMap quadrantes = (HashMap) pageContext.getSession().getAttribute(Constants.QUADRANTS_MAP_BEAN);

			if(quadrantes != null && this.code > 0 && this.property!= null)
			{
				Quadrante q = (Quadrante) quadrantes.get(Integer.valueOf(this.code));
				if("titulo".equals(property))
				{
					pageContext.getOut().println(q.getTitle());					
				}
				else if("texto".equals(property))
				{
					pageContext.getOut().println(q.getDescription());
				}
				else if("imagem".equals(property))
				{
					pageContext.getOut().println(q.getUrlFoto());
				}
				else if("url".equals(property))
				{
					pageContext.getOut().println(q.getUrl());
				}
				else if("flash".equals(property))
				{
					pageContext.getOut().println(q.getOther());
				} 
				
			}
			

			return EVAL_BODY_INCLUDE;

		} catch (Exception e) {

			return SKIP_PAGE;
		}
	}

	/**
	 * @return EVAL_PAGE
	 */
	public int doEndTag() {
		return EVAL_PAGE;
	}
}