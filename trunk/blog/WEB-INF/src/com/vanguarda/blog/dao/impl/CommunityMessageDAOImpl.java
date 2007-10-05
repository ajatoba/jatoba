package com.vanguarda.blog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.vanguarda.blog.bean.CommunityUser;
import com.vanguarda.blog.bean.Message;
import com.vanguarda.blog.bean.Topic;
import com.vanguarda.blog.dao.AbstractDAO;
import com.vanguarda.blog.dao.CommunityMessageDAO;
import com.vanguarda.blog.util.CommunityConstants;

public class CommunityMessageDAOImpl extends AbstractDAO implements CommunityMessageDAO{

	private static final String ADD_QUERY = "INSERT INTO TB_COMMUNITY_MESSAGE "
		+ "(NM_TOPIC_ID_FK,VC_TITLE,VC_CONTENT,NM_STATUS, VC_USER_NAME, DT_INSERT_DATE) "
		+ "VALUES (?,?,?,?,?, CURRENT_DATE)";
	
	private static final String DELETE_QUERY = "UPDATE TB_COMMUNITY_MESSAGE SET NM_STATUS = ? WHERE NM_MESSAGE_ID_PK = ?";
	
	private static final String LIST_QUERY = "SELECT M.NM_MESSAGE_ID_PK,M.NM_TOPIC_ID_FK,M.VC_TITLE,M.VC_CONTENT,M.NM_STATUS, M.VC_USER_NAME, M.DT_INSERT_DATE "
		+ "FROM TB_COMMUNITY_MESSAGE M WHERE M.NM_TOPIC_ID_FK = ? AND M.NM_STATUS = ?";
	
	public int add(Message message) throws SQLException,Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			getConnection();

			ps = conn.prepareStatement(ADD_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setInt   (1, message.getTopic().getId());
			ps.setString(2, message.getTitle());
			ps.setString(3, message.getContent());
			ps.setInt   (4, message.getStatus());
			ps.setString(5, message.getUser().getName());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
			{
				return rs.getInt(1);
			}
			
			return 0;
			
		}catch(SQLException sqle){
				throw sqle;

		} finally {
			closeAll(rs, ps);
		}
	}


	public void delete(Message message) throws SQLException,Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();
			ps = conn.prepareStatement(DELETE_QUERY);

			ps.setInt(1, message.getStatus());
			ps.setInt(2, message.getId());
			
			ps.executeUpdate();

		} finally {
			closeAll(rs, ps);
		}
	}

	public Collection listMessages(int status, Topic topic) throws SQLException,Exception {

		
		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection list = new ArrayList();

		Message message = null;
		CommunityUser user = null;
		try {
			
			getConnection();
			if (status == CommunityConstants.STATUS_ALL) {
				ps = conn.prepareStatement(LIST_QUERY.replaceAll(
						"M.NM_STATUS = ", "M.NM_STATUS >= "));
			} else {
				ps = conn.prepareStatement(LIST_QUERY);
			}
		
			ps.setInt(1, topic.getId());
			ps.setInt(2, status);

			rs = ps.executeQuery();

			while (rs.next()) {
				message = new Message();
				user = new CommunityUser();

				message.setId(rs.getInt("M.NM_MESSAGE_ID_PK"));
				message.setTitle(rs.getString("M.VC_TITLE"));
				message.setContent(rs.getString("M.VC_CONTENT"));
				message.setStatus(rs.getInt("M.NM_STATUS"));
				message.setInsertDate(new java.util.Date(rs.getDate("M.DT_INSERT_DATE").getTime()));
				message.setTopic(topic);
				
				user.setName(rs.getString("M.VC_USER_NAME"));
				
				message.setUser(user);
				
				list.add(message);
			}

			

		}catch(Exception e){
			e.printStackTrace();
		} finally {
			closeAll(rs, ps);
		}
		
		return list;
	}

}
