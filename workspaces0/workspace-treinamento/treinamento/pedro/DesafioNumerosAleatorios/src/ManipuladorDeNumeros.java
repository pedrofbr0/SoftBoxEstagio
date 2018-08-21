import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class ManipuladorDeNumeros {

	
	private Integer numero;
	
	private Usuario user;
	private List <Integer> listaNumeros;
	private Set<Integer> grupoNumeros;
	
	private File w;
	private File r;

	private Scanner scanner;
	
	private int counter;
	
	private int flag;
	
	public ManipuladorDeNumeros(String nome, String data) {

		user = new Usuario(nome, data);
		listaNumeros = new ArrayList<>();
		grupoNumeros = new HashSet<>();
	}
	
	public Integer gerarNumero() {
		
		Random geradorNumero = new Random();
	
		this.numero = geradorNumero.nextInt(89999999)+10000000;	
		
		while(grupoNumeros.contains(this.numero)) {
			this.numero = geradorNumero.nextInt(89999999)+10000000;		
		}
		
		grupoNumeros.add(this.numero);
		listaNumeros.add(this.numero);

		this.counter += 1;
		return this.numero;
		
	}
	
	public void remover() {
		listaNumeros.removeAll(listaNumeros);
		grupoNumeros.removeAll(grupoNumeros);
		this.counter = 0;
	}
	
	public String getListaSt() {
		
		String d = null;
		for(Integer numero : listaNumeros) {
			d = d + listaNumeros.get(numero) + "\n";
			System.out.println(d);
		}
		return d;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	public int getFlag() {
		return this.flag;
		
	}
	
	public void setFlag() {
		this.flag += 1;
	}
	
	public void setFlagZero() {
		this.flag = 0;
	}
	
	public void setArquivoDeEscrita(File arquivo) {
		this.w = arquivo;
		try {
			this.w.createNewFile();
		} catch (IOException e) {
			System.out.println("Erro na entrada ou saída de dados");
			e.printStackTrace();
		}
	}
	
	public void setArquivoDeLeitura(File arquivo) {
		this.r = arquivo;
	}
	
	public void salvar() {
		
		if (w==null) {
			w = new File("lista.txt");
		}
		
		PrintStream st = null;
		try {
			st = new PrintStream(w);
		} catch (FileNotFoundException e) {
			try {
				w.createNewFile();
			} catch (IOException e1) {
				System.out.println("Erro na entrada ou saída de dados");
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		st.println(this.toString());
		
		for (Integer numero : listaNumeros) {
			st.println(numero);
		}
		System.out.println("Arquivo salvo!");
		st.close();
		this.remover();
	}
	
	public String carregar() {
		
		try{
			scanner = new Scanner(this.r);
			String content = scanner.useDelimiter("\\Z").next();
			return content;
		} catch (FileNotFoundException e) {
			System.out.println("nArquivo não encontrado");
			e.printStackTrace();
		}
		return null;
	}
	
	public Usuario getUser() {
		return this.user;
	}
	
	@Override
	public String toString() {
		return "Nome: " + user.getNome() + "               Data: " + user.getDataS() + "\n\n\n";
	}
	

	
}
