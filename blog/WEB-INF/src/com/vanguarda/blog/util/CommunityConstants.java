package com.vanguarda.blog.util;

public interface CommunityConstants extends Constants {

		/** Mapeamento de DAOs **/
		public static final String MAPPING_COMMUNITY_DAO = "COMMUNITYDAO";
		public static final String MAPPING_COMMUNITY_USER_DAO = "COMMUNITYUSERDAO";
		public static final String MAPPING_COMMUNITY_TOPIC_DAO = "COMMUNITYTOPICDAO";
		public static final String MAPPING_COMMUNITY_MESSAGE_DAO = "COMMUNITYMESSAGEDAO";

		/** Forwards **/
		public static final String COMMUNITY_LOAD_FORWARD = "community_load";
		public static final String COMMUNITY_LIST_FORWARD = "community_list";
		public static final String COMMUNITY_TOPIC_LOAD_FORWARD = "community_topic_load";
		
		
		/** Atributos de request **/	
		public static final String COMMUNITY_BEAN = "community";
		public static final String COMMUNITY_TOPIC_BEAN = "communityTopic";
		
		public static final String COMMUNITIES_FILE_NAME = "communities";
		public static final String BLOGUSER_LOGIN_ERROR_FORWARD = "login_error";
	}
