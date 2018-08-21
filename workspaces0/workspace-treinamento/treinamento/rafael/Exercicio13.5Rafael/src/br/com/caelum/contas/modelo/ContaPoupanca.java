package br.com.caelum.contas.modelo;

public class ContaPoupanca extends Conta {
	
	public String getTipo() {
		return "Conta Poupança";
	}
	
	public void saca(double valor){
		if (valor<0) {
			throw new IllegalArgumentException("Valor Negativo");
		} else if (valor>this.saldo) {
			throw new SaldoInsuficienteException(valor);
		}else {
			this.saldo -= valor;
		}	
	}

}
