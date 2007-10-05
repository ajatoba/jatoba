/*
 * Created on 27/12/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.vanguarda.blog.dao;

/**
 * @author Fabio
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.util.LoggerUtil;

public abstract class AbstractDAO {
	protected Connection conn;

	

	public AbstractDAO() {

	}

	public void getConnection() throws Exception {
		if (this.conn == null || this.conn.isClosed()) {
			try {
				LoggerUtil.debug("PEGANDO CONEXÃO DO POLL");
				openConnection();
			} catch (SQLException sqle) {
				LoggerUtil.error("ERRO AO PEGAR CONEXÃO DO POLL", sqle);			
				throw sqle;

			} catch (Exception e) {
				LoggerUtil.error("ERRO AO PEGAR CONEXÃO DO POLL", e);
				throw e;

			}
		}
	}

	private void openConnection() throws Exception {
		
		String jndiLookup = (String) BlogManager.getInstance().getProperties().get("jndilookup");
		InitialContext context = new InitialContext();
		DataSource ds = null;
		
		if("java:/blog".equals(jndiLookup)){
			ds = (DataSource) context.lookup(jndiLookup);		
		}else{		 
			Context ambiente = (Context) context.lookup("java:comp/env"); 
			ds = (DataSource) ambiente.lookup(jndiLookup);		
		}
			
		this.conn = ds.getConnection();
			
}

	public void close() {
		try {
			if (this.conn != null) {
				this.conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void closeAll(ResultSet rs, Statement stmt) throws SQLException {
		
		LoggerUtil.debug("FECHANDO OBJETOS DE CONEXÃO");
		
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (this.conn != null) {
			try {
				LoggerUtil.debug("RETORNANDO CONEXÃO");
				this.conn.close();
			} catch (SQLException e1) {
				LoggerUtil.error("ERRO AO RETORNAR CONEXÃO DO POLL", e1);
				e1.printStackTrace();
				throw e1;

			}
		}
	}

	public Object execute(String method, ArrayList parameters) throws Exception {
		throw new NoSuchMethodException(method);
	}

}