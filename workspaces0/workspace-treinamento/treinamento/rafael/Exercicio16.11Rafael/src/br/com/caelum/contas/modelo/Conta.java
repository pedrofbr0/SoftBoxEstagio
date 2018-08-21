package br.com.caelum.contas.modelo;
import br.com.caelum.contas.data.*;

public abstract class Conta implements Comparable<Conta> {
	private String titular, agencia;
	private Data data = new Data();
	private int numero;
	protected double saldo = 0.0;
	private static int identificadorGeral = 1;
	private int identificador = 0;
	private String cpf = "";
	
	
	public Conta (String titular, String cpf){
		this.cpf = cpf;
		this.titular = titular;
		this.identificador = Conta.identificadorGeral;
		Conta.identificadorGeral++;
	}
	
	public Conta() {
		this.titular = titular;
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
	
	public void deposita(double valor) {
		if (valor>=-.0000001) {
			this.saldo += valor;
		}else {
			throw new IllegalArgumentException("Valor negativo");
		}
	}
	
	public void saca(double valor) {
		this.saca(valor);
	}
	
	public double getRendimento() {
		return this.saldo*0.1;
	}
	
	public String getTipo(){
		return "Conta";
	}
	
	public void transfere (double valor, Conta conta) {
		this.saca(valor);
		conta.deposita(valor);
	}
	
	public String toString() {
		return "[titular=" + titular.toUpperCase() + " saldo = " + this.saldo + "]";            
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + numero;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}
	
	public int compareTo(Conta outra) {
		return this.titular.compareTo(outra.titular);
	}

}
