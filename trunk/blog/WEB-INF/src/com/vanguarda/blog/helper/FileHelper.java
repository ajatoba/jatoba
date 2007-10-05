// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileHelper.java

package com.vanguarda.blog.helper;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

public class FileHelper
{

    public FileHelper()
    {
    }

    public static void writeControlWord(String path, String fileName, String content)
        throws Exception
    {
        File file = new File(path);
        if(!file.exists())
            file.mkdir();
        File f = new File(file, fileName);
        FileOutputStream out = new FileOutputStream(f);
        out.write(content.getBytes());
        out.close();
    }

    public static String readControlWord(String path, String fileName)
        throws Exception
    {
    	
    	File file = new File(path);
        File f = null;
        
    	FileInputStream in = null;
    	ByteArrayOutputStream baos = null;
    	try
    	{
    		file = new File(path);
            f = null;
            if(file.exists())
            {
                f = new File(file, fileName);
                if(!f.exists())
                    f.createNewFile();
                
                in = new FileInputStream(f);
                baos = new ByteArrayOutputStream();
                int bytesRead = 0;
                byte buffer[] = new byte[1024];
                
                while((bytesRead = in.read(buffer, 0, buffer.length)) != -1) 
                    baos.write(buffer, 0, bytesRead);
                return baos.toString();
            } else
            {
                return "";
            }
    	}finally
    	{    		
    		if (baos != null) baos.close();  
    		if (in != null) in.close();
    	}
    	
    }
    
     public static void createStaticHome(String blogPath,StringWriter writer) throws Exception
     {
     	FileOutputStream os = null;
		PrintStream ps = null;
		DataOutputStream ods = null;					
		try {
			
			File f = new File(blogPath);
			
			if(!f.exists())
				f.mkdir();
			
			os = new FileOutputStream(blogPath + "/index.html");		
			
			ps = new PrintStream(os);
			ods = new DataOutputStream(os);
			ods.flush();
			ods.writeBytes(writer.toString());				
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{				
			if(writer != null) writer.close();				
			if(os != null) os.close();
			if(ps != null) ps.close();
			if(ods != null) ods.close();
		}
     }
    

//    public static void writerStaticFile(String path, String fileName, String action_url)
//        throws IOException,Exception
//    {
//    	URL url = null;
//        URLConnection conection = null;
//        InputStream stream =null;
//        ByteArrayOutputStream baos = null;
//        FileOutputStream out = null;
//    	
//    	try {
//    		
//    	    url = new URL(action_url);
//            conection = url.openConnection();
//            stream = conection.getInputStream();
//            baos = new ByteArrayOutputStream();
//            int bytesRead = 0;
//            byte buffer[] = new byte[1024];
//            while((bytesRead = stream.read(buffer, 0, buffer.length)) != -1) 
//                baos.write(buffer, 0, bytesRead);
//            stream.close();
//            File file = new File(path);
//            if(!file.exists())
//                file.mkdir();
//            File f = new File(file, fileName);
//            if(!f.exists())
//                f.createNewFile();
//            out = new FileOutputStream(f);
//            out.write(baos.toByteArray());
//            
//		} catch (Exception e) {
//			throw e;
//		}finally
//		{			
//			if(baos != null)  baos.close();
//			if(out != null) out.close();
//			if(stream != null) stream.close();			
//		}
//        
//    }
//    
//    public static void writeXML(String xmlFile, Object obj) throws Exception{
//    	
//    	 FileOutputStream out = null;
//         BufferedOutputStream buff = null;
//         XMLEncoder xEnc = null;
//         
//         try {
//        	 out = new FileOutputStream(xmlFile);
//             buff = new BufferedOutputStream(out);
//             xEnc = new XMLEncoder(buff);
//             
//             xEnc.writeObject(obj);
//		} catch (Exception e) {
//			throw e;
//		}finally{
//			if(xEnc != null) xEnc.close();	
//			if(buff != null) buff.close();
//			if(out != null) out.close();
//		}
//    	
//    }
}
