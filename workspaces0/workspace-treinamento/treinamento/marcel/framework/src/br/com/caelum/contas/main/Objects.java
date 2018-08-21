package br.com.caelum.contas.main;

import java.io.PrintStream;
import java.lang.Throwable;
import java.util.*;

import br.com.caelum.contas.modelo.ContaCorrente;

public class Objects {

	public static void main(String[] args) {
//		System.out.println(new Throwable());
//		PrintStream saida = System.out;
//		saida.println(saida);
//		
		ContaCorrente c1 = new ContaCorrente();
		ContaCorrente c2 = new ContaCorrente();
	    Set<Object> contas = new HashSet<>();
		c1.setAgencia("123");
		c2.setAgencia("123");
		c1.setNumero(12);
		c2.setNumero(12);
		contas.add(c1);
		contas.add(c2);
		System.out.println(contas.size());

	}

}
