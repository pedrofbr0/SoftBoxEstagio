package br.com.mv.model;

public class ItemPedidoServico {

	int nroServico;
	double vrPrestacaoServico;
	double vrUnitarioServico;

	public ItemPedidoServico() {}
	
	public void setNroServico(int nroServico) {
		this.nroServico = nroServico;
	}
	
	public int getNroServico() {
		return this.nroServico;
	}
	
	public void setVrPrestacaoServico(double vrPrestacaoServico) {
		this.vrPrestacaoServico = vrPrestacaoServico;
	}
	
	public double getVrPrestacaoServico() {
		return this.vrPrestacaoServico;
	}
	
	public void setVrUnitarioServico(double vrUnitarioServico) {
		this.vrUnitarioServico = vrUnitarioServico;
	}
	
	public double getVrUnitarioServico() {
		return this.vrUnitarioServico;
	}
}