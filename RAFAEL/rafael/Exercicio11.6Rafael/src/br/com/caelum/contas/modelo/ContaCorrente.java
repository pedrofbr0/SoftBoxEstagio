package br.com.caelum.contas.modelo;

import br.com.caelum.contas.data.Data;

public class ContaCorrente implements ContaTributavel {
	
	private String titular, agencia;
	private Data data = new Data();
	private int numero;
	protected double saldo = 0.0;
	private static int identificadorGeral = 1;
	private int identificador = 0;
	private String cpf = "";
	
	
	
	//funcoes de Tributavel
	public String getTipo() {
		return "Conta Corrente";
	}
	
	@Override
	public double getValorImposto() {
		// TODO Auto-generated method stub	
		return this.getSaldo()*0.01;
	}
	
	public String getTitular() {
		return titular;
	}
	
	
	
	//funcoes de Conta
	public boolean saca (double valor) {
		if (this.saldo>(valor+0.10)){
			this.saldo -= (valor+0.10);
			return true;
		}
		else {
			return false;
		}
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}
	
	public void setDataAbertura(int dia, int mes, int ano) {
		this.data.setData(dia, mes, ano);
	}
	public String getDataAbertura() {
		return this.data.getData();
	}
	
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public int getIdentificador() {
		return this.identificador;
	}
	
	public boolean deposita(double valor) {
		if (valor>0) {
			this.saldo += valor;
			return true;
		}else {
			return false;
		}
	}
	
	public double getRendimento() {
		return this.saldo*0.1;
	}
	
	public void transfere (double valor, Conta conta) {
		this.saca(valor);
		conta.deposita(valor);
	}


}
