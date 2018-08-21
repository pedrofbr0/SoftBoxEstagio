package br.com.caelum.contas.modelo;

import br.com.caelum.contas.Data;

public interface Conta {
	
	public double getSaldo();
	public void deposita(double valor);
	public void saca(double valor);
	public void atualiza(double taxaSelic);
}