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
	
	public String getNome() {
		return this.nome;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public void adiciona (Conta c) {
		for (int i = 0; i < this.contas.length; i++) {
			if (contas[i] == null) {
				contas[i] = c;
				return;
			}	
		}
		 // throw new RuntimeException("Não tem espaço no Banco");
		
		Conta[] novoContas = new ContaCorrente[contas.length+1];
		System.arraycopy(contas, 0, novoContas, 0, contas.length);
		
		novoContas[contas.length] = c;
		contas = novoContas;
	}
	
	public void getContas() {
		for (int i = 0; i < this.contas.length; i++) {
			if (contas[i]==null) {
				return;
			} else {
				System.out.println("Conta do " + contas[i].getTitular() + " com " + contas[i].getSaldo());
			}
		}
	}
	
	public boolean contem(Conta c) {
		for (Conta x : contas) {
			if (x == null) {
				return false;
			} else if(x.equals(c)) {
				return true;
			}
		}
		return false;		
	}

}
