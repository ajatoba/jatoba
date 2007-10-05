package com.vanguarda.blog.bean;

import java.util.*;

public class CommunityUser extends ForumUser {

	
	private Collection communities;

	public Collection getCommunities() {
		return communities;
	}

	public void setCommunities(Collection communities) {
		this.communities = communities;
	}
	
}
