package br.com.mv.steps;

import org.jbehave.core.annotations.When;

import br.com.mv.pages.MensagemPage;
import br.com.mv.pages.NotaFiscalTransferenciaPage;
import br.com.mv.utils.ControleJanela;
import net.thucydides.core.annotations.Steps;

public class NotaFiscalTransferenciaSteps {

	@Steps
	NotaFiscalTransferenciaPage notaFiscalTransf;
	ControleJanela controleJanela;
	MensagemPage msg;
	
	@When("No recebimento de Notas de transferencias eu clico no botao Pesquisar Nota")
	public void noRecebimentoNFTransClicoBotaoPesquisarNota() throws InterruptedException {
		controleJanela.abrirPopupClick(notaFiscalTransf.getBtnPesquisarNotaFiscal());
	}
	
	@When("No recebimento de Notas de transferencias eu informo o grupo de estoque $grupoEstoque")
	public void noRecebimentoNFTransInformoGrupoEstoque(String grupoEstoque) throws InterruptedException {
		notaFiscalTransf.setGrupoEstoque(grupoEstoque);
	}
	
	@When("No recebimento de Notas de transferencias eu informo a quantidade conferida $qtdConferida")
	public void noRecebimentoNFTransInformoQuantidadeConferida(String qtdConferida) throws InterruptedException {
		notaFiscalTransf.setQtdConferida(qtdConferida);
	}
	
	@When("No recebimento de Notas de transferencias eu informo a observacao $observacao")
	public void noRecebimentoNFTransInformoObservacao(String observacao) throws InterruptedException {
		notaFiscalTransf.setObservacao(observacao);
	}
	
	@When("No recebimento de Notas de transferencias eu clico no botao Processar")
	public void noRecebimentoNFTransClicoBotaoProcessar() throws InterruptedException {
		notaFiscalTransf.clickProcessar();
	}
	
	@When("No recebimento de Notas de transferencias eu clico no botao Cancelar para Realizar o processamento para liberacao de pre-disponivel")
	public void noRecebimentoNFTransClicoBotaoCancelarProcessarLiberacaoPreDisponivel() throws InterruptedException {
		//notaFiscalTransf.clickProcessar();
		notaFiscalTransf.clicarBotaoCancelarProcessarLiberacaoPreDisponivel();
	}
	
	@When("No recebimento de Notas de transferencias eu clico no botao Ok para confirmar processamento da nota")
	public void noRecebimentoNFTransClicoBotaoOkConfirmarProcessamento() throws InterruptedException {
		//notaFiscalTransf.clicarBotaoOkConfirmarProcessamentoNota();
		//controleJanela.abrirPopupAlert(true);
		//notaFiscalTransf.clickProcessar();
		notaFiscalTransf.clicarBotaoOkConfirmarProcessamentoNota();
	}
	
	@When("No recebimento de Notas de transferencias eu clico no botao Fechar mensagem")
	public void noRecebimentoNFTransClicoBotaoFecharMensagem() throws InterruptedException {
		msg.clickBtnFechar();
		// Procedimento realizado com sucesso!
		controleJanela.voltarJanelaPrincipal(true);
	}
	
	@When("No recebimento de Notas de transferencias eu salvo a carga do deposito $deposito loja do pedido $lojaPedido")
	public void noRecebimentoNFTransSalvoACarga(String deposito, String lojaPedido) throws InterruptedException {
		Thread.sleep(6000);
		notaFiscalTransf.salvarCargaNaSessao(deposito, lojaPedido);
	}
	
	@When("Salvar a carga do deposito $deposito pedido loja $lojaPedido tipo $tipoControleFaturamento controle faturamento")
	public void noRecebimentoNFTransSalvoACargaPorTipoControleFaturamento(String deposito, String lojaPedido, String tipoControleFaturamento) throws InterruptedException {
		Thread.sleep(6000);
		notaFiscalTransf.salvarCargaPorTipoControleFaturamentoNaSessao(deposito, lojaPedido, tipoControleFaturamento);
	}
	
	
	
	
	
}