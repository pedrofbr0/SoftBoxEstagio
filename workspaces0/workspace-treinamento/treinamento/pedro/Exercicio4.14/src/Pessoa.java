/*
 * Pessoa
 * 
 * 1.0
 *
 * 02/03/2018 14:47
 * 
 * Pedro
 * 
 * Copyright notice
 * 
 * Esta classe apresenta atributos e métodos essencias à uma pessoa
 */

public class Pessoa {
	String nome;
	int idade;
	
	void fazAniversario(int valor) {
		this.idade += valor;
	}
}
