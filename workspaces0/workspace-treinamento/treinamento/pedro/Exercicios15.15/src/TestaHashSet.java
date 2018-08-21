import br.com.caelum.contas.modelo.Banco;
import br.com.caelum.contas.modelo.ContaCorrente;

public class TestaHashSet {
	
	public static void main(String[] args) {

		Banco banco = new Banco("MeuBanco", 666);
	
		for (int i = 0; i < 5; i++) {
			ContaCorrente conta = new ContaCorrente();
			conta.setTitular("Titular " + i);
			conta.setNumero(i);
			conta.setAgencia("1000");
			conta.deposita(i * 1000);
			banco.adiciona(conta);
		 }
		
		for (int i = 0; i < 5; i++) {
			System.out.println(banco.pega(i));
		}
	}
}
