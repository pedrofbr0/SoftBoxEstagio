package br.com.caelum.contas.modelo;
import br.com.caelum.contas.data.*;

public class Conta {
	private String titular, agencia;
	private Data data = new Data();
	private int numero;
	private double saldo = 0.0;
	private static int identificadorGeral = 1;
	private int identificador = 0;
	private String cpf = "";
	
	
	public Conta (String titular, String cpf){
		if (validaCpf(cpf)) {
			this.cpf = cpf;
		}else {
			this.cpf = "cpf invalido";
		}
		this.titular = titular;
		this.identificador = Conta.identificadorGeral;
		Conta.identificadorGeral++;
		
	}
	
	public Conta() {
		this.identificador = Conta.identificadorGeral;
		Conta.identificadorGeral++;
	}

	/* Getters e setters */
	
	public String getTitular() {
		return titular;
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
	
	/* Funcoes de Saldo */
	
	public boolean deposita(double valor) {
		if (valor>0) {
			this.saldo += valor;
			return true;
		}else {
			return false;
		}
	}
	
	public boolean saca(double valor) {
		if (valor>0 || valor<this.saldo) {
			this.saldo -= valor;
			return true;
		}else {
			return false;
		}
	}
	
	public double getRendimento() {
		return this.saldo*0.1;
	}
	
	/* Funcao ficticia de validacao de cpf */
	
	private boolean validaCpf(String cpf) {
		return true;
	}

}
