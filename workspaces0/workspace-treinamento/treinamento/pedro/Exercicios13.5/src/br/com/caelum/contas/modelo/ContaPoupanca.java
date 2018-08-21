package br.com.caelum.contas.modelo;

import br.com.caelum.contas.modelo.*;

public class ContaPoupanca extends Conta {
    @Override
    public void saca(double valor) {
        if(this.saldo < valor) {
        	throw new IllegalArgumentException("Saldo Insuficiente, " 
        										+ "tente um valor menor");
        } else {
        	this.saldo -= (valor + 0.10);
        }
    }

	public String getTipo() {
		return super.getTipo()+ " Poupança";
	}
}
