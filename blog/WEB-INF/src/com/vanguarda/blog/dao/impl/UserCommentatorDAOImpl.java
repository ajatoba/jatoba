// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name: UserCommentatorDAOImpl.java

package com.vanguarda.blog.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.bean.Group;
import com.vanguarda.blog.bean.UserCommentator;
import com.vanguarda.blog.dao.UserCommentatorDAO;
import com.vanguarda.blog.exception.EmailExistsException;
import com.vanguarda.blog.exception.InvalidPasswordException;
import com.vanguarda.blog.exception.LoginExistsException;
import com.vanguarda.blog.exception.LoginNotExistsException;

public class UserCommentatorDAOImpl extends UserDAOImpl implements
		UserCommentatorDAO {

	public UserCommentatorDAOImpl() {
	}

	public User login(String login, String password) throws SQLException,
			LoginNotExistsException, InvalidPasswordException, Exception {
		return (new UserDAOImpl()).login(login, password);
	}

	public void add(UserCommentator user) throws SQLException,
			LoginExistsException, EmailExistsException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();

			ps = conn
					.prepareStatement("INSERT INTO TB_BLOG_USER (VC_FIRSTNAME,VC_LASTNAME,VC_EMAIL,DT_INSERTDATE,NM_STATUS, VC_LOGIN,VC_PASSWORD, NM_GROUP_ID_FK, DT_BIRTHDATE, VC_GENDER, VC_STATE, VC_CITY) VALUES(?,?,?,NOW(),?,?,?,?,?,?,?,?) ");
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getStatus());
			ps.setString(5, user.getLogin());
			ps.setString(6, user.getPassword());
			ps.setInt(7, 3);
			ps.setDate(8, new java.sql.Date(user.getBirthDate().getYear(), user
					.getBirthDate().getMonth(), user.getBirthDate().getDate()));
			ps.setString(9, user.getGender());
			ps.setString(10, user.getState());
			ps.setString(11, user.getCity());
			ps.execute();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (sqle.getMessage().indexOf(user.getLogin()) > 0)
				throw new LoginExistsException();
			if (sqle.getMessage().indexOf(user.getEmail()) > 0)
				throw new EmailExistsException();
			else
				throw new SQLException();
		} finally {
			closeAll(rs, ps);
		}
		return;
	}

	public void update(UserCommentator user) throws SQLException,
			LoginExistsException, EmailExistsException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			getConnection();

			ps = conn.prepareStatement("UPDATE TB_BLOG_USER SET VC_FIRSTNAME = ?,VC_LASTNAME = ?," +
					"VC_EMAIL = ?, VC_GENDER = ?, VC_STATE = ?, VC_CITY = ?, DT_BIRTHDATE = ? WHERE NM_USER_ID = ?");
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getGender());
			ps.setString(5, user.getState());
			ps.setString(6, user.getCity());
			ps.setDate(7, new java.sql.Date(user.getBirthDate().getYear(), user
					.getBirthDate().getMonth(), user.getBirthDate().getDate()));
			ps.setInt(8, user.getId());
			
			ps.executeUpdate();
		} catch (SQLException sqle) {
			if (sqle.getMessage().indexOf(user.getLogin()) > 0)
				throw new LoginExistsException();
			if (sqle.getMessage().indexOf(user.getEmail()) > 0)
				throw new EmailExistsException();
			else
				throw new SQLException();
		} finally {
			closeAll(rs, ps);
		}
		return;
	}

	public UserCommentator load(int id) throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		UserCommentator user = null;
		UserCommentator usercommentator;
		try {
			getConnection();
			
			ps = conn
					.prepareStatement("SELECT  NM_USER_ID,VC_FIRSTNAME,VC_LASTNAME," +
							"VC_EMAIL,NM_STATUS,VC_LOGIN,VC_PASSWORD,NM_GROUP_ID_FK," +
							"DT_INSERTDATE,VC_GENDER, VC_STATE, VC_CITY,DT_BIRTHDATE " +
							" FROM TB_BLOG_USER WHERE NM_USER_ID = ? ");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserCommentator();
				user.setId(rs.getInt("NM_USER_ID"));
				user.setFirstName(rs.getString("VC_FIRSTNAME"));
				user.setLastName(rs.getString("VC_LASTNAME"));
				user.setLogin(rs.getString("VC_LOGIN"));
				user.setPassword(rs.getString("VC_PASSWORD"));
				user.setEmail(rs.getString("VC_EMAIL"));
				user.setStatus(rs.getInt("NM_STATUS"));
				user.setGender(rs.getString("VC_GENDER"));
				user.setBirthDate(rs.getDate("DT_BIRTHDATE"));
				user.setCity(rs.getString("VC_CITY"));
				user.setState(rs.getString("VC_STATE"));
				Group group = new Group(rs.getInt("NM_GROUP_ID_FK"));				
				user.setGroup(group);
				
			}
			usercommentator = user;
		} finally {
			closeAll(rs, ps);
		}
		return usercommentator;
	}

	public void delete(UserCommentator user) throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			getConnection();
			ps = conn
					.prepareStatement("DELETE FROM TB_BLOG_USER WHERE NM_USER_ID = ?");
			ps.setInt(1, user.getId());
			ps.executeUpdate();
		} finally {
			closeAll(rs, ps);
		}
		return;
	}

	public void block(int userId, int action) throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			getConnection();

			ps = conn
					.prepareStatement("UPDATE TB_BLOG_USER SET NM_STATUS = ? WHERE NM_USER_ID = ?");
			ps.setInt(1, action);
			ps.setInt(2, userId);

			ps.executeUpdate();
		} catch (SQLException sqle) {

			throw new SQLException(sqle.getMessage());
		} finally {
			closeAll(rs, ps);
		}
		return;
	}

	private static final String SELECT_USER_VALUES = " NM_USER_ID,VC_FIRSTNAME,VC_LASTNAME,VC_EMAIL,DT_INSERTDATE,NM_STATUS,VC_LOGIN,VC_PASSWORD, NM_GROUP_ID_FK ";

	private static final String INSERT_QUERY = "INSERT INTO TB_BLOG_USER (VC_FIRSTNAME,VC_LASTNAME,VC_EMAIL,DT_INSERTDATE,NM_STATUS, VC_LOGIN,VC_PASSWORD, NM_GROUP_ID_FK) VALUES(?,?,?,NOW(),?,?,?,?) ";

	private static final String UPDATE_QUERY = "UPDATE TB_BLOG_USER SET VC_FIRSTNAME = ?,VC_LASTNAME = ?,VC_EMAIL = ?,NM_STATUS = ?, VC_LOGIN = ? , VC_PASSWORD = ? WHERE NM_USER_ID = ?";

	private static final String LOAD_QUERY = "SELECT  NM_USER_ID,VC_FIRSTNAME,VC_LASTNAME,VC_EMAIL,DT_INSERTDATE,NM_STATUS,VC_LOGIN,VC_PASSWORD, NM_GROUP_ID_FK  FROM TB_BLOG_USER WHERE NM_USER_ID = ? ";

	private static final String DELETE_QUERY = "DELETE FROM TB_BLOG_USER WHERE NM_USER_ID = ?";
}
