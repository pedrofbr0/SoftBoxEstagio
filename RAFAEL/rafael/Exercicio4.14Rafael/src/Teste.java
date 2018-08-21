
public class Teste {

	public static void main(String[] args) {
		
		/* Parte 1 */
		
		Pessoa pessoa = new Pessoa();
		pessoa.nome = "Cerginho da Pereira Nunes";
		pessoa.idade = 37;
		
		System.out.println("Idade de " + pessoa.nome + " é: " + pessoa.idade);
		pessoa.fazAniversario();
		System.out.println("Idade de " + pessoa.nome + " é: " + pessoa.idade);

		/* Parte 2 */
		
		Porta porta = new Porta();
		
		porta.abre();
		porta.fecha();
		porta.cor = "Vermelha";
		porta.cor = "Preta";
		porta.estaAberta();
		
		/* Parte 3 */
		
		Casa casa = new Casa();
		
		casa.porta1.abre();
		casa.porta2.fecha();
		casa.porta3.abre();
		casa.pinta("Vermelha");
		
		System.out.println("A casa " + casa.cor + " tem " + casa.quantasPortasEstaoAbertas() + " portas abertas");
		
	}

}
