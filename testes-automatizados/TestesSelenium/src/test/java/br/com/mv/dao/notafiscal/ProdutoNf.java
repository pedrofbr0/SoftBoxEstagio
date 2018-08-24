package br.com.mv.dao.notafiscal;

public class ProdutoNf {
	
	private int nroNf;
	
	private int nroLoja;
	
	private int nroProduto;
	
	private int codNroProduto;
	
	private String descProduto;
	
	public ProdutoNf(int nroNf, int nroLoja, int nroProduto, int codNroProduto, String descProduto) {
		this.nroNf = nroNf;
		this.nroLoja = nroLoja;
		this.nroProduto = nroProduto;
		this.codNroProduto = codNroProduto;
		this.descProduto = descProduto;
	}

	public ProdutoNf() {}

	public int getNroNf() {
		return nroNf;
	}

	public void setNroNf(int nroNf) {
		this.nroNf = nroNf;
	}

	public int getNroLoja() {
		return nroLoja;
	}

	public void setNroLoja(int nroLoja) {
		this.nroLoja = nroLoja;
	}

	public int getNroProduto() {
		return nroProduto;
	}

	public void setNroProduto(int nroProduto) {
		this.nroProduto = nroProduto;
	}
	
	public int getCodNroProduto() {
		return codNroProduto;
	}
	
	public void setCodNroProduto(int codNroProduto) {
		this.codNroProduto = codNroProduto;
	}
	
	public String getDescProduto() {
		return descProduto;
	}
	
	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}

}
