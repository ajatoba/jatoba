package com.vanguarda.blog.dao;

import java.sql.SQLException;

import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.bean.UserCommentator;
import com.vanguarda.blog.exception.EmailExistsException;
import com.vanguarda.blog.exception.InvalidPasswordException;
import com.vanguarda.blog.exception.LoginExistsException;
import com.vanguarda.blog.exception.LoginNotExistsException;

public interface UserCommentatorDAO { 
	
	public User login(String login , String password) throws SQLException, LoginNotExistsException, InvalidPasswordException,Exception;
	
	public void add(UserCommentator user) throws SQLException, LoginExistsException, EmailExistsException,Exception;
	
	public void update(UserCommentator user) throws SQLException, LoginExistsException, EmailExistsException,Exception;
	
	public UserCommentator load(int id) throws SQLException,Exception;
	
	public void delete(UserCommentator user) throws SQLException,Exception;	

	
	
}
 
