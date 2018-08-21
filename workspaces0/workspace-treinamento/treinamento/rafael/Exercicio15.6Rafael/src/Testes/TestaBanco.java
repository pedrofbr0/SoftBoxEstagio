package Testes;
import java.util.*;
import br.com.caelum.contas.modelo.*;

public class TestaBanco {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Banco b = new Banco("Banco do Braséu",666);
		ContaCorrente cc1 = new ContaCorrente();
		ContaCorrente cc2 = new ContaCorrente();
		Conta c;
		Set<Conta> contasSet = new HashSet<>(); 
		
		cc1.setAgencia("222");
		cc1.setNumero(222);
		cc1.setTitular("Rogerinho");
		cc2.setAgencia("222");
		cc2.setNumero(222);
		cc2.setTitular("Maurilio");
		
		contasSet.add(cc1);
		contasSet.add(cc2);
		
		b.adiciona(cc1);
		b.adiciona(cc2);
		
		
		System.out.println(contasSet.size());
		c = b.pega(0);
		System.out.println(c);
		
		c = b.buscaPorTitular("Rogerinho");
		System.out.println(c);
		System.out.println("Total de contas: " + b.getQuantidadeDeContas());
	}

}
