package br.com.caelum.contas.main;

import br.com.caelum.contas.modelo.*;
import br.com.caelum.contas.*;

public class TestaTributavel {
	
	public static void main(String[] args) {
		
		ContaCorrente cc = new ContaCorrente();
		cc.deposita(100);
		System.out.println(cc.getValorImposto());
		
		Tributavel t = cc;
		System.out.println(t.getValorImposto());
	}

}
