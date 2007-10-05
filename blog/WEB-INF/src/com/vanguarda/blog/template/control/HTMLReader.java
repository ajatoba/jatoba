package com.vanguarda.blog.template.control;

import java.io.FileInputStream;
import java.io.*;

public class HTMLReader {

	public static String readHTML(String fileLocation) throws Exception{
		String html = "";
		String line = "";
		
		FileReader myFile = null;
		BufferedReader buff =null;
		
		try {
			  myFile = new FileReader(fileLocation);
			  buff = new BufferedReader(myFile);
			  
			  boolean eof = false;
			  
			  while(!eof){				  
				  line = buff.readLine();
				  if(line != null ){
					  html += line;
				  }else{
					  eof = true;
				  }
			  }
			  
			} catch (IOException e) {
			  e.printStackTrace();
			} finally{ 
			  buff.close();
			  myFile.close();
			}
		
		return html;
	}

	
	public static void main(String[] x){
		
		try {
			String html = HTMLReader.readHTML("D:\\jatoba\\projetos\\Java\\bloglog\\pages\\post_template.html");
			
			System.out.println(html.replaceAll("<POST_TITLE>","funciona!"));
		} catch (Exception e) {
			
			System.out.println("ERRO" +e.getMessage());
			// TODO: handle exception
		}
		
		
	}
}
