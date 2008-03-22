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
    
    public static String isDenied(String comment, String works[])
    {
    	String result = null;
    	for(int i = 0; i < works.length; i++)
        {
    		if(comment.length() == works[i].length())
    		{
    			String w = works[i];
                if((comment.indexOf(w))> -1){
                	result =  w;
                	break;
                }
    		}
    		else if(comment.length() > works[i].length())
    		{
    			String[] w = {" "+works[i],works[i]+" "};
    			for(int j = 0;j< 2; j++)
    			{
    				
                    if((comment.indexOf(w[j]))> -1){
                    	result =  w[j];
                    	break;
                    }
    			}
    			
    		}
            
        }
    	return result;

    }
}
