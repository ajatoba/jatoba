package com.vanguarda.blog.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.taglibs.string.TruncateNicelyTag;

import com.vanguarda.blog.bean.Blog;
import com.vanguarda.blog.bean.BlogUser;
import com.vanguarda.blog.bean.Comment;
import com.vanguarda.blog.bean.Group;
import com.vanguarda.blog.bean.Post;
import com.vanguarda.blog.bean.Template;
import com.vanguarda.blog.dao.AbstractDAO;
import com.vanguarda.blog.dao.PostDAO;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.LoggerUtil;

public class PostDAOImpl extends AbstractDAO implements PostDAO {

	
	
	
	private static final String SELECT_COMMENT_QUERY = "C.NM_COMMENT_ID,C.NM_POST_ID_FK ,  C.VC_TITLE ,  "
		+ "C.VC_CONTENT,  C.NM_STATUS,  C.VC_COMMENTATOR_NAME ,  C.VC_COMMENTATOR_EMAIL ,  C.VC_COMMENTATOR_HOMEPAGE ,  "
		+ "C.VC_COMMENTATOR_REMOTE_ADDR,C.DT_INSERT_DATE, C.NM_COMMENTATOR_GROUP_ID_FK";
	
	private static final String SELECT_POST_QUERY = "P.NM_POST_ID, P.NM_BLOG_ID_FK, P.VC_TITLE, " +
	"P.VC_CONTENT, P.NM_IS_CONTROLL, P.DT_INSERT_DATE,P.NM_STATUS,P.NM_COUNT_COMMNTS";
	
	private static final String TEMPLATE_SELECT = "T.NM_TEMPLATE_ID , T.VC_NAME , T.VC_DESCRIPTION , T.VC_BLOG_PATH, T.VC_COMMUNITY_PATH , " +
	"T.VC_FORUM_PATH, T.VC_STATUS ";
	
	private static final String SELECT_USER_QUERY = " U.NM_USER_ID,U.VC_FIRSTNAME,U.VC_LASTNAME,U.VC_EMAIL,U.DT_INSERTDATE,U.NM_STATUS,"
			+ "U.VC_LOGIN,U.VC_PASSWORD, U.NM_GROUP_ID_FK ";
	
	private static final String SELECT_USER_QUERY2 = " U2.NM_USER_ID  ";
	
	private static final String SELECT_BLOG_QUERY = " B.NM_BLOG_ID,B.VC_NAME ,  B.VC_PATH,B.VC_DESCRIPTION  " +
	",  B.DT_INSERTDATE ,  B.NM_STATUS";
	
	private static final String SELECT_BLOG_QUERY2 = " B2.NM_BLOG_ID ,   B2.VC_PATH  ";
	
	
	private static final String INSERT_QUERY = "INSERT INTO TB_BLOG_POST ( NM_BLOG_ID_FK, VC_TITLE, " +
			"VC_CONTENT, NM_IS_CONTROLL, DT_INSERT_DATE,NM_STATUS) "
			+ "VALUES(?,?,?,?,NOW(),?) ";
	
	private static final String UPDATE_QUERY = "UPDATE TB_BLOG_POST SET VC_TITLE = ?, " +
	"VC_CONTENT = ?, NM_IS_CONTROLL =  ? , NM_STATUS = ? WHERE NM_POST_ID = ? ";
	
		
	
	
	private static final String LOAD_POST_BY_ID_QUERY = "SELECT "+ SELECT_POST_QUERY +","+SELECT_USER_QUERY+ ","+SELECT_COMMENT_QUERY+","+SELECT_BLOG_QUERY+
	","+ TEMPLATE_SELECT +
	","+ SELECT_USER_QUERY2 +
	","+ SELECT_BLOG_QUERY2 +
	
	" FROM TB_BLOG_POST P " +
	" JOIN TB_BLOG B ON B.NM_BLOG_ID = P.NM_BLOG_ID_FK "+
	" LEFT JOIN TB_BLOG_TEMPLATE T ON NM_TEMPLATE_ID_FK = T.NM_TEMPLATE_ID" +
	" JOIN TB_BLOG_USER U ON U.NM_USER_ID = B.NM_USER_ID_FK "+	
	" LEFT JOIN TB_BLOG_COMMENT AS C ON C.NM_POST_ID_FK = NM_POST_ID " +	
	" AND C.NM_STATUS = ? "+
	
	"LEFT JOIN TB_BLOG_USER U2 ON U2.NM_USER_ID = C.NM_USER_ID_FK "+	
	"LEFT JOIN TB_BLOG B2 ON B2.NM_USER_ID_FK = C.NM_USER_ID_FK "+	
	
	 "WHERE NM_POST_ID = ?" +
	 
	 " ORDER BY C.DT_INSERT_DATE DESC" ;
	
	private static final String LIST_POSTS_QUERY = "SELECT NM_POST_ID, NM_BLOG_ID_FK, VC_TITLE, " +
	"VC_CONTENT, NM_IS_CONTROLL, DT_INSERT_DATE,NM_STATUS,NM_COUNT_COMMNTS,"+SELECT_BLOG_QUERY+" FROM TB_BLOG_POST,TB_BLOG_USER, " +
			"TB_BLOG AS B WHERE NM_STATUS = ? " +
	" AND NM_BLOG_ID_FK = NM_BLOG_ID"+	
	" ORDER BY DT_INSERT_DATE DESC ";

	
	private static final String LIST_POSTS_BY_BLOG_QUERY = "SELECT NM_POST_ID, NM_BLOG_ID_FK, VC_TITLE, " +
	"VC_CONTENT, NM_IS_CONTROLL, DT_INSERT_DATE,NM_STATUS,NM_COUNT_COMMNTS FROM TB_BLOG_POST " +
			" WHERE NM_STATUS = ? AND " +
	"NM_BLOG_ID_FK = ? "+
	
	" ORDER BY DT_INSERT_DATE DESC ";	

	private static final String SEARCH_POSTS_BY_BLOG_QUERY = " SELECT NM_BLOG_ID,B.VC_NAME,B.VC_DESCRIPTION,B.DT_INSERTDATE,B.NM_STATUS,VC_PATH,NM_TEMPLATE_ID_FK,"
		+ SELECT_POST_QUERY +","+SELECT_USER_QUERY+","+ TEMPLATE_SELECT
		+ " FROM TB_BLOG B JOIN TB_BLOG_USER  U ON  U.NM_USER_ID  = NM_USER_ID_FK"+  
		" LEFT JOIN TB_BLOG_TEMPLATE T ON NM_TEMPLATE_ID_FK = T.NM_TEMPLATE_ID" +
		" LEFT JOIN TB_BLOG_POST P  ON NM_BLOG_ID = P.NM_BLOG_ID_FK AND P.NM_STATUS = 1 AND (LOWER(P.VC_CONTENT) LIKE LOWER(?) OR LOWER(P.VC_TITLE) LIKE LOWER(?))"+ 
	" WHERE  NM_BLOG_ID = ?  ORDER BY P.DT_INSERT_DATE DESC";
	
	private static final String DELETE_QUERY = "DELETE FROM TB_BLOG_POST WHERE NM_POST_ID = ? ";

	
	public void add(Post post) throws SQLException,Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		LoggerUtil.debug("ADICIONANDO POST: "+ post);
		
		try {

			getConnection();
			ps = conn.prepareStatement(INSERT_QUERY);			
				
			ps.setInt(1,post.getBlog().getId());
			ps.setString(2,post.getTitle());
			ps.setString(3, post.getContent());			
			ps.setInt(4,post.isControll()?1:0);
			ps.setInt(5,post.getStatus());

			ps.execute();

		} finally {
			closeAll(rs, ps);
		}
	}
		
	
	public Post load(int id, int status) throws SQLException,Exception {
				
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Post post = new Post();	
		BlogUser user = new BlogUser();
		Blog blog = new Blog();
		
		Collection comments = new ArrayList();
		
		LoggerUtil.debug("CARREGANDO POST ID: "+ id);

		try {
			
			getConnection();
			if(status == Constants.STATUS_INACTIVE){
				ps = conn.prepareStatement(LOAD_POST_BY_ID_QUERY.replaceAll("NM_STATUS = ","NM_STATUS >= "));	
			}
			else
			{
				ps = conn.prepareStatement(LOAD_POST_BY_ID_QUERY);	
			}
			
			
					
			ps.setInt(1,status);
			ps.setInt(2,id);	
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				post.setId(rs.getInt("P.NM_POST_ID"));
				post.setTitle(rs.getString("P.VC_TITLE"));
				post.setContent(rs.getString("P.VC_CONTENT"));
				post.setControll(rs.getInt("P.NM_IS_CONTROLL")< 1);
				post.setInsertDate(new java.util.Date(rs.getDate("P.DT_INSERT_DATE").getTime()));
				post.setStatus(rs.getInt("P.NM_STATUS"));
				post.setCountComments(rs.getInt("NM_COUNT_COMMNTS"));
				
				blog.setDescription(rs.getString("B.VC_DESCRIPTION"));
				blog.setId(rs.getInt("B.NM_BLOG_ID"));
				blog.setInsertDate(new java.util.Date(rs.getDate("B.DT_INSERTDATE").getTime()));
				blog.setName(rs.getString("B.VC_NAME"));
				blog.setPath(rs.getString("B.VC_PATH"));
				blog.setStatus(rs.getInt("B.NM_STATUS"));
				
				post.setBlog(blog);
				
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
				user.setBlog(blog);
				
				blog.setBlogUser(user);
				
				post.setBlog(blog);
				
				if(rs.getInt("C.NM_COMMENT_ID") > 0)
				{
					Comment comment = new Comment();
					Group group = new Group(rs.getInt("C.NM_COMMENTATOR_GROUP_ID_FK"));
					
					
					
					Blog blog2 = new Blog();
					blog2.setId(rs.getInt("B2.NM_BLOG_ID"));					
					blog2.setPath(rs.getString("B2.VC_PATH"));
					
					
					BlogUser commentator = new BlogUser();
					
					commentator.setId(rs.getInt("U2.NM_USER_ID"));					
					commentator.setBlog(blog2);
					commentator.setGroup(group);
					commentator.setFirstName(rs.getString("C.VC_COMMENTATOR_NAME"));
					commentator.setEmail(rs.getString("C.VC_COMMENTATOR_EMAIL"));
					
					
					comment.setId(rs.getInt("C.NM_COMMENT_ID"));
					comment.setTitle(rs.getString("C.VC_TITLE"));
					comment.setContent(rs.getString("C.VC_CONTENT"));
					comment.setStatus(rs.getInt("C.NM_STATUS"));
					comment.setCommentatorName(rs.getString("C.VC_COMMENTATOR_NAME"));
					comment.setCommentatorEmail(rs
							.getString("C.VC_COMMENTATOR_EMAIL"));
					comment.setCommentatorHomepage(rs
							.getString("C.VC_COMMENTATOR_HOMEPAGE"));
					comment.setCommentatorRemoteAddr(rs
							.getString("C.VC_COMMENTATOR_REMOTE_ADDR"));
					comment.setInsertDate(rs.getDate("C.DT_INSERT_DATE")!= null?new java.util.Date(rs.getDate("C.DT_INSERT_DATE").getTime()):null);
					comment.setUser(commentator);
					comment.setPost(post);
					
					
					
					
					
					comments.add(comment);
				}
				
				
			}
			
			post.setComments(comments);
			
			return post;
			

		} finally {
			closeAll(rs, ps);
		}
	}
	
	
	public void update(Post post) throws SQLException,Exception {
		
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			getConnection();

			ps = conn.prepareStatement(UPDATE_QUERY);			
				
			ps.setString(1,post.getTitle());
			ps.setString(2, post.getContent());			
			ps.setInt(3,post.isControll()?1:0);
			ps.setInt(4,post.getStatus());
			ps.setInt(5,post.getId());

			ps.executeUpdate();

		} finally {
			closeAll(rs, ps);
		}
	}
	
	public void delete(Post post) throws SQLException,Exception {
		
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();
			ps = conn.prepareStatement(DELETE_QUERY);			
							
			ps.setInt(1,post.getId());

			ps.executeUpdate();

		} finally {
			closeAll(rs, ps);
		}
	}
	
	
	public Collection listPosts(int status) throws SQLException,Exception {
		
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection list =  new ArrayList();
		
		Post post = null;
		
		

		try {
			
			getConnection();

			if(status == Constants.STATUS_INACTIVE){
				ps = conn.prepareStatement(LIST_POSTS_QUERY.replaceAll("NM_STATUS = ","NM_STATUS >= "));	
			}
			else
			{
				ps = conn.prepareStatement(LIST_POSTS_QUERY);	
			}
			
			
			ps = conn.prepareStatement(LIST_POSTS_QUERY);			
			
			ps.setInt(1, status);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{				
				post = new Post();		
								
				post.setId(rs.getInt("NM_POST_ID"));
				
				post.setTitle(rs.getString("VC_TITLE"));
				post.setContent(rs.getString("VC_CONTENT"));
				post.setControll(rs.getInt("NM_IS_CONTROLL") < 1);
				post.setInsertDate(new java.util.Date(rs.getDate("DT_INSERT_DATE").getTime()));
				post.setStatus(rs.getInt("NM_STATUS"));
				post.setCountComments(rs.getInt("NM_COUNT_COMMNTS"));
				
				list.add(post);
				
			}
			
			return list;
			

		} finally {
			closeAll(rs, ps);
		}	
		
	}

	public Blog searchPosts(String keyWord, int id) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		Blog blog = new Blog();
		BlogUser user = new BlogUser();
		
		Collection posts = new ArrayList();

		try {
			
			getConnection();
			
			ps = conn.prepareStatement(SEARCH_POSTS_BY_BLOG_QUERY);

			ps.setString(1, "%"+keyWord + "%");
			ps.setString(2, "%"+keyWord + "%");
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
				blog.setBlogUser(user);

				TruncateNicelyTag trun = new TruncateNicelyTag();
				trun.setUpper("300");
				trun.setLower("10");
				
				if(rs.getInt("P.NM_POST_ID") > 0)
				{
					Post post = new Post();	
					post.setId(rs.getInt("P.NM_POST_ID"));
					post.setTitle(rs.getString("P.VC_TITLE"));
					//post.setContent(rs.getString("P.VC_CONTENT").replaceAll("<[^>]*>",""));
					post.setContent(trun.changeString(rs.getString("P.VC_CONTENT")));
					post.setControll(rs.getInt("P.NM_IS_CONTROLL")>0);
					post.setCountComments(rs.getInt("P.NM_COUNT_COMMNTS"));
					post.setInsertDate(rs.getDate("P.DT_INSERT_DATE")!= null?new java.util.Date(rs.getDate("P.DT_INSERT_DATE").getTime()):null);	
					post.setBlog(blog);
					post.setStatus(rs.getInt("P.NM_STATUS"));
					posts.add(post);
				}

			}
			blog.setPosts(posts);

			return blog;

		} finally {
			closeAll(rs, ps);
		}
	}

	
	public Collection listPostsByBlog(int status, Blog blog) throws SQLException,Exception {
				
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection list =  new ArrayList();
		
		Post post = null;

		try {
			
			getConnection();

			if(status == Constants.STATUS_ALL){
				ps = conn.prepareStatement(LIST_POSTS_BY_BLOG_QUERY.replaceAll("NM_STATUS = ","NM_STATUS >= "));	
			}
			else
			{
				ps = conn.prepareStatement(LIST_POSTS_BY_BLOG_QUERY);	
			}	
			
			ps.setInt(1, status);
			ps.setInt(2, blog.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{				
				post = new Post();		
								
				post.setId(rs.getInt("NM_POST_ID"));
				
								
				post.setTitle(rs.getString("VC_TITLE"));
				post.setContent(rs.getString("VC_CONTENT"));
				post.setControll(rs.getInt("NM_IS_CONTROLL")> 0);
				post.setInsertDate(new java.util.Date(rs.getDate("DT_INSERT_DATE").getTime()));
				post.setStatus(rs.getInt("NM_STATUS"));
				post.setCountComments(rs.getInt("NM_COUNT_COMMNTS"));
				
				list.add(post);
				
			}
			
			return list;
			

		} finally {
			closeAll(rs, ps);
		}
	
	}
	
	public Object execute(String method, ArrayList parameters) throws Exception {
		if (method.equals("load")) {
			return load(((Integer)parameters.get(0)).intValue(),((Integer)parameters.get(1)).intValue());
		}
		return super.execute(method, parameters);
	}
	
}
