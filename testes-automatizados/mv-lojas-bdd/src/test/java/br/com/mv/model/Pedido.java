package br.com.mv.model;

public class Pedido {

	private double basePlusJuros;
	private double vrEntrada;
	private int qtdeParcelas;
	private double vrPrestacao;
	
	public Pedido() {}

	public void setBasePlusJuros(double basePlusJuros) {
		this.basePlusJuros = basePlusJuros;
	}
	
	public double getBasePlusJuros() {
		return this.basePlusJuros;
	}
	
	public void setVrEntrada(double vrEntrada) {
		this.vrEntrada = vrEntrada;
	}
	
	public double getVrEntrada() {
		return this.vrEntrada;
	}
	
	public void setQtdeParcelas(int qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}
	
	public int getQtdeParcelas() {
		return this.qtdeParcelas;
	}
	
	public void setVrPrestacao(double vrPrestacao) {
		this.vrPrestacao = vrPrestacao;
	}
	
	public double getVrPrestacao() {
		return this.vrPrestacao;
	}
}