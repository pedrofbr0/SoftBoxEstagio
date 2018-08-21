package br.com.caelum.contas.modelo;

public class ContaCorrente extends Conta {
	
	public String getTipo() {
		return "Conta Corrente";
	}
	
	public void saca (double valor){	
		if (valor<0) {
			throw new IllegalArgumentException("Valor Negativo");
		} else if ((valor+0.10)>this.saldo) {
			throw new SaldoInsuficienteException(valor);
		}else {
			this.saldo -= (valor+0.10);
		}	
	}
}
