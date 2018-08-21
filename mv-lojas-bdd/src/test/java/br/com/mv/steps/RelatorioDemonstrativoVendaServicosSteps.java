package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.RelatorioDemonstrativoVendaServicosPage;
import net.thucydides.core.annotations.Steps;

public class RelatorioDemonstrativoVendaServicosSteps {

	@Steps
	RelatorioDemonstrativoVendaServicosPage relatorioDemonstrativoVendaServicos;
	
	@When("No relatorio de demonstrativo de venda de servicos eu clico no botao Periodo")
	public void noRelatorioDemonstrativoEuClicoBotaoPeriodo() throws InterruptedException {
		relatorioDemonstrativoVendaServicos.clicarBtnPeriodo();
	}
	
	@When("No relatorio de demonstrativo de venda de servicos eu seleciono periodo encontrado")
	public void noRelatorioDemonstrativoSelecionoPeriodo() throws InterruptedException {
		relatorioDemonstrativoVendaServicos.selecionarPeriodoEncontrado();
	}
	
	@When("No relatorio de demonstrativo de venda de servicos eu clico botao Selecionar")
	public void noRelatorioDemonstrativoClicoBotaoSelecionar() throws InterruptedException {
		relatorioDemonstrativoVendaServicos.clicarBtnSelecionar();
	}
	
	@When("No relatorio de demonstrativo de venda de servicos eu clico no botao Ok")
	public void noRelatorioDemonstrativoClicoBotaoOk() throws InterruptedException {
		relatorioDemonstrativoVendaServicos.clicarBtnOk();
	}
	
	@When("No relatorio de demonstrativo de venda de servicos eu clico no botao Imprimir")
	public void noRelatorioDemonstrativoClicoBotaoImprimir() throws InterruptedException {
		relatorioDemonstrativoVendaServicos.clickBtnImprimir();
	}
	
	@Then("No relatorio de devolucao e troca eu valido a impressao")
	public void noRelatorioDemonstrativoEuValidoImpressao() throws InterruptedException {
		relatorioDemonstrativoVendaServicos.validarRelatorio();
	}
}