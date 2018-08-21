package Modelos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


import Modelos.*;

public class ManipuladorDeContatos {
	
	private List <Contato> listaContatos = new ArrayList<>();
	
	//private Set<Contato> setContatos = new HashSet<>();
	
	private Map <String, Contato> mapaContatos = new HashMap();
	
	private Contato contato;
	
	private File escrita = new File("contatos.txt");
	
	private File leitura = new File("contatos.txt");
	
	//Métodos
	public void inserir(String nome, String numeroCel, int dia, int mes) {
		if(pesquisar(nome) == null) {
			System.out.println("Contato já cadastrado");
		}
		Contato contato = new Contato(nome,numeroCel,dia,mes);
		mapaContatos.put(contato.getNome(), contato);
		listaContatos.add(contato);
	}

	public void remover(String nome) {
		
		Contato contato = pesquisar(nome);
		if (contato==null) {
			System.out.println("Contato não encontrado");
		}else {
			listaContatos.remove(contato);
			mapaContatos.remove(contato.getNome());
			System.out.println("Contato deletado");
		}
			
	}
	
	public void editar(String nomeAntes, String nome, String numeroCel, int dia, int mes) {
		System.out.println("Alo2");
		
		Contato contato = pesquisar(nomeAntes);
		if(contato==null) {
			System.out.println("Contato nao encontrado");
		}else {	
			contato.setNome(nome);
			contato.setNumeroCel(numeroCel);
			contato.setData(dia,mes);
			System.out.println("Contato editado");
		}
		
		
	}
	
	public Contato pesquisar(String nome) {

		if (mapaContatos.containsKey(nome)) {
			Contato contato = mapaContatos.get(nome);
			return contato;
		}
		return null;
		
	}
	
	public void listarTodosContatos() {
		System.out.println(listaContatos);		
		
		
	}
	
	public void listarComInicial(char letra) {
		
		for(Contato contato : listaContatos) {
			if(contato.getNome()!= null && contato.getNome().charAt(0) == letra) {
				System.out.println(contato);
			}
		}
	}
	
	public List <Contato> listarTodosComInicial(char letra) {
		
		
		List <Contato> inicial = new ArrayList<>(); 
		
		for(Contato contato : listaContatos) {
			if(contato.getNome()!= null && contato.getNome().charAt(0) == letra) {
				inicial.add(contato);
			}
		}
		
		return inicial;
	}
	
	public List<Contato> getLista() {
		return listaContatos;
	}
	
	public List <Contato> imprimirNiversMes(int mes) {
		
		List <Contato> nivers = new ArrayList<>(); 
	
		for(Contato contato : listaContatos) {
			if(contato.getDataMes() == mes) {
				nivers.add(contato);
				System.out.println(contato);
			}
		}
		
		return nivers;
		
	}
	
	public void setArquivoEscrita(File file) {
		this.escrita = file;
	}
	
	public void setArquivoLeitura(File file) {
		this.leitura = file;
	}
	
	public void salva() {
		
		PrintStream st;
		try {
			st = new PrintStream(escrita);
			for (Contato contato : listaContatos) {
				st.println(contato);
			}
			st.close();
		}catch (FileNotFoundException e) {
				try {
					escrita.createNewFile();
					st = new PrintStream(escrita);
					for (Contato contato : listaContatos) {
						st.println(contato);
					}
					st.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			e.printStackTrace();
		}
	}
	
	public void load() {
		Scanner s;
		String[] d;
		Contato contato;
		
		try{
			s = new Scanner(leitura);
			listaContatos = new ArrayList<>();
			mapaContatos = new HashMap();
			
			while(s.hasNextLine()) {
				d = s.nextLine().split(",");
				contato = new Contato(d[0],d[1],d[2]);
				listaContatos.add(contato);
				mapaContatos.put(d[0],contato);
			}
		} catch (FileNotFoundException e) {
			System.out.println("nArquivo não encontrado");
			e.printStackTrace();
		}
	}

}
