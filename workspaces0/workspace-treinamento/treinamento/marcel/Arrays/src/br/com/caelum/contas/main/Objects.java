package br.com.caelum.contas.main;

import java.io.PrintStream;
import java.lang.Throwable;

public class Objects {

	public static void main(String[] args) {
		System.out.println(new Throwable());
		PrintStream saida = System.out;
		saida.println(saida);

	}

}
