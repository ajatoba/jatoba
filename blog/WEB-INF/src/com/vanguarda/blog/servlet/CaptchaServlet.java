package com.vanguarda.blog.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CaptchaServlet extends javax.servlet.http.HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse 

                         response) throws ServletException, IOException {

          ServletOutputStream out = response.getOutputStream();

          ByteArrayOutputStream jpegStream = new ByteArrayOutputStream();

          try {

                /*A imagem ser� gerada com base no ID da sess�o*/ 

                String jsid = request.getSession().getId();

                /*Gera a imagem*/

                BufferedImage challenge = Captcha.getInstance().getService().getImageChallengeForID(jsid);

                /*Codifica a imagem no formato JPEG*/

                JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegStream);

                jpegEncoder.encode(challenge);

          } catch (Exception e) {

             response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

             return;

          }

          /*Transforma a imagem em um array de bytes*/

          byte[] jpegBytes = jpegStream.toByteArray();

          /*Modifica os cabe�alhos http

* para que a imagem n�o seja armazenada em cache

*/

          response.setHeader("Cache-Control", "no-store");

          response.setHeader("Pragma", "no-cache");

          response.setDateHeader("Expires", 0);

          response.setContentType("image/jpeg");

          /*Envia a imagem para o cliente*/

                out.write(jpegBytes);

                out.flush();

                out.close();

    }     

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

          doGet(request, response);

    }               

}

