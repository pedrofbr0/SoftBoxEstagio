/*
 * Exercicio1
 * 
 * 1.0
 *
 * 02/03/2018 13:34
 * 
 * Pedro
 * 
 * Copyright notice
 * 
 * Esta classe calcula a sequência de Fibonacci de maneira recursiva
 */

public class Fibonacci {
	int calculaFibonacci(int i) {
		if(i == 0) {
			return 0;
		}
		if(i == 1) {
			return 1;
		} else {
			return calculaFibonacci(i-1) + calculaFibonacci(i-2);
		}
	}
}
