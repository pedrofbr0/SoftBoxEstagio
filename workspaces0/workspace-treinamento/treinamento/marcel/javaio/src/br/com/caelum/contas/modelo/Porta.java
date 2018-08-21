package br.com.caelum.contas.modelo;
public class Porta {
	boolean aberta;
	String cor;
	double dimensionx, dimensiony, dimensionz;

	public void abre() {
		this.aberta = true;
	}

	public void fecha() {
		this.aberta = false;
	}

	public boolean estaAberta() {
		if (this.aberta) {
			return true;
		} else {
			return false;
		}
	}
}