package br.com.caelum.contas.modelo;

import br.com.caelum.contas.Data;

public abstract class Conta  implements Comparable<Conta> {
	private String titular;
	private int numero;
	protected double saldo;
	private static int identificador;
	private String agencia;
	Data dataDeAbertura = new Data();

	public abstract String getTipo();

	public Conta() {

	}

	public Conta(String titular) {
		this.titular = titular;
	}

	public static int getIdentificador() {
		return identificador;
	}

	public int compareTo(Conta outraConta) {
		return this.titular.compareTo(outraConta.titular);
	}

	public void saca(double quantidade) {
		if (quantidade < 0) {
			throw new IllegalArgumentException("Você tentou depositar" + " um valor negativo");
		} else {
			double novoSaldo = this.saldo - quantidade;
			this.saldo = novoSaldo;
		}
	}

	public void deposita(double valor) {
		if (valor < 0) {
			throw new IllegalArgumentException("Você tentou sacar" + " um valor negativo");
		} else {
			this.saldo += valor;
		}
	}

	public String getTitular() {
		return this.titular;
	}

	public String getAgencia() {
		return this.agencia;
	}

	public int getNumero() {
		return this.numero;
	}

	public double getSaldo() {
		return this.saldo;
	}
	
    @Override
    public String toString() {
		String maiuscula = titular.toUpperCase();
        return "[titular=" + maiuscula + ", numero=" + numero
           + ", agencia=" + agencia + " saldo= " + saldo + "]";
    }
    
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + numero;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public void setAgencia(String i) {
		this.agencia = i;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void transfere(double valor, Conta conta) {
		this.saca(valor);
		conta.deposita(valor);
	}

	public double calculaRendimento() {
		double rendimento; // cria variavel de rendimentos
		rendimento = this.saldo * 0.1; // Multiplica o saldo por 0.1
		return rendimento; // retorna o valor do rendimento
	}

	public String recuperaDadosParaImpressao() {
		String dados = "Titular: " + this.titular;
		dados += "\nNúmero: " + this.numero;
		dados += "\nAgência: " + this.agencia;
		dados += "\nSaldo: " + this.saldo;
		dados += "\nData de abertura: " + this.dataDeAbertura.formatar();
		return dados;
	}

}