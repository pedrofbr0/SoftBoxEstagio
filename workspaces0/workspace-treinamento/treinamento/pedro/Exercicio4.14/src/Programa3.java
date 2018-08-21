/*
 * Programa3
 * 
 * 1.0
 *
 * 02/03/2018 14:44
 * 
 * Pedro
 * 
 * Copyright notice
 * 
 * Este código cria uma casa com 3 portas e verifica quantas estão abertas
 */


public class Programa3 {
	static void main(String[] args) {
		Casa casa = new Casa();
		casa.cor = "Branca";
		casa.porta1.aberta = true;
		casa.porta2.aberta = false;
		casa.porta3.aberta = true;
		
		casa.quantasPortasEstaoAbertas();
		
		System.out.println(casa.quantasPortasEstaoAbertas());
	}
}
