package com.vanguarda.blog.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.Ranking;
import com.vanguarda.blog.dao.AbstractDAO;
import com.vanguarda.blog.dao.FavoriteDAO;

public class FavoriteDAOImpl extends AbstractDAO implements FavoriteDAO {

	private static final String SELECT_QUERY = "SELECT  U.NM_USER_ID,U.VC_FIRSTNAME,U.VC_LASTNAME,U.VC_EMAIL,U.DT_INSERTDATE, " +
		"U.NM_STATUS,U.VC_LOGIN,U.VC_PASSWORD, U.NM_GROUP_ID_FK , B.NM_BLOG_ID, B.VC_NAME,B.VC_PATH,  B.VC_DESCRIPTION  ,  B.DT_INSERTDATE , " +
		"B.NM_STATUS,  count(C.NM_COMMENT_ID) as QTD " +
		"FROM TB_BLOG_POST P " +
		"JOIN TB_BLOG B ON NM_BLOG_ID = P.NM_BLOG_ID_FK " +  
		"JOIN TB_USER_FAVORITE_XREF X ON NM_BLOG_ID = X.NM_BLOG_ID_FK  AND X.NM_USER_ID_FK = ? " +
		"JOIN TB_BLOG_USER U ON NM_USER_ID = B.NM_USER_ID_FK " +
		"LEFT JOIN TB_BLOG_COMMENT AS C	 ON C.NM_POST_ID_FK = NM_POST_ID " + 
		"AND C.NM_STATUS = 1 AND B.NM_STATUS = 1 " +
		"GROUP BY  U.NM_USER_ID,U.VC_FIRSTNAME,	U.VC_LASTNAME,U.VC_EMAIL,U.DT_INSERTDATE, " +
		"U.NM_STATUS,U.VC_LOGIN,U.VC_PASSWORD, U.NM_GROUP_ID_FK ,  B.VC_NAME,  B.VC_DESCRIPTION  ,  B.DT_INSERTDATE ,  B.NM_STATUS " + 
		"ORDER BY U.VC_FIRSTNAME LIMIT 0 , 10";


	public void add(String[] favorites, BlogUser user) throws SQLException,Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();
			
			ps = conn.prepareStatement("DELETE FROM TB_USER_FAVORITE_XREF WHERE NM_USER_ID_FK = " + user.getId());
			ps.execute();
			
			String INSERT_QUERY = "INSERT INTO TB_USER_FAVORITE_XREF ( NM_USER_ID_FK ,  NM_BLOG_ID_FK ) VALUES ";
			
			for (int x=0; x<favorites.length; x++){
				INSERT_QUERY +="("+ user.getId()+","+ favorites[x] +")";
				if(x < favorites.length -1)INSERT_QUERY += ","; 
			}
			
			ps = conn.prepareStatement(INSERT_QUERY);			
			
			ps.execute();
			

		} finally {
			closeAll(rs, ps);
		}
		
	}

	public Collection getFavorites(int userId) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		Ranking r = null;
		
		Collection list = new ArrayList();		
		Blog blog = null;
		
		try {

			
			getConnection();
			
			ps = conn.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1,userId);
			
			rs = ps.executeQuery();
			int colocacao = 1;
			while (rs.next()) {
				
				r = new Ranking();
				
				r.setCountComments(rs.getInt("QTD"));
				r.setRanking(colocacao);
				
				blog = new Blog();

				BlogUser user = new BlogUser();

				user.setId(rs.getInt("U.NM_USER_ID"));
				user.setFirstName(rs.getString("U.VC_FIRSTNAME"));
				user.setLastName(rs.getString("U.VC_LASTNAME"));
				user.setLogin(rs.getString("U.VC_LOGIN"));
				user.setPassword(rs.getString("U.VC_PASSWORD"));
				user.setEmail(rs.getString("U.VC_EMAIL"));
				user.setStatus(rs.getInt("U.NM_STATUS"));

				blog.setBlogUser(user);

				blog.setDescription(rs.getString("B.VC_DESCRIPTION"));
				blog.setId(rs.getInt("B.NM_BLOG_ID"));
				blog.setInsertDate(rs.getDate("B.DT_INSERTDATE"));
				blog.setName(rs.getString("B.VC_NAME"));
				blog.setStatus(rs.getInt("B.NM_STATUS"));
				blog.setPath(rs.getString("B.VC_PATH"));
				
				r.setBlog(blog);

				list.add(r);
				
				colocacao++;

			}

			return list;

		} finally {
			closeAll(rs, ps);
		}
	}
	
	public Object execute(String method, ArrayList parameters) throws Exception {
		if (method.equals("getFavorites")) {
			return getFavorites(((Integer)parameters.get(0)).intValue());
		}
		return super.execute(method, parameters);
	}


}
