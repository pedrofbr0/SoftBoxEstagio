package Testes;
import java.util.*;

public class TestePerformance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Iniciando ...");
		Collection<Integer> teste = new ArrayList<>();
		long inicio = System.currentTimeMillis();
		
		int total = 30000;
		
		for (int i = 0; i < total; i++) {
			teste.add(i);
		}
		
		long fim  = System.currentTimeMillis();
		long tempo = fim-inicio;
		System.out.println("Tempo gasto: " + tempo);
		
		for (int i = 0; i < total; i++) {
			teste.contains(i);
		}
		
		fim  = System.currentTimeMillis();
		tempo = fim-inicio;
		System.out.println("Tempo gasto: " + tempo);
		
	}

}
