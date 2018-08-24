package br.com.mv.model;

public class PreRecibo {

	private int nroPedido;
	private int codNroLoja;
	
	public PreRecibo() {}

	public void setNroPedido(int nroPedido) {
		this.nroPedido = nroPedido;
	}
	
	public int getNroPedido() {
		return this.nroPedido;
	}
	
	public void setCodNroLoja(int codNroLoja) {
		this.codNroLoja = codNroLoja;
	}
	
	public int getCodNroLoja() {
		return this.codNroLoja;
	}
}