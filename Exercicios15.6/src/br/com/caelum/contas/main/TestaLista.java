package br.com.caelum.contas.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import br.com.caelum.contas.main.*;
import br.com.caelum.contas.main.*;
import br.com.caelum.contas.modelo.*;
import br.com.caelum.javafx.api.util.*;

public class TestaLista {
	
	public static void main(String[] args) {
		
		Banco banco = new Banco("MeuBanco", 666);
		
		//ArrayList<Conta> contas = new ArrayList<>();
		
		List<Conta> contas = new LinkedList<Conta>();
		
		int num = 0;
		 
		Random rnd = new Random();
		 
		for (int i = 0; i < 5; i++) {
			ContaCorrente conta = new ContaCorrente();
			conta.setTitular("Titular " + i);
			conta.setNumero(i);
			conta.setAgencia("1000");
			conta.deposita(rnd.nextInt(10000));
			contas.add(conta);
			
			banco.adiciona(conta);
		} 
		
		System.out.println(contas);
		
		banco.mostraContas();
	}
}
