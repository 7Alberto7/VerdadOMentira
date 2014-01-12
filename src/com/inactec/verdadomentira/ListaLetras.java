package com.inactec.verdadomentira;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

public class ListaLetras {
	private List<Letra> lista;

	public ListaLetras() {
		this.lista = new ArrayList<Letra>();
	}

	public void inicializarListaLetras() {
		
		this.lista.add(new Letra("A", 13));
		this.lista.add(new Letra("Á", 13));
		this.lista.add(new Letra("B", 4));
		this.lista.add(new Letra("C", 20));
		this.lista.add(new Letra("D", 16));
		this.lista.add(new Letra("E", 11));
		this.lista.add(new Letra("É", 11));
		this.lista.add(new Letra("F", 25));
		this.lista.add(new Letra("G", 17));
		this.lista.add(new Letra("H", 26));
		this.lista.add(new Letra("I", 7));
		this.lista.add(new Letra("Í", 7));
		this.lista.add(new Letra("J", 23));
		this.lista.add(new Letra("K", 22));
		this.lista.add(new Letra("L", 12));
		this.lista.add(new Letra("M", 18));
		this.lista.add(new Letra("N", 19));
		this.lista.add(new Letra("Ñ", 27));
		this.lista.add(new Letra("O", 14));
		this.lista.add(new Letra("Ó", 14));
		this.lista.add(new Letra("P", 10));
		this.lista.add(new Letra("Q", 5));
		this.lista.add(new Letra("R", 9));
		this.lista.add(new Letra("S", 15));
		this.lista.add(new Letra("T", 21));
		this.lista.add(new Letra("U", 6));
		this.lista.add(new Letra("Ú", 6));
		this.lista.add(new Letra("V", 8));
		this.lista.add(new Letra("W", 2));
		this.lista.add(new Letra("X", 3));
		this.lista.add(new Letra("Y", 24));
		this.lista.add(new Letra("Z", 1));
	}
	
	public Letra buscarLetra(String letra) {
		Letra result = null;
		
		Iterator<Letra> it = this.lista.iterator();
		while(it.hasNext() && result == null) {
			Letra l = it.next();
			
			if (l.getLetra().equals(letra.toUpperCase())) {
				result = l;
			}
			
		}
		
		return result;
	}
	
}
