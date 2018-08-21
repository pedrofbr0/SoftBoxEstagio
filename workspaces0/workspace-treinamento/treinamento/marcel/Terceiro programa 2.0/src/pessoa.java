
public class pessoa {

	public static void main(String[] args) {
		Pessoa carinha;
		carinha = new Pessoa();
		carinha.nome = "Um Dois Três de Oliveira Quatro";
		carinha.idade = 18;
		carinha.fazAniversario();
		carinha.fazAniversario();
		carinha.fazAniversario();
		System.out.println(carinha.nome + " possui " + carinha.idade + " anos de idade");
	}
	
}

class Pessoa {
	  String nome;
	  int idade;

	void fazAniversario() {
		this.idade++;
	    }
	}
