package teste;
import br.com.caelum.contas.modelo.*;
import br.com.caelum.javafx.api.main.*;


public class TestaException2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContaCorrente cc = new ContaCorrente();
		ContaPoupanca cp = new ContaPoupanca();
		
		cc.deposita(100.0);
		cp.deposita(100.0);
		
		try {
			cc.saca(500.0);
			cp.saca(50.0);
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}
