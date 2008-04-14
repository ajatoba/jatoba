// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name: UserCommentatorDAOImpl.java

package com.vanguarda.blog.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.bean.Quadrante;
import com.vanguarda.blog.dao.AbstractDAO;
import com.vanguarda.blog.dao.QuadranteDAO;
import com.vanguarda.blog.util.Constants;

public class QuadranteDAOImpl extends AbstractDAO   implements
		QuadranteDAO {

	public QuadranteDAOImpl() {
	}

	
	private static final String SELECT_VALUES = "SELECT NM_QUADRANT_ID ,VC_TITLE,VC_DESCRIPTION,VC_URL,VC_IMAGE,DT_INSERTDATE,NM_STATUS,NM_BLOG_ID_FK,VC_OTHER FROM TB_QUADRANT ";

	private static final String INSERT_QUERY = "INSERT INTO TB_QUADRANT (VC_TITLE,VC_DESCRIPTION,VC_URL,VC_IMAGE,DT_INSERTDATE,NM_STATUS,VC_OTHER,NM_BLOG_ID_FK) VALUES(?,?,?,?,NOW(),?,?,?) ";

	private static final String UPDATE_QUERY = "UPDATE TB_QUADRANT SET VC_TITLE = ?,VC_DESCRIPTION = ?,VC_URL = ?,VC_IMAGE = ?,NM_STATUS = ?,NM_BLOG_ID_FK = ?,VC_OTHER=? WHERE NM_QUADRANT_ID = ?";

	private static final String LOAD_QUERY =SELECT_VALUES+"  WHERE NM_QUADRANT_ID = ? ";

	private static final String DELETE_QUERY = "DELETE FROM TB_QUADRANT WHERE NM_QUADRANT_ID = ?";

	public void add(Quadrante quadrante) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();

			ps = conn.prepareStatement(INSERT_QUERY);
			ps.setString(1, quadrante.getTitle());
			ps.setString(2, quadrante.getDescription());
			ps.setString(3, quadrante.getUrl());
			ps.setString(4, quadrante.getUrlFoto());
			ps.setInt(5, quadrante.getStatus());
			ps.setString(6, quadrante.getOther());
			ps.setInt(7, quadrante.getBlog().getId());
			
			ps.execute();
		} catch (SQLException sqle) {
			sqle.printStackTrace();			
				throw new SQLException();
		} finally {
			closeAll(rs, ps);
		}
		return;
		
	}


	public void update(Quadrante quadrante) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();

			ps = conn.prepareStatement(UPDATE_QUERY);
			ps.setString(1, quadrante.getTitle());
			ps.setString(2, quadrante.getDescription());
			ps.setString(3, quadrante.getUrl());
			ps.setString(4, quadrante.getUrlFoto());
			ps.setInt(5, quadrante.getStatus());			
			ps.setInt(6, quadrante.getBlog().getId());
			ps.setString(7, quadrante.getOther());
			ps.setInt(8, quadrante.getCode());
			
			ps.execute();
		} catch (SQLException sqle) {
			sqle.printStackTrace();			
				throw new SQLException();
		} finally {
			closeAll(rs, ps);
		}
		return;
		
	}



	public void delete(int id) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			getConnection();
			ps = conn
					.prepareStatement(DELETE_QUERY);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();			
				throw new SQLException();
		} finally {
			closeAll(rs, ps);
		}
		return;
		
	}


	public Collection listaQuadrantes(int status) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Quadrante quadrante = null;
		
		Collection quadrantes = new ArrayList();
		
		try {
			getConnection();
			
			if (status == Constants.STATUS_ALL) {
				ps = conn.prepareStatement(SELECT_VALUES.concat(
						 " WHERE NM_STATUS >= -1 "));						  
			} else {
				ps = conn.prepareStatement(SELECT_VALUES.concat(
				 " WHERE NM_STATUS =  ")+status);
			}
						
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				quadrante = new Quadrante();
				quadrante.setCode(rs.getInt("NM_QUADRANT_ID"));
				quadrante.setTitle(rs.getString("VC_TITLE"));
				quadrante.setDescription(rs.getString("VC_DESCRIPTION"));
				quadrante.setUrl(rs.getString("VC_URL"));
				quadrante.setUrlFoto(rs.getString("VC_IMAGE"))	;			
				quadrante.setStatus(rs.getInt("NM_STATUS"));
				quadrante.setOther(rs.getString("VC_OTHER"));
				Blog b = new Blog();
				b.setId(rs.getInt("NM_BLOG_ID_FK"));				
				quadrante.setBlog(b);
				
				quadrantes.add(quadrante);
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();			
				throw new SQLException();
		} finally {
			closeAll(rs, ps);
		}
		return quadrantes;
	}
	
	public Quadrante load(int id) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Quadrante quadrante = null;
		
		try {
			getConnection();
			
			ps = conn
					.prepareStatement(LOAD_QUERY);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				quadrante = new Quadrante();
				quadrante.setCode(rs.getInt("NM_QUADRANT_ID"));
				quadrante.setTitle(rs.getString("VC_TITLE"));
				quadrante.setDescription(rs.getString("VC_DESCRIPTION"));
				quadrante.setUrl(rs.getString("VC_URL"));
				quadrante.setUrlFoto(rs.getString("VC_IMAGE"))	;			
				quadrante.setStatus(rs.getInt("NM_STATUS"));
				quadrante.setOther(rs.getString("VC_OTHER"));
				Blog b = new Blog();
				b.setId(rs.getInt("NM_BLOG_ID_FK"));				
				quadrante.setBlog(b);
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();			
				throw new SQLException();
		} finally {
			closeAll(rs, ps);
		}
		
		return quadrante;
	}
}
