package br.com.caelum.contas.modelo;
import java.util.*;

//parou na parte 6

public class Banco {
	
	private String nome;
	private int numero;
	private List <Conta> contas = new ArrayList<>();
	private Map <String, Conta> contasTitular = new HashMap<>();
	
	public Banco(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public void adiciona (Conta c) {
		contasTitular.put(c.getTitular(), c);
		contas.add(c);
	}
	
	public Conta pega( int x) {
		return contas.get(x);
	}
	
	public Conta buscaPorTitular (String titular) {
		Conta c = contasTitular.get(titular);
		return c;
	}
	
	public int getQuantidadeDeContas() {
		return contas.size();
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
