package com.vanguarda.blog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.vanguarda.blog.bean.Template;
import com.vanguarda.blog.dao.AbstractDAO;
import com.vanguarda.blog.dao.TemplateDAO;

	
public class TemplateDAOImpl extends AbstractDAO implements TemplateDAO{
	
	private static final String LIST_QUERY = "SELECT T.NM_TEMPLATE_ID , T.VC_NAME , T.VC_DESCRIPTION , T.VC_BLOG_PATH, T.VC_COMMUNITY_PATH , " +
			"T.VC_FORUM_PATH, T.VC_STATUS FROM TB_BLOG_TEMPLATE T";
 
	public Collection list() throws SQLException,Exception
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection list = new ArrayList();
		Template t = null;		

		try {
			
			getConnection();
			ps = conn.prepareStatement(LIST_QUERY);
			
			rs = ps.executeQuery();

			while(rs.next()) {
				
				t = new Template();
				
				t.setId(rs.getInt("T.NM_TEMPLATE_ID"));
				t.setBlogPath(rs.getString("T.VC_BLOG_PATH"));
				t.setCommunityPath(rs.getString("T.VC_COMMUNITY_PATH"));
				t.setDescription(rs.getString("T.VC_DESCRIPTION"));
				t.setForumPath(rs.getString("T.VC_FORUM_PATH"));
				t.setName(rs.getString("T.VC_NAME"));
				t.setStatus(rs.getInt("T.VC_STATUS"));
				
				list.add(t);				
			}
				
			return list;
		} finally {
			closeAll(rs, ps);
		}
		
	}
	
}