package Modelos;

public class Contato {
	
	//Atributos
	private String nome;
	private String numeroCel;
	private Data data;
	
	//Construtores
	public Contato() {}
	
	public Contato(String nome, String numeroCel, int dia, int mes) {
		setNome(nome);
		setNumeroCel(numeroCel);
		setData(dia,mes);
	}
	
	public Contato(String nome, String numeroCel, String data) {
		setNome(nome);
		setNumeroCel(numeroCel);
		setDataS(data);
	}
	
	//Métodos
	public String getNome() {
		return this.nome;
	}
	
	public String getNumeroCel() {
		return numeroCel;
	}
	
	public String getDataS() {
		return this.data.getData();
	}
	
	public int getDataMes() {
		
		String d = this.data.getData();
		String[] mes = d.split("/");
		return Integer.parseInt(mes[1]);
		
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNumeroCel(String numeroCel) {
		this.numeroCel = numeroCel;
	}
	
	public void setData(int dia, int mes) {
		this.data = new Data(dia, mes);
	}
	
	public void setDataS(String data) {
		this.data = new Data(data);
	} 

	@Override
	public String toString() {
		return nome + "," + numeroCel + "," + data.getData();
	}
	
}
