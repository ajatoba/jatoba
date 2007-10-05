package com.vanguarda.blog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import java.io.*;

import com.vanguarda.blog.form.*;

/**
* @author Deepak Kumar
* @Web http://www.roseindia.net
* @Email roseindia_net@yahoo.com
*/

/**
 * Struts File Upload Action Form.
 *
*/
public class UploadAction extends DispatchAction
{
	  public ActionForward execute(
	    ActionMapping mapping,
	    ActionForm form,
	    HttpServletRequest request,
	    HttpServletResponse response) throws Exception{
	    UploadForm myForm = (UploadForm)form;

	    
	    FormFile myFile = myForm.getTheFile();
	    String contentType = myFile.getContentType();

	    String fileName    = myFile.getFileName();
	    
	    byte[] fileData    = myFile.getFileData();
	    
	    String filePath = "";//getServlet().getServletContext().getRealPath("/") +"upload";
	    
	    filePath = request.getRealPath("/blogs/content/communities/images/")+File.separator;//+blog.getPath();
		
	    
	    try {
		
	    	if(!fileName.equals("")){  
		        System.out.println("Server path:" +filePath);
		        //Create file
		        File fileToCreate = new File(filePath, fileName);
		        //If file does not exists create file                      
		        if(!fileToCreate.exists()){
		          FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
		          fileOutStream.write(myFile.getFileData());
		          fileOutStream.flush();
		          fileOutStream.close();
		        }
	    	}
	    	
		    request.setAttribute("fileName",fileName);
		    
		    return mapping.findForward("success");
		      
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("success");
		}
	    
	  }
	} 