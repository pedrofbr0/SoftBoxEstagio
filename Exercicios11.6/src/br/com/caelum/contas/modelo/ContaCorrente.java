package br.com.caelum.contas.modelo;

import br.com.caelum.contas.Data;

public class ContaCorrente implements Conta {
    
	private String titular;
	private int numero;
	protected double saldo;
	private static int identificador;
	private String agencia;
	Data dataDeAbertura = new Data();
	private double taxaSelic;

	
	public double getValorImposto() {
		return this.getSaldo() * taxaSelic;
	}
	
	public double getSaldo() {
        return this.saldo;
    }
	
	public void saca(double quantidade) {
		double novoSaldo = this.saldo - quantidade;
		this.saldo = novoSaldo;
	}

	public void deposita(double quantidade) {
		double novoSaldo = this.saldo + quantidade;
		this.saldo = novoSaldo;
	}
	
	public void atualiza(double taxaSelic) {
		this.taxaSelic = taxaSelic;
	}
	
}
