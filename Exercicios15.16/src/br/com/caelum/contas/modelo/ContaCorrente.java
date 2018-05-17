package br.com.caelum.contas.modelo;


public class ContaCorrente extends Conta implements Tributavel {
    @Override
    public void saca(double valor) {
    	if(this.saldo < valor) {
        	throw new IllegalArgumentException("Saldo Insuficiente," 
        										+ "tente um valor menor");
        } else {
        	this.saldo -= (valor + 0.10);
        }
    }

	public String getTipo() {
		return super.getTipo()+ " Corrente";
	}

	@Override
	public double getValorImposto() {
		return this.getSaldo() * 0.01;
	}
}
