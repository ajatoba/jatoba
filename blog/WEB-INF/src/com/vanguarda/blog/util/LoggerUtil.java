package com.vanguarda.blog.util;

import org.apache.log4j.Logger;


/**
 * LoggerUtil
 */
public class LoggerUtil
{
	private static final String LOGGER_NAME = "com.vanguarda";
	

	public static Logger getLogger(String className)
	{
		return Logger.getLogger(className); 
	}

		
	public static void debug(String msg, String[] params, Object[] values)
	{
		StringBuffer buffer = new StringBuffer();

		String[] strs = getMethodName();
		buffer.append("method=[" + strs[1] + "] ");
		buffer.append("msisdn=[" + msg + "]");

		for (int i = 0; i < params.length; i++)
		{
			buffer.append(' ');
			buffer.append(params[i]);
			buffer.append('=');
			buffer.append(values[i]);
		}
		LoggerUtil.getLogger(strs[0]).debug(buffer.toString());
	}

	public static void debug(String msg)
	{
		String[] strs = getMethodName();		
		LoggerUtil.getLogger(strs[0]).debug(msg);
	}

		
	public static void warn(String msg)
	{
				
		String[] strs = getMethodName();
		
		LoggerUtil.getLogger(strs[0]).warn(msg);
	}
	
	public static void info(String msisdn, String msg)
	{
			
		String[] strs = getMethodName();		
		LoggerUtil.getLogger(strs[0]).info(msg);
	}

	public static void error(String msg, Throwable exc)
	{
		String[] strs = getMethodName();		
		LoggerUtil.getLogger(LOGGER_NAME).error(msg, exc);
	}

	
	private static String[] getMethodName()
	{
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		String method = stack[2].getMethodName();
		String c = stack[2].getClassName();

		String[] strs = new String[2];
		strs[0] = c;
		strs[1] = method;

		return strs;
	}
}