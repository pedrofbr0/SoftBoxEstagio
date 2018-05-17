package br.com.caelum.contas.modelo;

public class ContaPoupanca extends Conta {
    @Override
    public void saca(double valor) {
        this.saldo -= (valor + 0.10);
    }

	public String getTipo() {
		return super.getTipo()+ " Poupança";
	}
}
