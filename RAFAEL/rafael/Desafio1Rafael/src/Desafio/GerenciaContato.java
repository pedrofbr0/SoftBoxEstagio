package Desafio;
import java.util.*;
import java.io.*;
import java.lang.*;

public class GerenciaContato {
	
	private Map<String,Contato> contatos;
	private List<Contato> contatosLista;
	private File escrita;
	private File leitura;
	
	
	public GerenciaContato() {
		this.escrita = new File("contatosDefault.txt");
		this.leitura = new File("contatosDefault.txt");
		this.contatosLista = new ArrayList<>();
		this.contatos = new HashMap();
		
	}
	public boolean addContato(String nome, String telefone, String data) {
		Contato c = new Contato(nome,telefone,data);
		addContato1(nome, telefone, c);
		return false;
	}
	public boolean addContato(String nome, String telefone, int dia, int mes) {
		Contato c = new Contato(nome,telefone,dia,mes);
		addContato1(nome, telefone, c);
		return false;
	}
	
	public boolean addContato1(String nome, String telefone, Contato c) {
		if (contatos.containsKey(nome)==false){
			if(c.getData().equals("Data invalida")) {
				return false;
			}
			contatos.put(c.getNome(), c);
			contatosLista.add(c);
			return true;
		}
		return false;
	}
	
	public Contato pesquisaContato(String nome){
		if (contatos.containsKey(nome)) {
			Contato c = contatos.get(nome);
			return c;
		}
		return null;
	}
	
	public String removeContato(String nome) {	
		Contato c = pesquisaContato(nome);
		
		if (c==null) {
			return "Contato inválido";
		}else {
			contatosLista.remove(c);
			contatos.remove(c.getNome());
			return "Contato removido";
		}
	}
	
	public List<Contato> getLista() {
		return contatosLista;
	}
	
	public List<Contato> getListaLetra(char l){
		List<Contato> listaLetra = null;
		
		for (Contato x : contatosLista) {
			if (x.getNome().charAt(0)==l) {
				try {
					listaLetra.add(x);
				} catch (NullPointerException e) {
					listaLetra = new ArrayList<>();
					listaLetra.add(x);
				}
			}
		}
		return listaLetra;
	}
	
	public List<Contato> getListaAniversario(int mes){
		List<Contato> listaMes = null;
		
		for (Contato x : contatosLista) {
			if (x.getMes()==mes) {
				try {
					listaMes.add(x);
				} catch (NullPointerException e) {
					listaMes = new ArrayList<>();
					listaMes.add(x);
				}
			}
		}
		return listaMes;
	}

	public void listaContatos() {
		System.out.println(contatosLista);
	}
	
	public void setArquivoEscrita(File file) {
		this.escrita = file;
	}
	
	public void setArquivoLeitura(File file) {
		this.leitura = file;
	}
	
	public void gravaContatos() {
		PrintStream stream;
		try {
			stream = new PrintStream(escrita);
			for (Contato c : contatosLista) {
				stream.println(c);
			}
			stream.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				escrita.createNewFile();
				stream = new PrintStream(escrita);
				for (Contato c : contatosLista) {
					stream.println(c);
				}
				stream.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void carregaContatos() {
		String[] dados;
		Contato c = new Contato();
		
		try{
			Scanner s = new Scanner(leitura);
			contatosLista = new ArrayList<>();
			contatos = new HashMap();
			
			while(s.hasNextLine()) {
				dados = s.nextLine().split(",");
				c = new Contato(dados[0],dados[1],dados[2]);
				contatosLista.add(c);
				contatos.put(dados[0],c);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("nao achou o arquivo");
			try {
				leitura.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
	
	
