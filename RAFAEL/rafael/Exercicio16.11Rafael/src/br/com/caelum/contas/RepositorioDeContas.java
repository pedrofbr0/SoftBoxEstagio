package br.com.caelum.contas;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import br.com.caelum.contas.modelo.*;
import java.nio.file.*;

public class RepositorioDeContas {
	
	public void salva (List <Conta> contas) {
		PrintStream stream;
		try {
			stream = new PrintStream("contas.txt");
			for (Conta conta : contas) {
				stream.println(conta.getTipo() + "," + conta.getNumero() + "," + conta.getAgencia()+
									"," + conta.getTitular() + "," + conta.getSaldo());
			}
			stream.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Conta> carrega(){
		File arquivo = new File("/home/rafael/workspaces/workspace-treinamento/Exercicio16.11Rafael/contas.txt");
		Scanner s;
		String st;
		String [] dados;
		Conta c;
		List <Conta> contas = new ArrayList<>();
		
		
		try {
			s = new Scanner(arquivo);
			while(s.hasNextLine()) {
				st = s.nextLine();
				dados = st.split(",");
				if(dados[0].equals("Conta Corrente")) {
					c=new ContaCorrente();
					c.setNumero(Integer.parseInt(dados[1]));
					c.setAgencia(dados[2]);
					c.setTitular(dados[3]);
					c.deposita(Double.parseDouble(dados[4]));
					contas.add(c);
				}else if (dados[0].equals("Conta Poupança")) {
					c = new ContaPoupanca();
					c.setNumero(Integer.parseInt(dados[1]));
					c.setAgencia(dados[2]);
					c.setTitular(dados[3]);
					c.deposita(Double.parseDouble(dados[4]));
					contas.add(c);
				}
			}
			
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("nao achou o arquivo");
			e.printStackTrace();
		}
		
		return contas;
	}
}