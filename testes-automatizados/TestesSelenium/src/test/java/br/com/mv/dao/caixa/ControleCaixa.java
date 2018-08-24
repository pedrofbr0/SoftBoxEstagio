package br.com.mv.dao.caixa;

public class ControleCaixa {

	private int numeroControle;
	private String dataAberturaCaixa;
	private String dataFechamentoCaixa;
	
	public ControleCaixa() {}
	
	public void setNumeroControle(int numeroControle) {
		this.numeroControle = numeroControle;
	}
	
	public void setDataAberturaCaixa(String dataAberturaCaixa) {
		this.dataAberturaCaixa = dataAberturaCaixa;
	}
	
	public void setDataFechamentoCaixa(String dataFechamentoCaixa) {
		this.dataFechamentoCaixa = dataFechamentoCaixa;
	}
	
	public int getNumeroControle() {
		return numeroControle;
	}
	
	public String getDataAberturaCaixa() {
		return dataAberturaCaixa;
	}
	
	public String getDataFechamentoCaixa() {
		return dataFechamentoCaixa;
	}
}