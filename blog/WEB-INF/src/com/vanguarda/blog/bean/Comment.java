// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Comment.java

package com.vanguarda.blog.bean;

import java.util.Date;

// Referenced classes of package com.vanguarda.blog.bean:
//            Post, User

public class Comment
{

    public Comment()
    {
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

    public Post getPost()
    {
        return post;
    }

    public void setPost(Post post)
    {
        this.post = post;
    }

    public boolean isCensured()
    {
        return censured;
    }

    public void setCensured(boolean censured)
    {
        this.censured = censured;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
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
    private Post post;
    private boolean censured;
    private User user;
    
    
    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("id=");
		buffer.append(id+";");
		buffer.append("title=");
		buffer.append(title+";");
		buffer.append("insertDate=");
		buffer.append(insertDate+";");
		buffer.append("content=");
		buffer.append(content+";");
		buffer.append("status=");
		buffer.append(status+";");
		buffer.append("commentatorName");
		buffer.append(commentatorName+";");
		buffer.append("commentatorEmail=");
		buffer.append(commentatorEmail+";");
		buffer.append("commentatorHomepage=");
		buffer.append(commentatorHomepage+";");
		buffer.append("commentatorRemoteAddr=");
		buffer.append(commentatorRemoteAddr+";");
		buffer.append("censured=");
		buffer.append(censured+";");
	
		return buffer.toString();
	}
}
