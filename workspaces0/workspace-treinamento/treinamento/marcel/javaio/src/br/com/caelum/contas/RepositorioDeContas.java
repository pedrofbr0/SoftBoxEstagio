package br.com.caelum.contas;

import java.io.*;
import java.util.*;
import br.com.caelum.contas.modelo.*;

public class RepositorioDeContas {

	public void salva(List<Conta> contas) throws FileNotFoundException {
		PrintStream stream = new PrintStream("contas.txt");
		for (Conta conta : contas) {
			stream.println(conta.getTipo() + "," + conta.getNumero() + "," + conta.getAgencia() + ","
					+ conta.getTitular() + "," + conta.getSaldo());
		}
		stream.close();
	}

	public List<Conta> carrega() {
		List<Conta> contas = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File("contas.txt"))) {
			scanner.useDelimiter(",");
			while (scanner.hasNextLine()) {
				Conta conta;
				String linha = scanner.nextLine();
				String[] valores = linha.split(",");
				String tipo = valores[0];
				int numero = Integer.parseInt(valores[1]);
				String agencia = valores[2];
				String titular = valores[3];
				double saldo = Double.parseDouble(valores[4]);
				if(tipo.equals("Conta Corrente")) {
					conta = new ContaCorrente();
				}
				else {
					conta = new ContaPoupanca();
				}
				conta.setAgencia(agencia);
				conta.setNumero(numero);
				conta.setTitular(titular);
				conta.deposita(saldo);
				contas.add(conta);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Não tem arquivo ainda");
		}
		return contas;
	}

}
