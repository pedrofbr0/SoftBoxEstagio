package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.ConsultaNotaFiscalPage;
import net.thucydides.core.annotations.Steps;

public class ConsultaNotaFiscalSteps {

	@Steps
	ConsultaNotaFiscalPage consultaNotaFiscal;
	
	@When("Eu seleciono somente notas $statusNota")
    public void euSelecionoSomenteNotas(String statusNota) throws InterruptedException {
		consultaNotaFiscal.selecionarSomenteNotas(statusNota);
    }
	
	@When("Na consulta de nota fiscal eu informo pedido")
    public void infomarNumeroPedido() throws InterruptedException {
		consultaNotaFiscal.informarNumeroPedido();
    }
	
	@When("Na consulta de nota fiscal eu informo loja")
    public void informarCodigoLoja() throws InterruptedException {
		consultaNotaFiscal.informarCodigoLoja();
    }
	
	@When("Na consulta de nota fiscal eu clico no botao Pesquisar")
    public void naConsultaNotaFiscalBotaoPesquisar() throws InterruptedException {
		consultaNotaFiscal.clicarBtnPesquisar();
    }
	
	@When("Na consulta de nota fiscal eu informo o numero da nota")
    public void naConsultaNotaFiscalInformoNumeroNota() throws InterruptedException {
		consultaNotaFiscal.informarNumeroNota();
    }
	
	@Then("Eu valido nota fiscal encontrada $tipoNf")
    public void euValidoNotaFiscalEncontrada(String tipoNf) throws InterruptedException {
		consultaNotaFiscal.validarNotasEncontradas(tipoNf);
    }
}