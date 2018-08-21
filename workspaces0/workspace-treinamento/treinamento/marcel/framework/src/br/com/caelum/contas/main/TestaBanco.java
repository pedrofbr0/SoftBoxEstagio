package br.com.caelum.contas.main;

import br.com.caelum.contas.modelo.*;

public class TestaBanco {

	public static void main(String[] args) {
		Banco banco = new Banco("CaelumBank", 999);

		for (int i = 0; i < 10; i++) {
			ContaCorrente conta = new ContaCorrente();
			conta.setTitular("Titular " + i);
			conta.setNumero(i);
			conta.setAgencia("1000");
			conta.deposita(i * 1000);
			banco.adiciona(conta);
		}
		banco.mostraContas();

	}

}
