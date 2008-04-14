package com.vanguarda.blog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.vanguarda.blog.bean.Comment;
import com.vanguarda.blog.bean.Post;
import com.vanguarda.blog.dao.AbstractDAO;
import com.vanguarda.blog.dao.CommentsDAO;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.LoggerUtil;

public class CommentsDAOImpl extends AbstractDAO implements CommentsDAO {

	private static final int ADD_COMMENTS = 1;

	private static final int REMOVE_COMMENTS = 0;

	//NM_COMMENT_ID , NM_POST_ID_FK , VC_TITLE , VC_CONTENT, NM_STATUS,
	// VC_COMMENTATOR_NAME , VC_COMMENTATOR_EMAIL , VC_COMMENTATOR_HOMEPAGE ,
	// VC_COMMENTATOR_REMOTE_ADDR

	private static final String INSERT_QUERY = "INSERT INTO TB_BLOG_COMMENT ( NM_POST_ID_FK ,  VC_TITLE ,  "
			+ "VC_CONTENT,  NM_STATUS,  VC_COMMENTATOR_NAME ,  VC_COMMENTATOR_EMAIL ,  VC_COMMENTATOR_HOMEPAGE ,  "
			+ "VC_COMMENTATOR_REMOTE_ADDR,DT_INSERT_DATE, NM_COMMENTATOR_GROUP_ID_FK,NM_USER_ID_FK ) "
			+ "VALUES(?,?,?,?,?,?,?,?,NOW(),?,?) ";

	private static final String UPDATE_QUERY = "UPDATE TB_BLOG_COMMENT SET VC_TITLE = ? ,  "
			+ "VC_CONTENT  = ?,  NM_STATUS = ?,  VC_COMMENTATOR_NAME =? ,  VC_COMMENTATOR_EMAIL = ? ,  VC_COMMENTATOR_HOMEPAGE =? ,  "
			+ "VC_COMMENTATOR_REMOTE_ADDR =? , VC_ANSWER = ? WHERE NM_COMMENT_ID = ?";

	private static final String LOAD_QUERY = "SELECT NM_COMMENT_ID,NM_POST_ID_FK ,  C.VC_TITLE ,  "
			+ "C.VC_CONTENT,  C.NM_STATUS,  VC_COMMENTATOR_NAME ,  VC_COMMENTATOR_EMAIL ,  VC_COMMENTATOR_HOMEPAGE ,  "
			+ "VC_COMMENTATOR_REMOTE_ADDR,C.DT_INSERT_DATE,P.VC_TITLE,C.VC_ANSWER, P.NM_COUNT_COMMNTS FROM TB_BLOG_COMMENT C, TB_BLOG_POST P"
			+ " WHERE  NM_POST_ID_FK  = NM_POST_ID AND NM_COMMENT_ID = ?";

	private static final String DELETE_QUERY = "DELETE FROM TB_BLOG_COMMENT WHERE NM_COMMENT_ID = ?";

	private static final String LIST_BY_POST_QUERY = "SELECT NM_COMMENT_ID,NM_POST_ID_FK ,  C.VC_TITLE ,  "
			+ "C.VC_CONTENT,  C.NM_STATUS,  VC_COMMENTATOR_NAME ,  VC_COMMENTATOR_EMAIL ,  VC_COMMENTATOR_HOMEPAGE ,  "
			+ "VC_COMMENTATOR_REMOTE_ADDR,C.DT_INSERT_DATE,P.VC_TITLE, P.NM_COUNT_COMMNTS FROM TB_BLOG_COMMENT C, TB_BLOG_POST P"
			+ " WHERE  NM_POST_ID_FK  = NM_POST_ID AND C.NM_STATUS = ? AND NM_POST_ID_FK = ? ORDER BY C.DT_INSERT_DATE ASC";

	private static final String UPDATE_COUNT_COMMENTS_QUERY = "UPDATE TB_BLOG_POST SET NM_COUNT_COMMNTS = ? WHERE NM_POST_ID = ?";

	public void add(Comment comment) throws SQLException,Exception {
		

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		LoggerUtil.debug("ADICIONANDO COMENTARIO: "+ comment);

		try {
			
			getConnection();
			//conn.setAutoCommit(false);

			ps = conn.prepareStatement(INSERT_QUERY);

			ps.setInt(1, comment.getPost().getId());
			ps.setString(2, comment.getTitle());
			ps.setString(3, comment.getContent());
			ps.setInt(4, comment.getStatus());
			ps.setString(5, comment.getCommentatorName());
			ps.setString(6, comment.getCommentatorEmail());
			ps.setString(7, comment.getCommentatorHomepage());
			ps.setString(8, comment.getCommentatorRemoteAddr());
			ps.setInt(9, comment.getUser().getGroup().getId());
			ps.setInt(10, comment.getUser().getId());

			ps.executeUpdate();

			/*if (comment.getStatus() == Constants.STATUS_ACTIVE) {
				updateCountComments(comment.getPost().getId(), comment
						.getPost().getCountComments(), conn, ADD_COMMENTS);
			}*/

			//conn.commit();

		} catch (Exception e) {

			//conn.rollback();

			throw e;

		} finally {
			//conn.setAutoCommit(true);
			closeAll(rs, ps);
		}
	}

	public void update(Comment comment) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		

		try {

			getConnection();
			//conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(UPDATE_QUERY);

			ps.setString(1, comment.getTitle());
			ps.setString(2, comment.getContent());
			ps.setInt(3, comment.getStatus());
			ps.setString(4, comment.getCommentatorName());
			ps.setString(5, comment.getCommentatorEmail());
			ps.setString(6, comment.getCommentatorHomepage());
			ps.setString(7, comment.getCommentatorRemoteAddr());
			ps.setString(8, comment.getAnswer());
			ps.setInt(9, comment.getId());

			ps.executeUpdate();

			/*if (comment.getStatus() == Constants.STATUS_ACTIVE) {
				updateCountComments(comment.getPost().getId(), comment
						.getPost().getCountComments(), conn, ADD_COMMENTS);
			} else {
				updateCountComments(comment.getPost().getId(), comment
						.getPost().getCountComments(), conn, REMOVE_COMMENTS);
			}*/

			//conn.commit();

		} catch (Exception e) {

			//conn.rollback();

			throw e;

		} finally {
			//conn.setAutoCommit(true);
			closeAll(rs, ps);
		}
	}

	public void updateCountComments(int postId, int countAtual,
			Connection conn, int action) throws SQLException,Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		ps = conn.prepareStatement(UPDATE_COUNT_COMMENTS_QUERY);

		int qtd;

		if (action == ADD_COMMENTS) {
			qtd = countAtual + 1;
		} else {
			qtd = countAtual - 1;
		}

		ps.setInt(1, qtd);
		ps.setInt(2, postId);

		ps.executeUpdate();

	}

	public Comment load(int id) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		Comment comment = null;

		try {
			
			getConnection();

			ps = conn.prepareStatement(LOAD_QUERY);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				comment = new Comment();

				comment.setId(rs.getInt("NM_COMMENT_ID"));
				comment.setTitle(rs.getString("C.VC_TITLE"));
				comment.setContent(rs.getString("VC_CONTENT"));
				comment.setStatus(rs.getInt("C.NM_STATUS"));
				comment.setCommentatorName(rs.getString("VC_COMMENTATOR_NAME"));
				comment.setCommentatorEmail(rs
						.getString("VC_COMMENTATOR_EMAIL"));
				comment.setCommentatorHomepage(rs
						.getString("VC_COMMENTATOR_HOMEPAGE"));
				comment.setCommentatorRemoteAddr(rs
						.getString("VC_COMMENTATOR_REMOTE_ADDR"));
				comment.setInsertDate(new java.util.Date(rs.getDate(
						"DT_INSERT_DATE").getTime()));
				comment.setAnswer(rs.getString("C.VC_ANSWER"));

				Post post = new Post();
				post.setId(rs.getInt("NM_POST_ID_FK"));
				post.setCountComments(rs.getInt("P.NM_COUNT_COMMNTS"));
				post.setTitle(rs.getString("P.VC_TITLE"));
				comment.setPost(post);

			}

			return comment;

		} finally {
			closeAll(rs, ps);
		}
	}

	public Comment load(int id, Connection conn) throws SQLException,Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Comment comment = null;

		ps = conn.prepareStatement(LOAD_QUERY);

		ps.setInt(1, id);

		rs = ps.executeQuery();

		if (rs.next()) {
			comment = new Comment();

			comment.setId(rs.getInt("NM_COMMENT_ID"));
			comment.setTitle(rs.getString("C.VC_TITLE"));
			comment.setContent(rs.getString("VC_CONTENT"));
			comment.setStatus(rs.getInt("C.NM_STATUS"));
			comment.setCommentatorName(rs.getString("VC_COMMENTATOR_NAME"));
			comment.setCommentatorEmail(rs.getString("VC_COMMENTATOR_EMAIL"));
			comment.setCommentatorHomepage(rs
					.getString("VC_COMMENTATOR_HOMEPAGE"));
			comment.setCommentatorRemoteAddr(rs
					.getString("VC_COMMENTATOR_REMOTE_ADDR"));
			comment.setInsertDate(new java.util.Date(rs.getDate(
					"DT_INSERT_DATE").getTime()));

			Post post = new Post();
			post.setId(rs.getInt("NM_POST_ID_FK"));
			post.setCountComments(rs.getInt("P.NM_COUNT_COMMNTS"));
			post.setTitle(rs.getString("P.VC_TITLE"));
			comment.setPost(post);

		}

		return comment;

	}

	public void delete(Comment comment) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		

		try {

			getConnection();
			conn.setAutoCommit(false);
			
			comment = load(comment.getId(), conn);

			ps = conn.prepareStatement(DELETE_QUERY);

			ps.setInt(1, comment.getId());

			ps.executeUpdate();

			updateCountComments(comment.getPost().getId(), comment.getPost()
					.getCountComments(), conn, REMOVE_COMMENTS);

			conn.commit();
		} catch (Exception e) {
			conn.rollback();

		} finally {
			conn.setAutoCommit(true);
			closeAll(rs, ps);
		}
	}

	public Collection listCommentsByPost(int status, Post post)
			throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection list = new ArrayList();

		Comment comment = null;

		try {
			
			getConnection();

			if (status == Constants.STATUS_ALL) {
				ps = conn.prepareStatement(LIST_BY_POST_QUERY.replaceAll(
						"NM_STATUS = ", "NM_STATUS >= "));
			} else {
				ps = conn.prepareStatement(LIST_BY_POST_QUERY);
			}

			ps.setInt(1, status);
			ps.setInt(2, post.getId());

			rs = ps.executeQuery();

			while (rs.next()) {
				comment = new Comment();

				comment.setId(rs.getInt("NM_COMMENT_ID"));
				comment.setTitle(rs.getString("C.VC_TITLE"));
				comment.setContent(rs.getString("VC_CONTENT"));
				comment.setStatus(rs.getInt("C.NM_STATUS"));
				comment.setCommentatorName(rs.getString("VC_COMMENTATOR_NAME"));
				comment.setCommentatorEmail(rs
						.getString("VC_COMMENTATOR_EMAIL"));
				comment.setCommentatorHomepage(rs
						.getString("VC_COMMENTATOR_HOMEPAGE"));
				comment.setCommentatorRemoteAddr(rs
						.getString("VC_COMMENTATOR_REMOTE_ADDR"));
				comment.setInsertDate(new java.util.Date(rs.getDate(
						"DT_INSERT_DATE").getTime()));

				Post p = new Post();
				p.setId(rs.getInt("NM_POST_ID_FK"));
				p.setCountComments(rs.getInt("P.NM_COUNT_COMMNTS"));
				p.setTitle(rs.getString("P.VC_TITLE"));

				comment.setPost(p);

				list.add(comment);

			}

			return list;

		} finally {
			closeAll(rs, ps);
		}

	}

}
