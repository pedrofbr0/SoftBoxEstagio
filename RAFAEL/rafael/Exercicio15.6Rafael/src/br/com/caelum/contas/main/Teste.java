package br.com.caelum.contas.main;
import br.com.caelum.contas.modelo.*;
import br.com.caelum.javafx.api.main.*;
import java.util.*;

public class Teste {
	
	int x = 37;

	public static void main(String[] args) {
		
//		SistemaBancario.mostraTela(false);
		
		List<Conta> contas = new ArrayList<>();
		ContaCorrente cc1 = new ContaCorrente();
		ContaCorrente cc2 = new ContaCorrente();
		ContaCorrente cc3 = new ContaCorrente();
		ContaCorrente cc4 = new ContaCorrente();


		cc1.setTitular("bbb");
		cc2.setTitular("ccc");
		cc3.setTitular("aaa");
		cc4.setTitular("ddd");
		
		contas.add(cc1);
		contas.add(cc2);
		contas.add(cc3);
		contas.add(cc4);
		
		Collections.sort(contas);
		System.out.println(contas);

		
		Collections.reverse(contas);		
		System.out.println(contas);
		
		Collections.shuffle(contas);
		System.out.println(contas);
		
		List <Conta> contas2 = new LinkedList<>();
		
		for (int i = 0; i < 4; i++) {
			ContaCorrente cc = new ContaCorrente();
			cc.deposita(new Random().nextDouble()*100);
			cc.setTitular("Rogerinho" + i);
			contas2.add(cc);
		}
		
		System.out.println(contas2);

		/* 12.12
		 * 
		 * Quando acaba a memória da JVM é lançada a exceção java.lang.OutOfMemoryError,
		 * que imprime o stack trace da JVM.
		 * 
		 */
		
	}

}
