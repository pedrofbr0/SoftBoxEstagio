
public class Usuario {
	
	private String nome;
	private Data data;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String data) {

		this.nome = nome;
		this.data = new Data();
		this.data.setDataSt(data);

	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Data getData() {
		return data;
	}
	
	public String getDataS() {
		return data.getDataSt();
	}
	
	public void setData(Data data) {
		this.data = data;
	}

}
