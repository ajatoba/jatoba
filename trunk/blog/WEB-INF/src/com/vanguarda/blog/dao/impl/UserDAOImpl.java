package com.vanguarda.blog.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vanguarda.blog.bean.AdminUser;
import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.Group;
import com.vanguarda.blog.bean.User;
import com.vanguarda.blog.bean.UserCommentator;
import com.vanguarda.blog.dao.AbstractDAO;
import com.vanguarda.blog.dao.AdminUserDAO;
import com.vanguarda.blog.exception.InvalidPasswordException;
import com.vanguarda.blog.exception.LoginNotExistsException;
import com.vanguarda.blog.exception.UserBlockedException;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.LoggerUtil;


	
public class UserDAOImpl extends AbstractDAO implements AdminUserDAO{
	
	private static final String LOGIN_QUERY = "SELECT NM_USER_ID,VC_FIRSTNAME,VC_LASTNAME,VC_EMAIL,U.DT_INSERTDATE,U.NM_STATUS," +
	"VC_LOGIN,VC_PASSWORD, NM_GROUP_ID_FK, NM_BLOG_ID,VC_NAME,VC_DESCRIPTION,B.DT_INSERTDATE,B.NM_STATUS,VC_PATH FROM TB_BLOG_USER AS U LEFT JOIN " +
	"TB_BLOG AS B ON NM_USER_ID_FK = NM_USER_ID WHERE VC_LOGIN = ? AND (U.NM_STATUS = 1 or U.NM_STATUS = 2) ";
 
	public User login(String login , String password) throws SQLException, LoginNotExistsException, InvalidPasswordException,Exception
	{
		
		LoggerUtil.debug("AUTENTICANDO USUARIO LOGIN: "+login);
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		Blog blog = null;

		try {
			
			getConnection();
			
			ps = conn.prepareStatement(LOGIN_QUERY);

			ps.setString(1,login);			

			rs = ps.executeQuery();

			if (rs.next()) {
				
				if(!password.equals(rs.getString("VC_PASSWORD")))
				{
					throw new InvalidPasswordException();
				}
				
				if(rs.getInt("NM_GROUP_ID_FK") == Constants.USER_ADMIN)
				{
					user = new AdminUser();
				}
				else if(rs.getInt("NM_GROUP_ID_FK") == Constants.USER_BLOGGER){
					user  = new BlogUser();
				}
				else
				{
					user = new UserCommentator();
				}
				
				blog = new Blog();
				blog.setId(rs.getInt("NM_BLOG_ID"));
				blog.setDescription(rs.getString("VC_DESCRIPTION"));				
				blog.setInsertDate(rs.getDate("B.DT_INSERTDATE"));
				blog.setName(rs.getString("VC_NAME"));
				blog.setPath(rs.getString("VC_PATH"));
				blog.setStatus(rs.getInt("B.NM_STATUS"));
				
				Group group = new Group(rs.getInt("NM_GROUP_ID_FK"));
				
				user.setBlog(blog);
				user.setId(rs.getInt("NM_USER_ID"));
				user.setFirstName(rs.getString("VC_FIRSTNAME"));
				user.setLastName(rs.getString("VC_LASTNAME"));
				user.setEmail(rs.getString("VC_EMAIL"));
				user.setInsertDate(new java.util.Date(rs.getDate("DT_INSERTDATE").getTime()));
				user.setStatus(rs.getInt("U.NM_STATUS"));
				user.setLogin(rs.getString("VC_LOGIN"));
				user.setPassword(rs.getString("VC_PASSWORD"));
				user.setGroup(group);

			}else{
				throw new LoginNotExistsException();
			}
			
			if(user.getStatus() == Constants.STATUS_BLOCKEAD)
			{
				throw new UserBlockedException();
			}

			return user;
		} finally {
			closeAll(rs, ps);
		}
		
	}
		
	
	
}
 
