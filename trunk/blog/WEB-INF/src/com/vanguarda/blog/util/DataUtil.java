/*
* ------------------------------------------------------
* Classe: DataUtil.java
* Descriï¿½ï¿½o: Biblioteca de utilidades para manipulaï¿½ï¿½o e
*            comparaï¿½ï¿½o de datas. A biblioteca utiliza 
*            as classes GregorianCalendar, Date, 
*            Timestamp e Calendar.
* Autor: Marcello Tostes
* Email: marcellotostes@hotmail.com ou 
*        marcellotostes@globo.com
* Criaï¿½ï¿½o: 25/06/2002
* ï¿½ltima atualizaï¿½ï¿½o: 19/03/2003
* ------------------------------------------------------
*/

package com.vanguarda.blog.util; 

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Marcello Tostes
 */
public class DataUtil {
     
    private int dia;
    private int mes;
    private int ano;
    private int hra;
    private int min;
    private int seg;
    private int sem; //dia da semana
    
    public static final int DOMINGO = 1;
    public static final int SEGUNDA = 2;
    public static final int TERCA   = 3;
    public static final int QUARTA  = 4;
    public static final int QUINTA  = 5;
    public static final int SEXTA   = 6;
    public static final int SABADO  = 7;
    
    public static final int JAN     = 1;
    public static final int FEV     = 2;
    public static final int MAR     = 3;
    public static final int ABR     = 4;
    public static final int MAI     = 5;
    public static final int JUN     = 6;
    public static final int JUL     = 7;
    public static final int AGO     = 8;
    public static final int SET     = 9;
    public static final int OUT     = 10;
    public static final int NOV     = 11;
    public static final int DEZ     = 12;
    
    public static final int SEMANA_ATUAL      = 0;
    public static final int SEMANA_PASSADA    = -1;
    public static final int SEMANA_RETRASADA  = -2;
    
    /** Construtor padrï¿½o */
    public DataUtil() {
        GregorianCalendar c = new GregorianCalendar();
        this.dia = c.get(Calendar.DATE);
	this.mes = c.get(Calendar.MONTH) + 1;
	this.ano = c.get(Calendar.YEAR);
        this.hra = c.get(Calendar.HOUR_OF_DAY);
        this.min = c.get(Calendar.MINUTE);
        this.seg = c.get(Calendar.SECOND);
    }
   
    //Construtor com milesegundos
    public DataUtil(long l) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTimeInMillis(l);
        this.dia = c.get(Calendar.DATE);
	this.mes = c.get(Calendar.MONTH) + 1;
	this.ano = c.get(Calendar.YEAR);
        this.hra = c.get(Calendar.HOUR_OF_DAY);
        this.min = c.get(Calendar.MINUTE);
        this.seg = c.get(Calendar.SECOND);
        this.sem = c.get(Calendar.DAY_OF_WEEK);
    }
    
    
    
    /** Construtor com dia, mes e ano */
    public DataUtil(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hra = 0;
        this.min = 0;
        this.seg = 0;
        if (this.dia < 1)  this.dia = 1;
        if (this.dia > 31) this.dia = 31;
        if (this.mes < this.JAN) this.mes = this.JAN;
        if (this.mes > this.DEZ) this.mes = this.DEZ;
    }
    
    
    
    /*** Construtor com dia, mes, ano, hora, minuto */
    public DataUtil(int dia, int mes, int ano, int hra, int min) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hra = hra;
        this.min = min;
        this.seg = 0;
        if (this.dia < 1)  this.dia = 1;
        if (this.dia > 31) this.dia = 31;
        if (this.mes < this.JAN) this.mes = this.JAN;
        if (this.mes > this.DEZ) this.mes = this.DEZ;
        if (this.hra < 0)  this.hra = 0;
        if (this.hra > 23) this.hra = 23;
        if (this.min < 0)  this.min = 0;
        if (this.min > 59) this.min = 59;
    }
    
    /*** Construtor com dia, mes, ano, hora, minuto, segundo */
    public DataUtil(int dia, int mes, int ano, int hra, int min, int seg) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hra = hra;
        this.min = min;
        this.seg = seg;
        if (this.dia < 1)  this.dia = 1;
        if (this.dia > 31) this.dia = 31;
        if (this.mes < this.JAN) this.mes = this.JAN;
        if (this.mes > this.DEZ) this.mes = this.DEZ;
        if (this.hra < 0)  this.hra = 0;
        if (this.hra > 23) this.hra = 23;
        if (this.min < 0)  this.min = 0;
        if (this.min > 59) this.min = 59;
        if (this.seg < 0)  this.seg = 0;
        if (this.seg > 59) this.seg = 59;
    }
    
    /** Construtor DD/MM/YYYY, DD/MM/YYYY HH:MM ou DD/MM/YYYY HH:MM:SS */
    public DataUtil(String data) {
        int dataLen = data.length();
        if ((dataLen == 10) || (dataLen == 16) || (dataLen == 19)) {
            this.dia = Integer.parseInt(data.substring(0,2));
            this.mes = Integer.parseInt(data.substring(3,5));
            this.ano = Integer.parseInt(data.substring(6,10));
            if (dataLen == 10) {
                this.hra = 0;
                this.min = 0;
                this.seg = 0;
            } else if (dataLen == 16) {
                this.hra = Integer.parseInt(data.substring(11,13));
                this.min = Integer.parseInt(data.substring(14,16));
                this.seg = 0;
            } else if (dataLen == 19) {
                this.hra = Integer.parseInt(data.substring(11,13));
                this.min = Integer.parseInt(data.substring(14,16));
                this.seg = Integer.parseInt(data.substring(17,19));
            }
        } else {
            this.dia = 1;
            this.mes = 1;
            this.ano = 2000;
            this.hra = 0;
            this.min = 0;
            this.seg = 0;
        }
        if (this.dia < 1)  this.dia = 1;
        if (this.dia > 31) this.dia = 31;
        if (this.mes < this.JAN) this.mes = this.JAN;
        if (this.mes > this.DEZ) this.mes = this.DEZ;
        if (this.hra < 0)  this.hra = 0;
        if (this.hra > 23) this.hra = 23;
        if (this.min < 0)  this.min = 0;
        if (this.min > 59) this.min = 59;
        if (this.seg < 0)  this.seg = 0;
        if (this.seg > 59) this.seg = 59;
    }
    
    /** Construtor java.util.Date **/
    public DataUtil(java.util.Date data) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(data);
        this.dia = c.get(Calendar.DATE);
	this.mes = c.get(Calendar.MONTH) + 1;
	this.ano = c.get(Calendar.YEAR);
        this.hra = c.get(Calendar.HOUR_OF_DAY);
        this.min = c.get(Calendar.MINUTE);
        this.seg = c.get(Calendar.SECOND);
    }
    
    /** Soma N dias a data. N pode ser negativoS**/
    public static long addDays(java.util.Date data, int numDias) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(data);
        c.add(Calendar.DATE,numDias);
        return c.getTimeInMillis();
    }
    
    /** Soma N dias a data. N pode ser negativoS**/
    public static java.sql.Date addDaysToSQLDate(java.sql.Date data, int numDias) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(data);
        c.add(Calendar.DATE,numDias);
        return new java.sql.Date(c.getTimeInMillis());
    }
    
    
    public static java.sql.Date addyearsToSQLDate(java.util.Date data, int numAnos) {
        
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(data);
        c.add(Calendar.YEAR,numAnos);
        
        return new java.sql.Date(c.getTimeInMillis());
    }
  
    
    /** Retorna o dia */
    public int getDia() {
        return this.dia;
    }
    
    /** Retorna o mes */
    public int getMes() {
        return this.mes;
    }
    
    /** Retorna o ano */
    public int getAno() {
        return this.ano;
    }
    
    /** Retorna a hora */
    public int getHora() {
        return this.hra;
    }
    
    /** Retorna o minuto */
    public int getMinuto() {
        return this.min;
    }
    
    /** Retorna o segundo */
    public int getSegundo() {
        return this.seg;
    }
    
    /** String fixNumber()
     * Coloca um '0' na frente de numeros menores que 10 
     */
    private String fixNumber(int numero) {
        StringBuffer fixNumero = new StringBuffer();
        if (numero < 10) {
            fixNumero = fixNumero.append('0').append(numero);
        } else {
            fixNumero = fixNumero.append(numero);
        }
        return fixNumero.toString();
    }
    
    /** int toJulian()
     * Devolve o nï¿½mero juliano da data (this).
     * Obs.: Algoritimo do livro Core Java Vol.1
     */
    private int toJulian() {  
      int jy = this.ano;
      if (this.ano < 0) jy++;
      int jm = this.mes;
      if (this.mes > 2) jm++;
      else {  
         jy--;
         jm += 13;
      }
      int jul = (int) (java.lang.Math.floor(365.25 * jy) 
                + java.lang.Math.floor(30.6001*jm) + this.dia + 1720995.0);
      int IGREG = 15 + 31*(10+12*1582);
      if (this.dia + 31 * (this.mes + 12 * this.ano) >= IGREG) {  
        int ja = (int)(0.01 * jy);
        jul += 2 - ja + (int)(0.25 * ja);
      }
      return jul;
    }
    
    /** int fromJulian()
     * Calcula o dia, mes e ano da data (this) dado um nï¿½mero juliano.
     * Obs.: Algoritimo do livro Core Java Vol.1
     */
    private void fromJulian(int j) {  
      int ja = j;
      int JGREG = 2299161;
      if (j >= JGREG) {  
        int jalpha = (int)(((float)(j - 1867216) - 0.25) / 36524.25);
        ja += 1 + jalpha - (int)(0.25 * jalpha);
      }
      int jb = ja + 1524;
      int jc = (int)(6680.0 + ((float)(jb-2439870) - 122.1)/365.25);
      int jd = (int)(365 * jc + (0.25 * jc));
      int je = (int)((jb - jd)/30.6001);
      this.dia = jb - jd - (int)(30.6001 * je);
      this.mes = je - 1;
      if (this.mes > 12) this.mes -= 12;
      this.ano = jc - 4715;
      if (this.mes > 2) --this.ano;
      if (this.ano <= 0) --this.ano;
    }
    
    /** Date toDate()
     * Devolve this no formato java.util.Date
     */
    public java.util.Date toDate() {
        Calendar c = Calendar.getInstance();
        c.set(this.ano,this.mes-1,this.dia,this.hra,this.min,this.seg);
        return c.getTime();
    }
    
    /** Date toSqlDate()
     * Devolve this no formato java.sql.Date
     */
    public java.sql.Date toSqlDate() {
        DataUtil d = new DataUtil(this.dia,this.mes,this.ano);
        return new java.sql.Date(d.toDate().getTime());
    }
    
    /** Static Date toSqlDate()
     * Recebe String no formato DD/MM/YYYY
     * Devolve data correspondente no formato java.sql.Date
     */
    public static java.sql.Date toSqlDate(String data) {
        DataUtil d = new DataUtil(data);
        return new java.sql.Date(d.toDate().getTime());
    }
    
    /** Static String fromSqlDateToString()
     * Recebe um java.sql.Date e uma String de formataï¿½ï¿½o (ver mï¿½todo formataData)
     * Devolve uma String no formato indicado
     */
    public static String fromSqlDateToString(java.sql.Date d,String formato) {
        String data = d.toString();
        int dia = Integer.parseInt(data.substring(8));
        int mes = Integer.parseInt(data.substring(5,7));
        int ano = Integer.parseInt(data.substring(0,4));
        DataUtil d1 = new DataUtil(dia,mes,ano);
        return d1.formataData(formato);
    }
    
    /** Timestamp toTimestamp()
     * Devolve this no formato Timestamp
     */
    public Timestamp toTimestamp() {
        java.util.Date d = this.toDate();
        Timestamp ts = new Timestamp(d.getTime());
        return ts;
    }
    
    /** Timestamp toTimestamp()
     * Recebe uma string no formato DD/MM/YYYY HH:MM:SS
     * Devolve data correspondente no formato Timestamp
     */
    public static Timestamp toTimestamp(String data) {
        DataUtil d = new DataUtil(data);
        java.util.Date dd = d.toDate();
        return new Timestamp(dd.getTime());
    }
    
    /** int diferencaDias()
     * Calcula o nï¿½mero de dias entre this e o parï¿½metro.
     * Obs.: nï¿½mero positivo indica this posterior ao parï¿½metro.
     */
    public int diferencaDias(int d,int m,int a) {
        DataUtil data = new DataUtil(d,m,a);
        return this.toJulian() - data.toJulian();
    }
    
    /** int diferencaDias()
     * Calcula o nï¿½mero de dias entre a data e o parï¿½metro.
     * Obs.: nï¿½mero positivo indica data posterior ao parï¿½metro.
     */
    public int diferencaDias(DataUtil data) {
        return this.toJulian() - data.toJulian();
    }
    
    /** int diferencaDias()
     * Calcula o nï¿½mero de dias entre a data e o parï¿½metro.
     * Obs.: nï¿½mero positivo indica data posterior ao parï¿½metro.
     */
    public int diferencaDias(java.util.Date jud) {
        DataUtil data = new DataUtil(jud);
        return this.toJulian() - data.toJulian();
    }
    
    /** String formataData()
     * Devolve a data formatada de acordo com 
     * a mï¿½scara passada como parï¿½metro
     * dd - dia
     * mm - mï¿½s
     * yy - ano
     * yyyy - ano
     * hh - hora
     * nn - minuto
     * ss - segundo
     * sem - dia da semana
     * Exemplos: dd/mm/yy hh:nn, mm-dd-yyyy, hh:nn:ss, yyyymmdd ...
     */
    public String formataData(String mask) {
        StringBuffer dataFormatada = new StringBuffer();
        dataFormatada = dataFormatada.append(mask);
        String[] listaMasks = {"dd","mm","yy","yyyy","hh","nn","ss","sem"};
        int pos,tam,i;
        StringBuffer ddMask   = new StringBuffer();
        StringBuffer mmMask   = new StringBuffer();
        StringBuffer yyMask   = new StringBuffer();
        StringBuffer yyyyMask = new StringBuffer();
        StringBuffer hhMask   = new StringBuffer();
        StringBuffer nnMask   = new StringBuffer();
        StringBuffer ssMask   = new StringBuffer();
        StringBuffer semMask   = new StringBuffer();
        String[] rMasks = new String[8];
        rMasks[0] = ddMask.append(this.fixNumber(this.dia)).toString();
        rMasks[1] = mmMask.append(this.fixNumber(this.mes)).toString();
        rMasks[2] = yyMask.append(this.ano).delete(0,2).toString();
        rMasks[3] = yyyyMask.append(this.ano).toString();
        rMasks[4] = hhMask.append(this.fixNumber(this.hra)).toString();
        rMasks[5] = nnMask.append(this.fixNumber(this.min)).toString();
        rMasks[6] = ssMask.append(this.fixNumber(this.seg)).toString();
        rMasks[7] = semMask.append(this.nomeDiaDaSemanaAbrv()).toString();
        for (i = 0; i < listaMasks.length; i++) {
            pos = mask.indexOf(listaMasks[i]);
            tam = listaMasks[i].length();
            if (pos >= 0) dataFormatada = dataFormatada.replace(pos,pos+tam,rMasks[i]);
        }
        return dataFormatada.toString();
    }
    
    /** int numeroDiasMes()
     * Devolve o numero de dias do mes/ano de this
     */
    public int numeroDiasMes () {
        int num,m,a;
	num = 0;
        m = this.mes;
        a = this.ano;
	if (m == this.JAN) num = 31;
	if (m == this.FEV) {
            if (a % 4 == 0) num = 29; else num = 28;
	}
	if (m == this.MAR) num = 31;
	if (m == this.ABR) num = 30;
	if (m == this.MAI) num = 31;
	if (m == this.JUN) num = 30;
	if (m == this.JUL) num = 31;
	if (m == this.AGO) num = 31;
	if (m == this.SET) num = 30;
	if (m == this.OUT) num = 31;
	if (m == this.NOV) num = 30;
	if (m == this.DEZ) num = 31;
	return num;
    }
    
    /** int numeroDiasUteisMes()
     * Devolve o numero de dias ï¿½teis do mï¿½s de this
     * Obs.: Nï¿½o leva feriados em consideraï¿½ï¿½o, apenas retira os sï¿½bados e domingos.
     */
    public int numeroDiasUteisMes() {
        DataUtil data = new DataUtil(1,this.mes,this.ano);
        int totDiasMes,contFimDeSemana,i;
        contFimDeSemana = 0;
        i = 1;
        totDiasMes = data.numeroDiasMes();
        while (i <= totDiasMes) {
            if ((data.diaDaSemana() == this.DOMINGO) || 
                (data.diaDaSemana() == this.SABADO)) contFimDeSemana++;
            data.somaDias(1);
            i++;
        }
        return totDiasMes - contFimDeSemana;
    }
    
    /** boolean ehDiaUtil()
     * Devolve true se o dia da semana da instï¿½ncia atual
     * nï¿½o for nem sï¿½bado nem domingo, e false caso contrï¿½rio.
     */
    public boolean ehDiaUtil() {
        boolean diaUtil = false;
        if ((this.diaDaSemana() > this.DOMINGO) && 
            (this.diaDaSemana() < this.SABADO)) diaUtil = true;
        return diaUtil;
    }
    
    /** int diaDaSemana()
     * Devolve o numero do dia da semana
     * de 1 a 7.
     */
    public int diaDaSemana() {
        GregorianCalendar data = new GregorianCalendar(this.ano,this.mes-1,this.dia);
        return data.get(Calendar.DAY_OF_WEEK);
    }
    
    /** String nomeDiaDaSemana()
     * Devolve o nome do dia da semana
     */
    public String nomeDiaDaSemana () {
        int ds;
        ds = this.diaDaSemana();
        StringBuffer nome = new StringBuffer();
	if (ds == this.DOMINGO) nome = nome.append("Domingo");
	if (ds == this.SEGUNDA) nome = nome.append("Segunda");
	if (ds == this.TERCA)   nome = nome.append("Terça");
	if (ds == this.QUARTA)  nome = nome.append("Quarta");
	if (ds == this.QUINTA)  nome = nome.append("Quinta");
	if (ds == this.SEXTA)   nome = nome.append("Sexta");
	if (ds == this.SABADO)  nome = nome.append("Sábado");
        return nome.toString();
    }
    
        /** String nomeDiaDaSemana()
     * Devolve o nome do dia da semana
     */
    public String nomeDiaDaSemanaAbrv () {
        int ds;
        ds = this.diaDaSemana();
        StringBuffer nome = new StringBuffer();
	if (ds == this.DOMINGO) nome = nome.append("Dom");
	if (ds == this.SEGUNDA) nome = nome.append("Seg");
	if (ds == this.TERCA)   nome = nome.append("Ter");
	if (ds == this.QUARTA)  nome = nome.append("Qua");
	if (ds == this.QUINTA)  nome = nome.append("Qui");
	if (ds == this.SEXTA)   nome = nome.append("Sex");
	if (ds == this.SABADO)  nome = nome.append("Sáb");
        return nome.toString();
    }
    
    /** String nomeMes()
     * Devolve o nome do mï¿½s da instï¿½ncia atual
     */
    public String nomeMes () {
        int m;
        m = this.mes;
	StringBuffer nome = new StringBuffer();
	if (m == 1)  nome = nome.append("Janeiro");
	if (m == 2)  nome = nome.append("Fevereiro");
	if (m == 3)  nome = nome.append("Março");
	if (m == 4)  nome = nome.append("Abril");
	if (m == 5)  nome = nome.append("Maio");
	if (m == 6)  nome = nome.append("Junho");
	if (m == 7)  nome = nome.append("Julho");
	if (m == 8)  nome = nome.append("Agosto");
	if (m == 9)  nome = nome.append("Setembro");
	if (m == 10) nome = nome.append("Outubro");
	if (m == 11) nome = nome.append("Novembro");
	if (m == 12) nome = nome.append("Dezembro");
	return nome.toString();
    }
    
    /** void somaDias()
     * Soma n dias aa this.
     * Utilize n negativo para diminuir o
     * o numero de dias.
     */
    public void somaDias(int n) {
        Calendar data = GregorianCalendar.getInstance();
        data.set(Calendar.DATE, this.dia);
        data.set(Calendar.MONTH, this.mes-1);
        data.set(Calendar.YEAR, this.ano);
        data.add(Calendar.DATE, n);
        this.dia = data.get(Calendar.DATE);
	this.mes = data.get(Calendar.MONTH) + 1;
	this.ano = data.get(Calendar.YEAR);
    }
    
    
        /** boolean antes() - dia, mes e ano
      * devolve true se a instï¿½ncia atual
      * for anterior aos parï¿½metros.
      */
    public boolean antes(int d,int m,int a) {
        boolean antes;
        antes = false;
        GregorianCalendar dataAtual = new GregorianCalendar(this.ano,this.mes-1,this.dia);
        GregorianCalendar dataBase  = new GregorianCalendar(a,m-1,d);
        if (dataAtual.before(dataBase)) antes = true;
        return antes;
    }
    
    /** boolean antes() - dia, mes, ano, hora e minuto
      * devolve true se a instï¿½ncia atual
      * for anterior aos parï¿½metros.
      */
    public boolean antes(int dia,int mes,int ano,int hra,int min) {
        boolean antes;
        antes = false;
        GregorianCalendar dataAtual = new GregorianCalendar(this.ano,this.mes-1,this.dia,this.hra,this.min);
        GregorianCalendar dataBase  = new GregorianCalendar(ano,mes-1,dia,hra,min);
        if (dataAtual.before(dataBase)) antes = true;
        return antes;
    }
    
     /** boolean antes() - dia, mes, ano, hora, minuto e segundo
      * devolve true se a instï¿½ncia atual
      * for anterior aos parï¿½metros.
      */
    public boolean antes(int dia,int mes,int ano,int hra,int min,int seg) {
        boolean antes;
        antes = false;
        GregorianCalendar dataAtual = new GregorianCalendar(this.ano,this.mes-1,this.dia,this.hra,this.min,this.seg);
        GregorianCalendar dataBase  = new GregorianCalendar(ano,mes-1,dia,hra,min,seg);
        if (dataAtual.before(dataBase)) antes = true;
        return antes;
    }
    
    /** boolean antes() - java.util.Date
     * devolve true se this for anterior aos parï¿½metros
     */
    public boolean antes(java.util.Date data) {
        boolean antes = false;
        DataUtil dt = new DataUtil(data);
        if (this.antes(dt.dia,dt.mes,dt.ano,dt.hra,dt.min,dt.seg)) antes = true;
        return antes;
    }
    
    /** boolean antes() - DataUtil
     * devolve true se this for anterior aos parï¿½metros
     */
    public boolean antes(DataUtil data) {
        boolean antes = false;
        if (this.antes(data.dia,data.mes,data.ano,data.hra,data.min,data.seg)) antes = true;
        return antes;
    }

    /** boolean depois() - dia, mes e ano
     * devolve true se a instï¿½ncia atual
     * for posterior aos parï¿½metros.
     */
    public boolean depois(int d,int m,int a) {
        boolean depois;
        depois = false;
        GregorianCalendar dataAtual = new GregorianCalendar(this.ano,this.mes-1,this.dia);
        GregorianCalendar dataBase  = new GregorianCalendar(a,m-1,d);
        if (dataAtual.after(dataBase)) depois = true;
        return depois;
    }
    
    /** boolean depois() - dia, mes, ano, hora e minuto
     * devolve true se a instï¿½ncia atual
     * for posterior aos parï¿½metros.
     */
    public boolean depois(int dia,int mes,int ano,int hra, int min) {
        boolean depois;
        depois = false;
        GregorianCalendar dataAtual = new GregorianCalendar(this.ano,this.mes-1,this.dia,this.hra,this.min);
        GregorianCalendar dataBase  = new GregorianCalendar(ano,mes-1,dia,hra,min);
        if (dataAtual.after(dataBase)) depois = true;
        return depois;
    }
    
    /** boolean depois() - dia, mes, ano, hora, minuto e segundo
     * devolve true se a instï¿½ncia atual
     * for posterior aos parï¿½metros.
     */
    public boolean depois(int dia,int mes,int ano,int hra, int min,int seg) {
        boolean depois;
        depois = false;
        GregorianCalendar dataAtual = new GregorianCalendar(this.ano,this.mes-1,this.dia,this.hra,this.min,this.seg);
        GregorianCalendar dataBase  = new GregorianCalendar(ano,mes-1,dia,hra,min,seg);
        if (dataAtual.after(dataBase)) depois = true;
        return depois;
    }
    
    /** boolean depois() - java.util.Date
     * devolve true se this for posterior aos parï¿½metros
     */
    public boolean depois(java.util.Date data) {
        boolean depois = false;
        DataUtil dt = new DataUtil(data);
        if (this.depois(dt.dia,dt.mes,dt.ano,dt.hra,dt.min,dt.seg)) depois = true;
        return depois;
    }
    
    /** boolean depois() - DataUtil
     * devolve true se this for anterior aos parï¿½metros
     */
    public boolean depois(DataUtil data) {
        boolean depois = false;
        if (this.depois(data.dia,data.mes,data.ano,data.hra,data.min,data.seg)) depois = true;
        return depois;
    }

    /** boolean igual() - dia, mes e ano
      * devolve true se a instï¿½ncia atual
      * for igual aos parï¿½metros.
      */
    public boolean igual(int d,int m,int a) {
        boolean igual;
        igual = false;
        GregorianCalendar dataAtual = new GregorianCalendar(this.ano,this.mes-1,this.dia);
        GregorianCalendar dataBase  = new GregorianCalendar(a,m-1,d);
        if (dataAtual.equals(dataBase)) igual = true;
        return igual;
    }
    
    /** boolean igual() - dia, mes, ano, hora e minuto
      * devolve true se a instï¿½ncia atual
      * for igual aos parï¿½metros.
      */
    public boolean igual(int dia,int mes,int ano,int hra, int min) {
        boolean igual;
        igual = false;
        GregorianCalendar dataAtual = new GregorianCalendar(this.ano,this.mes-1,this.dia,this.hra,this.min);
        GregorianCalendar dataBase  = new GregorianCalendar(ano,mes-1,dia,hra,min);
        if (dataAtual.equals(dataBase)) igual = true;
        return igual;
    }
    
    /** boolean igual() - dia, mes, ano, hora, minuto e segundo
      * devolve true se a instï¿½ncia atual
      * for igual aos parï¿½metros.
      */
    public boolean igual(int dia,int mes,int ano,int hra, int min, int seg) {
        boolean igual;
        igual = false;
        GregorianCalendar dataAtual = new GregorianCalendar(this.ano,this.mes-1,this.dia,this.hra,this.min,this.seg);
        GregorianCalendar dataBase  = new GregorianCalendar(ano,mes-1,dia,hra,min,seg);
        if (dataAtual.equals(dataBase)) igual = true;
        return igual;
    }
    
    /** boolean igual() - java.util.Date
     * devolve true se this for igual aos parï¿½metros
     */
    public boolean igual(java.util.Date data) {
        boolean igual = false;
        DataUtil dt = new DataUtil(data);
        if (this.igual(dt.dia,dt.mes,dt.ano,dt.hra,dt.min,dt.seg)) igual = true;
        return igual;
    }
    
    /** boolean igual() - DataUtil
     * devolve true se this for anterior aos parï¿½metros
     */
    public boolean igual(DataUtil data) {
        boolean igual = false;
        if (this.igual(data.dia,data.mes,data.ano,data.hra,data.min,data.seg)) igual = true;
        return igual;
    }

    /** int analisaData() - dia, mes e ano.
      * devolve -1 se a instï¿½ncia atual
      * for anterior aos parï¿½metros, 0 se for
      * igual e 1 se for posterior.
      */
    public int analisaData(int d,int m,int a) {
        int resultadoAnalise;
        resultadoAnalise = 99;
        if (this.antes(d,m,a)) {
            resultadoAnalise = -1;
        } else if (this.igual(d,m,a)) {
            resultadoAnalise = 0;
        } else if (this.depois(d,m,a)) {
            resultadoAnalise = 1;
        }
        return resultadoAnalise;
    }
    
    /** int analisaData() - dia, mes, ano, hora e minuto.
      * devolve -1 se a instï¿½ncia atual
      * for anterior aos parï¿½metros, 0 se for
      * igual e 1 se for posterior.
      */
    public int analisaData(int dia,int mes,int ano,int hra,int min) {
        int resultadoAnalise;
        resultadoAnalise = 99;
        if (this.antes(dia,mes,ano,hra,min)) {
            resultadoAnalise = -1;
        } else if (this.igual(dia,mes,ano,hra,min)) {
            resultadoAnalise = 0;
        } else if (this.depois(dia,mes,ano,hra,min)) {
            resultadoAnalise = 1;
        }
        return resultadoAnalise;
    }
    
    /** int analisaData() - dia, mes, ano, hora, minuto e segundo.
      * devolve -1 se a instï¿½ncia atual
      * for anterior aos parï¿½metros, 0 se for
      * igual e 1 se for posterior.
      */
    public int analisaData(int dia,int mes,int ano,int hra,int min,int seg) {
        int resultadoAnalise;
        resultadoAnalise = 99;
        if (this.antes(dia,mes,ano,hra,min,seg)) {
            resultadoAnalise = -1;
        } else if (this.igual(dia,mes,ano,hra,min,seg)) {
            resultadoAnalise = 0;
        } else if (this.depois(dia,mes,ano,hra,min,seg)) {
            resultadoAnalise = 1;
        }
        return resultadoAnalise;
    }
    
    /*
     *@author Gabriel Mendes
     *@version 1.0
     * Classe usada para recuperar um objeto java.util.Date a partir de 
     * um objeto request que possui dados de dia , mes e ano.
     */
    public static final java.util.Date getDateFromRequest( HttpServletRequest request,String pattern) {
        int dia,mes,ano;
        java.util.Date result =null;
        try{
            dia = Integer.parseInt(request.getParameter( pattern + "Day"));
            mes = Integer.parseInt(request.getParameter( pattern + "Month"));
            ano = Integer.parseInt(request.getParameter( pattern + "Year"));
            if (dia > 0 && mes > 0 && ano >0) result = (new DataUtil(dia,mes,ano)).toDate();
            return result;
        }catch(Exception e) {
            return null;
        }
    }
    
    /*
      * dataInicioSemana()
      * Devolve a data (java.util.Date) do domingo da semana passado 
      * por parï¿½metro. Parï¿½metros: 0 = semana atual, -1 = semana passada,
      * -2 = semana retrasada, 1 = prï¿½xima semana etc.  
     */
    public static java.util.Date dataInicioSemana(int week) {
        DataUtil du = new DataUtil();
        int diaSemana = du.diaDaSemana();
        int param = 0;
        if (week <= 0) param = -1 * (Math.abs(week) * 7 + diaSemana - Calendar.SUNDAY);
        if (week > 0) param = week * 7 + Calendar.SATURDAY - diaSemana + 1;
        du.somaDias(param);
        return du.toDate();
    }
    
    public static java.util.Date dataInicioSemana ( ) {
        return dataInicioSemana(SEMANA_ATUAL);
    }

}
