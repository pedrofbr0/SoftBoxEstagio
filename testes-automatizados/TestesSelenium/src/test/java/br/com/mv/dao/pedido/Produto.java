package br.com.mv.dao.pedido;

/**
 * Produto
 * 
 * @author antoniojunior <antoniojunior@softbox.com.br>
 *
 */
public class Produto {
	
	private int nroItemPedidoProduto;
	
	private int nroProduto;
		
	private String descProduto;
	
	private int idProdutoReverso;
	
	public Produto(int nroItemPedidoProduto, int nroProduto, String descProduto) {
		// TODO Auto-generated constructor stub
		this.descProduto = descProduto;
		this.nroProduto = nroProduto;
		this.nroItemPedidoProduto = nroItemPedidoProduto;
	}
	
	public Produto() {}

	public int getNroProduto() {
		return nroProduto;
	}

	public void setNroProduto(int nroProduto) {
		this.nroProduto = nroProduto;
	}

	public String getDescProduto() {
		return descProduto;
	}

	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}
	
	public int getNroItemPedidoProduto() {
		return nroItemPedidoProduto;
	}
	
	public void setNroItemPedidoProduto(int nroItemPedidoProduto) {
		this.nroItemPedidoProduto = nroItemPedidoProduto;
	}
	
	public void setIdProdutoReverso(int idProdutoReverso) {
		this.idProdutoReverso = idProdutoReverso;
	}
	
	public int getIdProdutoReverso() {
		return idProdutoReverso;
	}

}
