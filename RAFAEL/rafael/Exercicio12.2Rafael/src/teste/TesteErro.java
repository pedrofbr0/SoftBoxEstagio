package teste;
import br.com.caelum.contas.modelo.*;
import br.com.caelum.javafx.api.main.*;



public class TesteErro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("inicio do main");
		metodo1();
		System.out.println("fim do main");
	}

	private static void metodo1() {
		// TODO Auto-generated method stub
		System.out.println("inicio do metodo 1");
		try {
			metodo2();
		} catch(NullPointerException e) {
			System.out.println("erro: "+e);
		}
		System.out.println("fim do metodo 1");
	}

	private static void metodo2() {
		// TODO Auto-generated method stub
		System.out.println("inicio do metodo 2");
		ContaCorrente cc = new ContaCorrente();
		for (int i = 0; i <= 15; i++) {
				cc.deposita(i+1000);
				System.out.println(cc.getSaldo());
				if (i == 5) {
					cc = null;
				}
		} 
		System.out.println("fim do metodo 2");
	}
}
