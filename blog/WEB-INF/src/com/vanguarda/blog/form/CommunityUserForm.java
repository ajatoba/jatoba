package com.vanguarda.blog.form;

import org.apache.struts.action.ActionForm;

public class CommunityUserForm extends ActionForm{

	private int communityId;
	private int communityUserId;
	
	public int getCommunityId() {
		return communityId;
	}
	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}
	public int getCommunityUserId() {
		return communityUserId;
	}
	public void setCommunityUserId(int communityUserId) {
		this.communityUserId = communityUserId;
	}
	
	
	
}
