package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.PrevisaoMontagemMostruarioPage;
import net.thucydides.core.annotations.Steps;

public class PrevisaoMontagemMostruarioSteps {

	@Steps
	PrevisaoMontagemMostruarioPage previsaoMontagem;
	
	@When("Eu clico botao Pesquisar produto modal")
	public void euClicoBotaoPesquisarProdutoModal() throws InterruptedException {
		previsaoMontagem.clicarBtnPesquisarProduto();
	}
	
	@When("Eu pesquiso um produto que possua ordem de montagem")
	public void euPesquisoProudtoPossuaOrdemMontagem() throws InterruptedException {
		previsaoMontagem.buscarProdutoJaPossuaOrdemMontagem();
	}
	
	@When("Eu clico no botao Pesquisar ordens de montagem")
	public void euClicoBotaoPesquisarOrdensMontagem() throws InterruptedException {
		previsaoMontagem.clicarBtnPesquisarOrdemMontagem();
	}
	
	@When("Eu seleciono o produto para ordem de montagem")
	public void euSelecionoProdutoOrdemMontagem() throws InterruptedException {
		previsaoMontagem.selecionarProdutoMontagem();
	}
	
	@When("Eu clico no botao Adicionar produto na ordem de montagem")
	public void euClicoBotaoAdicionarProdutoOrdemMontagem() throws InterruptedException {
		previsaoMontagem.clicarBtnAdicionar();
	}
	
	@Then("Eu valido mensagem de bloqueio ja existe ordem de montagem")
	public void euValidoMensagemBloqueioJaExisteOrdemMontagem() throws InterruptedException {
		previsaoMontagem.validarMensagemBloqueioJaExisteMontagemProduto();
	}
	
	@When("Eu pesquiso um produto sem estoque e ordem de montagem")
	public void euPesquisoProdutoNaoPossuaEstoqueEOrdemMontagem() throws InterruptedException {
		previsaoMontagem.buscarProdutoSemEstoqueEOrdemMontagem();
	}
	
	@Then("Eu valido mensagem de bloqueio produto sem estoque e ordem de montagem")
	public void euValidoMensagemBloqueioProdutoSemEstoqueEOrdemMontagem() throws InterruptedException {
		previsaoMontagem.validarMensagemBloqueioProdutoSemEstoqueEOrdemMontagem();
	}
	
	@When("Eu pesquiso um produto com estoque e sem ordem de montagem")
	public void euPesquisoProdutoComEstoqueSemOrdemMontagem() throws InterruptedException {
		previsaoMontagem.buscaProdutoComEstoqueESEmOrdemMontagem();
	}
	
	@Then("Eu valido mensagem de sucesso da ordem de montagem")
	public void euValidoMensagemSucessoOrdemMontagem() throws InterruptedException {
		previsaoMontagem.validarMensagemSucessoMontagem();
	}
	
	@When("Eu clico no botao Adicionar produto na ordem de desmontagem")
	public void euClicoBotaoAdicionarProdutoOrdemDesmontagem() throws InterruptedException {
		previsaoMontagem.clicarBtnAdicionarProdutOrdemDesmontagem();
	}
	
	@When("Eu clico no botao Confirmar ordem de desmontagem")
	public void euClicoBotaoConfirmarOrdemDesmontagem() throws InterruptedException {
		previsaoMontagem.clicarBtnConfirmarDesmontagem();
	}
	
	@When("Eu clico no botao Confirmar ordem de montagem")
	public void euClicoBotaoConfirmarOrdemMontagem() throws InterruptedException {
		previsaoMontagem.clicarBtnConfirmar();
	}
	
	@Then("Eu valido mensagem de sucesso da ordem de desmontagem")
	public void euValidoMensagemSucessoOrdemDesMontagem() throws InterruptedException {
		previsaoMontagem.validarMensagemSucessoDesMontagem();
	}
	
	@Then("Eu valido mensagem produto sem ordem de montagem")
	public void euValidoMensagemProdutoSemOrdemMontagem() throws InterruptedException {
		previsaoMontagem.validarMensagemProdutoSemOrdemMontagem();
	}
	
	@When("Eu pesquiso um produto com estoque e com ordem de montagem")
	public void euPesquisoProdutoComEstoqueEComOrdemMontagem() throws InterruptedException {
		previsaoMontagem.buscarProdutoComEstoqueEOrdemMontagem();
	}
	
	@When("Eu clico no botao Ok gerar ordem montagem de produto com ordem jah existente")
	public void euClicoBotaoOkGerarOrdemMontagemProdutoOrdemJahExistente() throws InterruptedException {
		previsaoMontagem.clicarBtnOkGerarOrdemMontagemProdutoOrdemExistente();
	}
	
	@Then("Eu clico no Ok para confirmar montadora e valido mensagem de sucesso da ordem de montagem")
	public void euClicoBotaoOkConfirmarMontadoraValidoMensagemSucesso() throws InterruptedException {
		previsaoMontagem.clicarBtnOkConfirmarMontadoraValidoMensagemSucesso();
	}
	
}