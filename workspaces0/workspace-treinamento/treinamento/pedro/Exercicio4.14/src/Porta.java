/*
 * Porta
 * 
 * 1.0
 *
 * 02/03/2018 14:41
 * 
 * Pedro
 * 
 * Copyright notice
 * 
 * Esta classe apresenta atributos e métodos essencias à uma porta,
 * como abrir e fechar, assim como saber se está aberta
 */

public class Porta {
	boolean aberta;
	String cor;
	double dimensaoX;
	double dimensaoY;
	double dimensaoZ;
	
	void abre() {
		this.aberta = true;
	}
	
	void fecha() {
		this.aberta = false;
	}
	
	void pinta(String s) {
		this.cor = s;
	}
	
	boolean estaAberta() {
		return this.aberta;
	}
}
