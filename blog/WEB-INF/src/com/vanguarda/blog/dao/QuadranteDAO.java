/*
 * Created on 15/03/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.vanguarda.blog.bean.Quadrante;

/**
 * @author Fabio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface QuadranteDAO {
	
public void add(Quadrante quadrante) throws SQLException,Exception;
	
	public void update(Quadrante quadrante) throws SQLException,Exception;
	
	public Quadrante load(int id) throws SQLException,Exception;
	
	public void delete(int id) throws SQLException,Exception;
	
	public Collection listaQuadrantes(int status) throws SQLException,Exception;
	

}
