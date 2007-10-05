package com.vanguarda.blog.dao.impl;

import java.sql.*;
import java.util.*;

import com.vanguarda.blog.bean.*;
import com.vanguarda.blog.dao.*;
import com.vanguarda.blog.util.Constants;

public class MessageDAOImpl extends AbstractDAO implements MessageDAO{

	private static final String ADD_QUERY = "INSERT INTO TB_FORUM_MESSAGE "
		+ "(NM_TOPIC_ID_FK,VC_TITLE,VC_CONTENT,NM_STATUS, VC_USER_NAME, DT_INSERT_DATE) "
		+ "VALUES (?,?,?,?,?, CURRENT_DATE)";
	
	private static final String DELETE_QUERY = "UPDATE TB_FORUM_MESSAGE SET NM_STATUS = ? WHERE NM_MESSAGE_ID_PK = ?";
	
	private static final String LIST_QUERY = "SELECT M.NM_MESSAGE_ID_PK,M.NM_TOPIC_ID_FK,M.VC_TITLE,M.VC_CONTENT,M.NM_STATUS, M.VC_USER_NAME, M.DT_INSERT_DATE "
		+ "FROM TB_FORUM_MESSAGE M WHERE M.NM_TOPIC_ID_FK = ? AND M.NM_STATUS = ?";
	
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
		ForumUser user = null;
		try {
			
			getConnection();
			if (status == Constants.STATUS_ALL) {
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
				user = new ForumUser();

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

			return list;

		} finally {
			closeAll(rs, ps);
		}
	}
	
	public Object execute(String method, ArrayList parameters) throws Exception {
		if (method.equals("listMessages")) {
			return listMessages(((Integer)parameters.get(0)).intValue(),(Topic)parameters.get(1));
		}	
		return super.execute(method, parameters);
	}

}
