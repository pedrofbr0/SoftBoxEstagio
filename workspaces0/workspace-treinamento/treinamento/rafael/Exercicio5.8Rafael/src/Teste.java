
public class Teste {
	
	int x = 37;

	public static void main(String[] args) {
		Conta c = new Conta("Cerginho da Pereira Nunes","888888");
		c.setAgencia("3333-3");
		c.setNumero(123456);
		c.setDataAbertura(2,3,2018);
		
		System.out.println("Identificador da conta: " + c.getIdentificador());
		System.out.println("Nome do titular: " + c.getTitular());
		System.out.println("Numero da agencia: " + c.getAgencia());
		System.out.println("Numero da conta: " + c.getNumero());
		System.out.println("Data de abertuar: " + c.getDataAbertura());
		System.out.println("Saldo atual : " + c.getSaldo());
		
		c.deposita(100.0);
		System.out.println("Depositando 100 dinheiros, saldo atual: " + c.getSaldo());
		c.saque(80.0);
		System.out.println("Sacando 80 dinheiros, saldo atual: " + c.getSaldo());
		
		Conta c2 = new Conta("aaaa","333");
		
		System.out.println("Novo objeto criado, identificador: " + c2.getIdentificador());
				
		/* Parte 5.9: O código não compila pois x é atributo da classe Teste
		 * e só pode ser acessado quando for criado um objeto da classe Teste.
		 *	Delcará-lo como um atributo estático resolveria o problema.
		 */
		
		/* 5.10 : A ideia é declarar o construtor de FabricaDeCarro como private,
		 * criar uma instancia de FabricaDeCarro, e, usar um método getInstance
		 * para retornar a referencia para essa única fábrica de carro criada.
		 */

	}

}
