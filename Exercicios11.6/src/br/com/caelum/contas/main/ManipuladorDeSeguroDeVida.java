package br.com.caelum.contas.main;

import br.com.caelum.contas.modelo.*;

import br.com.caelum.javafx.api.util.*;

public class ManipuladorDeSeguroDeVida {
	private SeguroDeVida seguroDeVida;
	
	public void criaSeguro(Evento evento) {
		this.seguroDeVida = new SeguroDeVida();
		this.seguroDeVida.setNumeroApolice(evento.getInt("numeroApolice"));
		this.seguroDeVida.setTitular(evento.getString("titular"));
		this.seguroDeVida.setValor(evento.getDouble("valor"));;
	}
}
