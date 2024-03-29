package com.vanguarda.blog.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.vanguarda.blog.bean.AbstractForum;
import com.vanguarda.blog.bean.ForumUser;
import com.vanguarda.blog.bean.Message;
import com.vanguarda.blog.bean.Topic;
import com.vanguarda.blog.dao.AbstractDAO;
import com.vanguarda.blog.dao.TopicDAO;
import com.vanguarda.blog.util.Constants;
import com.vanguarda.blog.util.ForumConstants;

public class TopicDAOImpl extends AbstractDAO implements TopicDAO {

	private static final String ADD_QUERY = "INSERT INTO TB_FORUM_TOPIC (VC_TITLE, VC_DESCRIPTION, DT_INSERT_DATE, NM_STATUS, NM_FORUM_ID_FK)"
			+ " values (?,?, CURRENT_DATE, ?, ?)";

	private static final String LOAD_QUERY = "SELECT T.NM_TOPIC_ID_PK,T.NM_FORUM_ID_FK, T.VC_TITLE, "
			+ "T.VC_DESCRIPTION,T.DT_INSERT_DATE, T.NM_STATUS "
			+ "FROM TB_FORUM_TOPIC T WHERE T.NM_TOPIC_ID_PK = ?";

	private static final String UPDATE_QUERY = "UPDATE TB_FORUM_TOPIC T SET T.VC_TITLE = ? ,"
			+ "T.VC_DESCRIPTION = ? WHERE T.NM_TOPIC_ID_PK = ?";

	private static final String DELETE_QUERY = "DELETE FROM TB_FORUM_TOPIC WHERE NM_TOPIC_ID_PK = ?";

	private static final String LIST_QUERY = "SELECT T.NM_TOPIC_ID_PK,T.NM_FORUM_ID_FK, T.VC_TITLE, T.VC_DESCRIPTION, T.DT_INSERT_DATE, T.NM_STATUS "
			+ "FROM TB_FORUM_TOPIC T "
			+ "WHERE T.NM_STATUS = ? ORDER BY T.DT_INSERT_DATE DESC,T.NM_TOPIC_ID_PK DESC";

	private static final String UPDATE_STATUS_QUERY = "UPDATE TB_FORUM_TOPIC T SET T.NM_STATUS = ? "
			+ "WHERE T.NM_TOPIC_ID_PK = ?";

	private static final String LOAD_TOP_TOPIC = "SELECT t.NM_TOPIC_ID_PK, t.NM_FORUM_ID_FK, t.VC_TITLE,  t.VC_DESCRIPTION, "
			+ "t.DT_INSERT_DATE,  t.NM_STATUS FROM    tb_forum_topic t join tb_forum_message m on NM_TOPIC_ID_PK = NM_TOPIC_ID_FK   "
			+ " order by t.DT_INSERT_DATE desc ,m.DT_INSERT_DATE desc limit 1";

	private static final String LIST_COMMENTS_QUERY = "SELECT M.NM_MESSAGE_ID_PK,M.NM_TOPIC_ID_FK,M.VC_TITLE,M.VC_CONTENT,M.NM_STATUS, M.VC_USER_NAME, M.DT_INSERT_DATE "
			+ "FROM TB_FORUM_MESSAGE M WHERE M.NM_TOPIC_ID_FK = ? AND M.NM_STATUS = ? ORDER BY M.DT_INSERT_DATE DESC,M.NM_MESSAGE_ID_PK DESC limit 2 ";

	public int add(Topic topic) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();
			ps = conn.prepareStatement(ADD_QUERY,
					PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, topic.getTitle());
			ps.setString(2, topic.getDescription());
			ps.setInt(3, topic.getStatus());
			ps.setInt(4, topic.getForum().getId());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				return rs.getInt(1);
			}

			return 0;

		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
			throw sqle;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;

		} finally {
			closeAll(rs, ps);
		}
	}

	public Topic load(int id) throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Topic topic = new Topic();

		try {

			getConnection();

			ps = conn.prepareStatement(LOAD_QUERY);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

				topic.setDescription(rs.getString("T.VC_DESCRIPTION"));
				topic.setId(rs.getInt("T.NM_TOPIC_ID_PK"));
				topic.setInsertDate(new java.util.Date(rs.getDate(
						"T.DT_INSERT_DATE").getTime()));
				topic.setTitle(rs.getString("T.VC_TITLE"));
				topic.setStatus(rs.getInt("T.NM_STATUS"));
			}

			return topic;

		} finally {
			closeAll(rs, ps);
		}
	}

	public void update(Topic topic) throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();

			ps = conn.prepareStatement(UPDATE_QUERY);

			ps.setString(1, topic.getTitle());
			ps.setString(2, topic.getDescription());
			ps.setInt(3, topic.getId());

			ps.executeUpdate();

		} catch (SQLException sqle) {
			throw sqle;

		} finally {
			closeAll(rs, ps);
		}
	}

	public void delete(Topic topic) throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();

			ps = conn.prepareStatement(DELETE_QUERY);

			ps.setInt(1, topic.getId());

			ps.executeUpdate();

		} catch (SQLException sqle) {
			throw sqle;

		} finally {
			closeAll(rs, ps);
		}
	}

	public Collection listTopics(int status, AbstractForum forum)
			throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection list = new ArrayList();

		Topic topic = null;

		try {

			getConnection();

			if (status == ForumConstants.STATUS_ALL) {
				ps = conn.prepareStatement(LIST_QUERY.replaceAll(
						"T.NM_STATUS = ", "T.NM_STATUS >= "));
			} else {
				ps = conn.prepareStatement(LIST_QUERY);
			}

			ps.setInt(1, status);

			rs = ps.executeQuery();

			while (rs.next()) {
				topic = new Topic();

				topic.setDescription(rs.getString("T.VC_DESCRIPTION"));
				topic.setId(rs.getInt("T.NM_TOPIC_ID_PK"));
				topic.setInsertDate(new java.util.Date(rs.getDate(
						"T.DT_INSERT_DATE").getTime()));
				topic.setTitle(rs.getString("T.VC_TITLE"));
				topic.setStatus(rs.getInt("T.NM_STATUS"));

				list.add(topic);
			}

			return list;

		} catch (Exception e) {
			throw e;

		} finally {
			closeAll(rs, ps);
		}
	}

	public Topic loadTopTopic() throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList list = new ArrayList();

		Topic topic = new Topic();

		try {

			getConnection();

			ps = conn.prepareStatement(LOAD_TOP_TOPIC);

			rs = ps.executeQuery();

			if (rs.next()) {

				topic.setDescription(rs.getString("T.VC_DESCRIPTION"));
				topic.setId(rs.getInt("T.NM_TOPIC_ID_PK"));
				topic.setInsertDate(new java.util.Date(rs.getDate(
						"T.DT_INSERT_DATE").getTime()));
				topic.setTitle(rs.getString("T.VC_TITLE"));
				topic.setStatus(rs.getInt("T.NM_STATUS"));

				ps = conn.prepareStatement(LIST_COMMENTS_QUERY);

				ps.setInt(1, topic.getId());
				ps.setInt(2, Constants.STATUS_ACTIVE);

				ResultSet rs2 = ps.executeQuery();

				while (rs2.next()) {
					Message message = new Message();
					ForumUser user = new ForumUser();

					message.setId(rs2.getInt("M.NM_MESSAGE_ID_PK"));
					message.setTitle(rs2.getString("M.VC_TITLE"));
					message.setContent(rs2.getString("M.VC_CONTENT"));
					message.setStatus(rs2.getInt("M.NM_STATUS"));				
					message.setTopic(topic);

					user.setName(rs2.getString("M.VC_USER_NAME"));

					message.setUser(user);

					list.add(message);
				}
			}
			topic.setMessages(list);

			return topic;

		} finally {
			closeAll(rs, ps);
		}
	}

	public void updateStatus(int topicId, int status) throws SQLException,
			Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			getConnection();

			ps = conn.prepareStatement(UPDATE_STATUS_QUERY);

			ps.setInt(1, status);
			ps.setInt(2, topicId);

			ps.executeUpdate();

		} catch (SQLException sqle) {
			throw sqle;

		} finally {
			closeAll(rs, ps);
		}
	}

	public Object execute(String method, ArrayList parameters) throws Exception {
		if (method.equals("listTopics")) {
			return listTopics(((Integer) parameters.get(0)).intValue(),
					(AbstractForum) parameters.get(1));
		}
		return super.execute(method, parameters);
	}
}
