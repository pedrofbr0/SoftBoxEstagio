package Testes;
import br.com.caelum.contas.modelo.*;

public class TestaBanco {

	public static void main(String args[]) {
		
		Banco b = new Banco("Banco do Braséu",666);
		
		for (int i = 0; i < 5; i++) {
			Conta c = new ContaCorrente();
			c.setAgencia("333");
			c.setDataAbertura(4, 5, 6);
			c.setNumero(555);
			c.setTitular("Rogerinho");
			c.deposita(i*100+10);
			b.adiciona(c);
		}
		
		b.getContas();
		
		Conta c = new ContaCorrente();
		
		c.setNumero(555);
		c.setAgencia("333");
		
		System.out.println(b.contem(c));
		
	}
	
}
