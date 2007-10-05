package com.vanguarda.blog.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.vanguarda.blog.bean.*;

public interface FavoriteDAO {

	public void add(String[] favorites, BlogUser user) throws SQLException,Exception;
	
	public Collection getFavorites(int userId) throws SQLException,Exception;
}
