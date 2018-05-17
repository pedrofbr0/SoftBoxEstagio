package br.com.caelum.contas.main;

import br.com.caelum.contas.modelo.*;

public class Teste {

	public static void main(String[] args) {
		ContaCorrente c = new ContaCorrente();
		//c.deposita(-400);
		c.deposita(100);
		c.saca(300);
	}

}
