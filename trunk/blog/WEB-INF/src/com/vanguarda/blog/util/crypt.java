
package com.vanguarda.blog.util; 


public class crypt
{

   private final int  DELTA    = 0x9E3779B9;
   private final int  NROUNDS  = 32;
   private final int  KEY0     = 0x74479410;
   private final int  KEY1     = 0x84632458;
   private final int  KEY2     = 0x5df44abe;
   private final int  KEY3     = 0x389ab47d;
   private final String szBase64 =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+-";
   


   private int b64(char c)
      {
      int i;

      for (i=0; i < szBase64.length(); i++)
         if (szBase64.charAt(i)==c)
            return i;

      return 0;
      }

   private void code (int [] v)
      {
      int y=v[0], z=v[1], sum=0;
      int n;

      for (n=0; n<NROUNDS; n++)
         {
         sum += DELTA;
         y   += (z<<4)+KEY0^z + sum^(z>>5) +KEY1;
         z   += (y<<4)+KEY2^y + sum^(y>>5) +KEY3;
         }
      v[0]=y;
      v[1]=z;
      }

   private void decode (int [] v)
      {
      int y=v[0], z=v[1], sum=0;
      int n;

      sum = DELTA * NROUNDS;
      for (n=0; n<NROUNDS; n++)
         {
         z   -= (y<<4)+KEY2^y + sum^(y>>5) +KEY3;
         y   -= (z<<4)+KEY0^z + sum^(z>>5) +KEY1;
         sum -= DELTA;
         }
      v[0]=y;
      v[1]=z;
      }

   /* ********************************************
         TEAencode

         Devolve a string de entrada criptografada
         com TEA, saida alocada com malloc()

         A string de saida e' maior que a de entrada

         O tamanho da string de saida e' dado pela
         expressao:

            (((((LEN+8)/8)*8)+2)/3)*4

         onde LEN é o tamanho da string de entrada

         Ex: se a string de entrada tem 50 caracteres,
             a string de saida tera' 76 caracteres

         NAO ESQUECA DE USAR free() NA STRING CRIPTOGRAFADA
         QUANDO NAO PRECISAR MAIS DELA!!!

      ******************************************** */

   public String TEAencode (String input)
      {
      StringBuffer saida;
      char [] strcrypt;
      int   tamcrypt, tamentrada;
      int   i;

      /* Se a string de entrada é nula, a saida é nula.
         Isso e' uma otimizacao: diminui um pouco a seguranca,
         porque se consegue saber quais sao as strings vazias,
         mas economiza bastante espaco no banco de dados */

      if ((tamentrada=input.length()) < 1)
         return new StringBuffer("").toString();

      /* Aloca buffer para criptografia (multiplo de 8 bytes) */
      tamcrypt = ((tamentrada+8)/8)*8;
      strcrypt = new char[tamcrypt+1];
      for (i=0;i<tamcrypt;i++)
         strcrypt[i]=0;
      input.getChars(0, tamentrada, strcrypt, 0);

      int [] v = new int[2];
      int o=0;
      for (i=0; i<tamcrypt; i+=8)
         {
         v[0]= strcrypt[i+3]<<24 | strcrypt[i+2]<<16 | strcrypt[i+1]<<8 | strcrypt[i+0];
         v[1]= strcrypt[i+7]<<24 | strcrypt[i+6]<<16 | strcrypt[i+5]<<8 | strcrypt[i+4];
         code( v );
         strcrypt[o++] = (char) (v[0] & 0xFF);
         strcrypt[o++] = (char) ((v[0] >>  8) & 0xFF);
         strcrypt[o++] = (char) ((v[0] >> 16) & 0xFF);
         strcrypt[o++] = (char) ((v[0] >> 24) & 0xFF);
         strcrypt[o++] = (char) (v[1] & 0xFF);
         strcrypt[o++] = (char) ((v[1] >>  8) & 0xFF);
         strcrypt[o++] = (char) ((v[1] >> 16) & 0xFF);
         strcrypt[o++] = (char) ((v[1] >> 24) & 0xFF);
         }


      /* Agora codifica em BASE64 */
      saida = new StringBuffer("");
      i=0;
      while (i<o)
         {
         char b1=0, b2=0, b3=0;
         long l;

         b1 = strcrypt[i++];
         if (i<tamcrypt)
            {
            b2 = strcrypt[i++];
            if (i<tamcrypt)
               b3 = strcrypt[i++];
            }

         /* monta um long com 24 bits */
         l = (b1 << 16) | (b2 << 8) | b3;

         /* parte em 4 caracteres de 6 bits */
         saida.append(szBase64.charAt( (int)(l & 0xFC0000) >> 18 ));
         saida.append(szBase64.charAt( (int)(l & 0x03F000) >> 12 ));
         saida.append(szBase64.charAt( (int)(l & 0x000FC0) >>  6 ));
         saida.append(szBase64.charAt( (int)(l & 0x00003F)       ));
         }

      return saida.toString();
      }

   /* ********************************************
         TEAdecode

         Devolve a string de entrada decriptografada
         com TEA, saida alocada com malloc()

         A string de saida e' menor que a de entrada

      ******************************************** */

   public String TEAdecode (String input)
      {
      char [] strcrypt;
      int   tam,i,o;

      /* Se a string de entrada é nula, ou tamanho nao multiplo de 4, a saida é nula */
      if ((tam=input.length()) < 1 || (tam%4!=0))
         return new StringBuffer("").toString();

      /* Primeiro step: converte de BASE64 para binario */
      strcrypt= new char [tam+8];
      for (i=0;i<tam+8;i++)
         strcrypt[i]=0;

      o=0;
      for (i=0; i<tam; i+=4)
         {
         long l = (b64(input.charAt(i)) << 18) | (b64(input.charAt(i+1)) << 12) | (b64(input.charAt(i+2)) << 6) | b64(input.charAt(i+3));
         strcrypt[o++] = (char) ((l >> 16) & 0x000000FF);
         strcrypt[o++] = (char) ((l >> 8)  & 0x000000FF);
         strcrypt[o++] = (char) (l         & 0x000000FF);
         }

      /* Segundo step: decodifica */
      int  [] v = new int[2];
      StringBuffer b = new StringBuffer();
      for (i=0; i<o; i+=8)
         {
         v[0]= strcrypt[i+3]<<24 | strcrypt[i+2]<<16 | strcrypt[i+1]<<8 | strcrypt[i+0];
         v[1]= strcrypt[i+7]<<24 | strcrypt[i+6]<<16 | strcrypt[i+5]<<8 | strcrypt[i+4];
         decode( v );
         b.append( (char) (v[0] & 0xFF) );
         b.append( (char) ((v[0] >>  8) & 0xFF) );
         b.append( (char) ((v[0] >> 16) & 0xFF) );
         b.append( (char) ((v[0] >> 24) & 0xFF) );
         b.append( (char) (v[1] & 0xFF) );
         b.append( (char) ((v[1] >>  8) & 0xFF) );
         b.append( (char) ((v[1] >> 16) & 0xFF) );
         b.append( (char) ((v[1] >> 24) & 0xFF) );
         }
         char[] cc = { '\u0000' };
         String zeroBinario = new String(cc);
         String retorno = b.toString();
         retorno = retorno.substring(0, retorno.indexOf(zeroBinario));
      return retorno;
      }
/*
    public static void main(String args[])
      {
      crypt crypt = new crypt();
      String coded, decoded;

      coded   = crypt.TEAencode("Ricardag é um camarada bacana, e conseguiu fazer esta coisa funcionar!!!");
      decoded = crypt.TEAdecode(coded);
      System.out.println(decoded);
      }
*/
}

