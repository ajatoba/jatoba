package com.vanguarda.blog.dao;

import java.sql.SQLException;

import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.exception.InvalidPasswordException;
import com.vanguarda.blog.exception.LoginNotExistsException;

public interface AdminUserDAO{
 
	public User login(String login , String password) throws SQLException, LoginNotExistsException, InvalidPasswordException,Exception;
	
}
 
