package br.com.mv.model;

public class Loja {

	private int nroLoja;
	private int codNroLoja;
	private int nroEmpresa;
	
	public Loja() {}

	public void setNroLoja(int nroLoja) {
		this.nroLoja = nroLoja;
	}
	
	public int getNroLoja() {
		return this.nroLoja;
	}
	
	public void setCodNroLoja(int codNroLoja) {
		this.codNroLoja = codNroLoja;
	}
	
	public int getCodNroLoja() {
		return this.codNroLoja;
	}
	
	public void setNroEmpresa(int nroEmpresa) {
		this.nroEmpresa = nroEmpresa;
	}
	
	public int getNroEmpresa() {
		return this.nroEmpresa;
	}
}