package com.vanguarda.blog.dao.impl;

import java.sql.*;
import java.util.*;

import com.vanguarda.blog.bean.*;
import com.vanguarda.blog.dao.*;

public class CommunityUserDAOImpl extends AbstractDAO implements CommunityUserDAO {
		
		private static final String ADD_QUERY = "INSERT INTO TB_COMMUNITY_USER_XREF (NM_COMMUNITY_USER_ID_FK, NM_COMMUNITY_ID_FK) " +
				"VALUES (?,?)";
		
		private static final String DELETE_QUERY = "DELETE FROM TB_COMMUNITY_USER_XREF WHERE NM_COMMUNITY_ID_FK = ? AND NM_COMMUNITY_USER_ID_FK = ?";
		
		private static final String LIST_QUERY = "SELECT C.NM_COMMUNITY_ID_PK,C.NM_COMMUNITY_USER_ID_FK,C.VC_TITLE,C.VC_DESCRIPTION,C.DT_INSERT_DATE, " +
				"C.NM_STATUS,CX.NM_COMMUNITY_ID_FK,CX.NM_COMMUNITY_USER_ID_FK , B.NM_BLOG_ID "+
				"FROM TB_COMMUNITY_USER_XREF CX, TB_COMMUNITY C, TB_BLOG B "+ 
				"WHERE CX.NM_COMMUNITY_USER_ID_FK = ? "+ 
				"AND C.NM_COMMUNITY_ID_PK = CX.NM_COMMUNITY_ID_FK "+ 
				"AND B.NM_USER_ID_FK = CX.NM_COMMUNITY_USER_ID_FK "+
				"AND C.NM_STATUS = ?";
		
		/*"SELECT C.NM_COMMUNITY_ID_PK,C.NM_COMMUNITY_USER_ID_FK,C.VC_TITLE,C.VC_DESCRIPTION,C.DT_INSERT_DATE,C.NM_STATUS,CX.NM_COMMUNITY_ID_FK,CX.NM_COMMUNITY_USER_ID_FK "+
				"FROM TB_COMMUNITY_USER_XREF CX, TB_COMMUNITY C "+ 
				"WHERE CX.NM_COMMUNITY_USER_ID_FK = ? AND C.NM_COMMUNITY_ID_PK = CX.NM_COMMUNITY_ID_FK AND C.NM_STATUS = ?";
		*/
		
		public void participate(Community community,CommunityUser user) throws SQLException, Exception {
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				
				getConnection();
				ps = conn.prepareStatement(ADD_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);

				ps.setInt(1, user.getId());
				ps.setInt(2, community.getId());
				
				ps.executeUpdate();
				rs=ps.getGeneratedKeys();
				
			}catch(SQLException sqle){
				System.out.println(sqle.getMessage());	
				throw sqle;

			}catch(Exception e){
				System.out.println(e.getMessage());	
				throw e;

			} finally {
				closeAll(rs,ps);
			}
		}

		
		public void unParticipate(Community community, CommunityUser user) throws SQLException,Exception {
					
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				
				getConnection();

				ps = conn.prepareStatement(DELETE_QUERY);

				ps.setInt(1, community.getId());
				ps.setInt(2, user.getId());

				ps.executeUpdate();

			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw sqle;
			
			} finally {
				closeAll(rs, ps);
			}
		}

		public Collection listCommunities(CommunityUser user, int status) throws SQLException, Exception {
			
			PreparedStatement ps = null;
			ResultSet rs = null;

			Collection list = new ArrayList();

			Community community = null;
			CommunityUser owner = null;
			Blog blog = null;
			

			try {
			
				getConnection();
				ps = conn.prepareStatement(LIST_QUERY);
				
				ps.setInt(1, user.getId());
				ps.setInt(2, status);
				
				rs = ps.executeQuery();

				while (rs.next()) {
					community = new Community();
					owner = new CommunityUser();
					blog = new Blog();

					community.setDescription(rs.getString("C.VC_DESCRIPTION"));
					community.setId(rs.getInt("C.NM_COMMUNITY_ID_PK"));
					community.setInsertDate(new java.util.Date(rs.getDate("C.DT_INSERT_DATE").getTime()));
					community.setTitle(rs.getString("C.VC_TITLE"));
					community.setStatus(rs.getInt("C.NM_STATUS"));
					
					owner.setId(rs.getInt("C.NM_COMMUNITY_USER_ID_FK"));
					
					community.setCommunityOwner(owner);
					
					blog.setId(rs.getInt("B.NM_BLOG_ID"));
					
					community.setBlog(blog);
					
					list.add(community);
				}

				return list;

			} catch(Exception e){
				e.printStackTrace();
				throw e;
				
			}finally {
				closeAll(rs, ps);
			}
		}

	}
