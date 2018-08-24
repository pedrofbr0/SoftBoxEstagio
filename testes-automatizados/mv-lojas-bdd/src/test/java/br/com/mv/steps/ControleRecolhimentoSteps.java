package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.ControleRecolhimentoProdutoPage;
import net.thucydides.core.annotations.Steps;

public class ControleRecolhimentoSteps {

	@Steps
	ControleRecolhimentoProdutoPage controleRecolhimento;
	
	@When("No controle de recolhimento eu informo o pedido")
	public void noControleRecolhimentoInformoFiltroPedido() throws InterruptedException {
		controleRecolhimento.informarPedido();
	}
	
	@When("No controle de recolhimento eu informo a loja $loja")
	public void noControleRecolhimentoInformoFiltroLoja(String loja) throws InterruptedException {
		controleRecolhimento.selecionarFiltroLoja(loja);
	}
	
	@When("No controle de recolhimento eu clico no botao Pesquisar")
	public void noControleRecolhimentoClicoBotaoPesquisar() throws InterruptedException {
		controleRecolhimento.clicarPesquisar();
	}
	
	@When("No controle de recolhimento eu seleciono a nota de devolucao")
	public void noControleRecolhimentoSelecionoNotaDevolucao() throws InterruptedException {
		controleRecolhimento.selecionarNotaFiscal();
	}
	
	@When("No controle de recolhimento eu seleciono o status $status")
	public void noControleRecolhimentoSelecionoStatus(String status) throws InterruptedException {
		controleRecolhimento.selecionarStatus(status);
	}
	
	@When("No controle de recolhimento eu seleciono o grupo de estoque $grupoEstoque")
	public void noControleRecolhimentoSelecionoGrupoEstoque(String grupoEstoque) throws InterruptedException {
		controleRecolhimento.selecionarGrupoEstoque(grupoEstoque);
	}
	
	@When("No controle de recolhimento eu informo a observacao $obsDevolucao")
	public void noControleRecolhimentoInformoObservacao(String obsDevolucao) throws InterruptedException {
		controleRecolhimento.informarObservacao(obsDevolucao);
	}
	
	@When("No controle de recolhimento eu clico no botao Ok")
	public void noControleRecolhimentoClicoBotaoOk() throws InterruptedException {
		controleRecolhimento.clicarOk();
	}
	
	@When("No controle de recolhimento eu clico no botao Ok modal sucesso")
	public void noControleRecolhimentoClicoBotaoOkModalSucesso() throws InterruptedException {
		controleRecolhimento.clicarOkModalSucesso();
	}
	
	@When("No controle de recolhimento eu seleciono o recolhimento")
	public void noControleRecolhimentoSelecionoRecolhimento() throws InterruptedException {
		controleRecolhimento.clickCheckRecolhimento();
	}
	
	@When("No controle de recolhimento eu clico no botao Autorizar Recibo")
	public void noControleRecolhimentoClicoAutorizarRecibo() throws InterruptedException {
		controleRecolhimento.clickBtnAutorizarRecibo();
	}
	
	@Then("No controle de recolhimento eu valido a autorizacao do recibo")
	public void noControleRecolhimentoValidoAutorizacaoRecibo() throws InterruptedException {
		controleRecolhimento.validarMensagemAutorizacaoRecibo();
	}	
}