// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PreparedCommentHelper.java

package com.vanguarda.blog.helper;


public class PreparedCommentHelper
{

    public PreparedCommentHelper()
    {
    }

    public static String getPreparedCommwnt(String comment, String works[])
    {
        for(int i = 0; i < works.length; i++)
        {
            String w = works[i];
            int number = w.length();
            StringBuffer buffer = new StringBuffer();
            for(int j = 0; j < number - 1; j++)
                buffer.append("*");

            w = w.substring(0, 1) + buffer.toString();
            comment = comment.toLowerCase().replaceAll(works[i].toLowerCase(), w);
        }

        return comment;
    }
}
