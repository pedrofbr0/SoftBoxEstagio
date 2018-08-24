package br.com.mv.model;

public class PlanoPagamento {

	double fatorPlano;
	String rCodTipoTabela;

	
	public PlanoPagamento() {}
	
	public void setFatorPlano(double fatorPlano) {
		this.fatorPlano = fatorPlano;
	}
	
	public double getFatorPlano() {
		return this.fatorPlano;
	}
	
	public void setRCodTipoTabela(String rCodTipoTabela) {
		this.rCodTipoTabela = rCodTipoTabela;
	}
	
	public String getRCodTipoTabela() {
		return this.rCodTipoTabela;
	}
}