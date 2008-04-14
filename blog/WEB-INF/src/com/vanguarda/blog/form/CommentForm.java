// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommentForm.java

package com.vanguarda.blog.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class CommentForm extends ActionForm
{

    public CommentForm()
    {
    }

    
    
	/**
	 * @return Returns the imageword.
	 */
	public String getImageword() {
		return imageword;
	}
	/**
	 * @param imageword The imageword to set.
	 */
	public void setImageword(String imageword) {
		this.imageword = imageword;
	}
    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getCommentatorEmail()
    {
        return commentatorEmail;
    }

    public void setCommentatorEmail(String commentatorEmail)
    {
        this.commentatorEmail = commentatorEmail;
    }

    public String getCommentatorHomepage()
    {
        return commentatorHomepage;
    }

    public void setCommentatorHomepage(String commentatorHomepage)
    {
        this.commentatorHomepage = commentatorHomepage;
    }

    public String getCommentatorName()
    {
        return commentatorName;
    }

    public void setCommentatorName(String commentatorName)
    {
        this.commentatorName = commentatorName;
    }

    public String getCommentatorRemoteAddr()
    {
        return commentatorRemoteAddr;
    }

    public void setCommentatorRemoteAddr(String commentatorRemoteAddr)
    {
        this.commentatorRemoteAddr = commentatorRemoteAddr;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getInsertDate()
    {
        return insertDate;
    }

    public void setInsertDate(Date insertDate)
    {
        this.insertDate = insertDate;
    }

    public int getPostId()
    {
        return postId;
    }

    public void setPostId(int postId)
    {
        this.postId = postId;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getCountComments()
    {
        return countComments;
    }

    public void setCountComments(int countComments)
    {
        this.countComments = countComments;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public boolean isCensured()
    {
        return censured;
    }
  
    public void setCensured(boolean censured)
    {
        this.censured = censured;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    private int id;
    private String title;
    private Date insertDate;
    private String content;
    private int status;
    private String commentatorName;
    private String commentatorEmail;
    private String commentatorHomepage;
    private String commentatorRemoteAddr;
    private int postId;
    private int countComments;
    private String from;
    private boolean censured;
    private String path;
    private int userId;    
    private int blogId;
    private String imageword;
    private String captcha;
    private String answer;
    
    
    private int commentatorGroup;
    	
    public int getCommentatorGroup() {
		return commentatorGroup;
	}

	public void setCommentatorGroup(int commentatorGroup) {
		this.commentatorGroup = commentatorGroup;
	}



	public int getBlogId() {
		return blogId;
	}



	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}



	public String getCaptcha() {
		return captcha;
	}



	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}



	public String getAnswer() {
		return answer;
	}



	public void setAnswer(String answer) {
		this.answer = answer;
	}

	
	
	
    
}
