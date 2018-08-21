package br.com.mv.steps;

import org.jbehave.core.annotations.When;

import br.com.mv.pages.PopupSelecaoClientePage;
import net.thucydides.core.annotations.Steps;

public class PopupSelecaoClienteSteps {

	@Steps
	PopupSelecaoClientePage selecaoCliente;
	
	@When("No popup de selecao cliente eu informo o cpf $cpf")
	public void euInformoCpf(String cpf) throws InterruptedException {
		selecaoCliente.informarCpf(cpf);
	}
	
	@When("No popup de selecao cliente eu clico no botao Pesquisar")
	public void euClicoBotaoPesquisar() throws InterruptedException {
		selecaoCliente.clicarBtnPesquisar();
	}
	
	@When("No popup de selecao cliente eu seleciono o cliente")
	public void euMarcoCliente() throws InterruptedException {
		selecaoCliente.checarClienteEncontrado();
	}
	
	@When("No popup de selecao cliente eu clico no botao Selecionar")
	public void euSelecionoCliente() throws InterruptedException {
		selecaoCliente.selecionarCliente();
	}
	
}