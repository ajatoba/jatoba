package com.vanguarda.blog.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.Post;
import com.vanguarda.blog.bean.Template;
import com.vanguarda.blog.dao.AbstractDAO;
import com.vanguarda.blog.dao.BlogDAO;
import com.vanguarda.blog.exception.BlogPathExistsException;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.LoggerUtil;

public class BlogDAOImpl extends AbstractDAO implements BlogDAO {
	
	
	private static final String SELECT_USER_VALUES = " U.NM_USER_ID,U.VC_FIRSTNAME, U.VC_LASTNAME,U.VC_EMAIL,U.DT_INSERTDATE,U.NM_STATUS,"
		+ "U.VC_LOGIN,U.VC_PASSWORD, U.NM_GROUP_ID_FK, U.VC_GENDER ";   
	
	private static final String POST_SELECT = "P.NM_POST_ID, P.NM_BLOG_ID_FK, P.VC_TITLE, " +
	"P.VC_CONTENT, P.NM_IS_CONTROLL, P.DT_INSERT_DATE,P.NM_STATUS,P.NM_COUNT_COMMNTS,VC_AUTHOR";
	
	private static final String TEMPLATE_SELECT = "T.NM_TEMPLATE_ID , T.VC_NAME , T.VC_DESCRIPTION , T.VC_BLOG_PATH, T.VC_COMMUNITY_PATH , " +
			"T.VC_FORUM_PATH, T.VC_STATUS ";
	
	private static final String INSERT_QUERY = "INSERT INTO TB_BLOG (VC_NAME, VC_DESCRIPTION, DT_INSERTDATE, NM_STATUS,NM_USER_ID_FK,VC_PATH,NM_TEMPLATE_ID_FK) "
			+ "VALUES(?,?,NOW(),?,?,?,?) ";

	private static final String LOAD_BLOG_BY_ID_QUERY = " SELECT NM_BLOG_ID,B.VC_NAME,B.VC_DESCRIPTION,B.DT_INSERTDATE,B.NM_STATUS,VC_PATH,NM_TEMPLATE_ID_FK,"
			+ POST_SELECT +","+SELECT_USER_VALUES +","+ TEMPLATE_SELECT
			+ " FROM TB_BLOG B JOIN TB_BLOG_USER  U ON  U.NM_USER_ID  = NM_USER_ID_FK"+ 
			" LEFT JOIN TB_BLOG_TEMPLATE T ON NM_TEMPLATE_ID_FK = T.NM_TEMPLATE_ID" +
			" LEFT JOIN TB_BLOG_POST P  ON NM_BLOG_ID = P.NM_BLOG_ID_FK"+ 
		" WHERE  NM_BLOG_ID = ?  ORDER BY P.DT_INSERT_DATE DESC";
	
	private static final String LOAD_BLOG_BY_DATE_QUERY = " SELECT NM_BLOG_ID,B.VC_NAME,B.VC_DESCRIPTION,B.DT_INSERTDATE,B.NM_STATUS,VC_PATH,NM_TEMPLATE_ID_FK,"
		+ POST_SELECT +","+SELECT_USER_VALUES+","+ TEMPLATE_SELECT
		+ " FROM TB_BLOG B JOIN TB_BLOG_USER  U ON  U.NM_USER_ID  = NM_USER_ID_FK"+  
		" LEFT JOIN TB_BLOG_TEMPLATE T ON NM_TEMPLATE_ID_FK = T.NM_TEMPLATE_ID" +
		" LEFT JOIN TB_BLOG_POST P  ON NM_BLOG_ID = P.NM_BLOG_ID_FK AND P.DT_INSERT_DATE >= ? AND P.DT_INSERT_DATE <= ? AND P.NM_STATUS = 1 "+ 
	" WHERE  NM_BLOG_ID = ?  ORDER BY P.DT_INSERT_DATE DESC";

	private static final String LOAD_LAST_POSTS_QUERY = " SELECT NM_BLOG_ID,B.VC_NAME,B.VC_DESCRIPTION,B.DT_INSERTDATE,B.NM_STATUS,VC_PATH,NM_TEMPLATE_ID_FK,"
		+ POST_SELECT +","+SELECT_USER_VALUES+","+ TEMPLATE_SELECT
		+ " FROM TB_BLOG B JOIN TB_BLOG_USER  U ON  U.NM_USER_ID  = NM_USER_ID_FK"+  
		" LEFT JOIN TB_BLOG_TEMPLATE T ON NM_TEMPLATE_ID_FK = T.NM_TEMPLATE_ID" +
		" LEFT JOIN TB_BLOG_POST P  ON NM_BLOG_ID = P.NM_BLOG_ID_FK "+ 
	" WHERE  NM_BLOG_ID = ?  AND P.NM_STATUS = 1 ORDER BY P.DT_INSERT_DATE DESC LIMIT 0 , ?";
	
	private static final String UPDATE_QUERY = "UPDATE TB_BLOG SET VC_NAME =?, VC_DESCRIPTION = ?,  NM_STATUS = ?, NM_TEMPLATE_ID_FK = ? WHERE NM_BLOG_ID = ?";

	private static final String DELETE_QUERY = "DELETE FROM TB_BLOG WHERE NM_BLOG_ID = ? ";

	private static final String LIST_BLOGS_QUERY = " SELECT NM_BLOG_ID,B.VC_NAME,B.VC_DESCRIPTION,B.DT_INSERTDATE,B.NM_STATUS,B.VC_PATH ,"
		+SELECT_USER_VALUES
			+ " FROM TB_BLOG B JOIN TB_BLOG_USER U ON  U.NM_USER_ID  = NM_USER_ID_FK  WHERE  B.NM_STATUS = ? "+
			" ORDER BY B.DT_INSERTDATE DESC ";
	
	private static final String LIST_BLOGS_QUERY_ORDER_BY_NAME = " SELECT NM_BLOG_ID,B.VC_NAME,B.VC_DESCRIPTION,B.DT_INSERTDATE,B.NM_STATUS"
		
		+ " FROM TB_BLOG B WHERE  B.NM_STATUS = ? "+
		" ORDER BY B.VC_NAME ";
	
	private static final String LIST_HISTORY_QUERY = "SELECT    MONTH(DT_INSERT_DATE)AS MES,YEAR(DT_INSERT_DATE) AS ANO, COUNT(*) AS QTD" +
			" FROM TB_BLOG_POST " +
			"WHERE NM_BLOG_ID_FK = ? AND NM_STATUS = 1  GROUP BY MONTH(DT_INSERT_DATE),YEAR(DT_INSERT_DATE) ";
	

	public int add(Blog blog) throws SQLException, BlogPathExistsException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		LoggerUtil.debug("ADICIONANDO BLOG: "+ blog);

		try {
			getConnection();
			ps = conn.prepareStatement(INSERT_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);
			
			LoggerUtil.debug(INSERT_QUERY);

			ps.setString(1, blog.getName());
			ps.setString(2, blog.getDescription());
			ps.setInt(3, blog.getStatus());
			ps.setInt(4, blog.getBloggerUser().getId());
			ps.setString(5,blog.getPath());						
			ps.setInt(6,blog.getTemplate().getId());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
			{
				return rs.getInt(1);
			}
			
			return 0;
			
		}catch(SQLException sqle){
			
			if(sqle.getMessage().indexOf(blog.getPath()) > 0)
			{
				throw new BlogPathExistsException();
			}
			
			else
				throw sqle;

		} finally {
			closeAll(rs, ps);
		}
	}

	public Blog load(int id) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		Blog blog = new Blog();
		BlogUser user = new BlogUser();
		
		Collection posts = new ArrayList();

		LoggerUtil.debug("CARREGANDO BLOG ID : "+ id);
		
		try {
			
			getConnection();
			ps = conn.prepareStatement(LOAD_BLOG_BY_ID_QUERY);

			LoggerUtil.debug(LOAD_BLOG_BY_ID_QUERY);
			
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

							
				blog.setDescription(rs.getString("B.VC_DESCRIPTION"));
				blog.setId(rs.getInt("NM_BLOG_ID"));
				blog.setInsertDate(new java.util.Date(rs.getDate("B.DT_INSERTDATE").getTime()));
				blog.setName(rs.getString("B.VC_NAME"));
				blog.setStatus(rs.getInt("B.NM_STATUS"));
				blog.setPath(rs.getString("VC_PATH"));
				
				Template t = new Template();
				t.setId(rs.getInt("T.NM_TEMPLATE_ID"));
				blog.setTemplate(t);
				
				
				user.setId(rs.getInt("U.NM_USER_ID"));
				user.setFirstName(rs.getString("U.VC_FIRSTNAME"));
				user.setLastName(rs.getString("U.VC_LASTNAME"));
				user.setLogin(rs.getString("U.VC_LOGIN"));
				user.setPassword(rs.getString("U.VC_PASSWORD"));
				user.setEmail(rs.getString("U.VC_EMAIL"));
				user.setStatus(rs.getInt("U.NM_STATUS"));
				user.setGender(rs.getString("U.VC_GENDER"));
				blog.setBlogUser(user);

				
				Post post = new Post();	
				post.setId(rs.getInt("P.NM_POST_ID"));
				post.setTitle(rs.getString("P.VC_TITLE"));
				post.setContent(rs.getString("P.VC_CONTENT"));
				post.setControll(rs.getInt("P.NM_IS_CONTROLL")>0);
				post.setCountComments(rs.getInt("P.NM_COUNT_COMMNTS"));
				post.setInsertDate(rs.getDate("P.DT_INSERT_DATE")!= null?new java.util.Date(rs.getDate("P.DT_INSERT_DATE").getTime()):null);	
				post.setBlog(blog);
				post.setStatus(rs.getInt("P.NM_STATUS"));
				post.setAuthor(rs.getString("P.VC_AUTHOR"));
				posts.add(post);

			}
			blog.setPosts(posts);

			return blog;

		} finally {
			closeAll(rs, ps);
		}
	}
	
	
	public Blog loadByDate(int id, Date startDate, Date endDate) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		Blog blog = new Blog();
		BlogUser user = new BlogUser();
		
		Collection posts = new ArrayList();

		LoggerUtil.debug("CARREGANDO BLOG ID : "+ id);
		
		try {
			
			getConnection();
			
			System.out.println(LOAD_BLOG_BY_DATE_QUERY);
			
			ps = conn.prepareStatement(LOAD_BLOG_BY_DATE_QUERY);

			
			ps.setDate(1,new java.sql.Date(startDate.getTime()));
			ps.setDate(2,new java.sql.Date(endDate.getTime()));
			ps.setInt(3, id);

			rs = ps.executeQuery();

			while (rs.next()) {

							
				blog.setDescription(rs.getString("B.VC_DESCRIPTION"));
				blog.setId(rs.getInt("NM_BLOG_ID"));
				blog.setInsertDate(new java.util.Date(rs.getDate("B.DT_INSERTDATE").getTime()));
				blog.setName(rs.getString("B.VC_NAME"));
				blog.setStatus(rs.getInt("B.NM_STATUS"));
				blog.setPath(rs.getString("VC_PATH"));
				
				
				Template t = new Template();
				t.setId(rs.getInt("T.NM_TEMPLATE_ID"));
				t.setBlogPath(rs.getString("T.VC_BLOG_PATH"));
				t.setCommunityPath(rs.getString("T.VC_COMMUNITY_PATH"));
				t.setDescription(rs.getString("T.VC_DESCRIPTION"));
				t.setForumPath(rs.getString("T.VC_FORUM_PATH"));
				t.setName(rs.getString("T.VC_NAME"));
				t.setStatus(rs.getInt("T.VC_STATUS"));
				
				blog.setTemplate(t);
					
				
				user.setId(rs.getInt("U.NM_USER_ID"));
				user.setFirstName(rs.getString("U.VC_FIRSTNAME"));
				user.setLastName(rs.getString("U.VC_LASTNAME"));
				user.setLogin(rs.getString("U.VC_LOGIN"));
				user.setPassword(rs.getString("U.VC_PASSWORD"));
				user.setEmail(rs.getString("U.VC_EMAIL"));
				user.setStatus(rs.getInt("U.NM_STATUS"));
				user.setGender(rs.getString("U.VC_GENDER"));
				blog.setBlogUser(user);

				if(rs.getInt("P.NM_POST_ID") > 0)
				{
					Post post = new Post();	
					post.setId(rs.getInt("P.NM_POST_ID"));
					post.setTitle(rs.getString("P.VC_TITLE"));
					post.setContent(rs.getString("P.VC_CONTENT"));
					post.setControll(rs.getInt("P.NM_IS_CONTROLL")>0);
					post.setCountComments(rs.getInt("P.NM_COUNT_COMMNTS"));
					post.setInsertDate(rs.getDate("P.DT_INSERT_DATE")!= null?new java.util.Date(rs.getDate("P.DT_INSERT_DATE").getTime()):null);	
					//post.setInsertDate(new Date(rs.getDate("P.DT_INSERT_DATE").getTime()));
					post.setBlog(blog);
					post.setStatus(rs.getInt("P.NM_STATUS"));
					post.setAuthor(rs.getString("P.VC_AUTHOR"));
					posts.add(post);
				}

			}
			blog.setPosts(posts);

			return blog;

		} finally {
			closeAll(rs, ps);
		}
	}

	public void update(Blog blog) throws SQLException, BlogPathExistsException,Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			getConnection();
			ps = conn.prepareStatement(UPDATE_QUERY);

			ps.setString(1, blog.getName());
			ps.setString(2, blog.getDescription());
			ps.setInt(3, blog.getStatus());
			
			ps.setInt(4, blog.getTemplate().getId());
			ps.setInt(5, blog.getId());

			ps.executeUpdate();
			
		}catch(SQLException sqle){
			
			if(sqle.getMessage().indexOf("PATH") > 0)
			{
				throw new BlogPathExistsException();
			}
			
			else
				throw sqle;

		} finally {
			closeAll(rs, ps);
		}
	}

	public void delete(Blog blog) throws SQLException,Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();
			ps = conn.prepareStatement(DELETE_QUERY);

			ps.setInt(1, blog.getId());

			ps.executeUpdate();

		} finally {
			closeAll(rs, ps);
		}
	}

	public Collection listBlogs(int status) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection list = new ArrayList();

		Blog blog = null;
		
		
		LoggerUtil.debug("LISTANDO BLOGS");

		try {
			
			getConnection();

			if (status == Constants.STATUS_ALL) {
				ps = conn.prepareStatement(LIST_BLOGS_QUERY.replaceAll(
						"B.NM_STATUS = ", "B.NM_STATUS >= "));
			} else {
				ps = conn.prepareStatement(LIST_BLOGS_QUERY);
			}

			
			ps.setInt(1, status);

			rs = ps.executeQuery();

			while (rs.next()) {
				blog = new Blog();

				blog.setDescription(rs.getString("B.VC_DESCRIPTION"));
				blog.setId(rs.getInt("NM_BLOG_ID"));
				blog.setInsertDate(new java.util.Date(rs.getDate("B.DT_INSERTDATE").getTime()));
				blog.setName(rs.getString("B.VC_NAME"));
				blog.setStatus(rs.getInt("B.NM_STATUS"));
				blog.setPath(rs.getString("B.VC_PATH"));
				
				
				BlogUser user = new BlogUser();
				user.setId(rs.getInt("U.NM_USER_ID"));
				user.setFirstName(rs.getString("U.VC_FIRSTNAME"));
				user.setLastName(rs.getString("U.VC_LASTNAME"));
				user.setLogin(rs.getString("U.VC_LOGIN"));
				user.setPassword(rs.getString("U.VC_PASSWORD"));
				user.setEmail(rs.getString("U.VC_EMAIL"));
				user.setStatus(rs.getInt("U.NM_STATUS"));
				blog.setBlogUser(user);

				list.add(blog);

			}

			return list;

		} finally {
			closeAll(rs, ps);
		}
	}
	
	public Collection listBlogsToFavorite(int status) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection list = new ArrayList();

		Blog blog = null;
		
		LoggerUtil.debug("LISTANDO BLOGS");

		try {
			
			getConnection();

			if (status == Constants.STATUS_ALL) {
				ps = conn.prepareStatement(LIST_BLOGS_QUERY_ORDER_BY_NAME.replaceAll(
						"B.NM_STATUS = ", "B.NM_STATUS >= "));
			} else {
				ps = conn.prepareStatement(LIST_BLOGS_QUERY_ORDER_BY_NAME);
			}

			
			ps.setInt(1, status);

			rs = ps.executeQuery();

			while (rs.next()) {
				blog = new Blog();

				blog.setDescription(rs.getString("B.VC_DESCRIPTION"));
				blog.setId(rs.getInt("NM_BLOG_ID"));
				blog.setInsertDate(new java.util.Date(rs.getDate("B.DT_INSERTDATE").getTime()));
				blog.setName(rs.getString("B.VC_NAME"));
				blog.setStatus(rs.getInt("B.NM_STATUS"));

				list.add(blog);

			}

			return list;

		} finally {
			closeAll(rs, ps);
		}
	}
		
		
		public Map getHistory(int blogId) throws SQLException ,Exception{
			
			PreparedStatement ps = null;
			ResultSet rs = null;

			Hashtable list = new Hashtable();

			try {				
			
				getConnection();
				ps = conn.prepareStatement(LIST_HISTORY_QUERY);				

				ps.setInt(1,blogId);
				rs = ps.executeQuery();

				while (rs.next()) {					
					list.put(rs.getString("MES")+rs.getString("ANO"),new Integer(rs.getInt("QTD")));			

				}

				return list;

			} finally {
				closeAll(rs, ps);
			}
		}
		
		public Blog loadLastPosts(int id, int qtdPosts) throws SQLException,Exception {
			
			PreparedStatement ps = null;
			ResultSet rs = null;

			Blog blog = new Blog();
			BlogUser user = new BlogUser();
			
			Collection posts = new ArrayList();

			try {

				getConnection();
				ps = conn.prepareStatement(LOAD_LAST_POSTS_QUERY);

				ps.setInt(1, id);
				ps.setInt(2,qtdPosts);

				rs = ps.executeQuery();

				while (rs.next()) {

								
					blog.setDescription(rs.getString("B.VC_DESCRIPTION"));
					blog.setId(rs.getInt("NM_BLOG_ID"));
					blog.setInsertDate(new java.util.Date(rs.getDate("B.DT_INSERTDATE").getTime()));
					blog.setName(rs.getString("B.VC_NAME"));
					blog.setStatus(rs.getInt("B.NM_STATUS"));
					blog.setPath(rs.getString("VC_PATH"));
					
					
					Template t = new Template();
					t.setId(rs.getInt("T.NM_TEMPLATE_ID"));
					t.setBlogPath(rs.getString("T.VC_BLOG_PATH"));
					t.setCommunityPath(rs.getString("T.VC_COMMUNITY_PATH"));
					t.setDescription(rs.getString("T.VC_DESCRIPTION"));
					t.setForumPath(rs.getString("T.VC_FORUM_PATH"));
					t.setName(rs.getString("T.VC_NAME"));
					t.setStatus(rs.getInt("T.VC_STATUS"));
					
					blog.setTemplate(t);
						
					
					user.setId(rs.getInt("U.NM_USER_ID"));
					user.setFirstName(rs.getString("U.VC_FIRSTNAME"));
					user.setLastName(rs.getString("U.VC_LASTNAME"));
					user.setLogin(rs.getString("U.VC_LOGIN"));
					user.setPassword(rs.getString("U.VC_PASSWORD"));
					user.setEmail(rs.getString("U.VC_EMAIL"));
					user.setStatus(rs.getInt("U.NM_STATUS"));
					user.setGender(rs.getString("U.VC_GENDER"));
					blog.setBlogUser(user);

					if(rs.getInt("P.NM_POST_ID") > 0)
					{
						Post post = new Post();	
						post.setId(rs.getInt("P.NM_POST_ID"));
						post.setTitle(rs.getString("P.VC_TITLE"));
						post.setContent(rs.getString("P.VC_CONTENT"));
						post.setControll(rs.getInt("P.NM_IS_CONTROLL")>0);
						post.setCountComments(rs.getInt("P.NM_COUNT_COMMNTS"));
						post.setInsertDate(rs.getDate("P.DT_INSERT_DATE")!= null?new java.util.Date(rs.getDate("P.DT_INSERT_DATE").getTime()):null);	
						post.setBlog(blog);
						post.setStatus(rs.getInt("P.NM_STATUS"));
						post.setAuthor(rs.getString("P.VC_AUTHOR"));
						posts.add(post);
					}

				}
				blog.setPosts(posts);

				return blog;

			} finally {
				closeAll(rs, ps);
			}
		}

		public Object execute(String method, ArrayList parameters) throws Exception {
			if (method.equals("loadByDate")) {
				return loadByDate(((Integer)parameters.get(0)).intValue(),(Date)parameters.get(1),(Date)parameters.get(2));
			}
			else if(method.equals("getHistory"))
			{
				return getHistory(((Integer)parameters.get(0)).intValue());
			}else if(method.equals("loadLastPosts"))
			{
				return loadLastPosts(((Integer)parameters.get(0)).intValue(),((Integer)parameters.get(1)).intValue());
			}
			else if(method.equals("listBlogs"))
			{
				return listBlogs(Constants.STATUS_ACTIVE);
			}
				
			return super.execute(method, parameters);
		}
}
