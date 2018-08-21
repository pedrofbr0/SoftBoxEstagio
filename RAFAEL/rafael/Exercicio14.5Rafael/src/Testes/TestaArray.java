package Testes;
import br.com.caelum.contas.modelo.*;
import br.com.caelum.javafx.api.main.*;

public class TestaArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Conta [] contas = new Conta[10];
		int media = 0;
		
		for (int i = 0; i < contas.length; i++) {
			Conta conta = new ContaCorrente();
			conta.deposita(i*100+1);
			contas[i] = conta;
		}
		
		for (Conta x : contas) {
			media += x.getSaldo();
		}
		
		media /= contas.length;
		
		System.out.println(media);
		
		String[] s = TestaSplit("Socorram-me, subi no ônibus em Marrocos");
		
		for (String x : s) {
			System.out.print(x+" ");
		}
	}
	
	public static String[] TestaSplit(String s) {
		String [] s2 = s.split(" ");
		String[] s3 = new String[(s2.length)];
		
		for (int i = 0; i < s2.length; i++) {
			s3[i] = s2[s2.length-i-1];
		}
		
		return s3;
	}
}
