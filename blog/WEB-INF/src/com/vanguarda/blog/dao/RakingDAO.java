/*
 * Created on 15/03/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.dao;

import java.sql.SQLException;
import java.util.Collection;

/**
 * @author Fabio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface RakingDAO {
	
	public Collection getRanking(int days) throws SQLException,Exception;

}
