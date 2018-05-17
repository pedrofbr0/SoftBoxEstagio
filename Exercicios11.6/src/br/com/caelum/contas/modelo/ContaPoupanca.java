package br.com.caelum.contas.modelo;

import br.com.caelum.contas.Data;

public class ContaPoupanca implements Conta {
	
	private String titular;
	private int numero;
	protected double saldo;
	private static int identificador;
	private String agencia;
	Data dataDeAbertura = new Data();
	private double taxaSelic;
	

	public void saca(double quantidade) {
		double novoSaldo = this.saldo - quantidade;
		this.saldo = novoSaldo;
	}
	
	public double getSaldo() {
        return this.saldo;
    }

	public void deposita(double quantidade) {
		double novoSaldo = this.saldo + quantidade;
		this.saldo = novoSaldo;
	}
	
	public void atualiza(double taxaSelic) {
		this.taxaSelic = taxaSelic;
	}
}
