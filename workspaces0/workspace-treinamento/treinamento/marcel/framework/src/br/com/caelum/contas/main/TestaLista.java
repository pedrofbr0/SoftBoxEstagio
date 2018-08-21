package br.com.caelum.contas.main;

import java.util.*;

import br.com.caelum.contas.modelo.*;

public class TestaLista {

	public static void main(String[] args) {
		Random rand = new Random();
//		ArrayList arrayList = new ArrayList();
		List<Conta> contas = new LinkedList<Conta>();
		for (int i = 0; i < 3; i++){
			ContaCorrente cc = new ContaCorrente();
			cc.setNumero(123);
			cc.setTitular("Batman");
			cc.setAgencia("698");
			cc.deposita(new Random().nextInt(1000));
			contas.add(cc);
		}
	    System.out.println(contas);
		
	}

}
