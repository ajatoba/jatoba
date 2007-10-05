package com.vanguarda.blog.dao.impl;



import java.sql.SQLException;

import com.vanguarda.blog.bean.AdminUser;
import com.vanguarda.blog.bean.User;

import com.vanguarda.blog.dao.AdminUserDAO;
import com.vanguarda.blog.exception.InvalidPasswordException;
import com.vanguarda.blog.exception.LoginNotExistsException;

	
public class AdminUserDAOImpl extends UserDAOImpl implements AdminUserDAO{
		
 
	public User login(String login , String password) throws SQLException, LoginNotExistsException, InvalidPasswordException,Exception
	{
		return (AdminUser) new UserDAOImpl().login(login ,password);
	}
	
	
	public void add(AdminUser user) throws SQLException
	{
		
	}
}
 
