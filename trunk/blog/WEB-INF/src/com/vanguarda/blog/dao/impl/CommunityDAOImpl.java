package com.vanguarda.blog.dao.impl;

import java.sql.*;
import java.util.*;

import com.vanguarda.blog.bean.*;
import com.vanguarda.blog.dao.*;
import com.vanguarda.blog.util.CommunityConstants;

public class CommunityDAOImpl extends AbstractDAO implements CommunityDAO {
		
		private static final String ADD_QUERY = "INSERT INTO TB_COMMUNITY (VC_TITLE, VC_DESCRIPTION, NM_STATUS, NM_COMMUNITY_USER_ID_FK, DT_INSERT_DATE) " +
				"VALUES (?,?,?,?, CURRENT_DATE)";
		
		private static final String LOAD_QUERY = 
			 "SELECT "+
			 /*selecionando propriedades da comunidade*/
			 "C.NM_COMMUNITY_ID_PK,C.NM_COMMUNITY_USER_ID_FK,C.VC_TITLE,C.VC_DESCRIPTION,"+
			 "C.DT_INSERT_DATE,C.NM_STATUS, "+
			 "T.NM_TOPIC_ID_PK,T.NM_COMMUNITY_ID_FK,T.VC_TITLE,T.VC_DESCRIPTION,T.DT_INSERT_DATE,T.NM_STATUS ,"+

			 /*selecionando propriedades do blog*/
			 "B.NM_BLOG_ID,B.VC_NAME,B.VC_DESCRIPTION,"+
			 "B.NM_STATUS,B.NM_USER_ID_FK,B.DT_INSERTDATE,B.VC_PATH,B.NM_TEMPLATE_ID_FK,"+

			 /*selecionando propriedades do usuario*/
			 "U.NM_USER_ID,U.VC_FIRSTNAME,U.VC_LASTNAME,U.VC_EMAIL,U.NM_STATUS,U.VC_LOGIN,U.VC_PASSWORD,U.NM_GROUP_ID_FK,U.DT_INSERTDATE "+

			 "FROM TB_COMMUNITY C JOIN TB_COMMUNITY_TOPIC T ON  C.NM_COMMUNITY_ID_PK  = T.NM_COMMUNITY_ID_FK ,"+
			 "TB_BLOG B , TB_BLOG_USER U "+
			 "WHERE  C.NM_COMMUNITY_ID_PK = ? "+  
			 "AND B.NM_USER_ID_FK  = C.NM_COMMUNITY_USER_ID_FK "+ 
			 "AND U.NM_USER_ID  = C.NM_COMMUNITY_USER_ID_FK "+
			 "ORDER BY T.DT_INSERT_DATE DESC ";
			
			/*
			" SELECT C.NM_COMMUNITY_ID_PK,C.NM_COMMUNITY_USER_ID_FK,C.VC_TITLE,C.VC_DESCRIPTION,C.DT_INSERT_DATE,C.NM_STATUS, "+
			"T.NM_TOPIC_ID_PK,T.NM_COMMUNITY_ID_FK,T.VC_TITLE,T.VC_DESCRIPTION,T.DT_INSERT_DATE,T.NM_STATUS "+
			"FROM TB_COMMUNITY C JOIN TB_COMMUNITY_TOPIC T ON  C.NM_COMMUNITY_ID_PK  = T.NM_COMMUNITY_ID_FK "+
			"WHERE  C.NM_COMMUNITY_ID_PK = ?  ORDER BY T.DT_INSERT_DATE DESC";
			*/
		private static final String UPDATE_QUERY = "UPDATE TB_COMMUNITY SET VC_TITLE=?, VC_DESCRIPTION=? WHERE NM_COMMUNITY_ID_PK = ?";
		
		private static final String DELETE_QUERY = "DELETE FROM TB_COMMUNITY WHERE NM_COMMUNITY_ID_PK = ?";
		
		private static final String LIST_QUERY = "SELECT C.NM_COMMUNITY_ID_PK, C.NM_COMMUNITY_USER_ID_FK,C.VC_TITLE,C.VC_DESCRIPTION," +
				"C.DT_INSERT_DATE, C.NM_STATUS " +
				"FROM TB_COMMUNITY C WHERE C.NM_STATUS = ?";
		
		private static final String UPDATE_STATUS_QUERY = "UPDATE TB_COMMUNITY SET NM_STATUS=? WHERE NM_COMMUNITY_ID_PK = ?";
		
		
		public int add(Community community) throws SQLException, Exception {
			
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {

				getConnection();
				ps = conn.prepareStatement(ADD_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);

				ps.setString(1, community.getTitle());
				ps.setString(2, community.getDescription());
				ps.setInt(3, community.getStatus());
				ps.setInt(4, community.getCommunityOwner().getId());
				
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();
				
				if(rs.next())
				{
					return rs.getInt(1);
				}
				
				return 0;
				
			}catch(SQLException sqle){
				System.out.println(sqle.getMessage());	
				throw sqle;

			}catch(Exception e){
				System.out.println(e.getMessage());	
				throw e;

			} finally {
				closeAll(rs, ps);
			}
		}

		public Community load(int id) throws SQLException,Exception {
			
			PreparedStatement ps = null;
			ResultSet rs = null;

			Community community = new Community();
			
			CommunityUser owner = new CommunityUser();
			
			CommunityTopic topic = null;
			
			Blog blog = null;
			
			BlogUser user = null;
			
			Collection topics = new ArrayList();

			try {

				getConnection();
				ps = conn.prepareStatement(LOAD_QUERY);

				ps.setInt(1, id);

				rs = ps.executeQuery();
			
				
				while (rs.next()) {
								
					community.setDescription(rs.getString("C.VC_DESCRIPTION"));
					community.setId(rs.getInt("C.NM_COMMUNITY_ID_PK"));
					community.setInsertDate(new java.util.Date(rs.getDate("C.DT_INSERT_DATE").getTime()));
					community.setTitle(rs.getString("C.VC_TITLE"));
					community.setStatus(rs.getInt("C.NM_STATUS"));
					
					owner.setId(rs.getInt("C.NM_COMMUNITY_USER_ID_FK"));
					
					community.setCommunityOwner(owner);
					
					topic = new CommunityTopic();
					
					topic.setId(rs.getInt("T.NM_TOPIC_ID_PK"));
					topic.setCommunity(community);
					topic.setTitle(rs.getString("T.VC_TITLE"));
					topic.setDescription(rs.getString("T.VC_DESCRIPTION"));
					topic.setInsertDate(new java.util.Date(rs.getDate("T.DT_INSERT_DATE").getTime()));
					topic.setStatus(rs.getInt("T.NM_STATUS"));
					
					topics.add(topic);
					
					blog = new Blog();
					
					blog.setDescription(rs.getString("B.VC_DESCRIPTION"));
					blog.setId(rs.getInt("B.NM_BLOG_ID"));
					blog.setInsertDate(new java.util.Date(rs.getDate("B.DT_INSERTDATE").getTime()));
					blog.setName(rs.getString("B.VC_NAME"));
					blog.setStatus(rs.getInt("B.NM_STATUS"));
					blog.setPath(rs.getString("B.VC_PATH"));
					
					user = new BlogUser();
					
					user.setBlog(blog);
					user.setId(rs.getInt("U.NM_USER_ID"));
					user.setFirstName(rs.getString("U.VC_FIRSTNAME"));
					user.setLastName(rs.getString("U.VC_LASTNAME"));
					user.setEmail(rs.getString("U.VC_EMAIL"));
					user.setInsertDate(new java.util.Date(rs.getDate("U.DT_INSERTDATE").getTime()));
					user.setStatus(rs.getInt("U.NM_STATUS"));
					user.setLogin(rs.getString("U.VC_LOGIN"));
					user.setPassword(rs.getString("U.VC_PASSWORD"));
					
					
				}

				
				blog.setBlogUser(user);
				community.setTopics(topics);
				community.setBlog(blog);
				
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();			
			} finally {
				closeAll(rs, ps);
			}
			return community;
		}
		
		public void update(Community community) throws SQLException,Exception {
						
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {

				getConnection();
				ps = conn.prepareStatement(UPDATE_QUERY);

				ps.setString(1, community.getTitle());
				ps.setString(2, community.getDescription());
				ps.setInt(3,community.getId());
				
				ps.executeUpdate();
				
			}catch(SQLException sqle){
				throw sqle;

			} finally {
				closeAll(rs, ps);
			}
		}

		public void delete(Community community) throws SQLException,Exception {
						
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {

				getConnection();
				ps = conn.prepareStatement(DELETE_QUERY);

				ps.setInt(1, community.getId());

				ps.executeUpdate();

			}catch(SQLException sqle){
				throw sqle;
			
			} finally {
				closeAll(rs, ps);
			}
		}

		public Collection listCommunities(int status) throws SQLException, Exception {
			
			PreparedStatement ps = null;
			ResultSet rs = null;

			Collection list = new ArrayList();

			Community community = null;
			CommunityUser owner = null;
			

			try {
				
				getConnection();			
				if (status == CommunityConstants.STATUS_ALL) {
					ps = conn.prepareStatement(LIST_QUERY.replaceAll(
							"C.NM_STATUS = ", "C.NM_STATUS >= "));						  
				} else {
					ps = conn.prepareStatement(LIST_QUERY);
				}
				
				ps.setInt(1, status);

				rs = ps.executeQuery();

				while (rs.next()) {
					community = new Community();
					owner = new CommunityUser();

					community.setDescription(rs.getString("C.VC_DESCRIPTION"));
					community.setId(rs.getInt("C.NM_COMMUNITY_ID_PK"));
					community.setInsertDate(new java.util.Date(rs.getDate("C.DT_INSERT_DATE").getTime()));
					community.setTitle(rs.getString("C.VC_TITLE"));
					community.setStatus(rs.getInt("C.NM_STATUS"));
					
					owner.setId(rs.getInt("C.NM_COMMUNITY_USER_ID_FK"));
					
					community.setCommunityOwner(owner);
					
					list.add(community);
				}

				return list;

			} catch(Exception e){
				throw e;
				
			}finally {
				closeAll(rs, ps);
			}
		}

		public void updateStatus(int communityId, int status) throws SQLException,Exception{
			
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				
				getConnection();
				ps = conn.prepareStatement(UPDATE_STATUS_QUERY);

				ps.setInt(1,status);
				ps.setInt(2,communityId);
				
				ps.executeUpdate();
				
			}catch(SQLException sqle){
				throw sqle;

			} finally {
				closeAll(rs, ps);
			}

			
		}
	}
