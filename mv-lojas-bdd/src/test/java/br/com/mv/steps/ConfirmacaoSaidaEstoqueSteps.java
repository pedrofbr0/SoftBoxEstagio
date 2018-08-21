package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.ConfirmacaoSaidaEstoquePage;
import net.thucydides.core.annotations.Steps;

public class ConfirmacaoSaidaEstoqueSteps {

	@Steps
	ConfirmacaoSaidaEstoquePage confirmacaoSaidaEstoque;
	
	@When("Eu pesquiso o pedido para confirmar saida estoque")
	public void euPesquisoPedidoParaConfirmarSaidaEstoque() throws InterruptedException {
		confirmacaoSaidaEstoque.setPedido();
	}
	
	@When("Eu seleciono o pedido para confirmar saida estoque")
	public void euSelecionoPedidoParaConfirmarSaidaEstoque() throws InterruptedException {
		confirmacaoSaidaEstoque.selecionarPedido();
	}
	
	@When("Eu clico no botao Confirmação de Pedido")
	public void euClicoBotaoConfirmacaoPedido() throws InterruptedException {
		confirmacaoSaidaEstoque.clickBtnConfirmacaoPedido();
	}
	
	@When("Eu seleciono os produtos para confirmacao")
	public void euSelecionoProdutosParaConfirmacao() throws InterruptedException {
		confirmacaoSaidaEstoque.selecionarProdutoPedido();
	}
	
	@When("Eu informo o cupom para confirmacao")
	public void euInformoCupomParaConfirmacao() throws InterruptedException {
		confirmacaoSaidaEstoque.informarCupomFiscal();
	}
	
	@When("Eu clico no botao Confirmar saida estoque")
	public void euClicoBotaoConfirmarSaidaEstoque() throws InterruptedException {
		confirmacaoSaidaEstoque.clickBtnConfirmar();
	}
	
	@Then("Eu valido a mensagem de sucesso de confirmacao de saida de estoque")
	public void euValidoMensagemSucessoConfirmarSaidaEstoque() throws InterruptedException {
		confirmacaoSaidaEstoque.validarMensagemSucesso();
	}
}