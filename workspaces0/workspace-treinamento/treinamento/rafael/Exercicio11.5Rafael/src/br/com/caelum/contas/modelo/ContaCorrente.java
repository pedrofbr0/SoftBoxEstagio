package br.com.caelum.contas.modelo;

public class ContaCorrente extends Conta implements Tributavel {
	
	public String getTipo() {
		return "Conta Corrente";
	}
	
	public void saca (double valor) {
		this.saldo -= (valor+0.10);
	}

	@Override
	public double getValorImposto() {
		// TODO Auto-generated method stub	
		return this.getSaldo()*0.01;
	}

}
