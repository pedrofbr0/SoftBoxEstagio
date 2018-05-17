/*
 * Exercicio3
 * 
 * 1.0
 *
 * 02/03/2018 13:36
 * 
 * Pedro
 * 
 * Copyright notice
 * 
 * Este código calcula a sequência de Fibonacci até o 6º termo
 */

public class Exercicio3 {
	public static void main(String[] args) {
		
		Fibonacci2 fibonacci = new Fibonacci2();
	    for (int i = 1; i <= 6; i++) {
	        int resultado = fibonacci.calculaFibonacci2(i);
	        System.out.println(resultado);
	    }
	}
}
