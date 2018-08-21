package br.com.caelum.contas.main;

import br.com.caelum.contas.modelo.*;

public class TestaArrays {

	public static void main(String[] args) {
		Conta[] contas = new Conta[10];

		for (int i = 0; i < contas.length; i++) {
			Conta conta = new ContaCorrente();
			conta.deposita(i * 100.0);
			contas[i] = conta;
		}
		double counter = 0;
		for (int i = 0; i < contas.length; i++) {
			Conta conta = new ContaCorrente();
			conta = contas[i];
			counter += conta.getSaldo();
		}
		System.out.println(counter/contas.length);

	}

}
