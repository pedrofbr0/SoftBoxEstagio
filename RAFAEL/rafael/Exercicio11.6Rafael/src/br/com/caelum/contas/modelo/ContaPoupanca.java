package br.com.caelum.contas.modelo;

import br.com.caelum.contas.data.Data;

public class ContaPoupanca implements Conta {
	
	private String titular, agencia;
	private Data data = new Data();
	private int numero;
	protected double saldo = 0.0;
	private static int identificadorGeral = 1;
	private int identificador = 0;
	private String cpf = "";
	
	
	public String getTipo() {
		return "Conta Poupança";
	}

	@Override
	public String getTitular() {
		// TODO Auto-generated method stub
		return this.titular;
	}

	@Override
	public void setTitular(String titular) {
		// TODO Auto-generated method stub
		this.titular = titular; 
	}

	@Override
	public void setDataAbertura(int dia, int mes, int ano) {
		// TODO Auto-generated method stub
		this.data.setData(dia, mes, ano);
	}

	@Override
	public String getDataAbertura() {
		// TODO Auto-generated method stub
		return this.data.getData();
	}

	@Override
	public String getAgencia() {
		// TODO Auto-generated method stub
		return this.agencia;
	}

	@Override
	public void setAgencia(String agencia) {
		// TODO Auto-generated method stub
		this.agencia = agencia;
	}

	@Override
	public int getNumero() {
		// TODO Auto-generated method stub
		return this.numero;
	}

	@Override
	public void setNumero(int numero) {
		// TODO Auto-generated method stub
		this.numero = numero;
	}

	@Override
	public double getSaldo() {
		// TODO Auto-generated method stub
		return this.saldo;
	}

	@Override
	public int getIdentificador() {
		// TODO Auto-generated method stub
		return this.identificador;
	}

	@Override
	public boolean deposita(double valor) {
		// TODO Auto-generated method stub
		if (valor>0) {
			this.saldo +=valor;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean saca(double valor) {
		// TODO Auto-generated method stub
		if (saldo>valor) {
			this.saldo -= valor;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public double getRendimento() {
		// TODO Auto-generated method stub
		return this.saldo*0.1;
	}

	@Override
	public void transfere(double valor, Conta conta) {
		// TODO Auto-generated method stub
		this.saca(valor);
		conta.deposita(valor);
	}

}
