package br.com.caelum.contas.modelo;

public class Casa {
	String cor;
	int totalDePortas;
	public Porta[] portas;
	
	public Casa() {
		this.portas = new Porta[10];
	}
	
	public void pinta(String s) {
		this.cor = s;
	}
	
	public int quantasPortasEstaoAbertas() {
		int counter=0;
		for(int i = 0; i < this.portas.length ; i++) {
			if (this.portas[i] == null) {
			  break;
				}
			if(this.portas[i].estaAberta()) {
			  counter++;
			}
		}
		return counter;
	}
	
	public void adicionaPorta(Porta p) {
		for (int i = 0; i < this.portas.length; i++) {
			if (this.portas[i] == null) {
			  this.portas[i] = p;
			  return;
			}
		}
	}
	
	public int totalDePortas() {
		return this.portas.length;
		
	}

}
