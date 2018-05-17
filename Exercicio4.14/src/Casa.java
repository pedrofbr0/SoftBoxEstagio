/*
 * Porta
 * 
 * 1.0
 *
 * 02/03/2018 14:41
 * 
 * Pedro
 * 
 * Copyright notice
 * 
 * Esta classe apresenta atributos e métodos de uma casa, em especial 
 * saber quantas pŕtas estão abertas
 */

public class Casa {
	String cor;
	Porta porta1 = new Porta();
	Porta porta2 = new Porta();
	Porta porta3 = new Porta();
	
	int quantasPortasEstaoAbertas() {
		int num = 0;
		if (porta1.estaAberta() == true) {
			num ++;
		}
		if (porta2.estaAberta() == true) {
			num ++;
		}
		if (porta3.estaAberta() == true) {
			num ++;
		}
		return num;
		}
}
	
		


