package Desafio;

public class Contato {
	
	private String nome;
	private String telefone;
	private Data data;

	public Contato() {}
	
	public Contato(String nome, String telefone, int dia, int mes) {
		setNome(nome);
		setTelefone(telefone);
		setData(dia,mes);
	}
	
	public Contato(String nome, String telefone, String data) {
		setNome(nome);
		setTelefone(telefone);
		setDataString(data);
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public String getData() {
		return  data.getData();
	}
	
	public int getMes() {
		return data.getMes();
	}
	
	private void setNome(String nome) {
		this.nome = nome;
	}
	
	private void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	private void setData(int dia, int mes) {
		this.data = new Data(dia,mes);
	}
	
	private void setDataString(String data) {
		this.data = new Data(data);
	}
	
	public String toString() {
		return nome + "," + telefone + "," + data.getData();
	}
	
}
