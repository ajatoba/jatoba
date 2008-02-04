package com.vanguarda.blog.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.exception.EmailExistsException;
import com.vanguarda.blog.exception.InvalidPasswordException;
import com.vanguarda.blog.exception.LoginExistsException;
import com.vanguarda.blog.exception.LoginNotExistsException;

public interface BlogUserDAO { 
	
	public User login(String login , String password) throws SQLException, LoginNotExistsException, InvalidPasswordException,Exception;
	
	public void add(BlogUser user) throws SQLException, LoginExistsException, EmailExistsException,Exception;
	
	public void update(BlogUser user) throws SQLException, LoginExistsException, EmailExistsException,Exception;
	
	public BlogUser load(int id) throws SQLException,Exception;
	
	public void delete(BlogUser user) throws SQLException,Exception;
	
	public Collection listUsers(int status) throws SQLException,Exception;
	
	public Collection listUsersByGroup(int status,int groupId) throws SQLException,Exception;
	
	public Collection listUsersByGroupForBlog(int status,int groupId) throws SQLException,Exception;
	
	public Collection searchUsersByGroup(int groupId, String firstName, String lastName, String email) throws SQLException,Exception;
	
	public BlogUser remindPassword(String email) throws SQLException,Exception;
	
	public int countUser(int groupId) throws Exception;
	
	public void updatePassword(User user, String newPassword) throws SQLException, LoginNotExistsException, InvalidPasswordException,Exception;
	
}
 
