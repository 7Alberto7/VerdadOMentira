package com.inactec.verdadomentira;

public class Letra {
	private String letra;
	private int valor;
	
	public Letra(String letra, int valor) {
		this.letra = letra;
		this.valor = valor;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
