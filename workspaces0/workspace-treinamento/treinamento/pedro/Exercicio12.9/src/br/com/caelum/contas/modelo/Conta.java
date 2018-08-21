package br.com.caelum.contas.modelo;

import br.com.caelum.contas.Data;
import execcao.SaldoInsuficienteException;

public class Conta {
	
	private String titular;
	private int numero;
	protected double saldo;
	private static int identificador;
	private String agencia;
	Data dataDeAbertura = new Data();
	
    public String getTipo() {
        return "Conta";
    }
    
    public Conta() {
    //	this.titular = titular;
    }
    
	public Conta(String titular) {
		this.titular = titular;
    }
	
	public static int getIdentificador() {
		return identificador;
	}

	public void saca(double quantidade) {
		if (this.saldo < quantidade ) {
			throw new SaldoInsuficienteException("Saldo Insuficiente, " + 
												"tente um valor menor");
		} else {
			this.saldo -= quantidade;
		}
		
	}

	public void deposita(double quantidade) {
		double novoSaldo = this.saldo + quantidade;
		this.saldo = novoSaldo;
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
	
	
    public void setTitular(String titular) {
        this.titular = titular;
    }
    

    public void setAgencia(String agencia) {
        this.agencia = agencia;
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
    	rendimento = this.saldo*0.1; // Multiplica o saldo por 0.1
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