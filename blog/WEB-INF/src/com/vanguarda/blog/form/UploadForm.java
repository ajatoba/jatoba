package com.vanguarda.blog.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;



/**
 * Form bean for Struts File Upload.
 *
*/
public class UploadForm extends ActionForm
{
  private FormFile theFile;

  /**
   * @return Returns the theFile.
   */
  public FormFile getTheFile() {
    return theFile;
  }
  /**
   * @param theFile The FormFile to set.
   */
  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }
}