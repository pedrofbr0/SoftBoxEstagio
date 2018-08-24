package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.AutorizacaoPage;
import br.com.mv.pages.ImportacaoPromocaoPage;
import br.com.mv.pages.MontadoraPage;
import br.com.mv.pages.PrevisaoMontagemMostruarioPage;
import net.thucydides.core.annotations.Steps;

public class MontadoraSteps {

	@Steps
	MontadoraPage montadora;
	
	@When("Eu informo a montadora para a ordem de montagem")
	public void euInformoMontadoraOrdemMontagem() throws InterruptedException {
		montadora.informarMontadora();
	}
	
	@When("Eu informo uma observacao para a montadora")
	public void euInformoDescricaoMontadora() throws InterruptedException {
		montadora.infomarObservacao();
	}
	
	@When("Eu clico no botao Ok confirmar montadora")
	public void euClicoBotaoOkConfirmarMontadora() throws InterruptedException {
		montadora.clicarBtnOk();
	}
}