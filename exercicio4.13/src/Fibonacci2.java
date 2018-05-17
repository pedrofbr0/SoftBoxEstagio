/*
 * Exercicio3
 * 
 * 1.0
 *
 * 02/03/2018 13:37
 * 
 * Pedro
 * 
 * Copyright notice
 * 
 * Esta classe calcula a sequência de Fibonacci de maneira recursiva
 * utiliazndo-se do operador condicional ternário
 */

public class Fibonacci2 {
	int calculaFibonacci2(int i) {
		return i>2 ? calculaFibonacci2(i-1) + calculaFibonacci2(i-2) : 1;
			}
}
