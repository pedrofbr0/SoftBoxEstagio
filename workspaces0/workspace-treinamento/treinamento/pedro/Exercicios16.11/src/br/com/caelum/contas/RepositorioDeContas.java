package br.com.caelum.contas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import br.com.caelum.contas.modelo.Conta;
import br.com.caelum.contas.modelo.ContaCorrente;
import br.com.caelum.contas.modelo.ContaPoupanca;

public class RepositorioDeContas {
	
	  public void salva(List<Conta> contas) {
	       PrintStream stream = null;
		try {
			stream = new PrintStream("contas.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       for (Conta conta : contas) {
	           stream.println(conta.getTipo() + "," + conta.getNumero() + ","
	               + conta.getAgencia() + "," + conta.getTitular() + ","
	               + conta.getSaldo());
	       }
	       stream.close();
	  }
	  

		public List<Conta> carrega() {
			
		File minhasContas = new File("/home/pedro/Documentos/workspaces/workspace-treinamento/treinamento/pedro/Exercicios16.11/contas.txt");
			
		Scanner scanner;
			
		List <Conta> contas = new ArrayList<>();
		Conta conta;
		String linha;
		String[] valores;
		    
		try {
			scanner = new Scanner(minhasContas);		    
				
			while (scanner.hasNextLine()) {
					
				linha = scanner.nextLine();
				valores = linha.split(",");
					
				if (valores[0].equals("Conta Corrente")) {
					conta = new ContaCorrente();
				    conta.setNumero(Integer.parseInt(valores[1]));
					conta.setAgencia(valores[2]);
					conta.setTitular(valores[3]);
					conta.deposita(Double.parseDouble(valores[4]));
					contas.add(conta);
				} else if (valores.equals("Conta Poupança")) {
				    conta = new ContaPoupanca();
				    String numeroTexto = valores[1];
				    int numero = Integer.parseInt(numeroTexto);
				    conta.setNumero(numero);
					conta.setAgencia(valores[2]);
					conta.setTitular(valores[3]);
					conta.deposita(Double.parseDouble(valores[4]));
					contas.add(conta);
				}
			}
				
			scanner.close();
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		    
		    return contas;
	}
}

