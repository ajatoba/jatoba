package com.vanguarda.blog.servlet;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import com.sun.image.codec.jpeg.*;
import com.vanguarda.blog.util.RandomTool;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;


public class RndImgEngine2 extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Random rnd;
    private final int BCKGRND_STYLE_DARK = 0; //fundo escuro
    private final int BCKGRND_STYLE_LIGHT = 1; //fundo claro

    private final int DARK_INI_RGB = 0;
    private final int DARK_END_RGB = 100;
    
    private final int LIGHT_INI_RGB = 101;
    private final int LIGHT_END_RGB = 255;
    
    private int sort(int min, int max){
        return (rnd.nextInt(max - min) + min);
    }
    private int sort(int max){
        return sort(0, max);
    }
    private Color sortGradientColor(int style){
        int min = 0;
        int max = 0;
        if (style == BCKGRND_STYLE_DARK){
            min = DARK_INI_RGB;
            max = DARK_END_RGB;
        }else{
            min = LIGHT_INI_RGB;
            max = LIGHT_END_RGB;
        }
        return new Color(sort(min, max), sort(min, max), sort(min, max));
        
    }
    private Color sortFontColor(int style){
        int min = 0;
        int max = 0;
        if (style == BCKGRND_STYLE_DARK){
            //Fundo Escuro, Fonte Clara
            min = LIGHT_INI_RGB;
            max = LIGHT_END_RGB;
        }else{
            //Fundo Claro, Fonte Escura
            min = DARK_INI_RGB;
            max = DARK_END_RGB;
        }
        return new Color(sort(min, max), sort(min, max), sort(min, max));
    }    
    private int sortStyle(){
        if (sort(10) < 5){
            return BCKGRND_STYLE_DARK;
        }else{
            return BCKGRND_STYLE_LIGHT;
        }
    	
    }
    private int sortFontStyle(){
        if (sort(10) < 5){
            return Font.BOLD;
        }else{
            return (Font.BOLD | Font.ITALIC);
        }
    }
    private String sortFontName(){
        switch (sort(6)){
            case 0:
            case 5:
                return "Monospaced";
            case 1:                
            case 4:
                return "SansSerif";
            case 2:
            case 3:
                return "Serif";
        }
        return "Monospaced";
    }
    private void drawCaptcha(HttpServletResponse response, String str) throws ServletException, IOException{
        ServletOutputStream out = response.getOutputStream();
        
               
        // Sort the BackGround Style (Dark/Light)
        int bckgrndStyle = sortStyle();
        
        int width  = 140;   // width of image
        int height = 40;   // height of image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);    // Image object to paint with
        Graphics2D g = image.createGraphics();    // Get graphics context
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);     // Anti-alias the painting
        //Frist Vertical Half
        Color gpIniColor = sortGradientColor(bckgrndStyle);
        Color gpFimColor = sortGradientColor(bckgrndStyle);
        GradientPaint gp = new GradientPaint(0, 0, gpIniColor, width/2, height/2, gpFimColor);  // Create a gradient fill from lightgray to black
        g.setPaint(gp); // Use gradient fill to draw text
        //Last Vertical Half
        gpIniColor = gpFimColor;
        gpFimColor = sortGradientColor(bckgrndStyle);
        gp = new GradientPaint(width/2, height/2, gpIniColor, width, height, gpFimColor);  // Create a gradient fill from lightgray to black
        
        //g.setPaint(gp); // Use gradient fill to draw text
        
        Rectangle2D rectangle = new Rectangle2D.Double(0, 0, width, height);
        g.fill(rectangle);
        
        // *** Draw the caracters ***
        int xMin = 0;
        int xMax = 0;
        int yMin = 17;
        int yMax = 35;
        for (int j=0; j<6; j++){ /**XXXXXXXXXXXXXXXXXXXXXXXX**/
            switch (j){
                case 0:
                        xMin = 5;
                        xMax = 15;
                        break;
                case 1:
                        xMin = 25;
                        xMax = 40;
                        break;
                case 2:
                        xMin = 50;
                        xMax = 65;
                        break;
                case 3:
	                    xMin = 70;
	                    xMax = 90;
	                    break;
                    
                case 4:
	                    xMin = 100;
	                    xMax = 115;
	                    break;
                
                case 5:
	                    xMin = 120;
	                    xMax = 130;
	                    break;
            }

            g.setColor(sortFontColor(bckgrndStyle)); // font color
            g.setFont(new Font(sortFontName(), sortFontStyle(), sort(18,24)));  // Set font name, style and size
            
            try{
                g.drawString(str.substring(j,j+1), sort(xMin,xMax), sort(yMin,yMax));  
            }catch(Exception e){
                g.drawString("0", sort(xMin,xMax), sort(yMin,yMax));  
            }
        }

        /******************************/
        int imgW = width;
        int imgH = height;
        
        int pix[] = new int[imgH * imgW];
        int k = 0;
        for (int j1 = 0; j1 < imgW; j1++) {
        	for (int k1 = 0; k1 < imgH; k1++) {
        		pix[k] = image.getRGB(j1, k1);
        		k++;
        	}
        }

        
        double distance = ranInt(imgW / 4, imgW / 3);
//      put the distortion in the (dead) middle
     int wMid = image.getWidth() / 2;
     int hMid = image.getHeight() / 2;
//     again iterate over all pixels..
     for (int x = 0; x < image.getWidth(); x++) {
    	 for (int y = 0; y < image.getHeight(); y++) {
      
		     int relX = x - wMid;
		     int relY = y - hMid;
		      
		     double d1 = Math.sqrt(relX * relX + relY * relY);
		     if (d1 < distance) {
		      
			     int j2 =wMid + (int) (((fishEyeFormula(d1 / distance) * distance) / d1)* (double) (x - wMid));
			     int k2 =hMid + (int) (((fishEyeFormula(d1 / distance) * distance) / d1)* (double) (y - hMid));
			     image.setRGB(x, y, pix[j2 * imgH + k2]);
		     }
    	 }
     }

        
        
        
        /*****************************/
        
        
       // *** Draw the horizontal lines ***
       int xIni = 0;
       int yIni = 0;
       int xFim = 0;
       int yFim = 0;
       
       // *** Draw the curve lines ***
       double x1 = 0;
       double y1= 0;
	   double ctrlx1= 0; 
	   double ctrly1= 0;
	   double ctrlx2= 0; 
	   double ctrly2= 0;
	   double x2= 0; 
	   double y2 = 0;
	   Shape s = null;
       for (int j=0; j<1; j++){
            xIni = 0;
            yIni = sort(0,height);
            xFim = width;
            yFim = sort(0,height);
            g.setColor(sortFontColor(bckgrndStyle)); // font color
            g.drawLine(xIni, yIni, xFim, yFim);  
            g.drawOval(xIni, yIni, xFim, yFim);  
            
           x1 	  = 0;
           y1     = sort(0,height);
     	   ctrlx1 = sort(0,width); 
     	   ctrly1 = sort(0,height);
     	   ctrlx2 = sort(0,width); 
     	   ctrly2 = sort(0,height);
     	   x2     = width; 
     	   y2     = sort(0,height);
     	   
     	   
     	  s =  new CubicCurve2D.Double(x1,y1,ctrlx1,ctrly1,ctrlx2,ctrly2,x2,y2);
          g.draw(s);
            
       }
               
       
        // Send image to the web browser
        response.setContentType("image/jpeg");  // Assign correct content-type
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  // Create JPEG encoder
        JPEGEncodeParam jpegParams = encoder.getDefaultJPEGEncodeParam(image);
        jpegParams.setQuality(0.5f, false);  // Set quality for JPEG
        encoder.setJPEGEncodeParam(jpegParams);
        encoder.encode(image);  // Encode image to JPEG and send to browser        
    }
    
    public void init() throws ServletException{
        rnd = new Random();
    }
    
    private int ranInt(int i, int j) {
    	double d = Math.random();
    	return (int) ((double) i + (double) ((j - i) + 1) * d);
    	}

    private double fishEyeFormula(double s) {
//    	 implementation of:
//    	 g(s) = - (3/4)s3 + (3/2)s2 + (1/4)s, with s from 0 to 1.
    	if (s < 0.0D) return 0.0D;
    	if (s > 1.0D) return s;
    	else
    	return -0.75D * s * s * s + 1.5D * s * s + 0.25D * s;
    	}

    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getParameter("rndimg");
        String strToPrint = "";
        if (str == null || str.trim().equals("")){
            strToPrint = sort(100,999) + "";
        }else{
            //If The password has a space, it means that a '+' is passed as parameter 
            //The request treat the '+' as a ' '
            str = str.replace(' ','+');
            strToPrint = RandomTool.deCrypt(str);
            
            if (strToPrint.equals("")){
                strToPrint = "ERR";
            }
        }
        
        drawCaptcha(response, strToPrint);
    }
}