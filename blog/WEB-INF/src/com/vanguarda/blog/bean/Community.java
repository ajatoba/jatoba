package com.vanguarda.blog.bean;

import java.util.*;

public class Community extends AbstractForum {

	private CommunityUser communityOwner;
	private Collection topics;
	private Blog blog;
	
	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public CommunityUser getCommunityOwner() {
		return communityOwner;
	}

	public void setCommunityOwner(CommunityUser communityOwner) {
		this.communityOwner = communityOwner;
	}

	public Collection getTopics() {
		return topics;
	}

	public void setTopics(Collection topics) {
		this.topics = topics;
	}
	
}
