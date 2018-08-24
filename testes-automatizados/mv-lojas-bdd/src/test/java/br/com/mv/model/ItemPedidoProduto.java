package br.com.mv.model;

public class ItemPedidoProduto {

	String codProduto;
	int nroProduto;
	double vrPrestacaoProduto;
	double vrEntradaProduto;
	double vrUnitarioProduto;

	public ItemPedidoProduto() {}
	
	public void setVrUnitarioProduto(double vrUnitarioProduto) {
		this.vrUnitarioProduto = vrUnitarioProduto;
	}
	
	public double getVrUnitarioProduto() {
		return this.vrUnitarioProduto;
	}
	
	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}
	
	public String getCodProduto() {
		return this.codProduto;
	}
	
	public void setNroProduto(int nroProduto) {
		this.nroProduto = nroProduto;
	}
	
	public int getNroProduto() {
		return this.nroProduto;
	}
	
	public void setVrPrestacaoProduto(double vrPrestacaoProduto) {
		this.vrPrestacaoProduto = vrPrestacaoProduto;
	}
	
	public double getVrPrestacaoProduto() {
		return this.vrPrestacaoProduto;
	}
	
	public void setVrEntradaProduto(double vrEntradaProduto) {
		this.vrEntradaProduto = vrEntradaProduto;
	}
	
	public double getVrEntradaProduto() {
		return this.vrEntradaProduto;
	}
}