package br.com.caelum.contas.modelo;

public class Banco {
	
	private String nome;
	private int numero;
	private Conta[] contas;
	private String endereco;
	
	public Banco (String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
		this.contas = new ContaCorrente[10];
	}
	
	public String getNome () {
		return this.nome;
	}
	public int getNumero () {
		return this.numero;
	}
	
	public void adiciona (Conta conta) {
		for (int i = 0; i < this.contas.length; i++) {
			if (contas[i] == null) {
				this.contas[i] = conta;
				return;
			}
		}
		
		Conta[] contasNovo = new ContaCorrente[contas.length+1];
		for( int i = 0; i < contas.length; i++) {
			contasNovo[i] = contas[i];
		}
		contasNovo[contasNovo.length-1] = conta;
		contas = contasNovo;
	}
	
	
	/*public void adiciona (Conta conta) {
		for (int i = 0; i < this.contas.length; i++) {
			if (contas[i] == null) {
				this.contas[i] = conta;
				break;
			}
		}
	}*/
	
	public Conta getConta (int i) {
		return this.contas[i];
	}
	
	/*public void mostracontas() {
		for (int i = 0; i < this.contas.length; i++) {
			if(contas[i] != null) {
				System.out.println("Conta na posição " + i);
				contas[i].toString();
			}
		}
	}*/
	
	public void mostracontas() {
		for (int i = 0; i < this.contas.length; i++) {
			if(contas[i] != null) {
				System.out.println("Conta na posição " + i);
				System.out.println(contas[i].getTitular());
				System.out.println(contas[i].getNumero());
				System.out.println(contas[i].getAgencia());
				System.out.println(contas[i].getSaldo());
				System.out.println();
			}
		}
	}
	
	/*public void mostracontas() {
		for (Conta x : contas) {
			System.out.println("Conta na posição " + x);
			System.out.println(x.getTitular());
			System.out.println(x.getNumero());
			System.out.println(x.getAgencia());
			System.out.println(x.getSaldo());
		}
	}*/
	
	public boolean contem (Conta conta) {
		int j = 0;
		for (int i = 0; i < contas.length; i++) {
			if(conta == contas[i] && conta != null) {
				j++;
				return true;
			}
		}
		return false;
	}
}
