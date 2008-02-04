package com.vanguarda.blog.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.dao.BlogUserDAO;
import com.vanguarda.blog.exception.EmailExistsException;
import com.vanguarda.blog.exception.InvalidPasswordException;
import com.vanguarda.blog.exception.LoginExistsException;
import com.vanguarda.blog.exception.LoginNotExistsException;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.LoggerUtil;

public class BlogUserDAOImpl extends UserDAOImpl implements BlogUserDAO {

	private static final String SELECT_USER_VALUES = " NM_USER_ID,VC_FIRSTNAME,VC_LASTNAME,VC_EMAIL,DT_INSERTDATE,NM_STATUS,"
			+ "VC_LOGIN,VC_PASSWORD, NM_GROUP_ID_FK ";

	private static final String INSERT_QUERY = "INSERT INTO TB_BLOG_USER (VC_FIRSTNAME,VC_LASTNAME,VC_EMAIL,"
			+ "DT_INSERTDATE,NM_STATUS, VC_LOGIN,VC_PASSWORD, NM_GROUP_ID_FK) VALUES(?,?,?,NOW(),?,?,?,?) ";

	private static final String UPDATE_QUERY = "UPDATE TB_BLOG_USER SET VC_FIRSTNAME = ?,VC_LASTNAME = ?,VC_EMAIL = ?,"
			+ "NM_STATUS = ?, VC_LOGIN = ? , VC_PASSWORD = ? WHERE NM_USER_ID = ?";

	private static final String LOAD_QUERY = "SELECT " + SELECT_USER_VALUES
			+ " FROM TB_BLOG_USER WHERE NM_USER_ID = ? ";

	private static final String DELETE_QUERY = "DELETE FROM TB_BLOG_USER WHERE NM_USER_ID = ?";

	private static final String LIST_USERS_QUERY = "SELECT "
			+ SELECT_USER_VALUES + " FROM TB_BLOG_USER WHERE NM_STATUS = ? ";

	private static final String LIST_USERS_GROUP_QUERY = "SELECT "
			+ SELECT_USER_VALUES + " FROM TB_BLOG_USER WHERE NM_STATUS = ? "
			+ "AND NM_GROUP_ID_FK = ?";

	private static final String LIST_USERS_GROUP_ForBlog_QUERY = "SELECT "
			+ SELECT_USER_VALUES
			+ " FROM TB_BLOG_USER WHERE NM_STATUS = ? "
			+ " AND NM_GROUP_ID_FK = ? AND NM_USER_ID NOT IN (SELECT NM_USER_ID_FK FROM TB_BLOG)";

	private static final String REMIND_PASSWORD_QUERY = "SELECT "
			+ SELECT_USER_VALUES + " FROM TB_BLOG_USER WHERE VC_EMAIL = ? ";
	
	private static final String UPDATE_PASSWORD_QUERY = "UPDATE TB_BLOG_USER SET VC_PASSWORD = ? WHERE NM_USER_ID = ?";


	public User login(String login, String password) throws SQLException,
			LoginNotExistsException, InvalidPasswordException, Exception {
		return new UserDAOImpl().login(login, password);
	}

	public void add(BlogUser user) throws SQLException, LoginExistsException,
			EmailExistsException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		LoggerUtil.debug("ADICIONANDO BLOGUEIRO: " + user);

		try {
			getConnection();
			ps = conn.prepareStatement(INSERT_QUERY);

			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getStatus());
			ps.setString(5, user.getLogin());
			ps.setString(6, user.getPassword());
			ps.setInt(7, Constants.USER_BLOGGER);
			ps.execute();
		} catch (SQLException sqle) {

			if (sqle.getMessage().indexOf(user.getLogin()) > 0) {
				throw new LoginExistsException();
			} else if (sqle.getMessage().indexOf(user.getEmail()) > 0) {
				throw new EmailExistsException();
			} else
				throw sqle;
		} finally {
			closeAll(rs, ps);
		}
	}

	public void update(BlogUser user) throws SQLException,
			LoginExistsException, EmailExistsException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();

			ps = conn.prepareStatement(UPDATE_QUERY);

			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getStatus());
			ps.setString(5, user.getLogin());
			ps.setString(6, user.getPassword());
			ps.setInt(7, user.getId());

			ps.executeUpdate();
		} catch (SQLException sqle) {

			if (sqle.getMessage().indexOf("LOGIN") > 0) {
				throw new LoginExistsException();
			} else if (sqle.getMessage().indexOf("EMAIL") > 0) {
				throw new EmailExistsException();
			} else
				throw new SQLException();
		} finally {
			closeAll(rs, ps);
		}
	}

	public BlogUser load(int id) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		BlogUser user = null;

		try {

			getConnection();
			ps = conn.prepareStatement(LOAD_QUERY);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {

				user = new BlogUser();

				user.setId(rs.getInt("NM_USER_ID"));
				user.setFirstName(rs.getString("VC_FIRSTNAME"));
				user.setLastName(rs.getString("VC_LASTNAME"));
				user.setLogin(rs.getString("VC_LOGIN"));
				user.setPassword(rs.getString("VC_PASSWORD"));
				user.setEmail(rs.getString("VC_EMAIL"));
				user.setStatus(rs.getInt("NM_STATUS"));

			}

			return user;

		} finally {
			closeAll(rs, ps);
		}

	}

	public void delete(BlogUser user) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();

			ps = conn.prepareStatement(DELETE_QUERY);

			ps.setInt(1, user.getId());

			ps.executeUpdate();

		} finally {
			closeAll(rs, ps);
		}
	}

	public Collection listUsers(int status) throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection list = new ArrayList();

		BlogUser user = null;

		try {
			getConnection();

			if (status == Constants.STATUS_ALL) {
				ps = conn.prepareStatement(LIST_USERS_QUERY.replaceAll(
						"NM_STATUS = ", "NM_STATUS > "));
			} else {
				ps = conn.prepareStatement(LIST_USERS_QUERY);
			}

			ps.setInt(1, status);

			rs = ps.executeQuery();

			while (rs.next()) {

				user = new BlogUser();

				user.setId(rs.getInt("NM_USER_ID"));
				user.setFirstName(rs.getString("VC_FIRSTNAME"));
				user.setLastName(rs.getString("VC_LASTNAME"));
				user.setLogin(rs.getString("VC_LOGIN"));
				user.setPassword(rs.getString("VC_PASSWORD"));
				user.setEmail(rs.getString("VC_EMAIL"));
				user.setStatus(rs.getInt("NM_STATUS"));

				list.add(user);

			}

			return list;

		} finally {
			closeAll(rs, ps);
		}

	}

	public Collection listUsersByGroupForBlog(int status, int groupId)
			throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection list = new ArrayList();

		BlogUser user = null;

		try {

			getConnection();

			if (status == Constants.STATUS_ALL) {
				ps = conn.prepareStatement(LIST_USERS_GROUP_ForBlog_QUERY
						.replaceAll("NM_STATUS = ", "NM_STATUS > "));
			} else {
				ps = conn.prepareStatement(LIST_USERS_GROUP_ForBlog_QUERY);
			}

			ps.setInt(1, status);
			ps.setInt(2, groupId);

			rs = ps.executeQuery();

			while (rs.next()) {

				user = new BlogUser();

				user.setId(rs.getInt("NM_USER_ID"));
				user.setFirstName(rs.getString("VC_FIRSTNAME"));
				user.setLastName(rs.getString("VC_LASTNAME"));
				user.setLogin(rs.getString("VC_LOGIN"));
				user.setPassword(rs.getString("VC_PASSWORD"));
				user.setEmail(rs.getString("VC_EMAIL"));
				user.setStatus(rs.getInt("NM_STATUS"));

				list.add(user);

			}

			return list;

		} finally {
			closeAll(rs, ps);
		}

	}

	public Collection listUsersByGroup(int status, int groupId)
			throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection list = new ArrayList();

		BlogUser user = null;

		try {

			getConnection();

			if (status == Constants.STATUS_ALL) {
				ps = conn.prepareStatement(LIST_USERS_GROUP_QUERY.replaceAll(
						"NM_STATUS = ", "NM_STATUS > "));
			} else {
				ps = conn.prepareStatement(LIST_USERS_GROUP_QUERY);
			}

			ps.setInt(1, status);
			ps.setInt(2, groupId);

			rs = ps.executeQuery();

			while (rs.next()) {

				user = new BlogUser();

				user.setId(rs.getInt("NM_USER_ID"));
				user.setFirstName(rs.getString("VC_FIRSTNAME"));
				user.setLastName(rs.getString("VC_LASTNAME"));
				user.setLogin(rs.getString("VC_LOGIN"));
				user.setPassword(rs.getString("VC_PASSWORD"));
				user.setEmail(rs.getString("VC_EMAIL"));
				user.setStatus(rs.getInt("NM_STATUS"));

				list.add(user);

			}

			return list;

		} finally {
			closeAll(rs, ps);
		}

	}

	public BlogUser remindPassword(String email) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		BlogUser user = null;

		try {

			getConnection();
			ps = conn.prepareStatement(REMIND_PASSWORD_QUERY);

			ps.setString(1, email);

			rs = ps.executeQuery();

			if (rs.next()) {

				user = new BlogUser();

				user.setId(rs.getInt("NM_USER_ID"));
				user.setFirstName(rs.getString("VC_FIRSTNAME"));
				user.setLastName(rs.getString("VC_LASTNAME"));
				user.setLogin(rs.getString("VC_LOGIN"));
				user.setPassword(rs.getString("VC_PASSWORD"));
				user.setEmail(rs.getString("VC_EMAIL"));
				user.setStatus(rs.getInt("NM_STATUS"));

			}

			return user;

		} finally {
			closeAll(rs, ps);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vanguarda.blog.dao.BlogUserDAO#searchUsersByGroup(int,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	public Collection searchUsersByGroup(int groupId, String firstName,
			String lastName, String email) throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection list = new ArrayList();

		BlogUser user = null;
		
		int countParameters = 1;

		try {

			getConnection();
			
			String query = "SELECT "
			+ SELECT_USER_VALUES + " FROM TB_BLOG_USER";
					
			query+=" where NM_GROUP_ID_FK = ?";
					
			if(firstName != null && firstName.length() > 0)
			{
				query+=" AND VC_FIRSTNAME LIKE ?";
			}
			
			if(lastName != null && lastName.length() > 0)
			{
				query+=" AND VC_LASTNAME LIKE ?";
			}
			
			if(email != null && email.length() > 0)
			{
				query+=" AND VC_EMAIL = ? ";
			}
			
			ps = conn.prepareStatement(query);
			ps.setInt(countParameters,groupId);
			countParameters++;
			
			if(firstName != null && firstName.length() > 0)
			{
				ps.setString(countParameters,"%"+firstName+"%");
				countParameters++;
			}
			
			if(lastName != null && lastName.length() > 0)
			{
				ps.setString(countParameters,"%"+lastName+"%");
				countParameters++;
			}
			
			if(email != null && email.length() > 0)
			{
				ps.setString(countParameters,email);
			}
			

			rs = ps.executeQuery();

			while (rs.next()) {

				user = new BlogUser();

				user.setId(rs.getInt("NM_USER_ID"));
				user.setFirstName(rs.getString("VC_FIRSTNAME"));
				user.setLastName(rs.getString("VC_LASTNAME"));
				user.setLogin(rs.getString("VC_LOGIN"));
				user.setPassword(rs.getString("VC_PASSWORD"));
				user.setEmail(rs.getString("VC_EMAIL"));
				user.setStatus(rs.getInt("NM_STATUS"));

				list.add(user);

			}

			return list;

		} finally {
			closeAll(rs, ps);
		}		
	}
	
	public int countUser(int groupId) throws Exception
	{
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			getConnection();
			
			ps = conn.prepareStatement("SELECT COUNT(*) as qtd FROM TB_BLOG_USER WHERE NM_GROUP_ID_FK = ?");				
			ps.setInt(1,groupId);
			
			rs = ps.executeQuery();	
			if(rs.next())
			{
				result = rs.getInt(1);
			}
			
			
		
			
		} finally{
			closeAll(rs, ps);
		}
		
		return result;
	}

	public void updatePassword(User user, String newPassword) throws SQLException,
				LoginExistsException, EmailExistsException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
		
			getConnection();
		
			ps = conn.prepareStatement(UPDATE_PASSWORD_QUERY);
		
			ps.setString(1, newPassword);
			ps.setInt(2, user.getId());

			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw sqle;
		} finally {
			closeAll(rs, ps);
		}
		}
	
}
