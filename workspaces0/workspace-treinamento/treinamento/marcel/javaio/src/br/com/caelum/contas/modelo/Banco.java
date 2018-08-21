package br.com.caelum.contas.modelo;

public class Banco {
	private String nome;
	private int numero;
	private Conta[] contas;

	public Banco(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
		this.contas = new ContaCorrente[10];
	}

	// getters para nome e número, não colocar os setters pois já recebemos no
	// construtor

	public int getNumero() {
		return this.numero;
	}

	public String getNome() {
		return this.nome;
	}

	public void adiciona(Conta c) {
		for (int i = 0; i < this.contas.length; i++) {
			// if(i==this.contas.length) {
			// Error error = new Error("Vetor cheio!");
			// System.out.println(error);
			// }
			if (this.contas[i] == null) {
				this.contas[i] = c;
				return;
			}
		}
		Conta[] contasNovo = new ContaCorrente[contas.length + 1];
		for (int i = 0; i < this.contas.length; i++) {
			contasNovo[i] = this.contas[i];
		}
		contasNovo[contas.length] = c;
		contas = contasNovo;
	}

	public void mostraContas() {
		for (int i = 0; i < this.contas.length; i++) {
			if (this.contas[i] == null) {
				break;
			}
			System.out.println("Conta na posição " + i);
			System.out.println(this.contas[i].toString());
		}
	}

	public boolean contem(Conta conta) {
		for (int i = 0; i < this.contas.length; i++) {
			if (this.contas[i] == conta) {
				return true;
			}
		}
		return false;

	}

}
