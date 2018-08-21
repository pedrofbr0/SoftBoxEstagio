package br.com.caelum.contas.modelo;

import java.util.*;

public class Banco {
	private String nome;
	private int numero;
//	private Conta[] contas;
	private List<Object> contas = new LinkedList<>();
	private Map<String, Conta> mapa = new HashMap<>(); 

	public Banco(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
	}

	public int getNumero() {
		return this.numero;
	}

	public String getNome() {
		return this.nome;
	}

//	public void adiciona(Conta c) {
//		for (int i = 0; i < this.contas.length; i++) {
//			// if(i==this.contas.length) {
//			// Error error = new Error("Vetor cheio!");
//			// System.out.println(error);
//			// }
//			if (this.contas[i] == null) {
//				this.contas[i] = c;
//				return;
//			}
//		}
//		Conta[] contasNovo = new ContaCorrente[contas.length + 1];
//		for (int i = 0; i < this.contas.length; i++) {
//			contasNovo[i] = this.contas[i];
//		}
//		contasNovo[contas.length] = c;
//		contas = contasNovo;
//	}
	
	public void adiciona(Conta c) {
		contas.add(c);
		mapa.put(c.getTitular(), c);
		}
	
	public Object pega(int x) {
		return contas.get(x);
	}
	
	public int pegaQuantidadeDeContas() {
		return contas.size();
	}
	
	public Conta buscaPorTitular(String nomeDoTitular) {
		return mapa.get(nomeDoTitular);
	}

//	public void mostraContas() {
//		for (int i = 0; i < this.contas.length; i++) {
//			if (this.contas[i] == null) {
//				break;
//			}
//			System.out.println("Conta na posição " + i);
//			System.out.println(this.contas[i].toString());
//		}
//	}
//
//	public boolean contem(Conta conta) {
//		for (int i = 0; i < this.contas.length; i++) {
//			if (this.contas[i] == conta) {
//				return true;
//			}
//		}
//		return false;
//
//	}

}
