package com.vanguarda.blog.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.vanguarda.blog.BlogManager;
import com.vanguarda.blog.bean.Comment;
import com.vanguarda.blog.bean.Post;
import com.vanguarda.blog.dao.AbstractDAO;

public class CacheManager {
	private static final CacheManager instance = new CacheManager();

	private Map cache = new HashMap();

	private Map synchedCache = Collections.synchronizedMap(cache);

	private Map rankingControll = new HashMap();

	private Map historyControll = new HashMap();

	//private
	private CacheManager() {
		super();
	}

	public static CacheManager getInstance() {

		return instance;
	}

	public Object hitCache(AbstractDAO dao, String method, ArrayList parameters)
			throws Exception {
		//Monta a chave do cache baseado no nome do método mais parametros
		String key = buildKey(method, parameters);

		//Se estiver no cache, retorna direto o valor
		Object cached = cache.get(key);
		if (cached == null) {

			try {
				//Se não encontrou no cache, executa a consulta
				cached = dao.execute(method, parameters);

				//Se ainda não está no cache, coloca usando cache syncronizado
				if (cached != null && !cache.containsKey(key)) {
					synchedCache.put(key, cached);
				}
			} catch (NoSuchMethodException exc) {
				exc.printStackTrace();
				throw exc;
			}
		}

		return cached;
	}

	private String buildKey(String method, ArrayList parameters) {
		StringBuffer key = new StringBuffer();
		key.append(method);
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				key.append('-');
				if (parameters.get(i) != null) {
					key.append(parameters.get(i).toString());
				} else {
					key.append("null");
				}
			}
		}
		return key.toString();
	}

	public void clear() {
		synchronized (cache) {
			cache.clear();
		}
	}

	public String dump() {
		StringBuffer buffer = new StringBuffer();
		try {
			Set set = cache.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				buffer.append("* " + key + ": " + cache.get(key) + "\n");
			}
		} catch (Exception e) {
			buffer.append(e.getMessage());
		}
		return buffer.toString();
	}

	public void commentsClearCache(int postId) {
		try {
			System.out.println("commentsClearCache");

			ArrayList keysToRemove = new ArrayList();
			Set set = cache.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();

				if (key.startsWith("load-" + postId)) {
					keysToRemove.add(key);
				}
			}

			for (int i = 0; i < keysToRemove.size(); i++) {
				synchronized (cache) {
					cache.remove(keysToRemove.get(i));
				}
			}
		} catch (Exception e) {
			LoggerUtil.error("Erro ao limpar cache do post" + postId, e);
		}
	}

	public void postsClearCache(int blogId) {
		try {

			System.out.println("postsClearCache");

			ArrayList keysToRemove = new ArrayList();
			Set set = cache.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();

				if (key.startsWith("loadByDate-" + blogId) || key.startsWith("loadLastPosts")) {
					keysToRemove.add(key);
				}

			}

			for (int i = 0; i < keysToRemove.size(); i++) {
				synchronized (cache) {
					cache.remove(keysToRemove.get(i));
				}
			}

		} catch (Exception e) {
			LoggerUtil.error("Erro ao limpar cache do blog" + blogId, e);
		}
	}

	public void historyClearCache(int blogId) {
		System.out.println("historyClearCache");
		Date data = new Date();
		if (data.getDate() == 1) {
			String today = data.getDate() + "-" + data.getMonth() + "-"
					+ data.getYear();

			if (historyControll.size() == 0
					|| !historyControll.containsKey(today)) {
				cache.remove("getHistory-" + blogId);
				historyControll.clear();
				historyControll.put(today, "true");
			}
		}
	}

	public void rankingClearCache() {
		System.out.println("rankingClearCache");
		Date data = new Date();

		String today = data.getDate() + "-" + data.getMonth() + "-"
				+ data.getYear();
		if (rankingControll.size() == 0 || !rankingControll.containsKey(today)) {
			cache.remove("getRanking");
			rankingControll.clear();
			rankingControll.put(today, "true");
		}
	}

	public void favoritesClearCache(int userId) {
		try {

			System.out.println("favoritesClearCache");

			ArrayList keysToRemove = new ArrayList();
			Set set = cache.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();

				if (key.startsWith("getFavorites-" + userId)) {
					keysToRemove.add(key);
				}

			}

			for (int i = 0; i < keysToRemove.size(); i++) {
				synchronized (cache) {
					cache.remove(keysToRemove.get(i));
				}
			}

		} catch (Exception e) {
			LoggerUtil.error("Erro ao limpar cache do usuario" + userId, e);
		}
	}

	public void topicClearCache(int status) {
		try {

			System.out.println("topicClearCache");

			ArrayList keysToRemove = new ArrayList();
			Set set = cache.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();

				if (key.startsWith("listTopics-")) {
					keysToRemove.add(key);
				}
			}

			for (int i = 0; i < keysToRemove.size(); i++) {
				synchronized (cache) {
					cache.remove(keysToRemove.get(i));
				}
			}

		} catch (Exception e) {
			LoggerUtil.error("Erro ao limpar cache do forum", e);
		}
	}

	public void topicMessageClearCache(int status, String topic) {
		try {

			System.out.println("topicMessageClearCache");

			ArrayList keysToRemove = new ArrayList();
			Set set = cache.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();

				if (key.startsWith("listMessages-" + status + "-" + topic)) {
					keysToRemove.add(key);
				}
			}

			for (int i = 0; i < keysToRemove.size(); i++) {
				synchronized (cache) {
					cache.remove(keysToRemove.get(i));
				}
			}

		} catch (Exception e) {
			LoggerUtil.error("Erro ao limpar cache do forum", e);
		}
	}

	public void remoteCommentsClearCache(int postId) {

		try {
			//commentsClearCache(postId);
			if (BlogManager.getInstance().getProperties().get(
					Constants.HOSTS_PROPERTY) != null) {
				String[] hosts = ((String) BlogManager.getInstance()
						.getProperties().get(Constants.HOSTS_PROPERTY))
						.split(";");

				for (int i = 0; i < hosts.length; i++) {
					if (!hosts[i].equals((String) BlogManager.getInstance()
							.getProperties().get(Constants.LOCALHOST_PROPERTY))) {
						accessUrl("http://"
								+ hosts[i]
								+ "/blog/servlet/cache?act=commentsClearCache&postId="
								+ postId);
						System.out.println(hosts[i]);
					}
				}

			}
		} catch (Exception e) {
			LoggerUtil.error("Erro ao efetuar limpeza de cache", e);
		}

	}

	public void remoteFavoritesClearCache(int userId) {
		try {
			favoritesClearCache(userId);
			if (BlogManager.getInstance().getProperties().get(
					Constants.HOSTS_PROPERTY) != null) {
				String[] hosts = ((String) BlogManager.getInstance()
						.getProperties().get(Constants.HOSTS_PROPERTY))
						.split(";");

				for (int i = 0; i < hosts.length; i++) {
					if (!hosts[i].equals((String) BlogManager.getInstance()
							.getProperties().get(Constants.LOCALHOST_PROPERTY))) {
						accessUrl("http://"
								+ hosts[i]
								+ "/blog/servlet/cache?act=favoritesClearCache&userId="
								+ userId);
						System.out.println(hosts[i]);
					}
				}

			}
		} catch (Exception e) {
			LoggerUtil.error("Erro ao efetuar limpeza de cache", e);
		}
	}

	public void remoteTopicClearCache(int status) {
		try {
			topicClearCache(status);

			if (BlogManager.getInstance().getProperties().get(
					Constants.HOSTS_PROPERTY) != null) {
				String[] hosts = ((String) BlogManager.getInstance()
						.getProperties().get(Constants.HOSTS_PROPERTY))
						.split(";");

				for (int i = 0; i < hosts.length; i++) {
					if (!hosts[i].equals((String) BlogManager.getInstance()
							.getProperties().get(Constants.LOCALHOST_PROPERTY))) {
						accessUrl("http://"
								+ hosts[i]
								+ "/blog/servlet/cache?act=topicClearCache&status="
								+ status);
						System.out.println(hosts[i]);
					}
				}

			}
		} catch (Exception e) {
			LoggerUtil.error("Erro ao efetuar limpeza de cache", e);
		}
	}

	public void remoteTopicMessageClearCache(int status, String topic) {
		try {
			topicMessageClearCache(status, topic);

			if (BlogManager.getInstance().getProperties().get(
					Constants.HOSTS_PROPERTY) != null) {
				String[] hosts = ((String) BlogManager.getInstance()
						.getProperties().get(Constants.HOSTS_PROPERTY))
						.split(";");

				for (int i = 0; i < hosts.length; i++) {
					if (!hosts[i].equals((String) BlogManager.getInstance()
							.getProperties().get(Constants.LOCALHOST_PROPERTY))) {
						accessUrl("http://"
								+ hosts[i]
								+ "/blog/servlet/cache?act=topicMessageClearCache&status="
								+ status + "&topic=" + topic);
						System.out.println(hosts[i]);
					}
				}

			}
		} catch (Exception e) {
			LoggerUtil.error("Erro ao efetuar limpeza de cache", e);
		}
	}

	public void remoteHistoryClearCache(int blogId) {
		try {

			historyClearCache(blogId);
			if (BlogManager.getInstance().getProperties().get(
					Constants.HOSTS_PROPERTY) != null) {
				String[] hosts = ((String) BlogManager.getInstance()
						.getProperties().get(Constants.HOSTS_PROPERTY))
						.split(";");

				for (int i = 0; i < hosts.length; i++) {
					if (!hosts[i].equals((String) BlogManager.getInstance()
							.getProperties().get(Constants.LOCALHOST_PROPERTY))) {
						accessUrl("http://"
								+ hosts[i]
								+ "/blog/servlet/cache?act=historyClearCache&blogId="
								+ blogId);
						System.out.println(hosts[i]);
					}
				}

			}
		} catch (Exception e) {
			LoggerUtil.error("Erro ao efetuar limpeza de cache", e);
		}

	}

	public void remoteRankingClearCache() {
		try {
			rankingClearCache();
			if (BlogManager.getInstance().getProperties().get(
					Constants.HOSTS_PROPERTY) != null) {
				String[] hosts = ((String) BlogManager.getInstance()
						.getProperties().get(Constants.HOSTS_PROPERTY))
						.split(";");

				for (int i = 0; i < hosts.length; i++) {
					if (!hosts[i].equals((String) BlogManager.getInstance()
							.getProperties().get(Constants.LOCALHOST_PROPERTY))) {
						accessUrl("http://" + hosts[i]
								+ "/blog/servlet/cache?act=rankingClearCache");
						System.out.println(hosts[i]);
					}
				}

			}
		} catch (Exception e) {
			LoggerUtil.error("Erro ao efetuar limpeza de cache", e);
		}
	}

	public void remotePostsClearCache(int blogId) {
		try {
			postsClearCache(blogId);
			if (BlogManager.getInstance().getProperties().get(
					Constants.HOSTS_PROPERTY) != null) {
				String[] hosts = ((String) BlogManager.getInstance()
						.getProperties().get(Constants.HOSTS_PROPERTY))
						.split(";");

				for (int i = 0; i < hosts.length; i++) {
					if (!hosts[i].equals((String) BlogManager.getInstance()
							.getProperties().get(Constants.LOCALHOST_PROPERTY))) {
						accessUrl("http://"
								+ hosts[i]
								+ "/blog/servlet/cache?act=postsClearCache&blogId="
								+ blogId);
						System.out.println(hosts[i]);
					}
				}

			}
		} catch (Exception e) {
			LoggerUtil.error("Erro ao efetuar limpeza de cache", e);
		}
	}

	private void accessUrl(String action_url) throws Exception {
		URL url = null;
		HttpURLConnection conection = null;

		url = new URL(action_url);
		conection = (HttpURLConnection) url.openConnection();
		conection.connect();

		int httpResponseCode = conection.getResponseCode();

	}

	/**
	 * @param c
	 */
	public void pushComments(Comment c) {

		try {

			Set set = cache.keySet();
			Iterator iterator = set.iterator();
			Post post = null;
			String key = "";
			Collection comments = null;
			Collection newComments = new ArrayList();
			
			//Só coloca o comentário na lista se seu status for > 0 (Post NÃO MODERADO)
			if(c.getStatus() != 0){
				newComments.add(c);
			}
			while (iterator.hasNext()) {
				key = (String) iterator.next();

				if (key.startsWith("load-" + c.getPost().getId() + "-1")) {
					post = (Post) cache.get(key);
					break;
				}
			}

			comments = post.getComments();
			for (Iterator iter = comments.iterator(); iter.hasNext();) {
				Comment comment = (Comment) iter.next();
				newComments.add(comment);
			}
			post.setComments(newComments);

			synchronized (cache) {

				cache.put(key, post);
			}
			
			
			
			
		} catch (Exception e) {

			LoggerUtil.error("erro ao adicionar comentario em cache", e);

		}
	}

}
