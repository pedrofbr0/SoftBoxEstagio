package br.com.mv.dao.pedido;

/**
 * ItemFluxoAprovacao
 * 
 * @author antoniojunior <antoniojunior@softbox.com.br>
 *
 */
public class ItemFluxoAprovacao {

		private int nroItemAprovacaoTrocadevol;
		
		private int nroProduto;
		
		private int nroSeqAprovacao;
		
		private String descFaseAprovacao;
		
		private int nroItemAprovacaoReverso;

		private int idProdutoReverso;
		
		public ItemFluxoAprovacao(int nroItemAprovacaoTrocadevol, int nroProduto, int nroSeqAprovacao,
				String descFaseAprovacao) {
			this.nroItemAprovacaoTrocadevol = nroItemAprovacaoTrocadevol;
			this.nroProduto = nroProduto;
			this.nroSeqAprovacao = nroSeqAprovacao;
			this.descFaseAprovacao = descFaseAprovacao;
		}

		public ItemFluxoAprovacao() {}

		public int getNroItemAprovacaoTrocadevol() {
			return nroItemAprovacaoTrocadevol;
		}

		public void setNroItemAprovacaoTrocadevol(int nroItemAprovacaoTrocadevol) {
			this.nroItemAprovacaoTrocadevol = nroItemAprovacaoTrocadevol;
		}

		public int getNroProduto() {
			return nroProduto;
		}

		public void setNroProduto(int nroProduto) {
			this.nroProduto = nroProduto;
		}

		public int getNroSeqAprovacao() {
			return nroSeqAprovacao;
		}

		public void setNroSeqAprovacao(int nroSeqAprovacao) {
			this.nroSeqAprovacao = nroSeqAprovacao;
		}

		public String getDescFaseAprovacao() {
			return descFaseAprovacao;
		}

		public void setDescFaseAprovacao(String descFaseAprovacao) {
			this.descFaseAprovacao = descFaseAprovacao;
		}
		
		public void setNroItemAprovacaoReverso(int nroItemAprovacaoReverso) {
			this.nroItemAprovacaoReverso = nroItemAprovacaoReverso;
		}
		
		public int getNroItemAprovacaoReverso() {
			return nroItemAprovacaoReverso;
		}
		
		public void setIdProdutoReverso(int idProdutoReverso) {
			this.idProdutoReverso = idProdutoReverso;
		}
		
		public int getIdProdutoReverso() {
			return idProdutoReverso;
		}
		
}