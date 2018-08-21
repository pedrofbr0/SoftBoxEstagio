
public class Contatos {
	private String nome;
	private int telefone;
	String nascimento;

	public String getNome() {
		return nome;
	}

	public int getTelefone() {
		return telefone;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public void setNascimento(String ddmm) {
		this.nascimento = ddmm;
	}

	@Override
	public String toString() {
		return "[Nome: " + this.nome + ",Telefone: " + this.telefone + ",Nascimento: " + this.nascimento + "]";
	}

}
