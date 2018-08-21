package br.com.mv.model;

public class OrdemMontagemDesmontagem {

	int nroProduto;
	int codNroProduto;
	int nroLoja;
	int nroOrdem;
	
	public OrdemMontagemDesmontagem() {
		
	}
	
	public void setNroProduto(int nroProduto) {
		this.nroProduto = nroProduto;
	}
	
	public int getNroProduto() {
		return this.nroProduto;
	}
	
	public void setCodNroProduto(int codNroPoduto) {
		this.codNroProduto = codNroPoduto;
	}
	
	public int getCodNroProduto() {
		return this.codNroProduto;
	}
	
	public void setNroLoja(int nroLoja) {
		this.nroLoja = nroLoja;
	}
	
	public int getNroLoja() {
		return this.nroLoja;
	}
	
	public void setNroOrdem(int nroOrdem) {
		this.nroOrdem = nroOrdem;
	}
	
	public int getNroOrdem() {
		return this.nroOrdem;
	}
}