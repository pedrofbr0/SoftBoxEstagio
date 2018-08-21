/*
 *Exercicio1
 * 
 * 1.0
 *
 * 02/03/2018 14:41
 * 
 * Pedro
 * 
 * Copyright notice
 * 
 * Este código cria uma pessoa e faz com que ela faça aniversários,
 * mostrando o nome e a idade logo em seguida
 */

public class Programa1 {
	public static void main(String[] args) {
		Pessoa Eu = new Pessoa();
		Eu.nome = "Pedro";
		Eu.idade = 22;
		
		Eu.fazAniversario(4);
		
		System.out.println(Eu.nome + Eu.idade);
	}
}
