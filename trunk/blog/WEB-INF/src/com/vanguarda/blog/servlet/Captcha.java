package com.vanguarda.blog.servlet;

import java.awt.Color;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.TwistedAndShearedRandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

public class Captcha {

    private ImageCaptchaService service;



    private static Captcha instance = new Captcha();

    

/*A classe deve ser um Singleton*/

    private Captcha(){

          DefaultManageableImageCaptchaService serv = 

                                new DefaultManageableImageCaptchaService();

          serv.setCaptchaEngine(new EngineNumeros());

          

          service = serv;

    }



    public static Captcha getInstance(){

          return instance;

    }

    

    public ImageCaptchaService getService(){

          return service;

    }



    /*Especializando um Engine para gerar apenas n�meros*/ 

    class EngineNumeros extends ListImageCaptchaEngine {

          protected void buildInitialFactories() {

                   /*Cria um um TextPaster, o tamanho m�nimo � de 5 caracteres

                    * Maximo de 8, e a cor do texto ser� branca

                    */

                   TextPaster textPaster = 

                              new RandomTextPaster(new Integer(3), new Integer(3), Color.white);

                  /*Um gerador de background, a imagem ter� 100 x 50 pixels

                   */

                   BackgroundGenerator backgroundGenerator = 

                                       new FunkyBackgroundGenerator(new Integer(100), new Integer(38));

                   /* Um gerador de fonte, � respons�vel por distorcer o

                    * texto, o tamanho m�nimo da fonte � 25 e o Maximo 30

    */

                   FontGenerator fontGenerator = 

                            new TwistedAndShearedRandomFontGenerator(new Integer(29), new Integer(30));

                   /* O objeto respons�vel por juntar o background, a fonte e 

                    * o texto para gerar a imagem

                    */

                   WordToImage wordToImage = 

                                   new ComposedWordToImage(fontGenerator, 

                                   backgroundGenerator, textPaster);

                   /* Adiciona o Factory RandomWordGenerator recebe os 

                    * caracteres v�lidos, no caso queremos apenas n�meros

        */

                   this.addFactory(new GimpyFactory(new RandomWordGenerator

                                              ("0123456789"), wordToImage));

          }

     }    

}


