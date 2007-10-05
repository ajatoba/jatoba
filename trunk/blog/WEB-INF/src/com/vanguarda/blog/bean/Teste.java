/*
 * Created on 23/05/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vanguarda.blog.bean;

/**
 * @author Fabio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Teste {
	
	public Teste(String n, int i, String e)
	{
		nome = n;
		idade = i;
		endereco = e;
	}
	
	private String nome;
	
	private int idade;
	
	private String endereco;
	
	
	public String toString() {
		
		StringBuffer buffer =  new StringBuffer();
		
		buffer.append("Nome:");
		buffer.append(nome);
		buffer.append("<br>");
		buffer.append("Idade:");
		buffer.append(idade);
		buffer.append("<br>");
		buffer.append("Endereço:");
		buffer.append(endereco);
		
		return buffer.toString();
	}

}
