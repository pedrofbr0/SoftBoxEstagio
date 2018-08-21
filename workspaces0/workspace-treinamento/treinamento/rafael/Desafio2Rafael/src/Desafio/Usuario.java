package Desafio;

public class Usuario {
	
	private String nome;
	private Data data;

	//construtores
	
	public Usuario() {
		
	}
	public Usuario(String nome, String data) {
		this.nome = nome;
		this.data = new Data(data);
	}
	
	//getters e setters
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setData(String data) {
		this.data = new Data(data);
	}
	
	public String getData(){
		return data.getData();
	}
	
	public boolean isValid() {
		return data.isValid();
	}
	
	

}
