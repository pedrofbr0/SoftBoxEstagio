package br.com.mv.model;

public class Carga {

	private int nroCargaVeiculo;
	private int nroTransportador;
	private String placaVeiculo;
	private String placaVeiculoUF;
	
	public Carga(int nroCargaVeiculo) {
		this.nroCargaVeiculo = nroCargaVeiculo;
	}
	
	public Carga() {}
	
	public void setNroCargaVeiculo(int nroCargaVeiculo) {
		this.nroCargaVeiculo = nroCargaVeiculo;
	}
	
	public int getNroCargaVeiculo() {
		return nroCargaVeiculo;
	}
	
	public void setNroTransportador(int nroTransportador) {
		this.nroTransportador = nroTransportador;
	}
	
	public int getNroTransportador() {
		return nroTransportador;
	}
	
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}
	
	public String getPlacaVeiculo() {
		return placaVeiculo;
	}
	
	public void setPlacaVeiculoUF(String placaVeiculoUF) {
		this.placaVeiculoUF = placaVeiculoUF;
	}
	
	public String getPlacaVeiculoUF() {
		return placaVeiculoUF;
	}
}