package br.com.mv.dao.pedido;

public class MotivoAprovacaoTrocadevol {
	
	private int nroMotivoFluxoAprovacao;
	
	private String descMotivoFluxoAprovacao;
	
	public MotivoAprovacaoTrocadevol(int nroMotivoFluxoAprovacao, String descMotivoFluxoAprovacao) {
		this.nroMotivoFluxoAprovacao = nroMotivoFluxoAprovacao;
		this.descMotivoFluxoAprovacao = descMotivoFluxoAprovacao;
	}

	public MotivoAprovacaoTrocadevol() {}

	public int getNroMotivoFluxoAprovacao() {
		return nroMotivoFluxoAprovacao;
	}

	public void setNroMotivoFluxoAprovacao(int nroMotivoFluxoAprovacao) {
		this.nroMotivoFluxoAprovacao = nroMotivoFluxoAprovacao;
	}

	public String getDescMotivoFluxoAprovacao() {
		return descMotivoFluxoAprovacao;
	}

	public void setDescMotivoFluxoAprovacao(String descMotivoFluxoAprovacao) {
		this.descMotivoFluxoAprovacao = descMotivoFluxoAprovacao;
	}
	
	
}
