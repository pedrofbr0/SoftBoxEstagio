package Desafio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ListaDeNumeros {
	
	private Usuario usuario;
	private File arquivo;
	private List <Integer> numeros;
	private Set <Integer> numerosSet;
	
	public ListaDeNumeros(Usuario u) {
		this.usuario = u;
		numeros = new ArrayList<>();
		arquivo = new File("numeros.txt");
		numerosSet = new HashSet<>();
	}
	
	//getters e setters
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public File getArquivo() {
		return this.arquivo;
	}
	
	public int getTamanho() {
		return this.numeros.size();
	}
	
	//gerar numeros e salvar no arquivo
	
	public Integer geraNumero() {
		Random numerosGerados = new Random();
		Integer numero = numerosGerados.nextInt(89999999) + 10000000;
		
		while(numerosSet.contains(numero)) {
			numero = numerosGerados.nextInt(89999999) + 10000000;
		}
		
		numerosSet.add(numero);
		numeros.add(numero);
		return numero;
	}
	
	public void gravaNumeros() {
		PrintStream stream;
		try {
			stream = new PrintStream(arquivo);
			stream.println("Nome: " + usuario.getNome() + "         Data: " + usuario.getData() );
			
			stream.println();
			
			for (Integer i : numeros) {
				stream.println(i);
			}
			stream.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				arquivo.createNewFile();	
				stream = new PrintStream(arquivo);
				stream.println("Nome: " + usuario.getNome() + "         Data: " + usuario.getData() );
				for (Integer i : numeros) {
					stream.println(i);
				}
				stream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
