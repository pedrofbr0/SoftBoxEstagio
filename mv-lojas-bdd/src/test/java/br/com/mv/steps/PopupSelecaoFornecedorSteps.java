package br.com.mv.steps;

import org.jbehave.core.annotations.When;

import br.com.mv.pages.PopupSelecaoClientePage;
import br.com.mv.pages.PopupSelecaoFornecedorPage;
import net.thucydides.core.annotations.Steps;

public class PopupSelecaoFornecedorSteps {

	@Steps
	PopupSelecaoFornecedorPage selecaoFornecedor;
	
	@When("No popup de selecao fornecedor eu informo o codigo $codigo do fornecedor")
	public void euInformoCpf(String codigo) throws InterruptedException {
		selecaoFornecedor.informarCodigo(codigo);
	}
	
	@When("No popup de selecao fornecedor eu clico no botao Pesquisar")
	public void euClicoBotaoPesquisar() throws InterruptedException {
		selecaoFornecedor.clicarBtnPesquisar();
	}
	
	@When("No popup de selecao fornecedor eu seleciono o fornecedor")
	public void euSelecionoFornecedor() throws InterruptedException {
		selecaoFornecedor.selecionarFornecedorEncontrado();
	}
	
}