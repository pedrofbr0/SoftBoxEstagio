package br.com.caelum.contas.main;

import br.com.caelum.contas.modelo.Casa;
import br.com.caelum.contas.modelo.Porta;

public class TestaCasa {

	public static void main(String[] args) {
		Casa casa = new Casa();
		Porta p1 = new Porta();
		Porta p2 = new Porta();
		Porta p3 = new Porta();
		casa.pinta("Vermelha");
		casa.adicionaPorta(p1);
		casa.adicionaPorta(p2);
		casa.adicionaPorta(p3);
		casa.portas[0].abre();
		casa.portas[1].fecha();
		casa.portas[2].abre();
		System.out.print("Temos " + casa.quantasPortasEstaoAbertas() + " portas abertas!");
	}

}
