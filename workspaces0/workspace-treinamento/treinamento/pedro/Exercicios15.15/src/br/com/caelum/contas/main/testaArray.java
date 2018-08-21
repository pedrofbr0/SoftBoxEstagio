/*package br.com.caelum.contas.main;

import br.com.caelum.contas.modelo.*;

public class testaArray {
	
	public static void main (String[] args) {
		Conta[] contas = new Conta[10];
		
		for (int i = 0; i < contas.length; i++) {
			Conta conta = new ContaCorrente();
			conta.deposita(i * 100);
			contas[i] = conta;
			System.out.println(contas[i].getSaldo());
		}
			double media = 0;
			
		for (int i = 0; i < contas.length; i++) {
			 media += contas[i].getSaldo()/contas.length;
		}
		System.out.println(media);
		
		TestaSplit teste = new TestaSplit();
		
		teste.split("Allahu Akbar Eita Porra Eh Nois");
		
	}
}*/


