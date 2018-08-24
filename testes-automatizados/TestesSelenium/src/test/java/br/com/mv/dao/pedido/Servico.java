package br.com.mv.dao.pedido;

/**
 * Servico
 * 
 * @author antoniojunior <antoniojunior@softbox.com.br>
 *
 */
public class Servico {
	
	private int nroTipoServico;
	
	private int nroServico;
	
	private String descServico;
	
	private String flagDevolucao;

	private int idServicoReverso;
	
	public Servico(int nroTipoServico, int nroServico, String descServico, String flagDevolucao) {
		this.nroTipoServico = nroTipoServico;
		this.nroServico = nroServico;
		this.descServico = descServico;
		this.flagDevolucao = flagDevolucao;
	}

	public Servico() {}

	public int getNroTipoServico() {
		return nroTipoServico;
	}

	public void setNroTipoServico(int nroTipoServico) {
		this.nroTipoServico = nroTipoServico;
	}

	public int getNroServico() {
		return nroServico;
	}

	public void setNroServico(int nroServico) {
		this.nroServico = nroServico;
	}

	public String getDescServico() {
		return descServico;
	}

	public void setDescServico(String descServico) {
		this.descServico = descServico;
	}
	
	public String getFlagDevolucao() {
		return flagDevolucao;
	}
	
	public void setFlagDevolucao(String flagDevolucao) {
		this.flagDevolucao = flagDevolucao;
	}

	public void setIdServicoReverso(int idServicoReverso) {
		this.idServicoReverso = idServicoReverso;
	}
	
	public int getIdServicoReverso() {
		return idServicoReverso;
	}
}