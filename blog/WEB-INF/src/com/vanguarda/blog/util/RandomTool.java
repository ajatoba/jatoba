
package com.vanguarda.blog.util; 

import java.util.Random;
import java.lang.StringBuffer;

public class RandomTool{
    public static synchronized int sortInt(int min, int max){
        Random rnd = new Random();
        return (rnd.nextInt(max - min) + min);
    }    
    public static synchronized char sortChar(int minAscii, int maxAscii){
        return ( (char)sortInt(minAscii, maxAscii) );
    }    
    public static synchronized String sortAlphaNumeric(int numChars){
        char letra = 0;
        char letraAux = 1;
        StringBuffer saida = new StringBuffer();
        for (int i = 0; i< numChars; i ++){
            do{
                letra = sortChar(48,122);
            }while( (letra > 57 && letra < 65) || (letra > 90 && letra < 97) || letraAux == letra);
            saida.append(letra);
            letraAux = letra;
        }
        return saida.toString();
    }
    public static synchronized String create(){
        //rule: Number between 100 and 999
        int min = 100;
        int max = 999;
        //return sortInt(min, max) + "";
        //return sortChar(min, max) + "";
        return  sortAlphaNumeric(min);
    }
    public static synchronized String toCrypt(String str){
        crypt c= new crypt();
        String M4uCrypted = c.TEAencode(str);
        
        //Misturando mais a senha criptografada.
        //Coloco um "lixo" no ínicio da String
        return sortAlphaNumeric(3) + M4uCrypted ;/**XXXXXXXXXXXXXXXXXXXXXXXX**/
        
    }
    
    public static synchronized String createCrypted(){
        return (toCrypt(create()));
    }
    
    public static synchronized String deCrypt(String cryptedStr){
        crypt c= new crypt();
        String decrypted = "";
        try{
            decrypted = c.TEAdecode(cryptedStr.substring(3,cryptedStr.length()));/**XXXXXXXXXXXXXXXXXXXXXXXX**/
        }catch(Exception e){
            //cryptedStr is not crypted
            decrypted = "";
        }
        return decrypted;
    }
    public static synchronized boolean compare(String str, String cryptedStr){
        //compare whether string (str) is equal to a crypted string (cryptedStr)
        String compareStr = (deCrypt(cryptedStr)).substring(0,6);        
        if (cryptedStr.equals("")){
            return false;
        }else{
            return (compareStr.toLowerCase()).equals(str.toLowerCase());
        }
    }
}