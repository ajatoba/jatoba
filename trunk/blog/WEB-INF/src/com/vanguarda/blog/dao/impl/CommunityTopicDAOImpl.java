package com.vanguarda.blog.dao.impl;

import java.sql.*;
import java.util.*;

import com.vanguarda.blog.bean.*;
import com.vanguarda.blog.dao.*;
import com.vanguarda.blog.util.ForumConstants;


public class CommunityTopicDAOImpl extends AbstractDAO implements CommunityTopicDAO {
	
	private static final String ADD_QUERY = "INSERT INTO TB_COMMUNITY_TOPIC (VC_TITLE, VC_DESCRIPTION, DT_INSERT_DATE, NM_STATUS, NM_COMMUNITY_ID_FK)"
									+ " values (?,?, CURRENT_DATE, ?, ?)";
	
	private static final String LOAD_QUERY = "SELECT T.NM_TOPIC_ID_PK,T.NM_COMMUNITY_ID_FK, T.VC_TITLE, "
		+ "T.VC_DESCRIPTION,T.DT_INSERT_DATE, T.NM_STATUS "
		+ "FROM TB_COMMUNITY_TOPIC T WHERE T.NM_TOPIC_ID_PK = ?";
	
	private static final String UPDATE_QUERY = "UPDATE TB_COMMUNITY_TOPIC T SET T.VC_TITLE = ? ,"
		+ "T.VC_DESCRIPTION = ? WHERE T.NM_TOPIC_ID_PK = ?";
	
	private static final String DELETE_QUERY = "DELETE FROM TB_COMMUNITY_TOPIC WHERE NM_TOPIC_ID_PK = ?";
	
	private static final String LIST_QUERY = "SELECT T.NM_TOPIC_ID_PK,T.NM_COMMUNITY_ID_FK, T.VC_TITLE, T.VC_DESCRIPTION, T.DT_INSERT_DATE, T.NM_STATUS "
		+ "FROM TB_COMMUNITY_TOPIC T "
		+ "WHERE T.NM_STATUS = ? AND T.NM_COMMUNITY_ID_FK = ?";
	
	private static final String UPDATE_STATUS_QUERY = "UPDATE TB_COMMUNITY_TOPIC T SET T.NM_STATUS = ? "
		+ "WHERE T.NM_TOPIC_ID_PK = ?";
	
	
	public int add(Topic topic) throws SQLException, Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();
			ps = conn.prepareStatement(ADD_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);

			CommunityTopic cTopic = (CommunityTopic) topic;			
			
			ps.setString(1, topic.getTitle());
			ps.setString(2, topic.getDescription());
			ps.setInt(3, topic.getStatus());
			ps.setInt(4, cTopic.getCommunity().getId());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
			{
				return rs.getInt(1);
			}
			
			return 0;
			
		}catch(SQLException sqle){
			sqle.printStackTrace();	
			throw sqle;

		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());	
			throw e;

		} finally {
			closeAll(rs, ps);
		}
	}

	public Topic load(int id) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		CommunityTopic topic = new CommunityTopic();

		try {
			
			getConnection();

			ps = conn.prepareStatement(LOAD_QUERY);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
							
				topic.setDescription(rs.getString("T.VC_DESCRIPTION"));
				topic.setId(rs.getInt("T.NM_TOPIC_ID_PK"));
				topic.setInsertDate(new java.util.Date(rs.getDate("T.DT_INSERT_DATE").getTime()));
				topic.setTitle(rs.getString("T.VC_TITLE"));
				topic.setStatus(rs.getInt("T.NM_STATUS"));				
			}

			return topic;

		} finally {
			closeAll(rs, ps);
		}
	}
	
	public void update(Topic topic) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			getConnection();

			ps = conn.prepareStatement(UPDATE_QUERY);

			ps.setString(1, topic.getTitle());
			ps.setString(2, topic.getDescription());
			ps.setInt(3,topic.getId());
			
			ps.executeUpdate();
			
		}catch(SQLException sqle){
			throw sqle;

		} finally {
			closeAll(rs, ps);
		}
	}

	public void delete(Topic topic) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			getConnection();

			ps = conn.prepareStatement(DELETE_QUERY);

			ps.setInt(1, topic.getId());

			ps.executeUpdate();

		}catch(SQLException sqle){
			throw sqle;
		
		} finally {
			closeAll(rs, ps);
		}
	}

	public Collection listTopics(int status, AbstractForum community) throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection list = new ArrayList();

		CommunityTopic topic = null;

		try {
		
			getConnection();
			if (status == ForumConstants.STATUS_ALL) {
				ps = conn.prepareStatement(LIST_QUERY.replaceAll(
						"T.NM_STATUS = ", "T.NM_STATUS >= "));						  
			} else {
				ps = conn.prepareStatement(LIST_QUERY);
			}
			
			ps.setInt(1, status);
			ps.setInt(2, community.getId());

			rs = ps.executeQuery();

			while (rs.next()) {
				topic = new CommunityTopic();

				topic.setDescription(rs.getString("T.VC_DESCRIPTION"));
				topic.setId(rs.getInt("T.NM_TOPIC_ID_PK"));
				topic.setInsertDate(new java.util.Date(rs.getDate("T.DT_INSERT_DATE").getTime()));
				topic.setTitle(rs.getString("T.VC_TITLE"));
				topic.setStatus(rs.getInt("T.NM_STATUS"));
				
				list.add(topic);
			}

			return list;

		} catch(Exception e){
			throw e;
			
		}finally {
			closeAll(rs, ps);
		}
	}

	public void updateStatus(int topicId, int status) throws SQLException,Exception{

		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();
			ps = conn.prepareStatement(UPDATE_STATUS_QUERY);

			ps.setInt(1,status);
			ps.setInt(2,topicId);
			
			ps.executeUpdate();
			
		}catch(SQLException sqle){
			throw sqle;

		} finally {
			closeAll(rs, ps);
		}

		
	}
}
