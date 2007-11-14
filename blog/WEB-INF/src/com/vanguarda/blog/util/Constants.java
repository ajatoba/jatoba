package com.vanguarda.blog.util;

/**
 * @author Fabio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Constants {
	
	
	/** Mapeamento de DAOs **/
	public static final String MAPPING_BLOG_DAO = "BLOGDAO";
	
	public static final String MAPPING_POST_DAO = "POSTDAO";
	
	public static final String MAPPING_COMMENTS_DAO = "COMMENTSDAO";
	
	public static final String MAPPING_ADMIN_DAO = "ADMINDAO";
	
	public static final String MAPPING_BLOGGERUSER_DAO = "BLOGGERUSERDAO";
	
	public static final String MAPPING_RANKING_DAO = "RANKINGDAO";
	
	public static final String MAPPING_TEMPLATE_DAO = "TEMPLATEDAO";
	
	public static final String MAPPING_USERCOMMENTATOR_DAO = "USERCOMMENTATORDAO";
	
	public static final String MAPPING_FAVORITE_DAO = "FAVORITEDAO";
	
	/**Proporiedade de Grupo de usuario **/
	public static final int USER_ADMIN = 1;
	
	public static final int USER_BLOGGER = 2;
	
	public static final int USER_COMENTATOR = 3;
	
	/** Possíveis status **/
	public static final int STATUS_ACTIVE = 1;
	
	public static final int STATUS_INACTIVE = 0;
	
	public static final int STATUS_ALL = -1;
	
	public static final int STATUS_BLOCKEAD = 2;
	
	
	/** Atributos de request **/
	
	public static final String ADMIN_USER_BEAN = "userAdmin";
	
	public static final String BLOGGER_USER_BEAN = "blogUser";
	
	public static final String USER_BEAN = "user";
	
	public static final String BLOG_BEAN = "blog";
	
	public static final String BLOGS_BEAN = "blogs";
	
	public static final String POST_BEAN = "post";

	public static final String SMTP = "smtp.bloglog.com.br";
	
	public static final String POSTS_BEAN = "posts";

	public static final String CENSURED = "Pedido de censura.";
	
	public static final String COMMENT_BEAN = "comment";
	
	public static final String COMMENTS_BEAN = "comments";
	
	public static final String RANKING_BEAN = "ranking";

	
	
	
	/** Forwards do Struts **/
	
	//User
	public static final String LOGIN_FORWARD = "login_sucess";
	
	public static final String USER_LIST_FORWARD = "user_list";
	
	public static final String USER_ADD_FORWARD = "user_add";
	
	public static final String USER_UPDATE_FORWARD = "user_update";
	
	public static final String USER_LOAD_FORWARD = "user_load";
	
	public static final String USER_ADD_FORM_FORWARD = "user_form";
	
	public static final String USER_UPDATE_FORM_FORWARD = "user_form_update";
	
	public static final String USER_DELETE_FORWARD = "user_delete";
	
	public static final String ADMIN_LOGIN_ERROR_FORWARD = "login_error";
	
	public static final String BLOGUSER_LOGIN_ERROR_FORWARD = "login_error";
	
	//Blog
	
	public static final String BLOG_FORM_FORWARD = "blog_form";
	
	public static final String BLOG_LIST_FORWARD = "blog_list";
	
	public static final String BLOG_ADD_FORWARD = "blog_add";
	
	public static final String BLOG_ERROR_FORWARD = "blog_error";
	
	public static final String BLOG_UPDATE_FORWARD = "blog_update";
	
	public static final String BLOG_LOAD_FORWARD = "blog_load";
	
	public static final String BLOG_DELETE_FORWARD = "blog_delete";
	
	
	//Post
	public static final String POST_LIST_FORWARD = "post_list";
	
	public static final String POST_ADD_FORWARD = "post_add";
	
	public static final String POST_UPDATE_FORWARD = "post_update";
	
	public static final String POST_LOAD_FORWARD = "post_load";
	
	public static final String POST_DELETE_FORWARD = "post_delete";
	
	
	//Comment
	public static final String COMMENT_LIST_FORWARD = "comment_list";
	
	public static final String COMMENT_ADD_FORWARD = "comment_add";
	
	public static final String COMMENT_UPDATE_FORWARD = "comment_update";
	
	public static final String COMMENT_LOAD_FORWARD = "comment_load";
	
	public static final String COMMENT_DELETE_FORWARD = "comment_delete";
	
	public static final String TO_SITE_FORWARD = "site";
	
	public static final String TO_ADMIN_FORWARD = "admin";
	
	//Ranking
	
	public static final String RANKING_FORWARD = "ranking_list";
	
	//HISTORY	
	public static final String HISTORY_FORWARD = "history_list";
	
	//CENSURED
	public static final String CENSURE_SUCCESS_FORWARD = "success";
	
	
	//conteudo gerado
	
	public static final String RANKING_FILE_NAME = "ranking_estatico.html";
	
	public static final String HISTORY_FILE_NAME = "history_estatico.html";

	public static final String RSS_FORWARD = "rss";
	
	//Properties utilizadas no sistema	
	public static final String ROOTPATH_PROPERTY = "docroot";
	
	public static final String HOSTS_PROPERTY = "hosts";
	
	public static final String LOCALHOST_PROPERTY = "localhost";
	
	//GRUPOS	
	public static final String ADMIN_GROUP = "1";
	
	public static final String BLOG_USER_GROUP = "2";
	
	public static final String USER_COMMENTATOR_GROUP = "3";
	
	
	
	
	
		
	
}
