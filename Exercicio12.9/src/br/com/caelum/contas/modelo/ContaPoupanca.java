package br.com.caelum.contas.modelo;

import execcao.*;

public class ContaPoupanca extends Conta {
    @Override
    public void saca(double valor) {
        if(this.saldo < valor) {
        	throw new SaldoInsuficienteException("Saldo Insuficiente," 
        										+ "tente um valor menor");
        } else {
        	this.saldo -= (valor + 0.10);
        }
    }

	public String getTipo() {
		return super.getTipo()+ " Poupança";
	}
}
